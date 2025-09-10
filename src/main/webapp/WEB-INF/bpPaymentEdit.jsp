<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ソフトテク株式会社 - BP支払登録・編集</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#theForm').validate({
                rules: {
                    month: { required: true },
                    employeeId: { required: true },
                    companyId: { required: true },
                    dispatchCompanyId: { required: true },
                    unitPriceExTax: { required: true, number: true },
                    outsourcingAmountExTax: { required: true, number: true },
                    transferDate: { required: true }
                },
                messages: {
                    month: '対象月を入力してください',
                    employeeId: '社員を選択してください',
                    companyId: '所属会社を選択してください',
                    dispatchCompanyId: '派遣(請負)先会社を選択してください',
                    unitPriceExTax: '外注単価（税抜）を入力してください',
                    outsourcingAmountExTax: '外注金額（税抜）を入力してください',
                    transferDate: '振込日を入力してください'
                }
            });
        });

 function doRegist() {
	 /*
     if ($('#theForm').valid()) {
         // 税込金額を計算して設定
         //var unitPriceExTax = $('#unitPriceExTax').val();
         var outsourcingAmountExTax = $('#outsourcingAmountExTax').val();
         if (outsourcingAmountExTax != 0 || outsourcingAmountExTax.equals("NULL")) {
             var taxRate = 1.1;
             var amountInTax = outsourcingAmountExTax * taxRate;
             $('#outsourcingAmountInTax').val(amountInTax);
         }
         // フォームを送信
         document.theForm.submit();
     }
	 */
     document.theForm.submit();
 }

        function goBack() {
            document.theForm.action = "bpPaymentList";
            document.theForm.submit();
        }
    </script>
</head>
<body>
<!-- 成功メッセージ -->
    <c:if test="${not empty successMessage}">
        <p style="color: green;">
            <c:out value="${successMessage}" />
        </p>
    </c:if>
    <!-- エラーメッセージ -->
    <p style="color: red;">
        <c:forEach items="${error}" var="error">
            <spring:message message="${error}"/><br>
        </c:forEach>
    </p>


    <!-- フォーム -->
    <h2>BP支払登録・編集</h2>
    <form:form name="theForm" id="theForm" method="post" modelAttribute="bpPaymentFormBean"
               action="saveBpPayment" enctype="multipart/form-data">
        <input type="hidden" name="insertFlg" value="${bpPaymentFormBean.insertFlg}" />
        <input type="hidden" name="no" value="${bpPaymentFormBean.no}" />
        <table border="1">
            <tr style="background-color:#dcfeeb">
                <td width="150px">支払ID <span style="color:red">*</span></td>
                <td width="250px">
                    <c:out value="${bpPaymentFormBean.no}" />

                </td>
            </tr>

            <tr style="background-color:#dcfeeb">
                <td width="150px">対象月 <span style="color:red">*</span></td>
                <td width="250px">
                    <form:input path="month" id="month" maxlength="6" placeholder="例:202507" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">社員 <span style="color:red">*</span></td>
                <td width="250px">
                    <form:select path="employeeId" id="employeeId" onchange="onEmployeeChange();" style="width:98%;">
                        <option value="">社員を選択してください</option>
                        <c:forEach items="${employeeList}" var="bpemployee">
                            <option value="${bpemployee.employeeId}"
                                    <c:if test="${bpemployee.employeeId == bpPaymentFormBean.employeeId}">selected</c:if>>
                                ${bpemployee.name}
                            </option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">所属会社 <span style="color:red">*</span></td>
                <td width="250px">
                    <form:select path="companyId" id="companyId" onchange="onCompanyChange();" style="width:98%;">
                        <option value="">会社を選択してください</option>
                        <c:forEach items="${companyList}" var="bpcompany">
                            <option value="${bpcompany.companyId}"
                                    <c:if test="${bpcompany.companyId == bpPaymentFormBean.companyId}">selected</c:if>>
                                ${bpcompany.companyName}
                            </option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">派遣(請負)先会社 <span style="color:red">*</span></td>
                <td width="250px">
                    <form:select path="dispatchCompanyId" id="dispatchCompanyId" style="width:98%;">
                        <option value="">派遣会社を選択してください</option>
                        <c:forEach items="${dispatchCompanyList}" var="dpcompany">
                            <option value="${dpcompany.companyID}"
                                    <c:if test="${dpcompany.companyID == bpPaymentFormBean.dispatchCompanyId}">selected</c:if>>
                                ${dpcompany.companyName}
                            </option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">外注単価（税抜） <span style="color:red">*</span></td>
                <td width="250px">
                    <form:input path="unitPriceExTax" id="unitPriceExTax" type="number" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">外注金額（税抜） <span style="color:red">*</span></td>
                <td width="250px">
                    <form:input path="outsourcingAmountExTax" id="outsourcingAmountExTax" type="number" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">外注金額（税込）</td>
                <td width="250px">
                    <form:input path="outsourcingAmountInTax" id="outsourcingAmountInTax" type="number" readonly="true" style="width:98%;"/>
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">手数料</td>
                <td width="250px">
                    <form:input path="commission" id="commission" type="number" value="0" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">振込日 <span style="color:red">*</span></td>
                <td width="250px">
                    <form:input path="transferDate" id="transferDate" type="date" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">記入日</td>
                <td width="250px">
                    <form:input path="entryDate" id="entryDate" type="date" style="width:98%;" />
                </td>
            </tr>
            <tr style="background-color:#dcfeeb">
                <td width="150px">備考</td>
                <td width="250px">
                    <form:textarea path="remarks" id="remarks" style="width:98%;height:80px;" />
                </td>
            </tr>

            <tr style="background-color:#dcfeeb">
                <td width="150px">アップロード</td>
                <td >
                    <input id="file" type="file" name="file"/>
                </td>
            </tr>
        </table>
        <br>
        <c:choose>
            <c:when test="${bpPaymentFormBean.insertFlg == '0'}">
                <!-- 新規 -->
                <input type="button" id="save-btn" class="btn-new" value="🆕 新規登録" onclick="doRegist()" />
            </c:when>
            <c:otherwise>
                <!-- 更新 -->
                <input type="button" id="save-btn" class="btn-update" value="✏️ 更新" onclick="doRegist()" />
            </c:otherwise>
        </c:choose>
        <input type="button" id="back" name="back" value="戻る" onclick="goBack();" />
    </form:form>
</body>
</html>