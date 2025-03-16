<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ソフトテク株式会社 - 支払い編集</title>
    <script type="text/javascript">
        // バリデーション: 対象年月の入力チェック（YYYYMM形式のみ許可）
        function validateForm() {
            var paymentMonth = document.getElementById("paymentMonth").value;
            var basicAmount = document.getElementById("basicAmount").value;
            var overtimeAmount = document.getElementById("overtimeAmount").value;
            var paymentDate = document.getElementById("paymentDate").value;

            // YYYYMM形式のチェック
            var regexMonth = /^[0-9]{6}$/;
            if (!regexMonth.test(paymentMonth)) {
                alert("対象年月はYYYYMMの形式で入力してください。");
                return false;
            }

            // 数値入力チェック
            var regexAmount = /^[0-9]+$/;
            if (!regexAmount.test(basicAmount)) {
                alert("基本金額は半角数字で入力してください。");
                return false;
            }
            if (overtimeAmount !== "" && !regexAmount.test(overtimeAmount)) {
                alert("残業額は半角数字で入力してください。");
                return false;
            }

            // 支払（予定）日のYYYYMMDD形式のチェック
            var regexDate = /^[0-9]{8}$/;
            if (!regexDate.test(paymentDate)) {
                alert("支払（予定）日はYYYYMMDDの形式で入力してください。");
                return false;
            }

            // フォームを送信
            document.theForm.submit();
        }
    </script>
</head>
<body>
    <h1>支払い情報編集</h1>

    <!-- エラーメッセージの表示 -->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}" /> <br>
        </c:forEach>
    </p>
    <c:if test="${not empty successMessage}">
    <script type="text/javascript">
        alert("${successMessage}"); // 登録完了メッセージ
    </script>
</c:if>

    <form:form name="theForm" id="theForm" method="post" modelAttribute="paymentFormBean" action="paymentUpdate">
        <!-- 支払ID（hidden） -->
        <input type="hidden" id="paymentID" name="paymentID" value="${paymentFormBean.paymentID}" />

        <!-- 対象年月 -->
        <table border="1">
            <tr>
                <td width="200px">対象年月</td>
                <td width="200px">
                    <input type="text" id="paymentMonth" name="paymentMonth" value="${paymentFormBean.paymentMonth}" maxlength="6" required />
                </td>
            </tr>

            <!-- 会社名 -->
            <tr>
                <td width="200px">会社名</td>
                <td width="200px">
                    <form:select path="companyID">
                        <form:options items="${companyList}" itemLabel="companyName" itemValue="companyID" />
                    </form:select>
                </td>
            </tr>

            <!-- 社員名 -->
            <tr>
                <td width="200px">社員名</td>
                <td width="200px">
                    <input type="text" id="paymentEmployeeName" name="paymentEmployeeName" value="${paymentFormBean.paymentEmployeeName}" maxlength="20" required />
                </td>
            </tr>

            <!-- 基本金額 -->
            <tr>
                <td width="200px">基本金額</td>
                <td width="200px">
                    <input type="text" id="basicAmount" name="basicAmount" value="${paymentFormBean.basicAmount}" maxlength="8" required />
                </td>
            </tr>

            <!-- 残業額 -->
            <tr>
                <td width="200px">残業額</td>
                <td width="200px">
                    <input type="text" id="overtimeAmount" name="overtimeAmount" value="${paymentFormBean.overtimeAmount}" maxlength="8" />
                </td>
            </tr>

            <!-- 支払（予定）日 -->
            <tr>
                <td width="200px">支払（予定）日</td>
                <td width="200px">
                    <input type="text" id="paymentDate" name="paymentDate" value="${paymentFormBean.paymentDate}" maxlength="8" placeholder="YYYYMMDD" required />
                </td>
            </tr>
        </table>

        <!-- 更新ボタン -->
        <input type="button" value="更新" onclick="validateForm();" />
    </form:form>

    <!-- 登録成功時のアラート表示 -->
    <c:if test="${not empty successMessage}">
        <script type="text/javascript">
            alert("${successMessage}"); // 登録完了メッセージ
        </script>
    </c:if>
</body>
</html>
