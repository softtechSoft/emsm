package com.softtech.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.actionForm.BpInvoiceFormBean;
import com.softtech.actionForm.BpPaymentFormBean;
import com.softtech.entity.BpCompany;
import com.softtech.entity.BpEmployee;
import com.softtech.entity.BpInvoice;
import com.softtech.entity.BpPayment;
import com.softtech.entity.DpCompany;
import com.softtech.service.BpCompanyService;
import com.softtech.service.BpEmployeeService;
import com.softtech.service.BpInvoiceService;
import com.softtech.service.BpPaymentService;
import com.softtech.service.DpCompanyService;

/**
 * BP支払管理用Controller
 */
@Controller
public class BpPaymentController {
    @Autowired
    private BpPaymentService bpPaymentService;

    @Autowired
    private BpEmployeeService bpEmployeeService;

    @Autowired
    private BpCompanyService bpCompanyService;

    @Autowired
    private DpCompanyService dpCompanyService;

    @Autowired
    private BpInvoiceService bpInvoiceService;

    @Value("${file.bpPayment.location}")
    private String bpPaymentLocation;

    @Autowired
    private BpInvoiceController bpInvoiceController;



    /**
     * 支払リスト画面
     */
    @RequestMapping(value = "/bpPaymentList", method = {RequestMethod.GET, RequestMethod.POST})
    public String bpPaymentList(@ModelAttribute BpPaymentFormBean bpPaymentFormBean, Model model) {
        String month = bpPaymentFormBean.getMonth();

        // 支払リストを取得
        List<BpPayment> list = bpPaymentService.getBpPaymentList(month);
        model.addAttribute("list", list);

        // 合計のデータを取得
        List<BpPayment> summaryList = bpPaymentService.getBpPaymentSummaryList(month);
        model.addAttribute("summaryList", summaryList);

        // 支払リスト画面初期化
        model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);

        return "bpPaymentList";
    }

    /**
     * 新規、変更初期化画面
     */
    @RequestMapping(value = "/toInitBpPayment", method = {RequestMethod.GET, RequestMethod.POST})
    public String toInitBpPayment(@RequestParam("insertFlg") String insertFlg,
                                @RequestParam(name = "no", required = false) String paymentId,
                                Model model) {
        BpPaymentFormBean bpPaymentFormBean = new BpPaymentFormBean();
        try {
            if ("1".equals(insertFlg)) {
                if (paymentId == null || paymentId.isEmpty()) {
                    model.addAttribute("error", "更新対象のIDが指定されていません。");
                    loadDropdownData(model);
                    model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
                    return "bpPaymentEdit";
                }
                // 変更ボタン押下時
                BpPayment bpPayment = bpPaymentService.getBpPaymentById(paymentId);
                if (bpPayment != null) {
                    bpPaymentFormBean.setNo(paymentId);
                    bpPaymentFormBean.setMonth(bpPayment.getMonth());
                    bpPaymentFormBean.setEmployeeId(bpPayment.getEmployeeId());
                    bpPaymentFormBean.setCompanyId(bpPayment.getCompanyId());
                    bpPaymentFormBean.setDispatchCompanyId(bpPayment.getDispatchCompanyId());
                    bpPaymentFormBean.setUnitPriceExTax(bpPayment.getUnitPriceExTax());
                    bpPaymentFormBean.setOutsourcingAmountExTax(bpPayment.getOutsourcingAmountExTax());
                    bpPaymentFormBean.setOutsourcingAmountInTax(bpPayment.getOutsourcingAmountInTax());
                    bpPaymentFormBean.setCommission(bpPayment.getCommission());
                    bpPaymentFormBean.setTransferDate(bpPayment.getTransferDate() != null ?
                        bpPayment.getTransferDate().toString() : "");
                    bpPaymentFormBean.setEntryDate(bpPayment.getEntryDate() != null ?
                        bpPayment.getEntryDate().toString() : "");
                    bpPaymentFormBean.setRemarks(bpPayment.getRemarks());
                    bpPaymentFormBean.setInvoiceNumber(bpPayment.getInvoiceNumber());
                    bpPaymentFormBean.setInsertFlg("1");
                } else {
                    model.addAttribute("error", "指定された支払情報が見つかりませんでした。");
                    // 加载下拉メニューデータ以保留表单可用性
                    loadDropdownData(model);
                    model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
                    return "bpPaymentEdit"; // 留在编辑页面
                }
            } else if ("0".equals(insertFlg)) {
                // 新規ボタン押下時
                // 生成新的 paymentId（最大値ID）
                String newPaymentId = bpPaymentService.getMaxPaymentId();
                bpPaymentFormBean.setNo(newPaymentId);
                bpPaymentFormBean.setInsertFlg("0");
                // 初始化默认値（可选）
                bpPaymentFormBean.setCommission(0);
                bpPaymentFormBean.setEntryDate(java.time.LocalDate.now().toString());
            } else {
                model.addAttribute("error", "无效的操作类型");
                loadDropdownData(model);
                model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
                return "bpPaymentEdit";
            }

            // 選択用のデータ
            loadDropdownData(model);
            model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
            return "bpPaymentEdit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "支払登録・編集画面の初期化中にエラーが発生しました: " + e.getMessage());
            loadDropdownData(model);
            model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
            return "bpPaymentEdit"; // 错误时留在编辑页面
        }
    }

    /**
     * 新規、更新画面登録ボタン
     */
    @RequestMapping(value = "/saveBpPayment", method = RequestMethod.POST)
    public String saveBpPayment(@Valid @ModelAttribute BpPaymentFormBean bpPaymentFormBean,
    							@RequestParam(value = "file", required = false) MultipartFile file,
                                BindingResult result,HttpSession session,Model model) {
    	try {
            String insertFlg = bpPaymentFormBean.getInsertFlg();
            boolean rnt = false;
            String bpPaymentId="";
            // BP支払情報の保存
            if ("0".equals(insertFlg)) {
                // 新規登録
            	bpPaymentId = bpPaymentService.insertBpPayment(bpPaymentFormBean);
                if (bpPaymentId.length()>0) {
                    model.addAttribute("successMessage", "新規登録が成功しました");
                } else {
                    model.addAttribute("error", "新規登録が失敗しました");
                    loadDropdownData(model);
                    return "bpPaymentEdit";
                }
            } else {
                // 更新
                rnt = bpPaymentService.updateBpPayment(bpPaymentFormBean);
                if (rnt) {
                    model.addAttribute("successMessage", "更新が成功しました");
                } else {
                    model.addAttribute("error", "更新が失敗しました");
                    loadDropdownData(model);
                    return "bpPaymentEdit";
                }
            }

         // 支払情報保存成功後、ファイルがある場合は請求書をアップロード
            if (file != null && !file.isEmpty()) {
                //String paymentId = bpPaymentFormBean.getNo();
                try {
                    // 使用サービス層方法処理ファイルアップロード而不是コントローラー方法を呼び出す
                    Map<String, String> response = this.handleInvoiceUpload(bpPaymentId, file);

                    if (response.containsKey("message")) {
                        model.addAttribute("invoiceSuccess", response.get("message"));
                    } else if (response.containsKey("error")) {
                        model.addAttribute("invoiceError", response.get("error"));
                    }
                } catch (Exception e) {
                    model.addAttribute("invoiceError", "請求書アップロードでエラーが発生しました: " + e.getMessage());
                }
            }
            loadDropdownData(model);
            return "bpPaymentEdit";

        } catch (Exception e) {
            model.addAttribute("error", "処理中にエラーが発生しました: " + e.getMessage());
            loadDropdownData(model);
            return "bpPaymentEdit";
        }
    }



    /**
     * 選択用のデータ
     */
    private void loadDropdownData(Model model) {
        try {
            model.addAttribute("employeeList", bpEmployeeService.getAllEmployees());
            model.addAttribute("companyList", bpCompanyService.getAllCompanies());
            model.addAttribute("dispatchCompanyList", dpCompanyService.getAllCompanies());
            model.addAttribute("bpPaymentForm", new BpPaymentFormBean());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "加载下拉メニューデータ時发生错误: " + e.getMessage());
        }
    }

    /**
     * マスタ管理画面
     */
    @RequestMapping(value = "/bpMasterManagement", method = {RequestMethod.GET, RequestMethod.POST})
    public String bpMasterManagement(@ModelAttribute BpPaymentFormBean bpPaymentFormBean, Model model) {
        // BP社員一覧を取得
        List<BpEmployee> employeeList = bpEmployeeService.getAllEmployees();
        model.addAttribute("employeeList", employeeList);

        // BP会社一覧を取得
        List<BpCompany> companyList = bpCompanyService.getAllCompanies();
        model.addAttribute("companyList", companyList);

        // 派遣会社一覧を取得
        List<DpCompany> dispatchCompanyList = dpCompanyService.getAllCompanies();
        model.addAttribute("dispatchCompanyList", dispatchCompanyList);

        // マスタ管理画面の初期化
        model.addAttribute("bpPaymentFormBean", new BpPaymentFormBean());


        return "bpMasterManagement";
    }

    /**
     * 处理发票上传的内部方法
     * @param paymentId 支付ID
     * @param file 上传的文件
     * @return 响应結果
     */
    private Map<String, String> handleInvoiceUpload(String paymentId, MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            // 入力パラメータを検証
            if (paymentId == null || paymentId.trim().isEmpty()) {
                response.put("error", "支払IDを選択してください");
                return response;
            }

            if (file == null || file.isEmpty()) {
                response.put("error", "アップロードするファイルを選択してください");
                return response;
            }

            // ファイル保存用ディレクトリを作成
            Path uploadPath = Paths.get(bpPaymentLocation).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // サーバーにファイルを保存
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                response.put("error", "ファイル名が無効です");
                return response;
            }

            Path destinationFile = uploadPath.resolve(fileName).normalize();
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // データベース保存用の相対パスを構築
            String relativeFilePath = "ems_files/bp_invoice/" + fileName;

            // 既にこのpaymentIdの請求書記録が存在するか確認
            List<BpInvoice> existingInvoices = bpInvoiceService.getAllInvoices(paymentId);

            if (existingInvoices != null && !existingInvoices.isEmpty()) {
                // 既存の記録を更新
                BpInvoice existingInvoice = existingInvoices.get(0);
                existingInvoice.setFileName(fileName);
                existingInvoice.setFilePath(relativeFilePath);
                // 月を現在の年月に設定
                String month = YearMonth.now().toString().replace("-", "");
                existingInvoice.setMonth(month);
                bpInvoiceService.updateInvoice(existingInvoice);
            } else {
                // 記録が存在しない場合は新規作成
                BpInvoiceFormBean formBean = new BpInvoiceFormBean();
                // 請求書IDを生成
                String invoiceId = bpInvoiceService.getMaxBpInvoiceId();
                formBean.setInvoiceId(invoiceId);
                formBean.setPaymentId(paymentId);
                formBean.setFileName(fileName);
                formBean.setFilePath(relativeFilePath);
                formBean.setFileSize(file.getSize());
                // 月を現在の年月に設定
                String month = YearMonth.now().toString().replace("-", "");
                formBean.setMonth(month);
                // 請求書番号を生成
                String invoiceNumber = "INV-" + paymentId + "-" + System.currentTimeMillis();
                formBean.setInvoiceNumber(invoiceNumber);
                bpInvoiceService.insertInvoice(formBean);
            }

            response.put("message", "ファイルのアップロードが成功しました！");
            return response;
        } catch (Exception e) {
            response.put("error", "アップロードに失敗しました: " + e.getMessage());
            return response;
        }
    }




}