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
<th colspan="2" style="text-align: center;">社員リスト</th>
</tr>
<tr>
<th>社員名</th>
<th>調整状況</th>
</tr>
</thead>
<tbody id="employeeList">
<%-- Employee list will be fetched and displayed by JavaScript --%>
</tbody>
</table>
</div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // 获取当前年份
            document.getElementById("currentYear").textContent = new Date().getFullYear();
            
            // Fetch and display request files and employee list
            fetch("/getAdjustmentData")
                .then(response => response.json())
                .then(data => {
                    const requestFiles = data.requestFiles;
                    const requestFilesContainer = document.getElementById("requestFiles");
                    requestFiles.forEach(file => {
                        const link = document.createElement("a");
                        link.href = "/download/" + file.fileName;
                        link.textContent = file.fileName;
                        requestFilesContainer.appendChild(link);
                        requestFilesContainer.appendChild(document.createElement("br"));
                    });

                    const employeeList = document.getElementById("employeeList");
                    data.employees.forEach(employee => {
                        const tr = document.createElement("tr");
                        const nameTd = document.createElement("td");
                        const statusTd = document.createElement("td");
                        nameTd.textContent = employee.employeeName;
                        statusTd.textContent = employee.adjustmentStatus === "0:未調整" ? "調整中" : "調整済み";
                        tr.appendChild(nameTd);
                        tr.appendChild(statusTd);
                        employeeList.appendChild(tr);
                    });
                })
                .catch(error => console.error("Error loading data:", error));
        });

        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = ''; // Clear existing list

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

            console.log("Uploading files:");
            const formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                console.log(files[i].name); // 打印文件名
                formData.append('files', files[i]);
            }

            fetch('/emsm/uploadFile', {
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                console.log('Response from server:', data); // 打印服务器响应
                alert(data.message || "アップロードが成功しました！");
                location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('ファイルのアップロードに失敗しました');
            });
        }



    </script>
</body>
</html>
