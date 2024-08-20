<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title></title>
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
            max-width: 150px;
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
        input[type="text"], select {
    width: 100%; /* Đảm bảo rằng input và select chiếm toàn bộ chiều rộng của ô */
    box-sizing: border-box; /* Bao gồm padding và border vào chiều rộng tổng thể */
}
    </style>
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
      var newUid = 'UID' + new Date().getTime(); // Tạo UID mới
      var newLineNumber = table.querySelectorAll('tr').length + 1; // Tăng giá trị lineNumber
      var debitAccountSelectId = 'debitAccountTitleID' + newLineNumber;
      var debitAccountNameSpanId = 'debitAccountTitleName' + newLineNumber;
      var creditAccountSelectId = 'creditAccountTitleID' + newLineNumber;
      var creditAccountNameSpanId = 'creditAccountTitleName' + newLineNumber;
      var debitTaxationKbnId = 'debitcdTaxationKbn' + newLineNumber;
      var debitCTaxPriceKbnId = 'debitcdCTaxPriceKbn' + newLineNumber;
      var creditTaxationKbnId = 'creditcdTaxationKbn' + newLineNumber;
      var creditCTaxPriceKbnId = 'creditcdCTaxPriceKbn' + newLineNumber;

      var newRow = document.createElement('tr');

      newRow.innerHTML = `
          <td>` + newLineNumber + `</td>
          <td>
              <select id="` + debitAccountSelectId + `" name="debitAccountTitleID" onchange="updateAccountTitleName(this, '` + debitAccountNameSpanId + `')">
                  <option value="">選択</option>
                  <c:forEach var="account" items="${accounts}">
                      <option value="${account.uid}">${account.uid}</option>
                  </c:forEach>
              </select>
          </td>
          <td>
              <span id="` + debitAccountNameSpanId + `"></span>
          </td>
          <td>
              <select id="` + debitTaxationKbnId + `" name="debitcdTaxationKbn" onchange="updateTaxProcessingOptions(this, '` + debitCTaxPriceKbnId + `')">
                  <option value="">選択</option>
                  <option value="0">非課税</option>
                  <option value="1">課税</option>
              </select>
          </td>
          <td>
              <select id="` + debitCTaxPriceKbnId + `" name="debitcdCTaxPriceKbn">
                  <option value="">選択</option>
                  <option value="0">対象外</option>
                  <option value="1">外税</option>
                  <option value="2">内税</option>
              </select>
          </td>
          <td><input type="text" name="debitTransValue" oninput="formatNumberWithCommas(this)"></td>
          <td><input type="text" name="debitDescription"></td>
          <td>
              <select id="` + creditAccountSelectId + `" name="creditAccountTitleID" onchange="updateAccountTitleName(this, '` + creditAccountNameSpanId + `')">
                  <option value="">選択</option>
                  <c:forEach var="account" items="${accounts}">
                      <option value="${account.uid}">${account.uid}</option>
                  </c:forEach>
              </select>
          </td>
          <td>
              <span id="` + creditAccountNameSpanId + `"></span>
          </td>
          <td>
              <select id="` + creditTaxationKbnId + `" name="creditcdTaxationKbn" onchange="updateTaxProcessingOptions(this, '` + creditCTaxPriceKbnId + `')">
                  <option value="">選択</option>
                  <option value="0">非課税</option>
                  <option value="1">課税</option>
              </select>
          </td>
          <td>
              <select id="` + creditCTaxPriceKbnId + `" name="creditcdCTaxPriceKbn">
                  <option value="">選択</option>
                  <option value="0">対象外</option>
                  <option value="1">外税</option>
                  <option value="2">内税</option>
              </select>
          </td>
          <td><input type="text" name="creditTransValue" oninput="formatNumberWithCommas(this)"></td>
          <td><input type="text" name="creditDescription"></td>
          <input type="hidden" name="uid" value="` + newUid + `">
          <input type="hidden" name="lineNumber" value="` + newLineNumber + `">
      `;

      table.appendChild(newRow);
  }
  function validateTotals() {
	    let debitTotal = 0;
	    let creditTotal = 0;

	    // Tính tổng số tiền bên Debit
	    document.querySelectorAll('tr').forEach(row => {
	        const debitAmountInput = row.querySelector('input[name="debitTransValue"]');
	        if (debitAmountInput) {
	            const amount = parseFloat(debitAmountInput.value.replace(/,/g, '')) || 0;
	            debitTotal += amount;
	        }
	    });

	    // Tính tổng số tiền bên Credit
	    document.querySelectorAll('tr').forEach(row => {
	        const creditAmountInput = row.querySelector('input[name="creditTransValue"]');
	        if (creditAmountInput) {
	            const amount = parseFloat(creditAmountInput.value.replace(/,/g, '')) || 0;
	            creditTotal += amount;
	        }
	    });

	    // So sánh tổng số tiền Debit và Credit
	    if (debitTotal !== creditTotal) {
	        alert("エラー: 借方と貸方の金額が一致しません。");
	        return false; // Ngăn gửi biểu mẫu nếu không khớp
	    }
	    return true; // Cho phép gửi biểu mẫu nếu khớp
	}
//Hàm để kiểm tra các trường hợp dữ liệu
// Hàm để kiểm tra các trường hợp dữ liệu
// Hàm để kiểm tra các trường hợp dữ liệu
function validateForm() {
    let isValid = true;
    let errors = [];

    // Kiểm tra trường Debit thông tin
    let debitAccountTitleID = document.querySelector('select[name="debitAccountTitleID"]').value;
    let debitTaxationKbn = document.querySelector('select[name="debitcdTaxationKbn"]').value;
    let debitTransValue = document.querySelector('input[name="debitTransValue"]').value;

    // Kiểm tra trường Credit thông tin
    let creditAccountTitleID = document.querySelector('select[name="creditAccountTitleID"]').value;
    let creditTaxationKbn = document.querySelector('select[name="creditcdTaxationKbn"]').value;
    let creditTransValue = document.querySelector('input[name="creditTransValue"]').value;

    // Kiểm tra nếu không có cả Debit và Credit thông tin
    if (!debitAccountTitleID && !creditAccountTitleID) {
        errors.push("借方または貸方の勘定科目番号のいずれかは必須です");
        isValid = false;
    }

    // Kiểm tra trường Debit nếu có thông tin
    if (debitAccountTitleID) {
        if (!debitTaxationKbn) {
            errors.push("借方の課税区分は必須です");
            isValid = false;
        }
        if (!debitTransValue) {
            errors.push("借方の金額は必須です");
            isValid = false;
        } else {
            if (!debitTransValue.match(/^[0-9]+$/)) {
                errors.push("借方の金額に数字のみを入力してください。");
                isValid = false;
            }
            if (debitTransValue.length > 10) {
                errors.push("借方の金額は10文字以下に入力してください。");
                isValid = false;
            }
            if (parseFloat(debitTransValue) < 1) {
                errors.push("借方金額は1以上でなければなりません");
                isValid = false;
            }
        }
    }

    // Kiểm tra trường Credit nếu có thông tin
    if (creditAccountTitleID) {
        if (!creditTaxationKbn) {
            errors.push("貸方の課税区分は必須です");
            isValid = false;
        }
        if (!creditTransValue) {
            errors.push("貸方の金額は必須です");
            isValid = false;
        } else {
            if (!creditTransValue.match(/^[0-9]+$/)) {
                errors.push("貸方の金額に数字のみを入力してください。");
                isValid = false;
            }
            if (creditTransValue.length > 10) {
                errors.push("貸方の金額は10文字以下に入力してください。");
                isValid = false;
            }
            if (parseFloat(creditTransValue) < 1) {
                errors.push("貸方金額は1以上でなければなりません");
                isValid = false;
            }
        }
    }

    // Hiển thị lỗi nếu có
    if (!isValid) {
        alert(errors.join('\n'));
        return false;
    }

    return true;
}


function formatNumberWithCommas(input) {
    // Xóa dấu phẩy cũ
    let value = input.value.replace(/,/g, '');

    // Thêm dấu phẩy mới
    input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
function removeCommasFromInputs() {
    document.querySelectorAll('input[name="debitTransValue"], input[name="creditTransValue"]').forEach(input => {
        input.value = input.value.replace(/,/g, '');
    });
}

function doRegist() {

	removeCommasFromInputs();
    if (validateForm() && validateTotals()) {
         // Loại bỏ dấu phẩy trước khi gửi biểu mẫu

        document.getElementById('theForm').submit(); // Gửi biểu mẫu nếu hợp lệ
    }
}
  function setDateLimits() {
      const today = new Date();
      const startOfYear = new Date(today.getFullYear(), 3, 2);
      const endOfYear = new Date(today.getFullYear() + 1, 2, 32);
      const startDateStr = startOfYear.toISOString().split('T')[0];
      const endDateStr = endOfYear.toISOString().split('T')[0];

      var dateInput = document.getElementById('systemDate');
      dateInput.setAttribute('min', startDateStr);
      dateInput.setAttribute('max', endDateStr);

      // Remove readonly to allow the date picker to work
      dateInput.removeAttribute('readonly');
  }

  // Prevent manual input in the date field
  function preventManualInput(e) {
      e.preventDefault();
  }

  document.addEventListener('DOMContentLoaded', (event) => {
      setDateLimits(); // Set date limits
      document.getElementById('systemDate').valueAsDate = new Date(); // Set default date
      updateDenpyoNumber(); // Update document number

      // Prevent manual input
      const dateInput = document.getElementById('systemDate');
      dateInput.addEventListener('keydown', preventManualInput);
      dateInput.addEventListener('paste', preventManualInput);
      dateInput.addEventListener('input', preventManualInput);
  });
  function updateTaxProcessingOptions(taxationSelect, taxProcessingSelectId) {
	    var selectedValue = taxationSelect.value;
	    var taxProcessingSelect = document.getElementById(taxProcessingSelectId);

	    if (selectedValue === '0') { // Non-taxable selected
	        taxProcessingSelect.value = "0"; // Set tax processing to "Not applicable"
	        taxProcessingSelect.disabled = true; // Disable tax processing
	        document.getElementById('debitcdCTaxPriceKbn').value = taxProcessingSelect.value;
	    } else { // Taxable selected
	        taxProcessingSelect.disabled = false; // Enable tax processing
	    }

	    // Ensure the value is set correctly to be sent to the server

	}

  function updateCreditTaxProcessingOptions(taxationSelect) {
	    var selectedValue = taxationSelect.value;
	    var taxProcessingSelect = document.querySelector('select[name="creditcdCTaxPriceKbn"]');

	    if (selectedValue === '0') { // Non-taxable selected
	        taxProcessingSelect.value = "0"; // Set tax processing to "Not applicable"
	        taxProcessingSelect.disabled = true; // Disable tax processing


	    } else { // Taxable selected
	        taxProcessingSelect.disabled = false; // Enable tax processing
	    }

	    // Ensure the value is set correctly to be sent to the server

	}

    // 更新する関数
   function updateAccountTitleName(selectElement, spanId) {
        var uid = selectElement.value;
        var span = document.getElementById(spanId);
        var account = accounts.find(a => a.uid === uid);
        if (account) {
            span.textContent = account.name;
        } else {
            span.textContent = '';
        }
    }

    // 数字をフォーマットする関数
    // Hàm để định dạng số với dấu phẩy


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
    document.addEventListener('DOMContentLoaded', (event) => {
        var debitTaxationSelect = document.getElementById('debitcdTaxationKbn');
        updateTaxProcessingOptions(debitTaxationSelect, 'debitcdCTaxPriceKbn');

        var creditTaxationSelect = document.getElementById('creditcdTaxationKbn');
        updateTaxProcessingOptions(creditTaxationSelect, 'creditcdCTaxPriceKbn');
    });
</script>
</head>
<body>
<h1 style="text-align: center;">一般会計</h1>

<!-- Hiển thị thông báo lỗi nếu có -->
<p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/><br>
        </c:forEach>
    </p>

<form:form name="theform" id="theForm"  action="journals/add" method="post" modelAttribute="tblJournalDetailFormBean">
    <div>
        <label for="systemDate">日付: </label>
        <input type="date" id="systemDate" name="bookDate" onchange="updateDenpyoNumber()" readonly>
        <br>
        <label for="denpyoNumber">伝票番号: </label>
        <input type="text" id="denpyoNumber" name="journalSerialNumber" readonly  style="width: 150px;">
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
                <td>${row.debitAccountTitleID}</td>
                <td>${row.debitAccountTitleName}</td>
                <td>
                    <c:choose>
                        <c:when test="${row.debitcdTaxationKbn == 0}">
                            非課税
                        </c:when>
                        <c:when test="${row.debitcdTaxationKbn == 1}">
                            課税
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${row.debitcdCTaxPriceKbn == 0}">
                            対象外
                        </c:when>
                        <c:when test="${row.debitcdCTaxPriceKbn == 1}">
                            外税
                        </c:when>
                        <c:when test="${row.debitcdCTaxPriceKbn == 2}">
                            内税
                        </c:when>
                    </c:choose>
                </td>
                <td>${row.debitTransValue}</td>
                <td>${row.debitDescription}</td>
                <td>${row.creditAccountTitleID}</td>
                <td>${row.creditAccountTitleName}</td>
                <td>
                    <c:choose>
                        <c:when test="${row.creditcdTaxationKbn == 0}">
                            非課税
                        </c:when>
                        <c:when test="${row.creditcdTaxationKbn == 1}">
                            課税
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${row.creditcdCTaxPriceKbn == 0}">
                            対象外
                        </c:when>
                        <c:when test="${row.creditcdCTaxPriceKbn == 1}">
                            外税
                        </c:when>
                        <c:when test="${row.creditcdCTaxPriceKbn == 2}">
                            内税
                        </c:when>
                    </c:choose>
                </td>
                <td>${row.creditTransValue}</td>
                <td>${row.creditDescription}</td>
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


</body>
</html>
