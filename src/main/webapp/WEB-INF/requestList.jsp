<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title> ソフトテク株式会社-社内管理システム </title>
    
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
                if (claimMonth) {
                    checkDatabase(claimMonth);
                }
            });
            function checkDatabase(claimMonth) {
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'checkDatabase', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        console.log(xhr.responseText); // 调试输出
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            handleDatabaseResponse(response);   //处理搜索结果的回调函数
                            alert('Error: ' + xhr.responseText);
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                // 送信するデータを構築する
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
                        console.log(xhr.responseText); // 调试输出
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            handleSearchResponse(response);  // 处理搜索结果的回调函数
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                // 送信するデータを構築する
                var formData = 'claimMonth=' + encodeURIComponent(claimMonth);
                xhr.send(formData);
            }

            //生成ボタンを押す及びその他のボタン状態
            btnGenerate.addEventListener('click', function() {  
                var claimMonth = input.value.trim();
                if (claimMonth) {
                    claimDatabase(claimMonth);
                }
            });
            function claimDatabase(claimMonth) {  
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'claimDatabase', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        console.log(xhr.responseText); // 调试输出
                        if (xhr.status === 200) {
                            var response = JSON.parse(xhr.responseText);
                            handleClaimResponse(response);  // 处理搜索结果的回调函数
                        } else {
                            alert('Error: ' + xhr.statusText);
                        }
                    }
                };
                // 送信するデータを構築する
                var formData = 'claimMonth=' + encodeURIComponent(claimMonth);
                xhr.send(formData); 
            }

            //ダウンロードボタンを押す及びその他のボタン状態
            btnDownload.addEventListener('click', function() {
                var claimMonth = document.getElementById('claimMonth').value.trim();
                if (claimMonth) {
                    downloadInvoice(claimMonth);
                }
            });
            function downloadInvoice(claimMonth) {
                
                document.theForm.action="requestSeikyu";
                alert("btnDownload clicked!");
                document.theForm.submit();
            }
            
        
            function downloadInvoice() {  
            var claimMonth = input.value.trim();
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'downloadInvoice', true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    console.log(xhr.responseText); // 调试输出
                    if (xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        handleDownloadResponse(response);  // 处理搜索结果的回调函数
                    } else {
                        alert('Error: ' + xhr.statusText);
                    }
                }
            };
            var formData = JSON.stringify({ claimMonth: claimMonth });
            xhr.send(formData); 
        }

        function handleDownloadResponse(response) {
            if (response.error) {
                alert('Error: ' + response.error);
                console.error('Server returned error:', response.error);
            } else if (response.updateMsg) {
                console.log('Download success:', response.updateMsg);
                var messageDiv = document.getElementById('messageDiv');
                messageDiv.innerHTML = ''; // メッセージをクリアする
                messageDiv.innerHTML = response.updateMsg;
            } else {
                console.log('Success: ', response);
            }
        }

            // 处理更新响应的函数
            function handleClaimResponse(response) {
                // 处理服务器响应的逻辑
                if (response.error) {
                    // 显示错误信息
                    alert('Error: ' + response.error);
                    console.error('Server returned error:', response.error);
                } else if (response.updateMsg) {
                    // 处理更新成功的情况
                    console.log('Update success:', response.updateMsg);
                    var messageDiv = document.getElementById('messageDiv');
                    messageDiv.innerHTML = ''; // メッセージをクリアする
                    messageDiv.innerHTML = response.updateMsg;
                    btnSearch.disabled = !response.updateMsg;
                    btnGenerate.disabled = response.updateMsg;
                    btnDownload.disabled = !response.updateMsg;
                    btnConfirm.disabled = response.updateMsg;
                    // alert('Update success: ' + response.updateMsg);
                    // 可以根据更新成功后的需求执行其他操作，如刷新页面或重新加载数据
                } else {
                    // 处理其他情况
                    console.log('Success: ', response);
                    // 根据响应内容进行其他处理
                }
            }

            // 处理搜索结果的回调函数示例
            function handleSearchResponse(response) {
                var tableBody = document.getElementById('resultTableBody');
                    tableBody.innerHTML = ''; // 清空表格内容
            
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

            //处理状況確認结果的回调函数示例
            function handleDatabaseResponse(response) {
                console.log('handleDatabaseResponse called with response: ', response);  // 调试输出

                var messageDiv = document.getElementById('messageDiv');
                messageDiv.innerHTML = ''; // メッセージをクリアする

                if (response.isDataEmpty) {
                    var message = '稼働時間がないです。<br>社員氏名:<ul>';
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
                    var message = '稼働時間データがあります。<br>';
                    //
                    if (response.isClaimEmpty) {
                        message += '請求データが空です。請求書を生成してください。';
                    } else {
                        message += '請求データはあります。請求書を生成しないでください。';
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
        <input type="text" id="claimMonth" name="claimMonth" placeholder="請求月 (例: 202401)" value="${month}" />
        <button type="button" id="btnConfirm" >状況確認</button>
        <button type="button" id="btnSearch" >検索</button>
        <button type="button" id="btnGenerate" >請求書生成</button>
        <button type="button" id="btnDownload" >請求書ダウンロード</button>  
    </div>
    <!-- <input type="hidden" id="contractID" name="contractID" value=""/> -->
	<input type="hidden" id="contractID" name="contractID" value=""/>
    <input type="hidden" id="businessTrip" name="businessTrip" value=""/>
	<input type="hidden" id="sum" name="sum" value=""/>
    <div id="messageDiv" style="color: red;"></div>

	<!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
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
