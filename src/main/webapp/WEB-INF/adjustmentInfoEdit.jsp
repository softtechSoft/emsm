<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>年末調整詳細</title>
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
    .button-container {
        text-align: right;
        padding-right: 10px;
        padding-top: 10px;
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
                <th>社員名</th>
                <td>${employeeName}</td>
            </tr>
            <tr>
                <th>アップ済み</th>
                <td>
                    <c:forEach var="file" items="${detailFiles}">
                        <a href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/${file.fileYear}/${file.employeeEmail}/${file.fileName}">
                            ${file.fileName}
                        </a>
                        <br />
                    </c:forEach>
                </td>
            </tr>
        </table>

        <!-- 结果表格 -->
        <table>
            <tr>
                <th rowspan="2">結果</th>
                <td>
                    <div class="upload-area">
                        <!-- 隐藏的文件输入框 -->
                        <input type="file" id="fileUpload" multiple class="file-input" onchange="updateFileList()" />
                        <!-- 使用<button>元素的“ファイルを選択”按钮 -->
                        <button type="button" onclick="document.getElementById('fileUpload').click();">ファイルを選択</button>
                        <ul id="fileList" class="file-list"></ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <!-- 显示已上传的 resultType 文件 -->
                    <div id="uploadedFiles">
                        <c:forEach var="file" items="${resultFiles}">
                            <a href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/${file.fileYear}/${file.employeeEmail}/${file.fileName}">
                                ${file.fileName}
                            </a>
                            <br />
                        </c:forEach>
                    </div>
                </td>
            </tr>
        </table>
        <div class="button-container">
            <button type="button" onclick="submitFiles()">調整完了</button>
        </div>

        <!-- 以前ファイル参照表格 -->
        <table>
            <thead>
                <tr>
                    <th colspan="2" style="text-align: center;">以前ファイル参照</th>
                </tr>
                <tr>
                    <th>年度</th>
                    <td>
                        <select id="yearSelect" onchange="loadFilesForSelectedYear()">
                            <c:forEach var="year" items="${yearList}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>ファイル</th>
                    <td id="pastFilesContainer">
                        <!-- 动态加载 -->
                    </td>
                </tr>
            </thead>
        </table>

        <!-- “戻る”按钮 -->
        <div style="text-align: center; margin-top: 20px;">
            <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/adjustmentList'">戻る</button>
        </div>

    </div>
    <script>
        var currentYear = '${currentYear}';
        var contextPath = '${pageContext.request.contextPath}';
        var employeeId = '${employeeId}';
        var employeeEmail = '${employeeEmail}';

        document.addEventListener("DOMContentLoaded", function() {
            loadFilesForSelectedYear();
        });

        function loadFilesForSelectedYear() {
            const selectedYear = document.getElementById('yearSelect').value;
            fetch(contextPath + "/adjustmentInfoEdit/getPastFiles?employeeId=" + employeeId + "&year=" + selectedYear)
                .then(response => response.json())
                .then(data => {
                    const container = document.getElementById('pastFilesContainer');
                    container.innerHTML = '';
                    data.files.forEach(file => {
                        const link = document.createElement('a');
                        link.href = contextPath + '/adjustmentInfoEdit/download/' + file.fileYear + '/' + file.employeeEmail + '/' + file.fileName;
                        link.textContent = file.fileName;
                        container.appendChild(link);
                        container.appendChild(document.createElement('br'));
                    });
                })
                .catch(error => console.error('Error loading past files:', error));
        }

        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = ''; 
            const dt = new DataTransfer();
            Array.from(input.files).forEach((file, index) => {
                dt.items.add(file);
                const li = document.createElement('li');
                li.textContent = file.name;
                li.classList.add('file-item');
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除';
                deleteBtn.className = 'delete-btn';
                deleteBtn.onclick = function() {
                    dt.items.remove(index);
                    input.files = dt.files;
                    this.parentElement.remove();
                };
                li.appendChild(deleteBtn);
                fileList.appendChild(li);
            });
            input.files = dt.files;
        }

        function submitFiles() {
            const files = document.getElementById('fileUpload').files;
            if (files.length === 0) {
                alert('ファイルを選択してください。');
                return;
            }
            const formData = new FormData();
            Array.from(files).forEach(file => {
                formData.append('files', file);
            });
            formData.append('employeeId', employeeId);
            fetch(contextPath + '/adjustmentInfoEdit/uploadResultFiles', {
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
                alert('ファイルのアップロードに失敗しました: ' + error.message);
                console.error('Upload error:', error);
            });
        }
    </script>
</body>
</html>
