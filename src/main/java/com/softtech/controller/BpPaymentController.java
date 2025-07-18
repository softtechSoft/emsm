package com.softtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.BpPaymentFormBean;
import com.softtech.entity.BpPayment;
import com.softtech.service.BpCompanyService;
import com.softtech.service.BpEmployeeService;
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
    private DpCompanyService companyService;

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

        // 
        model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);

        return "bpPaymentList";
    }

    
    /**
     * 新規登録・編集画面初期化
     */
    @RequestMapping(value = "/toInitBpPayment", method = RequestMethod.POST)
    public String toInitBpPayment(@ModelAttribute BpPaymentFormBean bpPaymentFormBean, Model model) {
        try {
            String insertFlg = bpPaymentFormBean.getInsertFlg();
            String paymentId = bpPaymentFormBean.getNo();
            
            if ("1".equals(insertFlg)) {
                // 編集モード
                BpPayment bpPayment = bpPaymentService.getBpPaymentById(paymentId);
                if (bpPayment != null) {
                    bpPaymentFormBean.setMonth(bpPayment.getMonth());
                    bpPaymentFormBean.setEmployeeId(bpPayment.getEmployeeId());
                    bpPaymentFormBean.setCompanyId(bpPayment.getCompanyId());
                    bpPaymentFormBean.setDispatchCompanyId(bpPayment.getDispatchCompanyId());
                    bpPaymentFormBean.setUnitPriceExTax(bpPayment.getUnitPriceExTax());
                    bpPaymentFormBean.setOutsourcingAmountExTax(bpPayment.getOutsourcingAmountExTax());
                    bpPaymentFormBean.setOutsourcingAmountInTax(bpPayment.getOutsourcingAmountInTax());
                    bpPaymentFormBean.setCommission(bpPayment.getCommission());
                    // 确保日期字段在转换为String之前进行空值检查
                    bpPaymentFormBean.setTransferDate(bpPayment.getTransferDate() != null ? 
                        bpPayment.getTransferDate().toString() : "");
                    bpPaymentFormBean.setEntryDate(bpPayment.getEntryDate() != null ? 
                        bpPayment.getEntryDate().toString() : "");
                    bpPaymentFormBean.setRemarks(bpPayment.getRemarks());
                    bpPaymentFormBean.setInvoiceNumber(bpPayment.getInvoiceNumber());
                } else {
                    // 如果找不到对应的paymentId，可以添加错误信息
                    model.addAttribute("error", "指定された支払情報が見つかりませんでした。");
                }
            }
            
            // マスタデータを取得
//            List<BpEmployee> employeeList = bpEmployeeService.getAllEmployees();
//            List<BpCompany> companyList = bpCompanyService.getAllCompanies();
//            List<DpCompany> dispatchCompanyList = companyService.getAllCompanies();
//            
//            model.addAttribute("employeeList", employeeList);
//            model.addAttribute("companyList", companyList);
//            model.addAttribute("dispatchCompanyList", dispatchCompanyList);
//            model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
            
            return "bpPaymentEdit";
        } catch (Exception e) {
            e.printStackTrace(); // 打印堆栈跟踪到控制台
            model.addAttribute("error", "支払登録・編集画面の初期化中にエラーが発生しました: " + e.getMessage());
            // 可以选择返回一个错误页面，或者返回到列表页面并显示错误
            return "bpPaymentList"; // 返回到列表页面，并显示错误信息
        }
    }

    /**
     * 支払情報保存
     */
//    @RequestMapping(value = "/saveBpPayment", method = RequestMethod.POST)
//    public String saveBpPayment(@ModelAttribute BpPaymentFormBean bpPaymentFormBean, 
//                               @RequestParam("invoiceFile") MultipartFile invoiceFile,
//                               RedirectAttributes redirectAttributes) {
//        try {
//            String insertFlg = bpPaymentFormBean.getInsertFlg();
//            BpPayment bpPayment = new BpPayment();
//            
//            // フォームデータをエンティティに設定
//            bpPayment.setMonth(bpPaymentFormBean.getMonth());
//            bpPayment.setEmployeeId(bpPaymentFormBean.getEmployeeId());
//            bpPayment.setCompanyId(bpPaymentFormBean.getCompanyId());
//            bpPayment.setDispatchCompanyId(bpPaymentFormBean.getDispatchCompanyId());
//            bpPayment.setUnitPriceExTax(bpPaymentFormBean.getUnitPriceExTax());
//            bpPayment.setOutsourcingAmountExTax(bpPaymentFormBean.getOutsourcingAmountExTax());
//            bpPayment.setOutsourcingAmountInTax(bpPaymentFormBean.getOutsourcingAmountInTax());
//            bpPayment.setCommission(bpPaymentFormBean.getCommission());
//            bpPayment.setRemarks(bpPaymentFormBean.getRemarks());
//            bpPayment.setInvoiceNumber(bpPaymentFormBean.getInvoiceNumber());
//            
//            if ("0".equals(insertFlg)) {
//                // 新規登録
//                // 不需要手动生成ID，Service会自动生成
//                bpPaymentService.insertBpPayment(bpPayment);
//                redirectAttributes.addFlashAttribute("message", "新規登録が完了しました。");
//            } else {
//                // 更新
//                bpPayment.setPaymentId(bpPaymentFormBean.getNo());
//                bpPaymentService.updateBpPayment(bpPayment);
//                redirectAttributes.addFlashAttribute("message", "更新が完了しました。");
//            }
//            
//            // 請求書ファイルのアップロード処理
//            if (!invoiceFile.isEmpty()) {
//                // ファイル保存処理
//                String fileName = invoiceFile.getOriginalFilename();
//                String filePath = "uploads/bp_invoices/" + fileName;
//                File dest = new File(filePath);
//                if (!dest.getParentFile().exists()) {
//                    dest.getParentFile().mkdirs();
//                }
//                invoiceFile.transferTo(dest);
//            }
//            
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "保存中にエラーが発生しました: " + e.getMessage());
//        }
//        
//        return "redirect:bpPaymentList";
//    }

    /**
     * 支払情報削除
     */
//    @RequestMapping(value = "/deleteBpPayment", method = RequestMethod.POST)
//    public String deleteBpPayment(@ModelAttribute BpPaymentFormBean bpPaymentFormBean, 
//                                 RedirectAttributes redirectAttributes) {
//        try {
//            String paymentId = bpPaymentFormBean.getNo();
//            bpPaymentService.deleteBpPayment(paymentId);
//            redirectAttributes.addFlashAttribute("message", "削除が完了しました。");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "削除中にエラーが発生しました: " + e.getMessage());
//        }
//        
//        return "redirect:bpPaymentList";
//    }
}