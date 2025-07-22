<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" pageEncoding="UTF-8"%>
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
            document.getElementById('insertFlg').value = '1'; // 更新フラグを1に設定
            document.getElementById('no').value = paymentId; // Noをセット
            document.theForm.action = "toInitBpPayment"; // 画面遷移先を設定
            document.theForm.submit(); // フォーム送信
        }

        // 削除処理
        /* function toDeleteJsp(paymentId) {
            if(confirm("本当に削除しますか？")) {
                document.getElementById('no').value = paymentId;
                document.theForm.action = "deleteBpPayment";
                document.theForm.submit();
            }
        } */

        // 新規登録画面への遷移処理
        function toMakeJsp() {
            document.getElementById('insertFlg').value = '0'; // 新規フラグを0に設定
            document.theForm.action = "toInitBpPayment"; // 画面遷移先を設定
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
    <h2>BP支払管理リスト</h2>

    <!-- 新功能入口 -->
    <div style="margin-bottom: 20px;">
        <input type="button" value="新規登録" onclick="toMakeJsp();" style="margin-right:10px;" />
        <input type="button" value="請求書一覧" onclick="window.location.href='bpInvoiceList';" style="margin-right:10px;" />
        <input type="button" value="マスタ管理" onclick="window.location.href='bpMasterManagement';" />
    </div>

    <c:if test="${not empty error}">
        <p style="color: red;">
            <c:out value="${error}" />
        </p>
    </c:if>

    <!-- 検索フォーム -->
    <form:form name="theForm" id="theForm" method="post" modelAttribute="bpPaymentFormBean" action="bpPaymentList">
        <b>月:</b>
        <form:input path="month" id="paymentMonth" maxlength="6" placeholder="例：202507" />
        <input type="button" name="search" value="検索" onclick="validateSearch();" />
        <!-- <input type="button" name="create" value="新規" onclick="toMakeJsp();" /> -->

        <!-- エラーメッセージ表示 -->
        <p style="color: red;">
            <c:forEach items="${errors}" var="error">
                <spring:message message="${error}" /><br>
            </c:forEach>
        </p>

        <input type="hidden" id="insertFlg" name="insertFlg" value="${bpPaymentFormBean.insertFlg}" />
        <input type="hidden" id="no" name="no" value="${bpPaymentFormBean.no}" />

        <!-- 支払い情報リストの表示 -->
        <c:choose>
            <c:when test="${not empty list}">
                <table border="1" class="bpPaymentList-table">
                    <thead>
                        <tr>
                            <th width="60">No</th>
                            <th width="80">月</th>
                            <th width="80">氏名</th>
                            <th width="120">所属会社</th>
                            <th width="120">派遣(請負)先会社</th>
                            <th width="100">外注単価（税抜）</th>
                            <th width="100">外注金額（税抜）</th>
                            <th width="100">外注金額（税込）</th>
                            <th width="80">手数料</th>
                            <th width="100">振込日</th>
                            <th width="100">記入日</th>
                            <th width="150">備考</th>
                            <th width="120">インボイス登録番号</th>
                            <th width="80">更新へ</th>
                           <!--<th width="80">削除</th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="bpPayment" varStatus="status">
                            <tr <c:if test="${status.count%2==0}">style="background-color:#bfe1ff"</c:if>
                                <c:if test="${status.count%2!=0}">style="background-color:#dcfeeb"</c:if>>
                                <td>${bpPayment.paymentId}</td>
                                <td>${bpPayment.month}</td>
                                <td>${bpPayment.employeeName}</td> <!-- 员工名称 -->
                                <td>${bpPayment.companyName}</td> <!-- BP公司名称 -->
                                <td>${bpPayment.dispatchCompanyName}</td> <!-- 派遣公司名称 -->
                                <td><fmt:formatNumber value="${bpPayment.unitPriceExTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.outsourcingAmountExTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.outsourcingAmountInTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.commission}" pattern="#,##0"/></td>
                                <td><fmt:formatDate value="${bpPayment.transferDate}" pattern="yyyy/MM/dd"/></td>
                                <td><fmt:formatDate value="${bpPayment.entryDate}" pattern="yyyy/MM/dd"/></td>
                                <td>${bpPayment.remarks}</td>
                                <td>${bpPayment.invoiceNumber}</td>
                                <td>
                                    <input type="button" value="更新" 
                                        onclick="toUpdateJsp('${bpPayment.paymentId}');"/>
                                </td>
                                <!--<td>
                                    <input type="button" value="削除" 
                                        onclick="toDeleteJsp('${bpPayment.paymentId}');"/>
                                </td>-->
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: red;">データが見つかりませんでした。</p>
            </c:otherwise>
        </c:choose>

        <!-- 派遣公司別・月別合計表示 -->
        <c:choose>
            <c:when test="${not empty summaryList}">
                <br><br>
                <h3 style="color: blue;">派遣公司別・月別合計</h3>
                <table border="1" class="bpPaymentSummary-table" style="background-color: #f0f8ff;">
                    <tr>
                        <th width="80">月</th>
                        <th width="150">派遣(請負)先会社</th>
                        <th width="100">人数</th>
                        <th width="100">外注金額（税抜）合計</th>
                        <th width="100">外注金額（税込）合計</th>
                        <th width="80">手数料合計</th>
                        <th width="120">請求書</th>
                    </tr>

                    <c:forEach items="${summaryList}" var="summaryItem" varStatus="status">
                        <tr style="background-color:#e6f3ff">
                            <td><c:out value="${summaryItem.month}"/></td>
                            <td><c:out value="${summaryItem.dispatchCompanyName}"/></td>
                            <td><c:out value="${summaryItem.employeeCount}"/></td>
                            <td style="text-align: right; font-weight: bold;">
                                <fmt:formatNumber value="${summaryItem.outsourcingAmountExTax}" pattern="#,##0"/>
                            </td>
                            <td style="text-align: right; font-weight: bold;">
                                <fmt:formatNumber value="${summaryItem.outsourcingAmountInTax}" pattern="#,##0"/>
                            </td>
                            <td style="text-align: right; font-weight: bold;">
                                <fmt:formatNumber value="${summaryItem.commission}" pattern="#,##0"/>
                            </td>
                            <td>
                                <c:if test="${not empty summaryItem.invoiceNumber and summaryItem.invoiceNumber != 'INV-${summaryItem.month}-SUM'}">
                                    <a href="downloadInvoice?month=${summaryItem.month}&company=${summaryItem.dispatchCompanyName}" 
                                       style="color: blue; text-decoration: underline;">
                                        請求書ダウンロード
                                    </a>
                                    <br>
                                    <small style="color: gray;">
                                        <c:out value="${summaryItem.invoiceNumber}"/>
                                    </small>
                                </c:if>
                                <c:if test="${empty summaryItem.invoiceNumber or summaryItem.invoiceNumber == 'INV-${summaryItem.month}-SUM'}">
                                    <span style="color: gray;">未アップロード</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: red;">合計データが見つかりませんでした。</p>
            </c:otherwise>
        </c:choose>
    </form:form>
</body>
</html>