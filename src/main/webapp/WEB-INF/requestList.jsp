<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ソフトテク株式会社-社内管理システム</title>

<script type="text/javascript">
        //dom
        document.addEventListener('DOMContentLoaded', function() {
            var input = document.getElementById('claimMonth');
            var btnSearch = document.getElementById('btnSearch');
            var btnGenerate = document.getElementById('btnGenerate');
            var btnDownload = document.getElementById('btnDownload');
            var btnConfirm = document.getElementById('btnConfirm');

            //初期化ボタン状態
            btnSearch.disabled = true;
            btnGenerate.disabled = true;
            btnDownload.disabled = true;
            btnConfirm.disabled = false;


            //入力するボタン状態
            input.addEventListener('input', checkInputAndDisableButtons);
            function checkInputAndDisableButtons() {
                if (input.value.trim() !== '') {
                    btnSearch.disabled = true;
                    btnGenerate.disabled = true;
                    btnDownload.disabled = true;
                    btnConfirm.disabled = false;
                } else {
                    btnSearch.disabled = true;
                    btnGenerate.disabled = true;
                    btnDownload.disabled = true;
                    btnConfirm.disabled = false;
                }
            }

            //状況確認ボタンを押す及びその他のボタン状態
            btnConfirm.addEventListener('click', function() {
                var claimMonth = input.value.trim();
                if (claimMonth && validateClaimMonth()) {
                    checkDatabase(claimMonth);
                }
            });

            function validateClaimMonth() {
        	    var input = document.getElementById("claimMonth");
        	    var inputValue = input.value.trim();
        	    var currentYear = new Date().getFullYear();

        	    // Check if the input length is 6 and if it is a number
        	    if (inputValue.length !== 6 || isNaN(inputValue)) {
        	        alert("請求月は6桁の数字で入力してください。");
        	        input.value = ""; // Clear the input field
        	        input.focus();
        	        return false;
        	    }

        	    // Extract year and month from the input
        	    var year = parseInt(inputValue.substring(0, 4));
        	    var month = parseInt(inputValue.substring(4, 6));

        	    // Check if the year is greater than the current year
        	    if (year > currentYear) {
        	        alert("年は今年以下にしてください。");
        	        input.value = ""; // Clear the input field
        	        input.focus();
        	        return false;
        	    }

        	    // Check if the month is between 01 and 12
        	    if (month < 1 || month > 12) {
        	        alert("月は01から12までの間で入力してください。");
        	        input.value = ""; // Clear the input field
        	        input.focus();
        	        return false;
        	    }

        	    return true;
        	}

            function checkDatabase(claimMonth) {
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'checkDatabase', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        console.log(xhr.responseText);
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            handleDatabaseResponse(response);
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                var formData = 'claimMonth=' + encodeURIComponent(claimMonth);
                xhr.send(formData);
            }

            //検索ボタンを押す及びその他のボタン状態
            btnSearch.addEventListener('click', function() {
                var claimMonth = input.value.trim();
                if (claimMonth) {
                    searchDatabase(claimMonth);
                }
            });
            function searchDatabase(claimMonth) {
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'searchDatabase', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        console.log(xhr.responseText);
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            handleSearchResponse(response);
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                var formData = 'claimMonth=' + encodeURIComponent(claimMonth);
                xhr.send(formData);
            }

            //請求書生成ボタンを押す及びその他のボタン状態
            btnGenerate.addEventListener('click', function() {
                var claimMonth = input.value.trim();
                if (claimMonth) {
                    // ▼▼▼ 修正：直接 generateInvoiceFileAjax を呼び出してDB更新＆ファイル生成 ▼▼▼
                    generateInvoiceFileAjax(claimMonth); // （DBインサート/アップデート実行後にExcel/PDFを生成する）
                }
            });

            //ダウンロードボタンを押す及びその他のボタン状態
            btnDownload.addEventListener('click', function() {
                var claimMonth = input.value.trim();
                if (!claimMonth) {
                    alert("請求月を入力してください。");
                    return;
                }
                window.location.href = "downloadInvoiceByMonth?claimMonth=" + encodeURIComponent(claimMonth);
            });

            // ▼▼▼ 修正：新規追加。サーバー側でExcel/PDF生成のAjax ▼▼▼
            function generateInvoiceFileAjax(claimMonth) {
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'generateInvoiceFile', true);
                xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        console.log(xhr.responseText);
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            if (response.error) {
                                alert('生成エラー: ' + response.error);
                            } else if (response.updateMsg) {
                                console.log('Generate success:', response.updateMsg);
                                var messageDiv = document.getElementById('messageDiv');
                                messageDiv.innerHTML = "請求書を生成完了：" + claimMonth;
                            }
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                var formData = JSON.stringify({ claimMonth: claimMonth });
                xhr.send(formData);
            }
            // ▲▲▲ 修正ここまで ▲▲▲

            function handleSearchResponse(response) {
                var tableBody = document.getElementById('resultTableBody');
                tableBody.innerHTML = '';

                if (response.list && response.list.length > 0) {
                    response.list.forEach(function(item) {
                        var row = '<tr>';
                        row += '<td>' + item.employeeName + '</td>';
                        row += '<td>' + item.companyName + '</td>';
                        row += '<td>' + item.contractID + '</td>';
                        row += '<td>' + item.contractTime + '</td>';
                        row += '<td>' + item.price + '</td>';
                        row += '<td>' + item.upperPrice + '</td>';
                        row += '<td>' + item.lowerPrice + '</td>';
                        row += '<td>' + item.workTime + '</td>';
                        row += '<td>' + item.upperTime + '</td>';
                        row += '<td>' + item.lowerTime + '</td>';
                        row += '<td>' + item.upperTotal + '</td>';
                        row += '<td>' + item.lowerTotal + '</td>';
                        row += '<td>' + item.transport + '</td>';
                        row += '<td>' + item.businessTrip + '</td>';
                        row += '<td>' + item.specialClaim + '</td>';
                        row += '<td>' + item.claimPrice + '</td>';
                        row += '<td>' + item.sum + '</td>';
                        row += '</tr>';
                        tableBody.innerHTML += row;
                    });
                } else {
                    alert('No data found for the selected claim month.');
                }
            }

            function handleDatabaseResponse(response) {
                console.log('handleDatabaseResponse called with response: ', response);

                var messageDiv = document.getElementById('messageDiv');
                messageDiv.innerHTML = '';
                var message = '';
                if (response.isDataEmpty) {
                    message = '稼働時間がないです。<br>社員氏名:<ul>';
                    response.employeeNames.forEach(function(name) {
                        message += '<li>' + name + '</li>';
                    });
                    message += '</ul>';
                    messageDiv.innerHTML = message;
                    btnSearch.disabled = true;
                    btnGenerate.disabled = true;
                    btnDownload.disabled = true;
                    btnConfirm.disabled = !response.isDataEmpty;
                } else {
                    message = '';
                    if (response.isClaimEmpty) {
                        message += '稼働時間データがあります。<br> 請求データが空です。請求書を生成してください。';
                    } else {
                        message += '請求データはあります';
                    }
                    messageDiv.innerHTML = message;
                    btnSearch.disabled = response.isClaimEmpty;
                    btnDownload.disabled = response.isClaimEmpty;
                    btnGenerate.disabled = !response.isClaimEmpty;
                    btnConfirm.disabled = true;
                }
            }
        });
    </script>

</head>
<body>
    <h2>自動請求</h2>
    <div style="color: red;">
        <p>${updateMsg}</p>
    </div>
    <form:form name="theForm" id="theForm" method="post" modelAttribute="requestFromBean" action="requestSeikyu">
        <div>
            <b>請求月:</b>
            <input type="text" id="claimMonth" name="claimMonth" placeholder="請求月 (例: 202401)" maxlength="6" value="${month}" />
            <button type="button" id="btnConfirm">状況確認</button>
            <button type="button" id="btnSearch">検索</button>
            <button type="button" id="btnGenerate">請求書生成</button>
            <button type="button" id="btnDownload">請求書ダウンロード</button>
        </div>
        <!-- <input type="hidden" id="contractID" name="contractID" value=""/> -->
        <input type="hidden" id="contractID" name="contractID" value="" />
        <input type="hidden" id="businessTrip" name="businessTrip" value="" />
        <input type="hidden" id="sum" name="sum" value="" />
        <div id="messageDiv" style="color: red;"></div>

        <!--エラーメッセージ-->
        <p style="color: red;">
            <c:forEach items="${errors}" var="error">
                <spring:message message="${error}" />
            </c:forEach>
        </p>
        <table border="1" class="claimlist-table">
            <thead>
                <tr>
                    <th width="80">社員氏名</th>
                    <th width="80">客先</th>
                    <th width="80">契約ID</th>
                    <th width="80">契約期間</th>
                    <th width="80">標準単価</th>
                    <th width="80">残業単価</th>
                    <th width="80">控除単価</th>
                    <th width="80">稼働時間</th>
                    <th width="80">超過時間</th>
                    <th width="80">不足時間</th>
                    <th width="80">残業額</th>
                    <th width="80">控除額</th>
                    <th width="80">交通費</th>
                    <th width="80">出張旅費</th>
                    <th width="80">特別請求</th>
                    <th width="80">税抜総額</th>
                    <th width="80">請求税込額</th>
                </tr>
            </thead>
            <tbody id="resultTableBody">
                <!-- 动态内容将在这里被添加 -->
            </tbody>
        </table>
    </form:form>

</body>
</html>
