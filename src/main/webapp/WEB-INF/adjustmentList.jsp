<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>年末調整</title>
<style>
/* テーブルコンテナのスタイル設定 */
.table-container {
	width: 50%;
	margin-left: 20px;
	margin-bottom: 20px;
}

/* 見出し1のスタイル設定 */
h1 {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
}

/* テーブルのスタイル設定 */
table {
	width: 100%;
	margin-top: 20px;
	border-collapse: collapse;
}

/* テーブルヘッダーとデータセルのスタイル設定 */
th, td {
	border: 2px solid #b3cbde;
	padding: 15px;
	text-align: left;
}

th {
	height: 40px;
	width: 20%;
}

/* アップロードエリアのスタイル設定 */
.upload-area {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

/* ファイルリストのスタイル設定 */
.file-list {
	list-style-type: disc; /* 黒丸を表示 */
	padding-left: 20px;
	margin-top: 10px;
}

/* ファイルアイテムのスタイル設定 */
.file-item {
	margin-bottom: 5px;
	word-wrap: break-word; /* テキストが長い場合に折り返し可能 */
}

/* ファイル入力フィールドのスタイル設定 */
.file-input {
	display: none;
}

/* ボタンのスタイル設定 */
button {
	padding: 3px 7px;
	font-size: 13px;
	cursor: pointer;
	margin: 5px;
}

/* 削除ボタンのスタイル設定 */
.delete-btn {
	margin-left: 10px;
	cursor: pointer;
}

/* 確定ボタンコンテナのスタイル設定：右寄せ */
.button-container {
	text-align: right;
	padding-top: 10px;
}

/* ファイルがない場合のメッセージスタイル設定 */
.no-file-message {
	font-weight: bold;
}
</style>
</head>
<body>
	<c:set var="isFinalized" value="${isFinalized}" scope="page" />

	<div class="table-container">
		<h1>年末調整</h1>
		<!-- 年度とアップロードセクションを表示するテーブル -->
		<table>
			<tr>
				<th>年度</th>
				<td>${currentYear}</td>
				<!-- 現在の年度を表示 -->
			</tr>
			<tr>
				<th>アップロード</th>
				<td>
					<div class="upload-area">
						<!-- ファイル選択フィールド -->
						<input type="file" id="fileUpload" multiple class="file-input"
							onchange="updateFileList()"
							<c:if test="${isFinalized eq true}">disabled</c:if> />
						<!-- ファイル選択ボタン -->
						<button type="button" id="selectBtn"
							onclick="document.getElementById('fileUpload').click();"
							<c:if test="${isFinalized eq true}">disabled</c:if>>
							ファイルを選択
						</button>
						<!-- アップロードボタン -->
						<button type="button" id="uploadBtn" onclick="submitFiles()"
							<c:if test="${isFinalized eq true}">disabled</c:if>>
							<c:choose>
								<c:when test="${isFinalized eq true}">アップロード済み</c:when>
								<c:otherwise>アップロード</c:otherwise>
							</c:choose>
						</button>
					</div>
					<!-- 選択したファイルのリストを表示する領域 -->
					<ul id="fileList" class="file-list"></ul>
				</td>
			</tr>
			<tr>
				<th>年末調整申請書</th>
				<td>
					<c:choose>
						<c:when test="${not empty files}">
							<!-- テンプレートファイルが存在する場合のリスト表示 -->
							<ul class="file-list">
								<c:forEach var="file" items="${files}">
									<li class="file-item">
										<!-- ダウンロードリンク -->
										<a href="${pageContext.request.contextPath}/download/${file.fileName}">
											${file.fileName}
										</a>
										<!-- 削除ボタン -->
										<button type="button" class="delete-btn"
											onclick="deleteFile('${file.fileName}')"
											<c:if test="${isFinalized eq true}">disabled</c:if>>
											削除
										</button>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<!-- テンプレートファイルがない場合のメッセージ表示 -->
							<span class="no-file-message">ファイルがありません</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>

		<!-- 確定ボタン -->
		<div class="button-container">
			<!-- 确定按钮若 isFinalized==true 则禁用并显示「確定済み」 -->
			<button type="button" id="finalizeBtn" onclick="finalizeAdjustment()"
				<c:if test="${isFinalized eq true}">disabled</c:if>>
				<c:choose>
					<c:when test="${isFinalized eq true}">確定済み</c:when>
					<c:otherwise>確定</c:otherwise>
				</c:choose>
			</button>
		</div>

		<!-- 社員リスト表 -->
		<table>
			<thead>
				<tr>
					<th colspan="3" style="text-align: center;">社員リスト</th>
					<!-- テーブル見出し -->
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
						<!-- 社員名をリンクとして表示 -->
						<td>
							<a href="${pageContext.request.contextPath}/adjustmentInfoEdit?employeeId=${employee.employeeID}">
								${employee.employeeName}
							</a>
						</td>
						<!-- アップロード状態 -->
						<td>
							<c:choose>
								<c:when test="${employee.uploadStatus == '1'}">アップロード完了</c:when>
								<c:otherwise>アップロード中</c:otherwise>
							</c:choose>
						</td>
						<!-- 調整状態 -->
						<td>
							<c:choose>
								<c:when test="${employee.adjustmentStatus == '1'}">調整済み</c:when>
								<c:otherwise>調整中</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script>
        // コンテキストパスをJavaScript変数に設定
        var contextPath = '${pageContext.request.contextPath}';
        var currentYear = '${currentYear}'; // JSP传入的当年年度

        /**
         * ファイルリストを更新する関数
         */
        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = '';

            // 選択されたファイルをリストに追加
            Array.from(input.files).forEach((file, index) => {
                const li = document.createElement('li');
                li.classList.add('file-item');
                li.textContent = file.name;

                // 削除ボタン
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除';
                deleteBtn.className = 'delete-btn';

                // 削除ボタンのクリックイベント
                deleteBtn.onclick = function() {
                    const dt = new DataTransfer();
                    const filesArray = Array.from(input.files);
                    filesArray.splice(index, 1);
                    filesArray.forEach(f => dt.items.add(f));
                    input.files = dt.files;
                    this.parentElement.remove();
                };

                li.appendChild(deleteBtn);
                fileList.appendChild(li);
            });
        }

        /**
         * ファイルをサーバーにアップロードする関数
         */
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

        /**
         * 指定されたファイルを削除する関数
         */
        function deleteFile(fileName) {
            if (!confirm('削除しますか？')) {
                return;
            }
            fetch(contextPath + '/deleteFile', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fileName: fileName })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('サーバーエラー');
                }
                return response.json();
            })
            .then(data => {
                alert(data.message);
                location.reload();
            })
            .catch(error => {
                console.error('Delete failed: ', error);
                alert('削除に失敗しました: ' + error.message);
            });
        }

        /**
         * 確定ボタンを押した時の関数
         */
        function finalizeAdjustment() {
            if (!confirm('注意：一旦確定すると、再アップロードはできなくなります。')) {
                return;
            }

            // 向サーバー发送 finalizeAdjustment 请求，携带当年年度
            fetch(contextPath + '/finalizeAdjustment', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fileYear: currentYear })
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errData => {
                        throw new Error(errData.message || '確定に失敗しました');
                    });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message || "確定しました！");

                // 1) "ファイルを選択" ボタンをdisable
                const selectBtn = document.getElementById('selectBtn');
                if (selectBtn) {
                    selectBtn.disabled = true;
                }

                // 2) "アップロード" ボタンをdisable & テキスト変更
                const uploadBtn = document.getElementById('uploadBtn');
                if (uploadBtn) {
                    uploadBtn.disabled = true;
                    uploadBtn.textContent = 'アップロード済み';
                }

                // 3) fileUpload input をdisable
                const fileInput = document.getElementById('fileUpload');
                if (fileInput) {
                    fileInput.disabled = true;
                }

                // 4) "確定" ボタンをdisable & テキスト変更
                const finalizeBtn = document.getElementById('finalizeBtn');
                if (finalizeBtn) {
                    finalizeBtn.disabled = true;
                    finalizeBtn.textContent = '確定済み';
                }

                // 5) すべての削除ボタンをdisable
                const deleteButtons = document.querySelectorAll('.delete-btn');
                deleteButtons.forEach(btn => {
                    btn.disabled = true;
                });
            })
            .catch(error => {
                console.error('確定に失敗:', error);
                alert('確定に失敗しました: ' + error.message);
            });
        }
    </script>
</body>
</html>
