<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>経費新規追加</title>
<style>
.table-container {
	width: 100%;
	margin: 0 auto;
	margin-bottom: 20px;
}

h1 {
	text-align: left;
	font-size: 24px;
	font-weight: bold;
}

table {
	width: 100%;
	margin-top: 30px;
	border-collapse: collapse;
	table-layout: fixed;
}

th, td {
	border: 2px solid #b3cbde;
	padding: 5px;
	text-align: center;
	vertical-align: middle;
}

th {
	background-color: #f2f2f2;
}

/* 各列幅設定 */
th:nth-child(1), td:nth-child(1) { width: 10%; }  /* 経費種別 */
th:nth-child(2), td:nth-child(2) { width: 10%; }  /* 経費名称 */
th:nth-child(3), td:nth-child(3) { width: 15%; }  /* 発生日付 */
th:nth-child(4), td:nth-child(4) { width: 10%; }  /* 金額 */
th:nth-child(5), td:nth-child(5) { width: 25%; }  /* 用途 */
th:nth-child(6), td:nth-child(6) { width: 8%; }   /* 担当者 */
th:nth-child(7), td:nth-child(7) { width: 15%; }  /* 精算日付 */
th:nth-child(8), td:nth-child(8) { width: 8%; }   /* 精算種別 */
th:nth-child(9), td:nth-child(9) { width: 5%; }   /* 画像 */
th:nth-child(10), td:nth-child(10) { width: 5%; } /* 操作 */

/* 入力項目・選択項目の共通スタイル */
td input[type="text"],
td input[type="number"],
td input[type="date"],
td select {
   box-sizing: border-box;
   padding: 5px;
   border: 1px solid #ccc;
   width: calc(100% - 10px);
   max-width: 100%;
}

/* 特定入力項目のスタイル調整 */
.expensesType, .expenseName {
   width: calc(100% - 5px) !important;
}
.accrualDate, .settlementDate {
   width: calc(100% - 5px) !important;
}
.settlementType {
   width: calc(100% - 5px) !important;
}

/* 特定入力項目の幅調整 */
.happenAddress {
   width: calc(100% - 5px) !important;
}
.cost {
   width: calc(100% - 5px) !important;
}

.error-message {
	color: red;
	text-align: center;
	margin-top: 10px;
}

button {
	padding: 6px 12px;
	margin: 0 5px;
	cursor: pointer;
}

/* 删除按钮样式 */
.deleteRowBtn {
	font-size: 11px;
	padding: 4px 8px;
	margin: 2px;
	cursor: pointer;
	border: 1px solid #ccc;
	background-color: #f8f9fa;
	border-radius: 3px;
}

.deleteRowBtn:hover {
	background-color: #e9ecef;
}


.hidden-file-input {
	display: none;
}

/* 证跡按钮样式 */
.receipt-button {
	font-size: 11px;
	padding: 4px 8px;
	margin: 2px;
	cursor: pointer;
	border: 1px solid #ccc;
	background-color: #f8f9fa;
	border-radius: 3px;
}

.receipt-button:hover {
	background-color: #e9ecef;
}

/* 文件名显示样式 */
.file-name-display {
	font-size: 10px;
	color: #666;
	margin-bottom: 3px;
	text-align: center;
	word-break: break-all;
	max-width: 100px;
	line-height: 1.2;
	display: none;
}

.btn-container {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

</style>
</head>
<body>
	<div class="table-container">
		<h1>経費新規追加</h1>

		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>

		<div>
			<button type="button" id="addRowBtn">追加</button>
		</div>

		<table>
			<thead>
				<tr>
					<th>経費種別</th>
					<th>経費名称</th>
					<th>発生日付</th>
					<th>金額(円)</th>
					<th>用途</th>
					<th>担当者</th>
					<th>精算日付</th>
					<th>精算種別</th>
					<th>証跡</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="expenseTableBody"></tbody>
		</table>

		<div class="btn-container">
			<button type="button" id="confirmBtn">確定</button>
			<button type="button" id="backBtn">戻る</button>
		</div>
	</div>

	<script>
	/**
	* 経費種別マスターデータをJavaScriptで使用できるように設定
	*/
	var expenseTypeGroups = {
		<c:forEach var="group" items="${expenseTypeGroups}" varStatus="status">
			"${group.key}": [
				<c:forEach var="type" items="${group.value}" varStatus="typeStatus">
					{
						id: ${type.id},
						expensesType: "${type.expensesType}",
						expensesTypeName: "${type.expensesTypeName}",
						expenseName: "${type.expenseName}"
					}<c:if test="${!typeStatus.last}">,</c:if>
				</c:forEach>
			]<c:if test="${!status.last}">,</c:if>
		</c:forEach>
	};

	/**
	* 未保存データの有無を管理するフラグ
	* @type {boolean}
	*/
	let unsavedData = false;

	/**
	* 行番号を管理するカウンター
	*/
	let rowCounter = 0;

	/**
	* ページ読み込み完了時の初期処理
	*/
	window.onload = function() {
	   addNewRow();
	};

	/**
	* 行追加ボタンのクリックイベントハンドラ
	*/
	document.getElementById("addRowBtn").addEventListener("click", function() {
	   addNewRow();
	});

	/**
	* 経費種別が変更されたときに経費名称のオプションを更新する
	*
	* @param {string} rowId - 行ID
	*/
	function updateExpenseNameOptions(rowId) {
	    const typeSelect = document.getElementById('expensesType_' + rowId);
	    const nameSelect = document.getElementById('expenseName_' + rowId);

	    if (!typeSelect || !nameSelect) return;

	    const selectedType = typeSelect.value;

	    // 経費名称セレクトボックスをクリア
	    nameSelect.innerHTML = '<option value="">選択してください</option>';

	    // 選択された種別に対応する経費名称を追加
	    if (selectedType && expenseTypeGroups[selectedType]) {
	        expenseTypeGroups[selectedType].forEach(function(item) {
	            const option = document.createElement('option');
	            option.value = item.id;
	            option.textContent = item.expenseName;
	            nameSelect.appendChild(option);
	        });
	    }
	}

	/**
	* アップロードファイルのサイズ検証を行う
	*
	* @param {HTMLInputElement} input - ファイル入力要素
	* @param {number} maxMB - 許容最大サイズ（MB単位）
	* @returns {boolean} 検証結果（true: OK、false: NG）
	*/
	function validateFileSize(input, maxMB) {
	   if (input.files && input.files[0]) {
	       const fileSize = input.files[0].size / 1024 / 1024;
	       if (fileSize > maxMB) {
	           alert(`ファイルサイズは${maxMB}MB以下にしてください。`);
	           input.value = '';
	           return false;
	       }
	   }
	   return true;
	}

	/**
	* 経費入力用の新規行を追加する
	*
	* @details
	* - 経費種別、経費名称は選択式（マスターデータから取得）
	* - 発生日付、金額、用途、担当者は必須入力
	* - 精算日付、精算種別は任意入力
	* - 領収書画像のアップロードとプレビュー機能あり
	*/
	function addNewRow() {
	   // 行IDの生成
	   rowCounter++;
	   const rowId = rowCounter;

	   // テーブルボディの取得
	   const tableBody = document.getElementById("expenseTableBody");
	   const newRow = document.createElement("tr");
	   newRow.id = "row_" + rowId;

	   // 経費種別セルの生成
	   const cell1 = document.createElement("td");
	   let typeOptions = '<select id="expensesType_' + rowId + '" class="expensesType" required onchange="updateExpenseNameOptions(' + rowId + ')">';
	   typeOptions += '<option value="">選択</option>';
	   for (let typeCode in expenseTypeGroups) {
	       const typeName = expenseTypeGroups[typeCode][0].expensesTypeName;
	       typeOptions += '<option value="' + typeCode + '">' + typeName + '</option>';
	   }
	   typeOptions += '</select>';
	   cell1.innerHTML = typeOptions;
	   newRow.appendChild(cell1);

	   // 経費名称セルの生成
	   const cell2 = document.createElement("td");
	   cell2.innerHTML = '<select id="expenseName_' + rowId + '" class="expenseName" required><option value="">選択</option></select>';
	   newRow.appendChild(cell2);

	   // 発生日付セルの生成
	   const cell3 = document.createElement("td");
	   cell3.innerHTML = '<input type="date" class="accrualDate" required />';
	   newRow.appendChild(cell3);

	   // 金額セルの生成
	   const cell4 = document.createElement("td");
	   cell4.innerHTML = '<input type="text" class="cost" required />';
	   newRow.appendChild(cell4);

	   // 用途セルの生成
	   const cell5 = document.createElement("td");
	   cell5.innerHTML = '<input type="text" class="happenAddress" style="width:100%;" required />';
	   newRow.appendChild(cell5);

	   // 担当者セルの生成
	   const cell6 = document.createElement("td");
	   cell6.innerHTML = '<input type="text" class="tantouName" required />';
	   newRow.appendChild(cell6);

	   // 精算日付セルの生成
	   const cell7 = document.createElement("td");
	   cell7.innerHTML = '<input type="date" class="settlementDate" />';
	   newRow.appendChild(cell7);

	   // 精算種別セルの生成
	   const cell8 = document.createElement("td");
	   cell8.innerHTML = `
	       <select class="settlementType">
	           <option value="">選択</option>
	           <option value="0">現金</option>
	           <option value="1">口座</option>
	       </select>
	   `;
	   newRow.appendChild(cell8);

	   // 领收书画像セルの生成
	   const cell9 = document.createElement("td");
	   cell9.innerHTML = `
	       <input type="file" class="receiptFile hidden-file-input"
	           accept=".jpg,.jpeg,.png,.pdf"
	           onchange="onFileSelected(this)" />
	       <div class="file-name-display" id="fileName_${rowId}"></div>
	       <button type="button" class="receipt-button"
	           onclick="this.parentElement.querySelector('.receiptFile').click();">
	           証跡
	       </button>
	   `;
	   newRow.appendChild(cell9);

	   // 操作ボタンセルの生成
	   const cell10 = document.createElement("td");
	   cell10.innerHTML = '<button type="button" class="deleteRowBtn" onclick="deleteRow(' + rowId + ')">削除</button>';
	   newRow.appendChild(cell10);

	   // 行の追加と未保存フラグの設定
	   tableBody.appendChild(newRow);
	   unsavedData = true;
	}

	/**
	* 行を削除する
	*
	* @param {number} rowId - 削除する行のID
	*/
	function deleteRow(rowId) {
	   const row = document.getElementById("row_" + rowId);
	   if (!row) return;

	   // 行内の入力値チェック
	   let hasData = false;
	   row.querySelectorAll("input, select").forEach(el => {
	       if (el.value && el.value.trim() !== "") {
	           hasData = true;
	       }
	   });

	   // データが存在する場合は削除確認
	   if (hasData && !confirm("該当行を削除します。よろしいですか？")) {
	       return;
	   }

	   // 行の削除と状態更新
	   row.remove();
	   unsavedData = true;
	}

	/**
	* ファイル選択時の処理を実行する
	*
	* @param {HTMLInputElement} input - ファイル入力要素
	* @details
	* - ファイルサイズの上限は5MB
	* - 画像ファイルの場合はプレビュー表示
	* - 画像以外のファイルはプレビューを非表示
	* - 選択されたファイル名を按钮上方に表示
	*/
	function onFileSelected(input) {
	   // ファイルサイズの検証
	   if (!validateFileSize(input, 5)) {
	       // 验证失败时清除文件名显示
	       const fileNameDisplay = input.parentElement.querySelector(".file-name-display");
	       if (fileNameDisplay) {
	           fileNameDisplay.textContent = "";
	           fileNameDisplay.style.display = "none";
	       }
	       return;
	   }

	   // 選択ファイルの取得
	   const file = input.files && input.files[0];
	   const fileNameDisplay = input.parentElement.querySelector(".file-name-display");

	   if (!file) {
	       // ファイルが選択されていない場合は表示をクリア
	       if (fileNameDisplay) {
	           fileNameDisplay.textContent = "";
	           fileNameDisplay.style.display = "none";
	       }
	       return;
	   }

	   // ファイル名の表示
	   if (fileNameDisplay) {
	       fileNameDisplay.textContent = file.name;
	       fileNameDisplay.style.display = "block";
	   }
	}

	/**
	* 確定ボタンクリック時の保存処理を実行する
	*
	* @details
	* - 必須項目: 経費種別、経費名称、発生日付、金額、用途、担当者
	* - 任意項目: 精算日付、精算種別、領収書画像
	* - 保存成功時は入力フォームをクリア
	* - エラー発生時はメッセージを表示
	*/
	document.getElementById("confirmBtn").addEventListener("click", async function() {
	   // 保存確認
	   if (!confirm("保存しますか？")) return;

	   // 入力データの存在確認
	   const rows = document.querySelectorAll("#expenseTableBody tr");
	   if (!rows.length) {
	       alert("入力データがありません。");
	       return;
	   }

	   try {
	       // 各行のデータを処理
	       for (let row of rows) {
	           // 経費データの取得
	           const expenseData = {
	               expensesType: row.querySelector(".expensesType").value,
	               mexpensesId: row.querySelector(".expenseName").value,
	               accrualDate: row.querySelector(".accrualDate").value,
	               cost: row.querySelector(".cost").value,
	               happenAddress: row.querySelector(".happenAddress").value,
	               tantouName: row.querySelector(".tantouName").value,
	               settlementDate: row.querySelector(".settlementDate").value || null,
	               settlementType: row.querySelector(".settlementType").value || null,
	               deleteFlg: "0"
	           };

	           // 必須項目の入力チェック
	           if (!expenseData.expensesType || !expenseData.mexpensesId ||
	               !expenseData.accrualDate || !expenseData.cost ||
	               !expenseData.happenAddress || !expenseData.tantouName) {
	               alert("経費種別、経費名称、発生日付、金額、用途、担当者は必須項目です。");
	               return;
	           }

	           // 送信データの準備
	           const formData = new FormData();
	           formData.append("expenseData", JSON.stringify(expenseData));

	           // 領収書ファイルの追加
	           const fileInput = row.querySelector(".receiptFile");
	           if (fileInput && fileInput.files[0]) {
	               formData.append("receiptFile", fileInput.files[0]);
	           }

	           // サーバーへのデータ送信
	           const resp = await fetch('${pageContext.request.contextPath}/expenseInfo/add', {
	               method: 'POST',
	               body: formData
	           });

	           // レスポンスの検証
	           if (!resp.ok) {
	               const txt = await resp.text();
	               throw new Error(txt || "保存失敗");
	           }

	           // レスポンスデータの解析
	           const contentType = resp.headers.get("content-type");
	           let result;
	           if (contentType && contentType.indexOf("application/json") !== -1) {
	               result = await resp.json();
	           } else {
	               result = { status: "error", message: await resp.text() };
	           }

	           // 処理結果の確認
	           if (result.status !== "ok") {
	               throw new Error(result.message || "保存失敗");
	           }
	       }

	       // 保存成功時の処理
	       alert("保存成功");
	       document.getElementById("expenseTableBody").innerHTML = "";
	       rowCounter = 0;
	       addNewRow();
	       unsavedData = false;

	   } catch (err) {
	       // エラー処理
	       console.error(err);
	       alert("保存エラー: " + err.message);
	   }
	});

	/**
	* 戻るボタンクリック時の処理を実行する
	*
	* @details
	* - 未保存データがある場合は確認ダイアログを表示
	* - 確認後、一覧画面に遷移
	*/
	document.getElementById("backBtn").addEventListener("click", function() {
	   // 未保存データの確認
	   if (unsavedData && !confirm("まだ保存していないデータがあります。放棄してよろしいですか？")) {
	       return;
	   }

	   // 一覧画面へ遷移
	   location.href = '${pageContext.request.contextPath}/expenseList';
	});
    </script>
</body>
</html>