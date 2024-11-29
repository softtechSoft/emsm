<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>年末調整</title>
    <style>
        .table-container {
            width: 50%;
            margin-left: 20px;
            margin-bottom: 20px;
        }
        h1 {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            border: 2px solid #b3cbde;
            padding: 15px;
            text-align: left;
        } 
        th {
            height: 40px;
            width: 20%;
        }
        .upload-area {
            display: flex;
            justify-content: space-between; 
            align-items: center;
            margin-bottom: 20px;
        }
        .file-list {
            list-style: none;
            padding: 0;
            margin-top: 10px;
        }
        .file-item {
            margin-bottom: 5px;
        }
        .file-input {
            display: none;
        }
    	button {
        	border-radius: px;
        	padding: 3px 7px;
        	font-size: 13px;
        	cursor: pointer;
        	margin: 5px;
    	}
        .delete-btn {
            margin-left: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="table-container">
        <h1>年末調整</h1>
        <table>
            <tr>
                <th>年度</th>
                <td>${currentYear}</td>
            </tr>
            <tr>
                <th>アップロード</th>
                <td>
                    <div class="upload-area">
                        <input type="file" id="fileUpload" multiple class="file-input" onchange="updateFileList()"/>
                        <button type="button" onclick="document.getElementById('fileUpload').click();">ファイルを選択</button>
                        <button type="button" onclick="submitFiles()">確定</button>
                    </div>
                    <ul id="fileList" class="file-list"></ul>
                </td>
            </tr>
            <tr>
                <th>テンプレート</th>
                <td id="requestFiles">
                    <c:forEach var="file" items="${files}">
                        <a href="${pageContext.request.contextPath}/download/${file.fileName}">${file.fileName}</a><br/>
                    </c:forEach>
                </td>
            </tr>
        </table>

        <!-- 社員リスト表 -->
        <table>
            <thead>
                <tr>
                    <th colspan="3" style="text-align: center;">社員リスト</th>
                </tr>
                <tr>
                    <th>社員氏名</th>
                    <th>アップロード状態</th>
                    <th>調整状態</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/adjustmentInfoEdit?employeeId=${employee.employeeID}">
                                ${employee.employeeName}
                            </a>
                        </td>
                        <td>${employee.uploadStatus}</td>
                        <td>${employee.adjustmentStatus}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        var contextPath = '${pageContext.request.contextPath}';

        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = '';

            Array.from(input.files).forEach((file, index) => {
                const li = document.createElement('li');
                li.textContent = file.name;
                li.classList.add('file-item');
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除';
                deleteBtn.className = 'delete-btn';
                deleteBtn.onclick = function() {
                    const dt = new DataTransfer();
                    const filesArray = Array.from(input.files);
                    filesArray.splice(index, 1);
                    filesArray.forEach(file => dt.items.add(file));
                    input.files = dt.files;
                    this.parentElement.remove();
                };
                li.appendChild(deleteBtn);
                fileList.appendChild(li);
            });
        }

        function submitFiles() {
            const input = document.getElementById('fileUpload');
            const files = input.files;
            if (files.length === 0) {
                alert('ファイルを選択してください。');
                return;
            }

            const formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                formData.append('files', files[i]);
            }

            fetch(contextPath + '/uploadFile', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errData => {
                        throw new Error(errData.message || 'アップロードに失敗しました');
                    });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message || "アップロードが成功しました！");
                location.reload();
            })
            .catch(error => {
                console.error('エラー:', error);
                alert('ファイルのアップロードに失敗しました: ' + error.message);
            });
        }
    </script>
</body>
</html>
