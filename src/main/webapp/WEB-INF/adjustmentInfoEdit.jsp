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
/* テーブルコンテナのスタイル設定 */
.table-container {
	width: 50%; /* 幅を50%に設定 */
	margin-left: 20px; /* 左マージンを20pxに設定 */
	margin-bottom: 20px; /* 下マージンを20pxに設定 */
}

/* 見出し1のスタイル設定 */
h1 {
	text-align: center; /* テキストを中央揃え */
	font-size: 24px; /* フォントサイズを24pxに設定 */
	font-weight: bold; /* フォントを太字に設定 */
}

/* テーブルのスタイル設定 */
table {
	width: 100%; /* 幅を100%に設定 */
	margin-top: 20px; /* 上マージンを20pxに設定 */
	border-collapse: collapse; /* ボーダーを重ねて表示 */
}

/* テーブルヘッダーとデータセルのスタイル設定 */
th, td {
	border: 2px solid #b3cbde; /* ボーダーの色と太さを設定 */
	padding: 15px; /* 内側の余白を15pxに設定 */
	text-align: left; /* テキストを左揃え */
}

th {
	height: 40px; /* 高さを40pxに設定 */
	width: 20%; /* 幅を20%に設定 */
}

/* アップロードエリアのスタイル設定 */
.upload-area {
	display: flex; /* フレックスボックスを使用 */
	justify-content: space-between; /* アイテム間のスペースを均等に配置 */
	align-items: center; /* アイテムを中央揃え */
	margin-bottom: 20px; /* 下マージンを20pxに設定 */
}

/* ファイルリストのスタイル設定 */
.file-list {
	list-style-type: disc; /* リストマーカーをディスクに設定 */
	padding-left: 20px; /* 左パディングを20pxに設定 */
	margin: 0; /* マージンを0に設定 */
}

/* ファイルアイテムのスタイル設定 */
.file-item {
	margin-bottom: 5px; /* 下マージンを5pxに設定 */
}

/* ファイル入力フィールドのスタイル設定 */
.file-input {
	display: none; /* 非表示に設定 */
}

/* ボタンのスタイル設定 */
button {
	border-radius: px;
	padding: 3px 7px; /* 内側の余白を3px上、下、7px左右に設定 */
	font-size: 13px; /* フォントサイズを13pxに設定 */
	cursor: pointer; /* カーソルをポインターに設定 */
	margin: 5px; /* マージンを5pxに設定 */
}

/* 削除ボタンのスタイル設定 */
.delete-btn {
	margin-left: 10px; /* 左マージンを10pxに設定 */
	cursor: pointer; /* カーソルをポインターに設定 */
}

/* ボタンコンテナのスタイル設定 */
.button-container {
	text-align: right; /* テキストを右揃え */
	padding-right: 10px; /* 右パディングを10pxに設定 */
	padding-top: 10px; /* 上パディングを10pxに設定 */
}

/* ファイルがない場合のメッセージスタイル設定 */
.no-file-message {
	font-weight: bold; /* フォントを太字に設定 */
}

/* ファイルセルのスタイル設定 */
.file-cell {
	padding-left: 15px; /* 左パディングを15pxに設定 */
}
</style>
</head>
<body>
	<!-- 
	   adjustmentStatus=='1' => 調整済み 
	   => アップロード/ファイル選択/削除ボタン/確定ボタン 全て disable
	-->
	<div class="table-container">
		<h1>年末調整</h1>
		<!-- 年度と社員名を表示するテーブル -->
		<table>
			<tr>
				<th>年度</th>
				<td>${currentYear}</td>
				<!-- 現在の年度を表示 -->
			</tr>

			<tr>
				<th>社員名</th>
				<td>${employeeName}</td>
				<!-- 社員の名前を表示 -->
			</tr>

			<tr>
				<th>アップ済み</th>
				<td class="file-cell" id="detailFilesContainer">
					<c:choose>
						<c:when test="${not empty detailFiles}">
							<!-- アップ済みファイルが存在する場合のリスト表示 -->
							<ul class="file-list">
								<c:forEach var="file" items="${detailFiles}">
									<li>
										<a href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/detailType/${file.fileYear}/${file.employeeID}/${file.fileName}">
											${file.fileName}
										</a>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<!-- アップ済みファイルがない場合のメッセージ表示 -->
							<span class="no-file-message">ファイルがありません</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>

		<!-- 結果に関するアップロードと表示を行うテーブル -->
		<table>
			<tr>
				<th rowspan="2">結果</th>
				<!-- 結果の見出しを2行にまたがる -->
				<td class="file-cell">
					<div class="upload-area">
						<input type="file" id="fileUpload" multiple class="file-input"
							onchange="updateFileList()"
							<c:if test="${adjustmentStatus == '1'}">disabled</c:if> />
						<!-- ファイル選択ボタン、adjustmentStatusが1の場合はdisabled -->
						<button type="button"
							onclick="document.getElementById('fileUpload').click();"
							id="selectBtn"
							<c:if test="${adjustmentStatus == '1'}">disabled</c:if>>
							ファイルを選択
						</button>
						<!-- アップロードボタン、adjustmentStatusが1の場合はdisabled -->
						<button type="button"
							onclick="submitFiles()"
							id="uploadBtn"
							<c:if test="${adjustmentStatus == '1'}">disabled</c:if>>
							<c:choose>
								<c:when test="${adjustmentStatus == '1'}">アップロード済み</c:when>
								<c:otherwise>アップロード</c:otherwise>
							</c:choose>
						</button>
					</div>
					<ul id="fileList" class="file-list"></ul> <!-- アップロードしたファイルのリスト表示 -->
				</td>
			</tr>
			<tr>
				<td class="file-cell" id="uploadedFiles">
					<c:choose>
						<c:when test="${not empty resultFiles}">
							<!-- 結果ファイルが存在する場合のリスト表示 -->
							<ul class="file-list">
								<c:forEach var="file" items="${resultFiles}">
									<li class="file-item">
										<a href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/resultType/${file.fileYear}/${file.employeeID}/${file.fileName}">
											${file.fileName}
										</a>
										<!-- 削除ボタンを追加 -->
										<button type="button"
											class="delete-btn"
											onclick="deleteResultFile('${file.employeeID}','${file.fileYear}','${file.fileName}','resultType')"
											<c:if test="${adjustmentStatus == '1'}">disabled</c:if>>
											削除
										</button>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<!-- 結果ファイルがない場合のメッセージ表示 -->
							<span class="no-file-message">ファイルがありません</span>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>

		<!-- 調整完了ボタンを配置するコンテナ -->
		<div class="button-container">
			<button type="button"
				onclick="finalizeAdjustment()"
				id="finalizeBtn"
				<c:if test="${adjustmentStatus == '1'}">disabled</c:if>>
				<c:choose>
					<c:when test="${adjustmentStatus == '1'}">調整済み</c:when>
					<c:otherwise>調整完了</c:otherwise>
				</c:choose>
			</button>
		</div>

		<!-- 以前のファイルを参照するテーブル -->
		<table>
			<thead>
				<tr>
					<th colspan="2" style="text-align: center;">以前ファイル参照</th>
				</tr>
				<tr>
					<th>年度</th>
					<td>
						<select id="yearSelect" onchange="loadFilesForSelectedYear()">
							<!-- 年度選択ドロップダウン -->
							<c:forEach var="year" items="${yearList}">
								<option value="${year}">${year}</option>
								<!-- 年度の選択肢 -->
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>ファイル</th>
					<td class="file-cell" id="pastFilesContainer">
						<c:choose>
							<c:when test="${not empty pastFiles}">
								<!-- 過去のファイルが存在する場合のリスト表示 -->
								<ul class="file-list">
									<c:forEach var="file" items="${pastFiles}">
										<li>
											<a href="${pageContext.request.contextPath}/adjustmentInfoEdit/downloadFileDirect?filePath=${file.filePath}">
												${file.fileName}
											</a>
										</li>
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<span class="no-file-message">ファイルがありません</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</thead>
		</table>

		<!-- 戻るボタンを中央に配置 -->
		<div style="text-align: center; margin-top: 20px;">
			<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/adjustmentList'">
				戻る
			</button>
		</div>
	</div>

	<script>
        // コンテキストパスと社員IDをJavaScript変数に設定
        var currentYear = '${currentYear}';
        var contextPath = '${pageContext.request.contextPath}';
        var employeeId = '${employeeId}';

        // ページ読み込み時に過去ファイルをロード
        document.addEventListener("DOMContentLoaded", function() {
            loadFilesForSelectedYear();
        });

        /**
         * 過去ファイルをロード
         */
        function loadFilesForSelectedYear() {
            const selectedYear = document.getElementById('yearSelect').value;
            fetch(contextPath + "/adjustmentInfoEdit/getPastFiles?employeeId=" + employeeId + "&year=" + selectedYear)
                .then(response => response.json())
                .then(data => {
                    const container = document.getElementById('pastFilesContainer');
                    container.innerHTML = '';
                    if (data.files && data.files.length > 0) {
                        const ul = document.createElement('ul');
                        ul.classList.add('file-list');
                        data.files.forEach(file => {
                            const li = document.createElement('li');
                            const link = document.createElement('a');
                            link.href = contextPath + '/adjustmentInfoEdit/download/resultType/'
                                       + file.fileYear + '/' + employeeId + '/'
                                       + encodeURIComponent(file.fileName);
                            link.textContent = file.fileName;
                            li.appendChild(link);
                            ul.appendChild(li);
                        });
                        container.appendChild(ul);
                    } else {
                        const noFileMessage = document.createElement('span');
                        noFileMessage.textContent = 'ファイルがありません';
                        noFileMessage.classList.add('no-file-message');
                        container.appendChild(noFileMessage);
                    }
                })
                .catch(error => {
                    console.error('Error loading past files:', error);
                    const container = document.getElementById('pastFilesContainer');
                    container.innerHTML = 'ファイルの読み込み中にエラーが発生しました';
                });
        }

        /**
         * ファイルリストを更新する関数
         */
        function updateFileList() {
            const input = document.getElementById('fileUpload');
            const fileList = document.getElementById('fileList');
            fileList.innerHTML = '';
            const dt = new DataTransfer();
            Array.from(input.files).forEach((file, index) => {
                dt.items.add(file);
                const li = document.createElement('li');
                li.classList.add('file-item');
                li.textContent = file.name;

                // 削除ボタン
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

        /**
         * ファイルをサーバーにアップロードする関数
         */
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

        /**
         * 結果ファイルを削除する関数
         */
        function deleteResultFile(employeeID, fileYear, fileName, fileType) {
            if (!confirm('削除しますか？')) {
                return;
            }
            fetch(contextPath + '/adjustmentInfoEdit/deleteFile', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    employeeID: employeeID,
                    fileYear: fileYear,
                    fileName: fileName,
                    fileType: fileType
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('サーバーエラー');
                }
                return response.json();
            })
            .then(data => {
                if (data.message) {
                    alert(data.message);
                    location.reload();
                } else if (data.error) {
                    alert(data.error);
                }
            })
            .catch(error => {
                console.error('Delete failed: ', error);
                alert('ファイルの削除に失敗しました: ' + error.message);
            });
        }

        /**
         * 調整を確定する関数
         */
        function finalizeAdjustment() {
            if (!confirm('注意：一旦確定すると、再アップロードはできなくなります。')) {
                return;
            }
            fetch(contextPath + '/adjustmentInfoEdit/finalizeAdjustment?employeeId=' + employeeId, {
                method: 'POST'
            })
            .then(response => response.json())
            .then(data => {
                if (data.message) {
                    // data.message が成功メッセージの場合のみ以下の処理を実行
                    if (data.message.indexOf("完了しました") !== -1) {
                        alert(data.message);

                        // 1) 禁用アップロードボタン
                        const uploadBtn = document.getElementById('uploadBtn');
                        if (uploadBtn) {
                            uploadBtn.disabled = true;
                            uploadBtn.textContent = 'アップロード済み';
                        }

                        // 2) 禁用ファイル選択ボタン
                        const fileSelectBtn = document.querySelector('.upload-area button:nth-of-type(1)');
                        if (fileSelectBtn) {
                            fileSelectBtn.disabled = true;
                        }

                        // 3) 禁用確定ボタン
                        const finalizeBtn = document.querySelector('.button-container button');
                        if (finalizeBtn) {
                            finalizeBtn.disabled = true;
                            finalizeBtn.textContent = '調整済み';
                        }

                        // 4) 物理的に"ファイルを選択" inputもdisable
                        const fileInput = document.getElementById('fileUpload');
                        if (fileInput) {
                            fileInput.disabled = true;
                        }

                        // 5) すべての削除ボタンを無効化
                        const deleteButtons = document.querySelectorAll('.delete-btn');
                        deleteButtons.forEach(btn => {
                            btn.disabled = true;
                        });
                    } else {
                        // 失敗メッセージの場合
                        alert(data.message);
                    }
                } else if (data.error) {
                    alert(data.error);
                }
            })
            .catch(error => {
                console.error("Processing failed: ", error);
                alert('調整完了に失敗しました: ' + error.message);
            });
        }
    </script>
</body>
</html>
