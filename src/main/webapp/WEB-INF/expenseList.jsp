<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>経費管理</title>
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

.form-group {
	margin-top: 20px;
	display: flex;
	flex-wrap: wrap;
	align-items: center;
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
	margin-right: 5px;
}

table {
	width: 100%;
	margin-top: 30px;
	border-collapse: collapse;
}

th, td {
	border: 2px solid #b3cbde;
	padding: 8px;
	text-align: center;
	vertical-align: middle;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #fafafa;
}

.thumb-img {
	max-width: 100px;
	max-height: 100px;
	border: 1px solid #ccc;
	display: block;
	margin: 0 auto 5px auto;
}

.hidden-file-input {
	display: none;
}
</style>
</head>
<body>
	<div class="table-container">
		<h1>経費管理</h1>

		<c:if test="${not empty errorMessage}">
		    <div style="color:red; margin-bottom:10px;">
		        ${errorMessage}
		    </div>
		</c:if>

		<div id="errorBox" style="color:red; margin-bottom:10px;"></div>

		<!-- 検索フォーム -->
		<form action="${pageContext.request.contextPath}/expenseList/search"
			method="get"
			onsubmit="return validateSearchPeriod();">
			<div class="form-group">
				<label>期間:</label>
				<label for="fromYear">From</label>
				<select name="fromYear" id="fromYear">
				    <option value="">-</option>
				    <c:forEach var="yearItem" items="${yearList}">
				        <option value="${yearItem}"
				            <c:if test="${yearItem eq currentFromYear}">selected</c:if>>
				            ${yearItem}年
				        </option>
				    </c:forEach>
				</select>

				<select name="fromMonth" id="fromMonth">
				    <option value="">-</option>
				    <c:forEach var="monthItem" items="${monthList}">
				        <option value="${monthItem}"
				            <c:if test="${monthItem eq currentFromMonth}">selected</c:if>>
				            ${monthItem}月
				        </option>
				    </c:forEach>
				</select>

				<span>～</span>

				<label for="toYear">To</label>
				<select name="toYear" id="toYear">
				    <option value="">-</option>
				    <c:forEach var="yearItem" items="${yearList}">
				        <option value="${yearItem}"
				            <c:if test="${yearItem eq currentToYear}">selected</c:if>>
				            ${yearItem}年
				        </option>
				    </c:forEach>
				</select>

				<select name="toMonth" id="toMonth">
				    <option value="">-</option>
				    <c:forEach var="monthItem" items="${monthList}">
				        <option value="${monthItem}"
				            <c:if test="${monthItem eq currentToMonth}">selected</c:if>>
				            ${monthItem}月
				        </option>
				    </c:forEach>
				</select>
				<label for="tantouName">担当者:</label>
					<select name="tantouName" id="tantouName">
					  <option value="" <c:if test="${empty currentTantouName}">selected</c:if>>-</option>
					  <c:forEach var="t" items="${tantouList}">
					    <option value="${t}" <c:if test="${t eq currentTantouName}">selected</c:if>>
					      ${t}
					    </option>
					  </c:forEach>
					</select>
				<label for="expensesType">経費タイプ:</label>
					<select name="expensesType" id="expensesType">
					  <option value=""
					    <c:if test="${empty currentExpensesType}">selected</c:if>>
					    -
					  </option>
					  <c:forEach var="type" items="${expenseTypeOptionList}">
					    <option value="${type.expensesType}"
					      <c:if test="${type.expensesType eq currentExpensesType}">selected</c:if>>
					      ${type.expensesTypeName}
					    </option>
					  </c:forEach>
					</select>
				<label for="settlementStatus">精算ステータス:</label>
					<select name="settlementStatus" id="settlementStatus">
					    <option value="" <c:if test="${empty currentSettlementStatus}">selected</c:if>>-</option>
					    <option value="1" <c:if test="${currentSettlementStatus eq '1'}">selected</c:if>>精算済</option>
					    <option value="0" <c:if test="${currentSettlementStatus eq '0'}">selected</c:if>>未精算</option>
					</select>
				<button type="submit">検索</button>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/expenseInfo'">
					新規</button>
			<button type="button" onclick="downloadCurrentFilter()">
			    検索結果をダウンロード
			</button>
			</div>
		</form>

		<!-- データ一覧テーブル -->
		<table id="expenseTable">
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
					<th>作成日</th>
    				<th>更新日</th>
					<th>編集</th>
					<th>削除</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty expenseList}">
						<c:forEach var="expense" items="${expenseList}">
							<tr id="row${expense.expensesID}">
								<!-- 経費種別 -->
								<td class="col-expensesType" data-type-code="${expense.expensesType}">
								<c:out value="${expense.expensesTypeName}" />
								</td>
								<!-- 经费名称 -->
			                    <td class="col-expenseName" data-expense-id="${expense.mexpensesId}">
			                        <c:out value="${expense.expenseNameText}" />
			                    </td>
								<!-- 発生日付 -->
								<td class="col-accrualDate"><c:out
										value="${expense.accrualDate}" /></td>
								<!-- 金額 -->
								<td class="col-cost">
									<fmt:formatNumber value="${expense.cost}" pattern="#,###" />
								</td>
								<!-- 用途 -->
								<td class="col-happenAddress"><c:out
										value="${expense.happenAddress}" /></td>
								<!-- 担当者 -->
								<td class="col-tantouName"><c:out
										value="${expense.tantouName}" /></td>
								<!-- 精算日付 -->
								<td class="col-settlementDate"><c:out
										value="${expense.settlementDate}" /></td>
								<!-- 精算種別 -->
								<td class="col-settlementType"><c:choose>
										<c:when test="${expense.settlementType eq '0'}">現金</c:when>
										<c:when test="${expense.settlementType eq '1'}">口座</c:when>
										<c:otherwise></c:otherwise>
									</c:choose></td>
								<!-- 画像 -->
								<td class="col-receipt"><c:choose>
										<c:when test="${empty expense.receiptPath}">
                                            なし
                                        </c:when>
										<c:otherwise>
											<!--<img class="thumb-img"
												src="${pageContext.request.contextPath}/expenseList/showThumbnail?path=${expense.receiptPath}"
												alt="receipt" />-->
											<c:out
										value="${expense.receiptPath}"/>
											<button type="button"
												onclick="downloadReceipt('${expense.expensesID}')">
												ダウンロード</button>
										</c:otherwise>
									</c:choose></td>
								<!-- 作成日 -->
								<td>
								    <c:choose>
								        <c:when test="${not empty expense.insertDate}">
								            ${fn:substring(expense.insertDate,0,4)} -
								            ${fn:substring(expense.insertDate,4,6)} -
								            ${fn:substring(expense.insertDate,6,8)}
								        </c:when>
								        <c:otherwise>ー</c:otherwise>
								    </c:choose>
								</td>
								<!-- 更新日 -->
								<td>
								    <c:choose>
								        <c:when test="${not empty expense.updateDate}">
								            ${fn:substring(expense.updateDate,0,4)} -
								            ${fn:substring(expense.updateDate,4,6)} -
								            ${fn:substring(expense.updateDate,6,8)}
								        </c:when>
								        <c:otherwise>ー</c:otherwise>
								    </c:choose>
								</td>

								<!-- 編集ボタン -->
								<td class="col-edit"><c:choose>
										<c:when
											test="${not empty expense.settlementDate and not empty expense.settlementType}">
											<button type="button" disabled title="精算済みのため編集できません">編集</button>
										</c:when>
										<c:otherwise>
											<button type="button"
												onclick="editExpense('${expense.expensesID}')">編集</button>
										</c:otherwise>
									</c:choose></td>
								<!-- 削除ボタン -->
								<td class="col-delete"><c:choose>
										<c:when
											test="${not empty expense.settlementDate and not empty expense.settlementType}">
											<button type="button" disabled title="精算済みのため削除できません">削除</button>
										</c:when>
										<c:otherwise>
											<button type="button"
												onclick="deleteExpense('${expense.expensesID}')">削除</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
						<!-- 合计行 -->
						<tr>
							<td colspan="3"></td>
							<td>
								<strong>
									合計: <fmt:formatNumber value="${totalCost}" pattern="#,###"/> 円
								</strong>
							</td>
							<td colspan="9"></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="13">データがありません。</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

	<!-- 経費種別マスターデータをJavaScriptで使用できるように設定 -->
	<script>
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

	<!-- エラーメッセージ表示処理 -->
	function showError(msg) {
	    document.getElementById("errorBox").innerText = msg;
	}

	<!-- 検索期間の入力チェック処理 -->
	function validateSearchPeriod() {
		const errorBox = document.getElementById("errorBox");
		errorBox.innerText = "";
	    const fromYear = document.getElementById("fromYear").value;
	    const fromMonth = document.getElementById("fromMonth").value;
	    const toYear = document.getElementById("toYear").value;
	    const toMonth = document.getElementById("toMonth").value;

	    const fromFilled = fromYear !== "" || fromMonth !== "";
	    const toFilled = toYear !== "" || toMonth !== "";

	    if ((fromYear === "" && fromMonth !== "") || (fromYear !== "" && fromMonth === "")) {
	    	showError("Fromは年・月をセットで選択してください。");
	        return false;
	    }

	    if ((toYear === "" && toMonth !== "") || (toYear !== "" && toMonth === "")) {
	    	showError("Toは年・月をセットで選択してください。");
	        return false;
	    }

	    // From <= To
	    if (fromFilled && toFilled) {
	        const fromVal = parseInt(fromYear) * 100 + parseInt(fromMonth);
	        const toVal = parseInt(toYear) * 100 + parseInt(toMonth);
	        if (fromVal > toVal) {
	        	showError("FromはTo以前を選択してください。");
	            return false;
	        }
	    }

	    return true;
	}

	/**
	* 経費種別が変更されたときに経費名称のオプションを更新する
	*
	* @param {string} selectId - 経費種別セレクトボックスのID
	* @param {string} targetId - 経費名称セレクトボックスのID
	* @param {number} selectedValue - 選択する経費名称のID（任意）
	*/
	function updateExpenseNameOptions(selectId, targetId, selectedValue) {
	    const typeSelect = document.getElementById(selectId);
	    const nameSelect = document.getElementById(targetId);

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
	            if (selectedValue && item.id == selectedValue) {
	                option.selected = true;
	            }
	            nameSelect.appendChild(option);
	        });
	    }
	}

	/**
	* 経費データの行内編集処理を行う
	*
	* @param {string} expensesID - 経費ID
	* @details
	* - 画像のプレビュー機能あり
	* - 精算済みデータの編集不可
	* - 入力内容の検証あり
	*/
	function editExpense(expensesID) {
	   console.log("editExpense:", expensesID);
	   const row = document.getElementById("row" + expensesID);
	   if (!row) return;

	   // 各カラムの要素を取得
	   const colExpensesType   = row.querySelector(".col-expensesType");
	   const colExpenseName    = row.querySelector(".col-expenseName");
	   const colAccrualDate    = row.querySelector(".col-accrualDate");
	   const colCost           = row.querySelector(".col-cost");
	   const colHappenAddress  = row.querySelector(".col-happenAddress");
	   const colTantouName     = row.querySelector(".col-tantouName");
	   const colSettlementDate = row.querySelector(".col-settlementDate");
	   const colSettlementType = row.querySelector(".col-settlementType");
	   const colReceipt        = row.querySelector(".col-receipt");
	   const colEdit           = row.querySelector(".col-edit");

	   // 現在の表示値を取得
	   const originalExpTypeCode = colExpensesType.getAttribute("data-type-code");
	   const originalExpenseId = colExpenseName.getAttribute("data-expense-id");
	   const originalAccrualDate= colAccrualDate.textContent.trim();
	   const originalCostTxt    = colCost.textContent.replace("円","").trim();
	   const originalHappenTxt  = colHappenAddress.textContent.trim();
	   const originalTantouTxt  = colTantouName.textContent.trim();
	   const originalSettlDate  = colSettlementDate.textContent.trim();
	   const originalSettlType  = colSettlementType.textContent.trim();

	   // 精算状態の確認
	   if (originalSettlDate && (originalSettlType === '現金' || originalSettlType === '口座')) {
	       alert('精算済みのため編集できません。');
	       return;
	   }

	   // 経費種別の編集フォーム生成
	   let typeOptions = '<select id="editExpensesType_'+expensesID+'" onchange="updateExpenseNameOptions(\'editExpensesType_'+expensesID+'\', \'editExpenseName_'+expensesID+'\')">';
	   for (let typeCode in expenseTypeGroups) {
	       const typeName = expenseTypeGroups[typeCode][0].expensesTypeName;
	       const selected = (typeCode === originalExpTypeCode) ? 'selected' : '';
	       typeOptions += '<option value="'+typeCode+'" '+selected+'>'+typeName+'</option>';
	   }
	   typeOptions += '</select>';
	   colExpensesType.innerHTML = typeOptions;

	   // 経費名称の編集フォーム生成
	   colExpenseName.innerHTML = '<select id="editExpenseName_'+expensesID+'"><option value="">選択してください</option></select>';

	   // 経費名称のオプションを設定
	   setTimeout(function() {
	       updateExpenseNameOptions('editExpensesType_'+expensesID, 'editExpenseName_'+expensesID, originalExpenseId);
	   }, 100);

	   // 発生日付の編集フォーム生成
	   colAccrualDate.innerHTML =
	       '<input type="date" id="editAccrualDate_'+expensesID+'" value="'+formatDateForInput(originalAccrualDate)+'" style="width:90%;">';

	   // 金額の編集フォーム生成
	   colCost.innerHTML =
	       '<input type="text" id="editCost_'+expensesID+'" value="'+originalCostTxt+'"  style="width:60%;">';

	   // 用途の編集フォーム生成
	   colHappenAddress.innerHTML =
	       '<input type="text" id="editHappenAddress_'+expensesID+'" value="'+originalHappenTxt+'" style="width:95%;">';

	   // 担当者の編集フォーム生成
	   colTantouName.innerHTML =
	       '<select id="editTantouName_'+expensesID+'">' +
	       '<option value="">-</option>' +
	       '<c:forEach var="t" items="${tantouList}">' +
	           '<option value="${t}" ' + ('${t}' === originalTantouTxt ? 'selected' : '') + '>' +
	               '${t}' +
	           '</option>' +
	       '</c:forEach>' +
	       '</select>';
	   // 精算日付の編集フォーム生成
	   colSettlementDate.innerHTML =
	       '<input type="date" id="editSettlementDate_'+expensesID+'" value="'+formatDateForInput(originalSettlDate)+'" style="width:90%;">';

	   // 精算種別の編集フォーム生成
	   let selCash="", selAcct="";
	   if (originalSettlType==="現金") selCash="selected";
	   else if (originalSettlType==="口座") selAcct="selected";

	   colSettlementType.innerHTML =
	       '<select id="editSettlementType_'+expensesID+'">' +
	           '<option value=""></option>' +
	           '<option value="0" '+selCash+'>現金</option>' +
	           '<option value="1" '+selAcct+'>口座</option>' +
	       '</select>';

	   // 領収書画像の処理
   let thumbHTML = "";
   let oldThumbSrc = "";
   let currentFileName = "なし";

   // 既存のファイル名を取得
   const receiptText = colReceipt.textContent.trim();
   if (receiptText && receiptText !== "なし") {
       currentFileName = receiptText.replace("ダウンロード", "").trim();
   }

//    const imgTag = colReceipt.querySelector("img.thumb-img");
//    if (imgTag) {
//        oldThumbSrc = imgTag.getAttribute("src");
//        thumbHTML =
//          '<img id="previewImg_'+expensesID+'" class="thumb-img" src="'+oldThumbSrc+'" alt="receipt">';
//    } else {
//        thumbHTML =
//          '<img id="previewImg_'+expensesID+'" class="thumb-img" src="" style="display:none;" alt="receipt">';
//    }

   // 領収書更新フォームの生成
   colReceipt.innerHTML =
      thumbHTML+
      '<div id="fileName_'+expensesID+'" style="margin-top:5px;">'+currentFileName+'</div>'+
      '<br/><button type="button" onclick="triggerFileSelect(\''+expensesID+'\')">更新</button>'+
      '<input type="file" id="editReceiptFile_'+expensesID+'" class="hidden-file-input" accept=".jpg,.jpeg,.png,.pdf" onchange="onFileChanged(\''+expensesID+'\')" />';

	   // 操作ボタンの生成
	   colEdit.innerHTML =
	       '<button type="button" onclick="confirmEdit(\''+expensesID+'\')">確定</button>'+
	       '<button type="button" onclick="cancelEdit(\''+expensesID+'\')">キャンセル</button>';
	}

	/**
	* 日付文字列をHTML5の日付入力形式（yyyy-MM-dd）に変換する
	*
	* @param {string} dateStr - 変換対象の日付文字列
	* @returns {string} 変換後の日付文字列（yyyy-MM-dd形式）
	*/
	function formatDateForInput(dateStr) {
	   if (!dateStr) return "";
	   let val = dateStr.trim();

	   // 時刻部分を削除
	   const spaceIndex = val.indexOf(" ");
	   if (spaceIndex>0) {
	       val = val.substring(0,spaceIndex);
	   }

	   // 区切り文字を'-'に統一
	   val = val.replace(/\//g, "-");

	   // yyyy-MM-dd形式で先頭10文字を取得
	   if (val.length>=10) {
	       val = val.substring(0,10);
	   }

	   return val;
	}

	/**
	* ファイル選択ダイアログを表示する
	*
	* @param {string} expensesID - 経費ID
	* @details 非表示のファイル入力欄をクリックして表示
	*/
	function triggerFileSelect(expensesID) {
	   document.getElementById("editReceiptFile_"+expensesID).click();
	}

	/**
	* 選択されたファイルの処理とプレビュー表示を行う
	*
	* @param {string} expensesID - 経費ID
	* @details
	* - 画像ファイルの場合はプレビュー表示
	* - PDF等その他のファイルの場合はプレビューを非表示
	*/
	function onFileChanged(expensesID) {
   const fileInput = document.getElementById("editReceiptFile_"+expensesID);
   const previewImg= document.getElementById("previewImg_"+expensesID);
   const fileNameDiv = document.getElementById("fileName_"+expensesID);
   if (!fileInput || !fileInput.files || fileInput.files.length===0) return;

   const file = fileInput.files[0];
   const mime = file.type.toLowerCase();

   // ファイル名を表示
   if (fileNameDiv) {
       fileNameDiv.textContent = file.name;
   }

   if (mime.includes("image/")) {
       // 画像ファイルのプレビュー表示
       const reader = new FileReader();
       reader.onload = e => {
           previewImg.src = e.target.result;
           previewImg.style.display = "block";
       };
       reader.readAsDataURL(file);
   } else {
       // 画像以外のファイルはプレビューを非表示
       previewImg.src="";
       previewImg.style.display="none";
   }
}

	/**
	* 編集をキャンセルし、画面を再読み込みする
	*
	* @param {string} expensesID - 経費ID
	*/
	function cancelEdit(expensesID) {
	   window.location.reload();
	}

	/**
	* 経費データの更新処理を実行する
	*
	* @param {string} expensesID - 経費ID
	* @details
	* - 必須入力項目の検証
	* - 画像ファイルのアップロード処理
	* - サーバーへの非同期通信による更新
	*/
	function confirmEdit(expensesID) {
	   if (!confirm("更新しますか？")) return;

	   // フォーム値の取得
	   const expensesType = document.getElementById("editExpensesType_"+expensesID).value;
	   const mexpensesId = document.getElementById("editExpenseName_"+expensesID).value;
	   const accrualDate = document.getElementById("editAccrualDate_"+expensesID).value;
	   const cost = document.getElementById("editCost_"+expensesID).value.replace(/,/g, "").trim();
	   const happenAddress= document.getElementById("editHappenAddress_"+expensesID).value;
	   const tantouName = document.getElementById("editTantouName_"+expensesID).value;
	   const settlementDate= document.getElementById("editSettlementDate_"+expensesID).value;
	   const settlementType= document.getElementById("editSettlementType_"+expensesID).value;

	   // 必須項目の入力チェック
	   if (!mexpensesId || !accrualDate || !cost || !happenAddress || !tantouName) {
	       alert("経費名称、発生日付、金額、用途、担当者は必須です。");
	       return;
	   }

	   // 送信用データの作成
	   const formData = new FormData();

	   // 経費データをJSONとして設定
	   const expenseObj = {
	       expensesID,
	       expensesType,
	       mexpensesId,
	       accrualDate,
	       cost,
	       happenAddress,
	       tantouName,
	       settlementDate,
	       settlementType
	   };
	   formData.append("expenseData", JSON.stringify(expenseObj));

	   // 領収書ファイルの追加
	   const fileInput = document.getElementById("editReceiptFile_"+expensesID);
	   if (fileInput && fileInput.files.length>0) {
	       formData.append("receiptFile", fileInput.files[0]);
	   }

	   // サーバーへのデータ送信
	   fetch("${pageContext.request.contextPath}/expenseList/update", {
	       method: "POST",
	       body: formData
	   })
	   .then(resp => {
	       if (!resp.ok) {
	           return resp.text().then(t => { throw new Error(t); });
	       }
	       return resp.json();
	   })
	   .then(data => {
	       if (data.status==="ok") {
	           alert(data.message || "更新成功");
	           window.location.reload();
	       } else {
	           throw new Error(data.message || "更新失敗");
	       }
	   })
	   .catch(err => {
	       console.error(err);
	       alert("更新失敗: "+err.message);
	   });
	}

	/**
	* 領収書ファイルのダウンロード処理を実行する
	*
	* @param {string} expensesID - 経費ID
	* @details IDの存在チェック後、ダウンロードを実行
	*/
	function downloadReceipt(expensesID) {
	   if (!expensesID) {
	       alert("ダウンロード失敗：IDが見つかりません");
	       return;
	   }
	   window.location.href = '${pageContext.request.contextPath}/expenseList/downloadReceipt/' + expensesID;
	}

	/**
	* 経費データの削除処理を実行する
	*
	* @param {string} expensesID - 経費ID
	* @details
	* - 精算済みデータは削除不可
	* - 削除前に確認ダイアログを表示
	* - 削除後は一覧画面を再表示
	*/
	function deleteExpense(expensesID) {
	   const row = document.getElementById("row" + expensesID);
	   if (!row) return;

	   // 精算状態の確認
	   const settlementDate = row.querySelector(".col-settlementDate").textContent.trim();
	   const settlementType = row.querySelector(".col-settlementType").textContent.trim();

	   // 精算済みの場合は削除不可
	   if (settlementDate && (settlementType === '現金' || settlementType === '口座')) {
	       alert('精算済みのため削除できません。');
	       return;
	   }

	   // 削除確認と実行
	   if (confirm("削除しますか？")) {
	       location.href = "${pageContext.request.contextPath}/expenseList/delete/" + expensesID;
	   }
	}

	/**
	* アップロードファイルのサイズ検証を行う
	*
	* @param {HTMLInputElement} input - ファイル入力要素
	* @param {number} maxMB - 許容最大サイズ（MB単位）
	* @details
	* - ファイルサイズが制限を超える場合は警告を表示
	* - 入力値をクリアして再選択を促す
	*/
	function validateFileSize(input, maxMB) {
	   if (input.files && input.files[0]) {
	       // ファイルサイズをMB単位で計算
	       const size = input.files[0].size / 1024 / 1024;

	       // サイズ制限チェック
	       if (size > maxMB) {
	           alert("ファイルサイズは"+maxMB+"MB以下にしてください。");
	           input.value="";  // 入力値をクリア
	       }
	   }
	}

	function downloadCurrentFilter() {
		const rows = document.querySelectorAll("#expenseTable tbody tr");

		if (rows.length === 1 && rows[0].innerText.includes("データがありません")) {
	        alert("該当データがありません。");
	        return;
	    }
		// 現在表示されている年月を取得（フォームから）
	    const params = new URLSearchParams({
	    	fromYear: '${currentFromYear != null ? currentFromYear : ""}',
	        fromMonth: '${currentFromMonth != null ? currentFromMonth : ""}',
	        toYear: '${currentToYear != null ? currentToYear : ""}',
	        toMonth: '${currentToMonth != null ? currentToMonth : ""}',

	        tantouName: '${currentTantouName != null ? currentTantouName : ""}',
	        expensesType: '${currentExpensesType != null ? currentExpensesType : ""}',
	        settlementStatus: '${currentSettlementStatus != null ? currentSettlementStatus : ""}'
	    });

	 	// ダウンロード実行
	    location.href = `${pageContext.request.contextPath}/expenseList/downloadZip?` + params.toString();
	}

    </script>
</body>
</html>