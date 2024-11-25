<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>年末調整個人別</title>
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
        .custom-button {
            display: inline-block;
            padding: 10px 15px;
            background-color: #7fa3bc;
            color: white;
            cursor: pointer;
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
                <td><%= java.time.LocalDate.now().getYear() %></td>
            </tr>
            <tr>
                <th>社員名</th>
                <td id="employeeName"></td>
            </tr>
            <tr>
                <th>ファイル</th>
                <td id="fileContainer"></td>
            </tr>
        </table>

        <table>
            <tr>
                <th>結果</th>
                <td>
                    <div class="upload-area">
                        <input type="file" id="fileUpload" multiple class="file-input" onchange="updateFileList()"/>
                        <label for="fileUpload" class="custom-button">ファイルを選択</label>
                        <ul id="fileList"></ul>
                    </div>
                </td>
            </tr>
        </table>
        <div class="button-container">
            <button onclick="submitFiles()">調整完了</button>
        </div>

        <table>
            <thead>
                <tr>
                    <th colspan="2" style="text-align: center;">以前ファイル参照</th>
                </tr>
                <tr>
                    <th>年度</th>
                    <td>
                        <select id="yearSelect" onchange="loadFilesForSelectedYear()">
                            <!-- 由 JavaScript 动态填充年份选项 -->
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>ファイル</th>
                    <td id="pastFilesContainer"></td>
                </tr>
            </thead>
        </table>
    </div>
    <script>
        var currentYear = '<%= java.time.LocalDate.now().getYear() %>';
        var contextPath = '<%= request.getContextPath() %>';
        var employeeId = '<%= request.getParameter("employeeId") %>';

        document.addEventListener("DOMContentLoaded", function() {
            initializeYearSelect(currentYear);
            fetchEmployeeData(employeeId, currentYear);
        });

        function initializeYearSelect(currentYear) {
            const yearSelect = document.getElementById('yearSelect');
            if (!yearSelect) {
                console.error('yearSelect element not found');
                return;
            }
            for (let year = currentYear; year > currentYear - 10; year--) {
                const option = document.createElement('option');
                option.value = year;
                option.textContent = year;
                yearSelect.appendChild(option);
            }
        }

        function fetchEmployeeData(employeeId, year) {
            fetch(contextPath + "/getEmployeeDetails?employeeId=" + employeeId + "&year=" + year)
            .then(response => response.json())
            .then(data => {
                document.getElementById('employeeName').textContent = data.employeeName;
                displayFiles(data.files);
            })
            .catch(error => console.error("Failed to load employee data:", error));
        }

        function displayFiles(files) {
            const container = document.getElementById('fileContainer');
            container.innerHTML = ''; 
            files.forEach(file => {
                const link = document.createElement('a');
                link.href = contextPath + '/download/' + file.fileName;
                link.textContent = file.fileName;
                container.appendChild(link);
                container.appendChild(document.createElement('br'));
            });
        }

        function loadFilesForSelectedYear() {
            const selectedYear = document.getElementById('yearSelect').value;
            fetchEmployeeData(employeeId, selectedYear);
        }

        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = ''; 
            Array.from(input.files).forEach(file => {
                const li = document.createElement('li');
                li.textContent = file.name;
                li.classList.add('file-item');
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除';
                deleteBtn.className = 'delete-btn';
                deleteBtn.onclick = function() { this.parentElement.remove(); };
                li.appendChild(deleteBtn);
                fileList.appendChild(li);
            });
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
                alert('ファイルのアップロードに失敗しました: ' + error.message);
                console.error('Upload error:', error);
            });
        }
    </script>
</body>
</html>
