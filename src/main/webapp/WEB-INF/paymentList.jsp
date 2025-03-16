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
    <title>ソフトテク株式会社 - 支払い一覧</title>
    <script type="text/javascript">
        // 対象年月の入力チェック（YYYYMM形式のみ許可）
        function validateSearch() {
            var paymentMonth = document.getElementById("paymentMonth").value;
            var regex = /^[0-9]{6}$/; // 6桁の数字のみ（YYYYMM）

            if (!regex.test(paymentMonth)) {
                alert("対象年月はYYYYMMの形式で入力してください。"); // 入力ミス時のアラート
                return false;
            }

            document.theForm.submit(); // フォーム送信
        }

        // 更新画面への遷移処理
        function toUpdateJsp(paymentId) {
            document.getElementById('insertFlg').value = '1'; // 追加フラグを1に設定
            document.getElementById('paymentID').value = paymentId; // 追加：paymentIDをセット
            document.theForm.action = "toInitPaymentInfo"; // 画面遷移先を設定
            document.theForm.submit(); // フォーム送信
        }

        // 新規登録画面への遷移処理
        function toMakeJsp() {
            document.getElementById('insertFlg').value = '0'; // 追加フラグを0に設定
            document.theForm.action = "toInitPaymentInfo"; // 画面遷移先を設定
            document.theForm.submit(); // フォーム送信
        }
    </script>
    <style>
        .hidden-column {
            display: none;
        }
        .merged-cell {
            vertical-align: top;
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>支払い情報</h2>

    <!-- データがない場合のエラーメッセージ表示 -->
    <c:if test="${searchFlg and empty list}">
        <p style="color: red; font-weight: bold;">データが存在しないため、もう一度入力してください。</p>
    </c:if>

    <!-- 検索フォーム -->
    <form:form name="theForm" id="theForm" method="get" modelAttribute="paymentListFormBean" action="paymentSearch">
        <b>対象年月:</b>
        <form:input path="paymentMonth" id="paymentMonth" maxlength="6" />
        <input type="button" name="search" value="検索" onclick="validateSearch();" />
        <input type="button" name="create" value="新規" onclick="toMakeJsp();" />

        <!-- エラーメッセージ表示 -->
        <p style="color: red;">
            <c:forEach items="${errors}" var="error">
                <spring:message message="${error}" /><br>
            </c:forEach>
        </p>

        <input type="hidden" id="insertFlg" name="insertFlg" value="${paymentListFormBean.insertFlg}" />
        <input type="hidden" id="paymentID" name="paymentID" value="" /> <!-- 追加：paymentIDをhiddenでセット -->

        <!-- 支払い情報リストの表示 -->
        <c:if test="${not empty list}">
            <table border="1" class="paymentList-table">
                <thead>
                    <tr>
                        <th width="300">対象年月</th>
                        <th width="400">会社名</th>
                        <th width="400">社員名</th>
                        <th width="50">更新</th>
                        <th width="300">基本金額</th>
                        <th width="300">残業額</th>
                        <th width="300">総合金額</th>
                        <th width="300">支払（予定）日</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="currentMonth" value="" />
                    <c:set var="currentCompany" value="" />
                    <c:set var="groupSize" value="0" />
                    <c:set var="rowColor" value="#dcfeeb" />

                    <c:forEach items="${list}" var="paymentInfo">
                        <c:set var="isNewGroup" value="${paymentInfo.paymentMonth ne currentMonth or paymentInfo.companyName ne currentCompany}" />

                        <c:if test="${isNewGroup}">
                            <c:choose>
                                <c:when test="${rowColor eq '#dcfeeb'}">
                                    <c:set var="rowColor" value="#bfe1ff" />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="rowColor" value="#dcfeeb" />
                                </c:otherwise>
                            </c:choose>

                            <c:set var="currentMonth" value="${paymentInfo.paymentMonth}" />
                            <c:set var="currentCompany" value="${paymentInfo.companyName}" />

                            <c:set var="groupSize" value="0" />
                            <c:set var="groupTotal" value="0.0" />
                            <c:set var="groupPaymentDate" value="${paymentInfo.paymentDate}" />

                            <c:forEach items="${list}" var="item">
                                <c:if test="${item.paymentMonth eq currentMonth and item.companyName eq currentCompany}">
                                    <c:set var="groupSize" value="${groupSize + 1}" />
                                    <c:set var="groupTotal" value="${groupTotal + item.basicAmount + item.overtimeAmount}" />
                                </c:if>
                            </c:forEach>
                        </c:if>

                        <tr style="background-color: ${rowColor};">
                            <c:if test="${isNewGroup}">
                                <td class="merged-cell" rowspan="${groupSize}">
                                    <c:out value="${paymentInfo.paymentMonth}" />
                                </td>
                                <td class="merged-cell" rowspan="${groupSize}">
                                    <c:out value="${paymentInfo.companyName}" />
                                </td>
                            </c:if>
                            <td><c:out value="${paymentInfo.paymentEmployeeName}" /></td>
                            <td>
                                <input type="button" name="update" value="更新" onclick="toUpdateJsp('${empty paymentInfo.paymentID ? '' : paymentInfo.paymentID}');" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${paymentInfo.basicAmount}" pattern="#,###" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${paymentInfo.overtimeAmount}" pattern="#,###" />
                            </td>
                            <c:if test="${isNewGroup}">
                                <td class="merged-cell" rowspan="${groupSize}">
                                    <fmt:formatNumber value="${groupTotal}" pattern="#,###" />
                                </td>
                                <td class="merged-cell" rowspan="${groupSize}">
                                    <c:out value="${groupPaymentDate}" />
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </form:form>
</body>
</html>
