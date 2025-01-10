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
                <label for="year">年度:</label> 
                <select name="year" id="year">
                    <c:forEach var="yearItem" items="${yearList}">
                        <option value="${yearItem}"
                            <c:if test="${yearItem eq currentYear}">selected</c:if>>
                            ${yearItem}年</option>
                    </c:forEach>
                </select> 
                <label for="month">月度:</label> 
                <select name="month" id="month">
                    <c:forEach var="monthItem" items="${monthList}">
                        <option value="${monthItem}"
                            <c:if test="${monthItem eq currentMonth}">selected</c:if>>
                            ${monthItem}月</option>
                    </c:forEach>
                </select>

                <button type="submit">検索</button>

                <!-- 新規ボタン -->
                <button type="button"
                    onclick="location.href='${pageContext.request.contextPath}/expenseInfo'">
                    新規</button>

            </div>
        </form>

        <!-- データテーブル -->
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
                            <tr id="row_${expense.expensesID}">
                                <td class="col-expensesType">
                                    <c:choose>
                                        <c:when test="${expense.expensesType eq '1'}">一般経費</c:when>
                                        <c:when test="${expense.expensesType eq '2'}">固定経費</c:when>
                                        <c:otherwise>その他</c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="col-accrualDate"><c:out
                                        value="${expense.accrualDate}" /></td>
                                <td class="col-cost"><c:out value="${expense.cost}" />円</td>
                                <td class="col-happenAddress"><c:out
                                        value="${expense.happenAddress}" /></td>
                                <td class="col-tantouName"><c:out
                                        value="${expense.tantouName}" /></td>
                                <td class="col-settlementDate"><c:out
                                        value="${expense.settlementDate}" /></td>
                                <td class="col-settlementType">
                                    <c:choose>
                                        <c:when test="${expense.settlementType eq '0'}">現金</c:when>
                                        <c:when test="${expense.settlementType eq '1'}">口座</c:when>
                                        <c:otherwise>その他</c:otherwise>
                                    </c:choose>
                                </td>
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
                        <!-- 合計行 -->
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

        <!--         <div class="button-container">
            <button type="button" id="finalizeBtn" onclick="history.back()">戻る</button>
        </div> -->
    </div>

    <!-- JavaScript スクリプト -->
    <script>
        // 行内編集機能
        function editExpense(expensesID) {
            const row = document.getElementById("row_" + expensesID);
            if (!row) return;

            const colExpensesType    = row.querySelector(".col-expensesType");
            const colAccrualDate     = row.querySelector(".col-accrualDate");
            const colCost            = row.querySelector(".col-cost");
            const colHappenAddress   = row.querySelector(".col-happenAddress");
            const colTantouName      = row.querySelector(".col-tantouName");
            const colSettlementDate  = row.querySelector(".col-settlementDate");
            const colSettlementType  = row.querySelector(".col-settlementType");
            const colEdit            = row.querySelector(".col-edit");

            // 一時保存
            row.dataset.originalExpensesType   = colExpensesType.textContent.trim();
            row.dataset.originalAccrualDate    = colAccrualDate.textContent.trim();
            row.dataset.originalCost           = colCost.textContent.replace("円","").trim();
            row.dataset.originalHappenAddress  = colHappenAddress.textContent.trim();
            row.dataset.originalTantouName     = colTantouName.textContent.trim();
            row.dataset.originalSettlementDate = colSettlementDate.textContent.trim();
            row.dataset.originalSettlementType = colSettlementType.textContent.trim();

            // 経費種別 ドロップダウン
            let selected1 = (row.dataset.originalExpensesType === "一般経費") ? "selected" : "";
            let selected2 = (row.dataset.originalExpensesType === "固定経費") ? "selected" : "";
            let selected99 = (row.dataset.originalExpensesType !== "一般経費" && row.dataset.originalExpensesType !== "固定経費") ? "selected" : "";

            colExpensesType.innerHTML = 
                '<select id="editExpensesType_' + expensesID + '">' +
                    '<option value="1" ' + selected1 + '>一般経費</option>' +
                    '<option value="2" ' + selected2 + '>固定経費</option>' +
                    '<option value="99" ' + selected99 + '>その他</option>' +
                '</select>';

            // 発生日付 => <input type="date">
            colAccrualDate.innerHTML = 
                '<input type="date" id="editAccrualDate_' + expensesID + '" value="' + row.dataset.originalAccrualDate + '" />';

            // 金額
            colCost.innerHTML = 
                '<input type="number" step="0.01" id="editCost_' + expensesID + '" value="' + row.dataset.originalCost + '" />円';

            // 用途
            colHappenAddress.innerHTML = 
                '<input type="text" id="editHappenAddress_' + expensesID + '" value="' + row.dataset.originalHappenAddress + '" style="width:100%" />';

            // 担当者
            colTantouName.innerHTML = 
                '<input type="text" id="editTantouName_' + expensesID + '" value="' + row.dataset.originalTantouName + '" />';

            // 精算日付
            colSettlementDate.innerHTML = 
                '<input type="date" id="editSettlementDate_' + expensesID + '" value="' + row.dataset.originalSettlementDate + '" />';

            // 精算種別 ドロップダウン
            let selectedCash = (row.dataset.originalSettlementType === "現金") ? "selected" : "";
            let selectedAccount = (row.dataset.originalSettlementType === "口座") ? "selected" : "";
            let selectedOther = (row.dataset.originalSettlementType !== "現金" && row.dataset.originalSettlementType !== "口座") ? "selected" : "";

            colSettlementType.innerHTML = 
                '<select id="editSettlementType_' + expensesID + '">' +
                    '<option value="0" ' + selectedCash + '>現金</option>' +
                    '<option value="1" ' + selectedAccount + '>口座</option>' +
                    '<option value="99" ' + selectedOther + '>その他</option>' +
                '</select>';

            // 編集ボタン -> 「確定」「取消」
            colEdit.innerHTML = 
                '<button type="button" onclick="confirmEdit(\'' + expensesID + '\')">確定</button>' +
                '<button type="button" onclick="cancelEdit(\'' + expensesID + '\')">取消</button>';
        }

        // 編集のキャンセル機能
        function cancelEdit(expensesID) {
            const row = document.getElementById("row_" + expensesID);
            if (!row) return;

            // 元に戻す
            row.querySelector(".col-expensesType").innerHTML = row.dataset.originalExpensesType;
            row.querySelector(".col-accrualDate").innerHTML = row.dataset.originalAccrualDate + ""; // 直接設定
            row.querySelector(".col-cost").innerHTML = row.dataset.originalCost + "円";
            row.querySelector(".col-happenAddress").innerHTML = row.dataset.originalHappenAddress;
            row.querySelector(".col-tantouName").innerHTML = row.dataset.originalTantouName;
            row.querySelector(".col-settlementDate").innerHTML = row.dataset.originalSettlementDate + ""; // 直接設定
            row.querySelector(".col-settlementType").innerHTML = row.dataset.originalSettlementType;

            // 編集ボタンを復元する
            row.querySelector(".col-edit").innerHTML = 
                '<button type="button" onclick="editExpense(\'' + expensesID + '\')">編集</button>';
        }

        // 編集を確定し、AJAXで更新を送信
        function confirmEdit(expensesID) {
            if (!confirm("更新しますか？")) {
                return; // ユーザーがキャンセルした場合、実行を停止
            }

            const row = document.getElementById("row_" + expensesID);
            if (!row) return;

            const newExpensesType   = document.getElementById(`editExpensesType_${expensesID}`).value;
            const newAccrualDate    = document.getElementById(`editAccrualDate_${expensesID}`).value;
            const newCost           = document.getElementById(`editCost_${expensesID}`).value;
            const newHappenAddress  = document.getElementById(`editHappenAddress_${expensesID}`).value;
            const newTantouName     = document.getElementById(`editTantouName_${expensesID}`).value;
            const newSettlementDate = document.getElementById(`editSettlementDate_${expensesID}`).value;
            const newSettlementType = document.getElementById(`editSettlementType_${expensesID}`).value;

            // 入力データが有効かどうかを検証
            if (!newAccrualDate || !newSettlementDate) {
                alert("発生日付 と 精算日付 は必須です。");
                return;
            }

            // 送信するデータを構築する
            const params = {
                expensesID: expensesID,
                expensesType: newExpensesType,
                accrualDate: newAccrualDate,
                cost: newCost,
                happenAddress: newHappenAddress,
                tantouName: newTantouName,
                settlementDate: newSettlementDate,
                settlementType: newSettlementType
            };

            // デバッグのためにパラメータを出力する
            console.log("Updating expense:", params);

            // fetchを使用してデータをバックエンド /expenseList/update に送信
            fetch('${pageContext.request.contextPath}/expenseList/update', {
                method: 'POST',
                headers: { 
                    "Content-Type": "application/json" 
                },
                body: JSON.stringify(params)
            })
            .then(res => {
                if (!res.ok) {
                    return res.text().then(text => { throw new Error(text) });
                }
                return res.text(); 
            })
            .then(data => {
                // バックエンドからのレスポンスを表示
                alert(data || "更新成功"); 

                // テーブルの内容を更新する
                row.querySelector(".col-expensesType").textContent  = mapExpensesType(newExpensesType);
                row.querySelector(".col-accrualDate").textContent   = newAccrualDate;
                row.querySelector(".col-cost").textContent          = newCost + "円";
                row.querySelector(".col-happenAddress").textContent = newHappenAddress;
                row.querySelector(".col-tantouName").textContent    = newTantouName;
                row.querySelector(".col-settlementDate").textContent= newSettlementDate;
                row.querySelector(".col-settlementType").textContent= mapSettlementType(newSettlementType);

                // 編集ボタンを復元する
                row.querySelector(".col-edit").innerHTML = `
                    <button type="button" onclick="editExpense('${expensesID}')">編集</button>
                `;
            })
            .catch(err => {
                console.error("Fetch error:", err);
                alert("更新失敗: " + err);
            });
        }

        // 数値を経費種別テキストにマッピング
        function mapExpensesType(val) {
            switch(val) {
                case "1":
                    return "一般経費";
                case "2":
                    return "固定経費";
                case "99":
                    return "その他";
                default:
                    return "その他";
            }
        }

        // 数値を精算種別テキストにマッピング
        function mapSettlementType(val) {
            switch(val) {
                case "0":
                    return "現金";
                case "1":
                    return "口座";
                case "99":
                    return "その他";
                default:
                    return "その他";
            }
        }

        // 削除機能
        function deleteExpense(expensesID) {
            if (confirm("削除しますか？")) {
                location.href = '${pageContext.request.contextPath}/expenseList/delete/' + expensesID;
            }
        }
    </script>
</body>
</html>
