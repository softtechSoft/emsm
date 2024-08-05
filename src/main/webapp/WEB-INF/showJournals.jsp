<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>仕訳データ表示</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            overflow-x: auto;
        }
        th, td {
            border: 1px solid black;
            text-align: center;
            padding: 8px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        th[colspan] {
            border-bottom: none;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        .button-container .left, .button-container .right {
            display: flex;
            gap: 10px;
        }
        .error-messages {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">仕訳データ表示</h1>

<!-- Hiển thị thông báo lỗi nếu có -->
<p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/><br>
        </c:forEach>
    </p>

<form:form name="theform" id="theForm"  action="journals/add" method="post" modelAttribute="tblJournalDetailFormBean">
    <div>
        <label for="systemDate">日付: </label>
        <input type="date" id="systemDate" name="bookDate" onchange="updateDenpyoNumber()">
        <br>
        <label for="denpyoNumber">伝票番号: </label>
        <input type="text" id="denpyoNumber" name="journalSerialNumber">
    </div>
    <table>
        <thead>
        <tr>
            <th rowspan="3">NO</th>
            <th colspan="6">借方</th>
            <th colspan="6">貸方</th>
        </tr>
        <tr>
            <th colspan="2">勘定科目</th>
            <th rowspan="2">課税区分</th>
            <th rowspan="2">税処理区分</th>
            <th rowspan="2">金額</th>
            <th rowspan="2">備考</th>
            <th colspan="2">勘定科目</th>
            <th rowspan="2">課税区分</th>
            <th rowspan="2">税処理区分</th>
            <th rowspan="2">金額</th>
            <th rowspan="2">備考</th>
        </tr>
        <tr>
            <th>番号</th>
            <th>名称</th>
            <th>番号</th>
            <th>名称</th>
        </tr>
        </thead>
        <tbody>
        <!-- resultSet をループして、データを表示 -->
        <c:forEach var="row" items="${resultSet}">
            <tr>
                <td>${row.lineNumber}</td>
                <td>${row.accountTitleID}</td>
                <td>${row.accountTitleName}</td>
                <td>
                    <c:choose>
                        <c:when test="${row.cdTaxationKbn == 0}">
                            非課税
                        </c:when>
                        <c:when test="${row.cdTaxationKbn == 1}">
                            課税
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${row.cdCTaxPriceKbn == 0}">
                            対象外
                        </c:when>
                        <c:when test="${row.cdCTaxPriceKbn == 1}">
                            外税
                        </c:when>
                        <c:when test="${row.cdCTaxPriceKbn == 2}">
                            内税
                        </c:when>
                    </c:choose>
                </td>
                <td>${row.transValue}</td>
                <td>${row.description}</td>
                <td>${row.accountTitleID1}</td>
                <td>${row.accountTitleName1}</td>
                <td>
                    <c:choose>
                        <c:when test="${row.cdTaxationKbn == 0}">
                            非課税
                        </c:when>
                        <c:when test="${row.cdTaxationKbn == 1}">
                            課税
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${row.cdCTaxPriceKbn == 0}">
                            対象外
                        </c:when>
                        <c:when test="${row.cdCTaxPriceKbn == 1}">
                            外税
                        </c:when>
                        <c:when test="${row.cdCTaxPriceKbn == 2}">
                            内税
                        </c:when>
                    </c:choose>
                </td>
                <td>${row.transValue1}</td>
                <td>${row.description1}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 最大の lineNumber の値を保存するために hidden input を使用 -->
    <input type="hidden" id="maxLineNumber" value="${maxLineNumber}">

    <div class="button-container">
        <div class="left">
            <!-- 新しい行を追加するボタン -->
            <button type="button" onclick="addRow()">行追加</button>
        </div>
        <div class="right">
            <!-- キャンセルボタン -->
            <button type="button" onclick="cancelAction()">キャンセル</button>
            <!-- 保存ボタン -->
            <input type="button" id="Registration" name="Registration" value="保存"  onclick="doRegist()"  />

        </div>
    </div>
</form:form>

<script>
    // Define the accounts array in JavaScript scope
    const accounts = [
        <c:forEach var="account" items="${accounts}">
            { uid: "${account.uid}", name: "${account.name}" },
        </c:forEach>
    ];

    // 新しい行をテーブルに追加する関数
  function addRow() {
    var table = document.querySelector('table tbody');
    var newUid = 'UID' + new Date().getTime(); // Generate a new UID
    var newLineNumber = table.querySelectorAll('tr').length + 1; // Increment lineNumber

    var newRow = document.createElement('tr');

    newRow.innerHTML = `
        <td>` + newLineNumber + `</td>
        <td>
            <select name="accountTitleID" onchange="updateAccountTitleName(this, 'accountTitleName')">
                <option value="">選択してください</option>
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.uid}">${account.uid}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <span id="accountTitleName"></span>
        </td>
        <td>
            <select name="cdTaxationKbn">
            	<option value="">選択してください</option>
                <option value="0">非課税</option>
                <option value="1">課税</option>
            </select>
        </td>
        <td>
            <select name="cdCTaxPriceKbn">
            	<option value="">選択してください</option>
                <option value="0">対象外</option>
                <option value="1">外税</option>
                <option value="2">内税</option>
            </select>
        </td>
        <td><input type="text" name="transValue"></td>
        <td><input type="text" name="description"></td>
        <td>
            <select name="accountTitleID1" onchange="updateAccountTitleName(this, 'accountTitleName1')">
                <option value="">選択してください</option>
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.uid}">${account.uid}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <span id="accountTitleName1"></span>
        </td>
        <td>
            <select name="cdTaxationKbn1">
            	<option value="">選択してください</option>
                <option value="0">非課税</option>
                <option value="1">課税</option>
            </select>
        </td>
        <td>
            <select name="cdCTaxPriceKbn1">
            	<option value="">選択してください</option>
                <option value="0">対象外</option>
                <option value="1">外税</option>
                <option value="2">内税</option>
            </select>
        </td>
        <td><input type="text" name="transValue1"></td>
        <td><input type="text" name="description1"></td>
        <input type="hidden" name="uid" value="` + newUid + `">
        <input type="hidden" name="lineNumber" value="` + newLineNumber + `">
    `;

    table.appendChild(newRow);
}
  function doRegist() {
	    document.getElementById('theForm').submit();
	}
  function setDateLimits() {
	    const today = new Date();
	    const startOfYear = new Date(today.getFullYear(), 0, 2);
	    const endOfYear = new Date(today.getFullYear(), 11, 32);
	    const startDateStr = startOfYear.toISOString().split('T')[0];
	    const endDateStr = endOfYear.toISOString().split('T')[0];

	    document.getElementById('systemDate').setAttribute('min', startDateStr);
	    document.getElementById('systemDate').setAttribute('max', endDateStr);
	}


    // 更新する関数
    function updateAccountTitleName(selectElement, spanId) {
        const selectedValue = selectElement.value;
        const account = accounts.find(account => account.uid === selectedValue);
        if (account) {
            document.getElementById(spanId).innerText = account.name;
        } else {
            document.getElementById(spanId).innerText = '';
        }
    }

    // 数字をフォーマットする関数
    function formatNumber(input) {
        let value = input.value.replace(/,/g, '');
        if (!isNaN(value) && value !== "") {
            input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        }
    }

    // 伝票番号を更新する関数
    function updateDenpyoNumber() {
        const date = document.getElementById('systemDate').value;
        const formattedDate = date.replace(/-/g, '');
        const denpyoNumber = 'TS' + formattedDate;
        document.getElementById('denpyoNumber').value = denpyoNumber;
    }


    function cancelAction() {

        var userConfirmed = confirm("キャンセルしたいですか。");

        if (userConfirmed) {
            window.location.href = 'http://dev.it-softtech.com/emsm/journals'; //
        }
    }


    // HTML ドキュメントが完全に読み込まれたときのイベント
    document.addEventListener('DOMContentLoaded', (event) => {
        setDateLimits(); // Thiết lập giới hạn ngày
        document.getElementById('systemDate').valueAsDate = new Date(); // Đặt ngày hệ thống
        updateDenpyoNumber(); // Cập nhật số chứng từ
    });
    document.addEventListener('DOMContentLoaded', (event) => {
        // Hiển thị thông báo thành công nếu có
        const successMessage = '${successMessage}';
        if (successMessage) {
            alert(successMessage);
        }
    });
</script>
</body>
</html>
