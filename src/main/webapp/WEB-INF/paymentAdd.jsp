<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> ソフトテク株式会社 - 支払い新規登録 </title>
    <script type="text/javascript">
        // 入力データのバリデーション
        function validateForm() {
            var paymentMonth = document.getElementById("paymentMonth").value;
            var basicAmount = document.getElementById("basicAmount").value;
            var overtimeAmount = document.getElementById("overtimeAmount").value;
            var paymentDate = document.getElementById("paymentDate").value;

            // YYYYMM形式のチェック（6桁の数字のみ）
            var regexMonth = /^[0-9]{6}$/;
            if (!regexMonth.test(paymentMonth)) {
                alert("対象年月はYYYYMMの形式で入力してください。");
                return false;
            }

            // 数値入力チェック（基本金額、残業額）
            var regexAmount = /^[0-9]+$/;
            if (!regexAmount.test(basicAmount)) {
                alert("基本金額は半角数字で入力してください。");
                return false;
            }
            if (overtimeAmount !== "" && !regexAmount.test(overtimeAmount)) {
                alert("残業額は半角数字で入力してください。");
                return false;
            }

            // YYYYMMDD形式のチェック（8桁の数字のみ）
            var regexDate = /^[0-9]{8}$/;
            if (!regexDate.test(paymentDate)) {
                alert("支払（予定）日はYYYYMMDDの形式で入力してください。");
                return false;
            }

            // 対象年月の翌月以降であることをチェック
            var year = parseInt(paymentMonth.substring(0, 4), 10);
            var month = parseInt(paymentMonth.substring(4, 6), 10);
            var nextMonthYear = year;
            var nextMonth = month + 1;

            if (nextMonth > 12) { // 12月の場合、翌年1月にする
                nextMonth = 1;
                nextMonthYear += 1;
            }

            var minPaymentDate = nextMonthYear * 10000 + nextMonth * 100 + 1; // YYYYMMDD形式（翌月の1日）

            var enteredPaymentDate = parseInt(paymentDate, 10);

            if (enteredPaymentDate < minPaymentDate) {
                alert("支払（予定）日は対象年月の翌月以降の日付を入力してください。");
                return false;
            }

            // フォームを送信
            document.theForm.submit();
        }
    </script>
</head>
<body>
    <form:form name="theForm" id="theForm" method="post" modelAttribute="paymentFormBean" action="paymentAdd">
        <h1>支払い新規登録</h1>

        <!-- エラーメッセージの表示 -->
        <p style="color: red;">
            <c:forEach items="${errors}" var="error">
                <spring:message message="${error}" /> <br>
            </c:forEach>
        </p>

        <!-- 成功メッセージの表示 -->
        <c:if test="${not empty message}">
            <div style="color: red;">
                <p><c:out value="${message}" /></p>
            </div>
        </c:if>

        <table border="1">
            <!-- 支払ID（非表示） -->
            <tr style="display: none;">
                <td width="200px">支払ID</td>
                <td width="200px">
                    <input type="text" id="paymentID" name="paymentID"
                        value="${paymentFormBean.paymentID}" maxlength="36" required />
                </td>
            </tr>

            <!-- 対象年月 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">対象年月</td>
                <td width="200px">
                    <input type="text" id="paymentMonth" name="paymentMonth"
                        value="${paymentFormBean.paymentMonth}" maxlength="6" placeholder="YYYYMM" required />
                </td>
            </tr>

            <!-- 会社名 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">会社名</td>
                <td width="200px">
                    <form:select path="companyID">
                        <form:options items="${companyList}" itemLabel="companyName" itemValue="companyID" />
                    </form:select>
                </td>
            </tr>

            <!-- 社員名 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">社員名</td>
                <td width="200px">
                    <input type="text" id="paymentEmployeeName" name="paymentEmployeeName"
                        value="${paymentFormBean.paymentEmployeeName}" maxlength="20" required />
                </td>
            </tr>

            <!-- 基本金額 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">基本金額</td>
                <td width="200px">
                    <input type="text" id="basicAmount" name="basicAmount"
                        value="${paymentFormBean.basicAmount}" maxlength="8" required />
                </td>
            </tr>

            <!-- 残業額 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">残業額</td>
                <td width="200px">
                    <input type="text" id="overtimeAmount" name="overtimeAmount"
                        value="${paymentFormBean.overtimeAmount}" maxlength="8" />
                </td>
            </tr>

            <!-- 支払（予定）日 -->
            <tr style="background-color:#dcfeeb">
                <td width="200px">支払（予定）日</td>
                <td width="200px">
                    <input type="text" id="paymentDate" name="paymentDate"
                        value="${paymentFormBean.paymentDate}" maxlength="8" placeholder="YYYYMMDD" required />
                </td>
            </tr>
        </table>

        <!-- 登録ボタン -->
        <table border="0">
            <tr>
                <td width="200px"></td>
                <td width="200px" style="text-align: right;">
                    <input type="button" id="Registration" name="Registration" value="登録" onclick="validateForm()" />
                </td>
            </tr>
        </table>
    </form:form>

    <!-- 登録成功時のアラート表示 -->
    <c:if test="${not empty successMessage}">
        <script type="text/javascript">
            alert("${successMessage}"); // 登録完了メッセージ
        </script>
    </c:if>
</body>
</html>
