<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        .custom-button {
            display: inline-block;
            padding: 10px 15px;
            background-color: #7fa3bc;
            color: white;
            cursor: pointer;
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
                <th>アップロード</th>
                <td>
                    <div class="upload-area">
                        <label for="fileUpload" class="custom-button">ファイルを選択</label>
                        <input type="file" id="fileUpload" multiple class="file-input" onchange="updateFileList()"/>
                        <button onclick="submitFiles()" class="custom-button">確定</button>
                    </div>
                    <ul id="fileList" class="file-list"></ul>
                </td>
            </tr>
            <tr>
                <th>申請書</th>
                <td id="requestFiles"></td>
            </tr>
        </table>

        <!-- 社員リスト表 -->
        <table>
            <thead>
                <tr>
                    <th colspan="3" style="text-align: center;">社員リスト</th>
                </tr>
                <tr>
                    <th>社員名</th>
                    <th>アップロード状態</th>
                    <th>調整状態</th>
                </tr>
            </thead>
            <tbody id="employeeList">
                <!-- 社員リストはJavaScriptによってフェッチして表示されます -->
            </tbody>
        </table>
    </div>
    <script>

    var contextPath = '<%= request.getContextPath() %>';
    
    document.addEventListener("DOMContentLoaded", function() {
        fetch(contextPath + "/getAdjustmentData")
            .then(response => response.json())
            .then(data => {
                console.log("Received data:", data); 
                const requestFilesContainer = document.getElementById("requestFiles");
                if(data.requestFiles && data.requestFiles.length > 0) {
                    data.requestFiles.forEach(file => {
                        const link = document.createElement("a");
                        link.href = contextPath + "/download/" + file.fileName;
                        link.textContent = file.fileName;
                        requestFilesContainer.appendChild(link);
                        requestFilesContainer.appendChild(document.createElement("br"));
                    });
                } else {
                    console.log("No request files to display.");
                    requestFilesContainer.textContent = "申請書がありません。";
                }

                const employeeList = document.getElementById("employeeList");
                if(data.employees && data.employees.length > 0) {
                    data.employees.forEach(employee => {
                        const tr = document.createElement("tr");
                        const nameTd = document.createElement("td");
                        const uploadStatusTd = document.createElement("td");
                        const adjustmentStatusTd = document.createElement("td");

                        nameTd.textContent = employee.employeeName;
                        uploadStatusTd.textContent = employee.uploadStatus === "1" ? "アップロード完了" : "アップロード中";
                        adjustmentStatusTd.textContent = employee.adjustmentStatus === "1" ? "調整済み" : "調整中";

                        tr.appendChild(nameTd);
                        tr.appendChild(uploadStatusTd);
                        tr.appendChild(adjustmentStatusTd);
                        employeeList.appendChild(tr);
                    });
                } else {
                    console.log("No employees to display.");
                    employeeList.textContent = "社員情報がありません。";
                }
            })
            .catch(error => {
                console.error("Error loading data:", error);
            });
    });

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

        console.log("ファイルをアップロード中:");
        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            console.log(files[i].name); // ファイル名を印刷
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
            console.log('サーバーからのレスポンス:', data);
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
