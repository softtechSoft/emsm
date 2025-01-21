<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>経費管理</title>
<style>
.table-container {
	width: 90%;
	margin-left: 5%;
	margin-bottom: 20px;
}

h1 {
	text-align: left;
	font-size: 24px;
	font-weight: bold;
}

.form-group {
	margin-top: 20px;
	display: flex;
	align-items: center;
	flex-wrap: wrap;
}

label {
	margin-right: 10px;
	font-weight: bold;
}

select {
	margin-right: 20px;
	padding: 5px;
}

button {
	padding: 5px 10px;
	font-size: 14px;
	cursor: pointer;
	margin-right: 10px;
}

table {
	width: 100%;
	margin-top: 30px;
	border-collapse: collapse;
}

th, td {
	border: 2px solid #b3cbde;
	padding: 15px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #fafafa;
}

.button-container {
	text-align: center;
	padding-top: 10px;
}

.no-data-message {
	font-weight: bold;
	text-align: center;
	padding: 20px 0;
}
</style>
</head>
<body>
	<div class="table-container">
		<h1>経費管理</h1>

		<!-- 検索フォーム -->
		<form action="${pageContext.request.contextPath}/expenseList/search"
			method="get">
			<div class="form-group">
				<label for="year">年度:</label> <select name="year" id="year">
					<c:forEach var="yearItem" items="${yearList}">
						<option value="${yearItem}"
							<c:if test="${yearItem eq currentYear}">selected</c:if>>
							${yearItem}年</option>
					</c:forEach>
				</select> <label for="month">月度:</label> <select name="month" id="month">
					<c:forEach var="monthItem" items="${monthList}">
						<option value="${monthItem}"
							<c:if test="${monthItem eq currentMonth}">selected</c:if>>
							${monthItem}月</option>
					</c:forEach>
				</select>

				<button type="submit">検索</button>

				<!-- 新規追加ボタン -->
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/expenseInfo'">
					新規</button>
			</div>
		</form>

		<!-- 経費データテーブル -->
		<table>
			<thead>
				<tr>
					<th>経費種別</th>
					<th>発生日付</th>
					<th>金額</th>
					<th>用途</th>
					<th>担当者</th>
					<th>精算日付</th>
					<th>精算種別</th>
					<th>編集</th>
					<th>削除</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty expenseList}">
						<c:forEach var="expense" items="${expenseList}">
							<tr id="row${expense.expensesID}">
								<td class="col-expensesType"><c:choose>
										<c:when test="${expense.expensesType eq '1'}">一般経費</c:when>
										<c:when test="${expense.expensesType eq '2'}">固定経費</c:when>
										<c:otherwise>その他</c:otherwise>
									</c:choose></td>
								<td class="col-accrualDate"><c:out
										value="${expense.accrualDate}" /></td>
								<td class="col-cost"><c:out value="${expense.cost}" />円</td>
								<td class="col-happenAddress"><c:out
										value="${expense.happenAddress}" /></td>
								<td class="col-tantouName"><c:out
										value="${expense.tantouName}" /></td>
								<td class="col-settlementDate"><c:out
										value="${expense.settlementDate}" /></td>
								<td class="col-settlementType"><c:choose>
										<c:when test="${expense.settlementType eq '0'}">現金</c:when>
										<c:when test="${expense.settlementType eq '1'}">口座</c:when>
										<c:otherwise>その他</c:otherwise>
									</c:choose></td>
								<td class="col-edit">
									<button type="button"
										onclick="editExpense('${expense.expensesID}')">編集</button>
								</td>
								<td class="col-delete">
									<button type="button"
										onclick="deleteExpense('${expense.expensesID}')">削除</button>
								</td>
							</tr>
						</c:forEach>
						<!-- 合計金額表示行 -->
						<tr>
							<td colspan="2"></td>
							<td><strong>合計: ${totalCost}円</strong></td>
							<td colspan="6"></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="9" class="no-data-message">データがありません。</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

	<!-- JavaScript スクリプト -->
	<script>
    /**
     * 行内編集機能
     * 指定された経費IDの行を編集モードに切り替える
     * @param {string} expensesID - 編集対象の経費ID
     */
    function editExpense(expensesID) {
        console.log("editExpense called with:", expensesID);
        const row = document.getElementById("row" + expensesID);
        if (!row) {
            console.error("Row with id 'row" + expensesID + "' not found.");
            return;
        }

        const colExpensesType = row.querySelector(".col-expensesType");
        const colAccrualDate = row.querySelector(".col-accrualDate");
        const colCost = row.querySelector(".col-cost");
        const colHappenAddress = row.querySelector(".col-happenAddress");
        const colTantouName = row.querySelector(".col-tantouName");
        const colSettlementDate = row.querySelector(".col-settlementDate");
        const colSettlementType = row.querySelector(".col-settlementType");
        const colEdit = row.querySelector(".col-edit");

        // 元のデータを取得
        const originalCost = colCost.textContent.replace('円', '').trim();
        const originalExpensesType = colExpensesType.textContent.trim();
        const originalSettlementType = colSettlementType.textContent.trim();

        // selectの選択状態を設定
        const selected1 = (originalExpensesType === "一般経費") ? "selected" : "";
        const selected2 = (originalExpensesType === "固定経費") ? "selected" : "";
        const selectedCash = (originalSettlementType === "現金") ? "selected" : "";
        const selectedAccount = (originalSettlementType === "口座") ? "selected" : "";

        // 各フィールドを編集可能な入力要素に置き換える
        colExpensesType.innerHTML = 
            '<select id="editExpensesType_' + expensesID + '" name="expensesType">' +
                '<option value="1" ' + selected1 + '>一般経費</option>' +
                '<option value="2" ' + selected2 + '>固定経費</option>' +
            '</select>';

        colAccrualDate.innerHTML = 
            '<input type="date" ' +
                   'id="editAccrualDate_' + expensesID + '" ' +
                   'name="accrualDate" ' +
                   'value="' + colAccrualDate.textContent.trim() + '" />';

        colCost.innerHTML = 
            '<input type="number" ' +
                   'id="editCost_' + expensesID + '" ' +
                   'name="cost" ' +
                   'value="' + originalCost + '" ' +
                   'step="1" />円';

        colHappenAddress.innerHTML = 
            '<input type="text" ' +
                   'id="editHappenAddress_' + expensesID + '" ' +
                   'name="happenAddress" ' +
                   'value="' + colHappenAddress.textContent.trim() + '" ' +
                   'style="width:100%" />';

        colTantouName.innerHTML = 
            '<input type="text" ' +
                   'id="editTantouName_' + expensesID + '" ' +
                   'name="tantouName" ' +
                   'value="' + colTantouName.textContent.trim() + '" />';

        colSettlementDate.innerHTML = 
            '<input type="date" ' +
                   'id="editSettlementDate_' + expensesID + '" ' +
                   'name="settlementDate" ' +
                   'value="' + colSettlementDate.textContent.trim() + '" />';

        colSettlementType.innerHTML = 
            '<select id="editSettlementType_' + expensesID + '" name="settlementType">' +
                '<option value="0" ' + selectedCash + '>現金</option>' +
                '<option value="1" ' + selectedAccount + '>口座</option>' +
            '</select>';

        // 編集ボタンを確定とキャンセルボタンに変更
        colEdit.innerHTML = 
            '<button type="button" onclick="confirmEdit(\'' + expensesID + '\')">確定</button>' +
            '<button type="button" onclick="cancelEdit(\'' + expensesID + '\')">キャンセル</button>';
    }

    /**
     * 編集のキャンセル機能
     * 編集モードをキャンセルし、元のデータに戻す
     * @param {string} expensesID - キャンセル対象の経費ID
     */
    function cancelEdit(expensesID) {
        const row = document.getElementById("row" + expensesID);
        if (!row) return;

        const colExpensesType = row.querySelector(".col-expensesType");
        const colAccrualDate = row.querySelector(".col-accrualDate");
        const colCost = row.querySelector(".col-cost");
        const colHappenAddress = row.querySelector(".col-happenAddress");
        const colTantouName = row.querySelector(".col-tantouName");
        const colSettlementDate = row.querySelector(".col-settlementDate");
        const colSettlementType = row.querySelector(".col-settlementType");
        const colEdit = row.querySelector(".col-edit");

        // 各フィールドを元の値に戻す
        colExpensesType.textContent = mapExpensesType(row.querySelector('select').value);
        colAccrualDate.textContent = row.querySelector('input[type="date"]').value;
        colCost.textContent = row.querySelector('input[type="number"]').value + "円";
        colHappenAddress.textContent = row.querySelector('input[type="text"]').value;
        colTantouName.textContent = row.querySelector('input[name="tantouName"]').value;
        colSettlementDate.textContent = row.querySelector('input[name="settlementDate"]').value;
        colSettlementType.textContent = mapSettlementType(row.querySelector('select[name="settlementType"]').value);

        // 編集ボタンを復元
        colEdit.innerHTML = '<button type="button" onclick="editExpense(\'' + expensesID + '\')">編集</button>';
    }

    /**
     * 編集を確定し、AJAXで更新を送信する
     * @param {string} expensesID - 更新対象の経費ID
     */
    function confirmEdit(expensesID) {
        try {
            console.log("confirmEdit called with:", expensesID);
            if (!confirm("更新しますか？")) {
                return;
            }

            // すべての入力要素を取得
            const expensesTypeSelect = document.getElementById("editExpensesType_" + expensesID);
            const accrualDateInput = document.getElementById("editAccrualDate_" + expensesID);
            const costInput = document.getElementById("editCost_" + expensesID);
            const happenAddressInput = document.getElementById("editHappenAddress_" + expensesID);
            const tantouNameInput = document.getElementById("editTantouName_" + expensesID);
            const settlementDateInput = document.getElementById("editSettlementDate_" + expensesID);
            const settlementTypeSelect = document.getElementById("editSettlementType_" + expensesID);

            // 必須フィールドの存在を確認
            if (!expensesTypeSelect || !accrualDateInput || !costInput || 
                !happenAddressInput || !tantouNameInput || !settlementDateInput || 
                !settlementTypeSelect) {
                
                let errorMessage = "更新失敗: 以下の入力フィールドが見つかりません：\n";
                if (!expensesTypeSelect) errorMessage += "- 経費種別\n";
                if (!accrualDateInput) errorMessage += "- 発生日付\n";
                if (!costInput) errorMessage += "- 金額\n";
                if (!happenAddressInput) errorMessage += "- 用途\n";
                if (!tantouNameInput) errorMessage += "- 担当者\n";
                if (!settlementDateInput) errorMessage += "- 精算日付\n";
                if (!settlementTypeSelect) errorMessage += "- 精算種別\n";

                alert(errorMessage);
                return;
            }

            // 入力値を取得し、パラメータオブジェクトを構築
            const params = {
                expensesID: expensesID,
                expensesType: expensesTypeSelect.value,
                accrualDate: accrualDateInput.value,
                cost: costInput.value,
                happenAddress: happenAddressInput.value,
                tantouName: tantouNameInput.value,
                settlementDate: settlementDateInput.value,
                settlementType: settlementTypeSelect.value
            };

            console.log("Sending params:", params);

            // 更新リクエストを送信
            fetch('${pageContext.request.contextPath}/expenseList/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(params)
            })
            .then(function(response) {
                return response.text();
            })
            .then(function(data) {
                alert(data || "更新成功");
                window.location.reload();
            })
            .catch(function(error) {
                console.error('Error:', error);
                alert("更新失敗: " + error.message);
            });
        } catch (error) {
            console.error("Error in confirmEdit:", error);
            alert("エラーが発生しました: " + error.message);
        }
    }

    /**
     * 経費種別の数値をテキストにマッピング
     * @param {string} val - 経費種別の数値
     * @returns {string} - 経費種別のテキスト
     */
    function mapExpensesType(val) {
        switch(val) {
            case "1":
                return "一般経費";
            case "2":
                return "固定経費";
            default:
                return "その他";
        }
    }

    /**
     * 精算種別の数値をテキストにマッピング
     * @param {string} val - 精算種別の数値
     * @returns {string} - 精算種別のテキスト
     */
    function mapSettlementType(val) {
        switch(val) {
            case "0":
                return "現金";
            case "1":
                return "口座";
            default:
                return "その他";
        }
    }

    /**
     * 経費削除機能
     * @param {string} expensesID - 削除対象の経費ID
     */
    function deleteExpense(expensesID) {
        if (confirm("削除しますか？")) {
            location.href = '${pageContext.request.contextPath}/expenseList/delete/' + expensesID;
        }
    }
</script>
</body>
</html>
