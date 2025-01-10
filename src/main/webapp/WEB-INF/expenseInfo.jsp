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
            width: 90%;
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
        }
        th, td {
            border: 2px solid #b3cbde;
            padding: 15px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
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
        .btn-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="table-container">
        <h1>経費新規追加</h1>

        <!-- エラーメッセージ（オプション） -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <!-- 追加ボタン -->
        <div>
            <button type="button" id="addRowBtn">追加</button>
        </div>

        <!-- 複数入力用のテーブル -->
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
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="expenseTableBody">
                <!-- 動的に行を挿入 -->
            </tbody>
        </table>

        <!-- 下部ボタンエリア： 確定 + 戻る -->
        <div class="btn-container">
            <button type="button" id="confirmBtn">確定</button>
            <button type="button" id="backBtn">戻る</button>
        </div>
    </div>

    <script>
        // 未保存データの有無を示すフラグ
        let unsavedData = false;

        // ページ読み込み時に空行を自動追加
        window.onload = function() {
            addNewRow();
        };

        // 1. “追加”ボタン：行を追加
        document.getElementById("addRowBtn").addEventListener("click", function() {
            addNewRow();
        });

        // 新しい行を追加する関数
        function addNewRow() {
            const tableBody = document.getElementById("expenseTableBody");
            const newRow = document.createElement("tr");

            // 経費種別
            const cell1 = document.createElement("td");
            cell1.innerHTML = `
                <select class="expensesType" required>
                    <option value="">選択</option>
                    <option value="1">一般経費</option>
                    <option value="2">固定経費</option>
                </select>
            `;
            newRow.appendChild(cell1);

            // 発生日付
            const cell2 = document.createElement("td");
            cell2.innerHTML = `<input type="date" class="accrualDate" required />`;
            newRow.appendChild(cell2);

            // 金額
            const cell3 = document.createElement("td");
            cell3.innerHTML = `<input type="number" step="0.01" class="cost" required />円`;
            newRow.appendChild(cell3);

            // 用途
            const cell4 = document.createElement("td");
            cell4.innerHTML = `<input type="text" class="happenAddress" style="width:100%;" required />`;
            newRow.appendChild(cell4);

            // 担当者
            const cell5 = document.createElement("td");
            cell5.innerHTML = `<input type="text" class="tantouName" required />`;
            newRow.appendChild(cell5);

            // 精算日付
            const cell6 = document.createElement("td");
            cell6.innerHTML = `<input type="date" class="settlementDate" required />`;
            newRow.appendChild(cell6);

            // 精算種別
            const cell7 = document.createElement("td");
            cell7.innerHTML = `
                <select class="settlementType" required>
                    <option value="">選択</option>
                    <option value="0">現金</option>
                    <option value="1">口座</option>
                </select>
            `;
            newRow.appendChild(cell7);

            // 操作：削除ボタン
            const cell8 = document.createElement("td");
            cell8.innerHTML = `<button type="button" class="deleteRowBtn">削除</button>`;
            newRow.appendChild(cell8);

            tableBody.appendChild(newRow);

            // 追加操作は未保存データとみなす
            unsavedData = true;
        }

        // 削除ボタンのクリックイベントを委譲
        document.addEventListener("click", function(e) {
            if (e.target && e.target.classList.contains("deleteRowBtn")) {
                const row = e.target.closest("tr");
                if (!row) return;

                // 行にデータが入力されているか確認
                const inputs = row.querySelectorAll("input, select");
                let hasData = false;
                for (let item of inputs) {
                    if (item.value && item.value.trim() !== "") {
                        hasData = true;
                        break;
                    }
                }

                // データが入力されている場合は確認
                if (hasData) {
                    if (!confirm("該当行を削除します。よろしいですか？")) {
                        return; // キャンセルされた場合は削除しない
                    }
                }
                // 確認済みまたはデータなしの場合は行を削除
                row.remove();
                unsavedData = true;
            }
        });

        // “確定”ボタン：確認後、AJAXで保存
        document.getElementById("confirmBtn").addEventListener("click", function() {
            if (!confirm("保存しますか？")) {
                return; // キャンセルされた場合は保存しない
            }

            // データを収集
            const rows = document.querySelectorAll("#expenseTableBody tr");
            if (rows.length === 0) {
                alert("入力データがありません。");
                return;
            }

            let expenseData = [];
            for (let row of rows) {
                const expensesType   = row.querySelector(".expensesType").value;
                const accrualDate    = row.querySelector(".accrualDate").value;
                const cost           = row.querySelector(".cost").value;
                const happenAddress  = row.querySelector(".happenAddress").value;
                const tantouName     = row.querySelector(".tantouName").value;
                const settlementDate = row.querySelector(".settlementDate").value;
                const settlementType = row.querySelector(".settlementType").value;

                // 簡単な必須入力チェック
                if (!expensesType || !accrualDate || !cost || !happenAddress 
                    || !tantouName || !settlementDate || !settlementType) {
                    alert("未入力項目があります。全て入力してください。");
                    return;
                }

                expenseData.push({
                    expensesType,
                    accrualDate,
                    cost,
                    happenAddress,
                    tantouName,
                    settlementDate,
                    settlementType
                });
            }

            // AJAXで送信
            fetch('${pageContext.request.contextPath}/expenseInfo/addMultiple', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(expenseData)
            })
            .then(response => response.json())
            .then(result => {
                if (result.status === "ok") {
                    alert("保存成功");
                    // テーブルをクリアし、空行を追加
                    document.getElementById("expenseTableBody").innerHTML = "";
                    addNewRow();
                    unsavedData = false;
                } else {
                    alert("保存失敗: " + result.message);
                }
            })
            .catch(err => {
                console.error(err);
                alert("保存エラー: " + err);
            });
        });

        // “戻る”ボタン：未保存データがある場合は確認
        document.getElementById("backBtn").addEventListener("click", function() {
            if (unsavedData) {
                if (!confirm("まだ保存していないデータがあります。放棄してよろしいですか？")) {
                    return; // キャンセルされた場合はそのまま
                }
            }
            // 放棄が確認された場合、または未保存データがない場合は移動
            location.href = '${pageContext.request.contextPath}/expenseList';
        });
    </script>
</body>
</html>
