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
    <style>
        .hidden-column {
            display: none;
        }
        .merged-cell {
            vertical-align: top;
            text-align: center;
        }
        .file-list { 
            list-style-type: disc; 
            padding-left: 20px; 
            margin-top: 10px; 
        }
        .file-item { 
            margin-bottom: 5px; 
            word-wrap: break-word; 
        }
        .delete-btn { 
            margin-left: 10px; 
            cursor: pointer; 
            color: red; 
        }
        .message { 
            padding: 10px; 
            margin: 10px 0; 
            border-radius: 4px; 
        }
        .success { 
            background-color: #d4edda; 
            color: #155724; 
            border: 1px solid #c3e6cb; 
        }
        .error { 
            background-color: #f8d7da; 
            color: #721c24; 
            border: 1px solid #f5c6cb; 
        }
        .warning {
            background-color: #fff3cd;
            color: #856404;
            border: 1px solid #ffeaa7;
        }
    </style>
    <script type="text/javascript">
        // 対象年月の入力チェック（YYYYMM形式のみ許可）
        function validateSearch() {
            var paymentMonth = document.getElementById("paymentMonth").value;
            var regex = /^[0-9]{6}$/; // 6桁の数字のみ（YYYYMM）

            if (!regex.test(paymentMonth)) {
                alert("対象年月はYYYYMMの形式で入力してください。");
                return false;
            }

            document.theForm.submit();
        }

        // 更新画面への遷移処理
        function toUpdateJsp(paymentId) {
            document.getElementById('insertFlg').value = '1';
            document.getElementById('no').value = paymentId;
            document.theForm.action = "toInitBpPayment";
            document.theForm.submit();
        }

        // 新規登録画面への遷移処理
        function toMakeJsp() {
            document.getElementById('insertFlg').value = '0';
            document.theForm.action = "toInitBpPayment";
            document.theForm.submit();
        }
        
        // ファイルリスト表示を更新（統一された関数）
        function updateFileList(elementId) {
            const input = document.getElementById('fileUpload_' + elementId);
            const fileList = document.getElementById('fileList_' + elementId);
            if (!input || !fileList) return;
            
            fileList.innerHTML = '';
            Array.from(input.files).forEach((file) => {
                const li = document.createElement('li');
                li.classList.add('file-item');
                li.textContent = file.name;
                fileList.appendChild(li);
            });
        }
        
        // メッセージ表示
        function showMessage(text, type) {
            // 既存のメッセージを削除
            const existingMessages = document.querySelectorAll('.message');
            existingMessages.forEach(msg => msg.remove());
            
            // 新しいメッセージを作成
            const messageDiv = document.createElement('div');
            messageDiv.className = 'message ' + type;
            messageDiv.textContent = text;
            
            // h2タイトルの後に挿入
            const h2Element = document.querySelector('h2');
            h2Element.parentNode.insertBefore(messageDiv, h2Element.nextSibling);
            
            // 5秒後に自動削除
            setTimeout(() => {
                if (messageDiv.parentNode) {
                    messageDiv.remove();
                }
            }, 5000);
        }
        
        // 操作完了後のページリフレッシュ
        function refreshPageAfterSuccess(message) {
            showMessage(message, 'success');
            setTimeout(() => {
                location.reload();
            }, 1500);
        }
        
        // エラーハンドリング
        function handleError(error, operation) {
            showMessage(operation + '失敗: ' + error.message, 'error');
        }
        
        // 請求書ファイルをアップロード
        function submitFileForPayment(paymentId) {
            const input = document.getElementById('fileUpload_' + paymentId);
            if (!input || input.files.length === 0) {
                showMessage('ファイルを選択してください。', 'error');
                return;
            }
            
            const formData = new FormData();
            formData.append('file', input.files[0]);
            formData.append('paymentId', paymentId);
            
            showMessage('ファイルアップロード中、しばらくお待ちください...', 'warning');
            
            fetch('uploadBpInvoice', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('ネットワークエラー: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                if (data.error) {
                    showMessage('ファイルアップロード失敗: ' + data.error, 'error');
                } else {
                    refreshPageAfterSuccess('アップロード成功！1.5秒後ページをリフレッシュします...');
                }
            })
            .catch(error => {
                handleError(error, 'ファイルアップロード');
            });
        }
        
        // 請求書ファイルをダウンロード
        function downloadFile(invoiceId, fileName) {
            if (!fileName) {
                showMessage('ファイルが存在しません。', 'error');
                return;
            }
            window.open('downloadBpInvoice?no=' + encodeURIComponent(invoiceId), '_blank');
        }
        
        // 請求書ファイルを削除
        function deleteFile(invoiceId, fileName) {
            if (!fileName) {
                showMessage('ファイルが存在しません。', 'error');
                return;
            }
            
            if (!confirm('"' + fileName + '"を削除しますか？')) {
                return;
            }
            
            fetch('deleteBpInvoice', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: 'no=' + encodeURIComponent(invoiceId)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('削除失敗: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                if (data.error) {
                    showMessage('削除失敗: ' + data.error, 'error');
                } else {
                    refreshPageAfterSuccess('削除成功！1.5秒後ページをリフレッシュします...');
                }
            })
            .catch(error => {
                handleError(error, 'ファイル削除');
            });
        }
    </script>
</head>
<body>
    <h2>BP支払管理リスト</h2>

    <c:if test="${not empty error}">
        <div class="message error">
            <c:out value="${error}" />
        </div>
    </c:if>

    <!-- 検索フォーム -->
    <form:form name="theForm" id="theForm" method="post" modelAttribute="bpPaymentFormBean" action="bpPaymentList">
        <b>月:</b>
        <form:input path="month" id="paymentMonth" maxlength="6" placeholder="例：202507" />
        <input type="button" name="search" value="検索" onclick="validateSearch();" style="margin-right:10px;" />
        <input type="button" value="新規登録" onclick="toMakeJsp();" style="margin-right:10px;" />
        <input type="button" value="マスタ管理" onclick="window.location.href='bpMasterManagement';" />

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
                            <th width="120">請求書</th>
                            <th width="80">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="bpPayment" varStatus="status">
                            <tr <c:if test="${status.count%2==0}">style="background-color:#bfe1ff"</c:if>
                                <c:if test="${status.count%2!=0}">style="background-color:#dcfeeb"</c:if>>
                                <td>${bpPayment.paymentId}</td>
                                <td>${bpPayment.month}</td>
                                <td>${bpPayment.employeeName}</td>
                                <td>${bpPayment.companyName}</td>
                                <td>${bpPayment.dispatchCompanyName}</td>
                                <td><fmt:formatNumber value="${bpPayment.unitPriceExTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.outsourcingAmountExTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.outsourcingAmountInTax}" pattern="#,##0"/></td>
                                <td><fmt:formatNumber value="${bpPayment.commission}" pattern="#,##0"/></td>
                                <td><fmt:formatDate value="${bpPayment.transferDate}" pattern="yyyy/MM/dd"/></td>
                                <td><fmt:formatDate value="${bpPayment.entryDate}" pattern="yyyy/MM/dd"/></td>
                                <td>${bpPayment.remarks}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty bpPayment.invoiceFileName}">
                                            <span style="color: green;">${bpPayment.invoiceFileName}</span>
                                            <br/>
                                            <button type="button" onclick="downloadFile('${bpPayment.invoiceId}', '${bpPayment.invoiceFileName}')">ダウンロード</button>
                                            <button type="button" class="delete-btn" onclick="deleteFile('${bpPayment.invoiceId}', '${bpPayment.invoiceFileName}')">削除</button>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: gray;">未アップロード</span><br/>
                                            <input type="file" id="fileUpload_${bpPayment.paymentId}" name="file_${bpPayment.paymentId}" accept=".pdf,.jpg,.jpeg,.png,.doc,.docx,.xls,.xlsx" onchange="updateFileList('${bpPayment.paymentId}')"/>
                                            <button type="button" onclick="submitFileForPayment('${bpPayment.paymentId}')">アップロード</button>
                                            <ul id="fileList_${bpPayment.paymentId}" class="file-list"></ul>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <input type="button" value="更新" 
                                        onclick="toUpdateJsp('${bpPayment.paymentId}');"/>
                                </td>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="color: red;">データが見つかりませんでした。</p>
            </c:otherwise>
        </c:choose>

        <!-- 派遣会社別・月別合計表示 -->
        <c:choose>
            <c:when test="${not empty summaryList}">
                <br><br>
                <h3 style="color: blue;">派遣会社別・月別合計</h3>
                <table border="1" class="bpPaymentSummary-table" style="background-color: #f0f8ff;">
                    <tr>
                        <th width="80">月</th>
                        <th width="150">派遣(請負)先会社</th>
                        <th width="100">人数</th>
                        <th width="100">外注金額（税抜）合計</th>
                        <th width="100">外注金額（税込）合計</th>
                        <th width="80">手数料合計</th>
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