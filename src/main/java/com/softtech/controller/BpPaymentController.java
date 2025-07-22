package com.softtech.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    private DpCompanyService dpCompanyService;

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
                    // 加载下拉菜单数据以保留表单可用性
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
                // 初始化默认值（可选）
                bpPaymentFormBean.setCommission(0);
                bpPaymentFormBean.setEntryDate(java.time.LocalDate.now().toString());
            } else {
                model.addAttribute("error", "无效的操作类型");
                loadDropdownData(model);
                model.addAttribute("bpPaymentFormBean", bpPaymentFormBean);
                return "bpPaymentEdit";
            }

            // 加载下拉菜单数据
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
     * 保存支払信息
     */
    @RequestMapping(value = "/saveBpPayment", method = RequestMethod.POST)
    public String saveBpPayment(@Valid @ModelAttribute BpPaymentFormBean bpPaymentFormBean,
                                    @RequestParam("insertFlg") String insertFlg,
                                    Model model) {
        try {
            if ("0".equals(insertFlg)) {
                // 新建保存逻辑
                bpPaymentService.insertBpPayment(bpPaymentFormBean);
            } else {
                // 编辑保存逻辑
                bpPaymentService.updateBpPayment(bpPaymentFormBean);
            }
            return "redirect:/bpPaymentList";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "保存过程中发生错误: " + e.getMessage());
            loadDropdownData(model);
            return "bpPaymentEdit";
        }
    }

    /**
     * 加载下拉菜单数据
     */
    private void loadDropdownData(Model model) {
        try {
            model.addAttribute("employeeList", bpEmployeeService.getAllEmployees());
            model.addAttribute("companyList", bpCompanyService.getAllCompanies());
            model.addAttribute("dispatchCompanyList", dpCompanyService.getAllCompanies());
            model.addAttribute("bpPaymentForm", new BpPaymentFormBean());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "加载下拉菜单数据时发生错误: " + e.getMessage());
        }
    }
}