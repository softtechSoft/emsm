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

/* テーブルヘッダーの特定スタイル設定 */
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
				<td class="file-cell" id="detailFilesContainer"><c:choose>
						<c:when test="${not empty detailFiles}">
							<!-- アップ済みファイルが存在する場合のリスト表示 -->
							<ul class="file-list">
								<c:forEach var="file" items="${detailFiles}">
									<li><a
										href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/detailType/${file.fileYear}/${file.employeeID}/${file.fileName}">
											${file.fileName} </a></li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<!-- アップ済みファイルがない場合のメッセージ表示 -->
							<span class="no-file-message">ファイルがありません</span>
						</c:otherwise>
					</c:choose></td>
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
							onchange="updateFileList()" />
						<!-- ファイル入力フィールド -->
						<button type="button"
							onclick="document.getElementById('fileUpload').click();">ファイルを選択</button>
						<!-- ファイル選択ボタン -->
						<button type="button" onclick="submitFiles()" id="uploadBtn">アップロード</button>
						<!-- アップロードボタン -->
					</div>
					<ul id="fileList" class="file-list"></ul> <!-- アップロードしたファイルのリスト表示 -->
				</td>
			</tr>
			<tr>
				<td class="file-cell" id="uploadedFiles"><c:choose>
						<c:when test="${not empty resultFiles}">
							<!-- 結果ファイルが存在する場合のリスト表示 -->
							<ul class="file-list">
								<c:forEach var="file" items="${resultFiles}">
									<li><a
										href="${pageContext.request.contextPath}/adjustmentInfoEdit/download/resultType/${file.fileYear}/${file.employeeID}/${file.fileName}">
											${file.fileName} </a></li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<!-- 結果ファイルがない場合のメッセージ表示 -->
							<span class="no-file-message">ファイルがありません</span>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>

		<!-- 調整完了ボタンを配置するコンテナ -->
		<div class="button-container">
			<button type="button" onclick="finalizeAdjustment()">調整完了</button>
			<!-- 調整完了ボタン -->
		</div>

		<!-- 以前のファイルを参照するテーブル -->
		<table>
			<thead>
				<tr>
					<th colspan="2" style="text-align: center;">以前ファイル参照</th>
					<!-- テーブル見出し -->
				</tr>
				<tr>
					<th>年度</th>
					<td><select id="yearSelect"
						onchange="loadFilesForSelectedYear()">
							<!-- 年度選択ドロップダウン -->
							<c:forEach var="year" items="${yearList}">
								<option value="${year}">${year}</option>
								<!-- 年度の選択肢 -->
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>ファイル</th>
					<td class="file-cell" id="pastFilesContainer"><c:choose>
							<c:when test="${not empty pastFiles}">
								<!-- 過去のファイルが存在する場合のリスト表示 -->
								<ul class="file-list">
									<c:forEach var="file" items="${pastFiles}">
										<li><a
											href="${pageContext.request.contextPath}/adjustmentInfoEdit/downloadFileDirect?filePath=${file.filePath}">
												${file.fileName} <!-- ファイル名を表示 -->
										</a></li>
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<!-- 過去のファイルがない場合のメッセージ表示 -->
								<span class="no-file-message">ファイルがありません</span>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</thead>
		</table>

		<!-- 戻るボタンを中央に配置 -->
		<div style="text-align: center; margin-top: 20px;">
			<button type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/adjustmentList'">戻る</button>
			<!-- 戻るボタン -->
		</div>
	</div>
	<script>
        // コンテキストパスと社員IDをJavaScript変数に設定
        var currentYear = '${currentYear}';
        var contextPath = '${pageContext.request.contextPath}';
        var employeeId = '${employeeId}';

        // ページの読み込みが完了したら、選択された年度のファイルを読み込む
        document.addEventListener("DOMContentLoaded", function() {
            loadFilesForSelectedYear();
        });

        /**
         * 選択された年度のファイルをサーバーから取得し、表示する関数
         */
        function loadFilesForSelectedYear() {
            const selectedYear = document.getElementById('yearSelect').value; // 選択された年度を取得
            fetch(contextPath + "/adjustmentInfoEdit/getPastFiles?employeeId=" + employeeId + "&year=" + selectedYear)
                .then(response => response.json())
                .then(data => {
                    const container = document.getElementById('pastFilesContainer'); // ファイル表示コンテナを取得
                    container.innerHTML = ''; // コンテナの内容をクリア
                    if (data.files && data.files.length > 0) {
                        const ul = document.createElement('ul');
                        ul.classList.add('file-list'); // ファイルリストのクラスを追加
                        data.files.forEach(file => {
                            const li = document.createElement('li');
                            const link = document.createElement('a');
                            // ダウンロードリンクを設定
                            link.href = contextPath + '/adjustmentInfoEdit/download/resultType/' + file.fileYear + '/' + employeeId + '/' + encodeURIComponent(file.fileName);
                            link.textContent = file.fileName; // ファイル名を表示
                            li.appendChild(link);
                            ul.appendChild(li);
                        });
                        container.appendChild(ul); // リストをコンテナに追加
                    } else {
                        const noFileMessage = document.createElement('span');
                        noFileMessage.textContent = 'ファイルがありません';
                        noFileMessage.classList.add('no-file-message'); // メッセージのクラスを追加
                        container.appendChild(noFileMessage); // メッセージをコンテナに追加
                    }
                })
                .catch(error => {
                    console.error('Error loading past files:', error); // エラーログを出力
                    const container = document.getElementById('pastFilesContainer');
                    container.innerHTML = 'ファイルの読み込み中にエラーが発生しました'; // エラーメッセージを表示
                });
        }

        /**
         * ファイルリストを更新する関数
         */
        function updateFileList() {
            const input = document.getElementById('fileUpload'); // ファイル入力フィールドを取得
            const fileList = document.getElementById('fileList'); // ファイルリスト表示エリアを取得
            fileList.innerHTML = ''; // リストをクリア
            const dt = new DataTransfer(); // 新しいDataTransferオブジェクトを作成
            Array.from(input.files).forEach((file, index) => {
                dt.items.add(file); // ファイルをDataTransferに追加
                const li = document.createElement('li');
                li.textContent = file.name; // ファイル名を表示
                li.classList.add('file-item'); // ファイルアイテムのクラスを追加
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除'; // 削除ボタンのテキストを設定
                deleteBtn.className = 'delete-btn'; // 削除ボタンのクラスを設定
                deleteBtn.onclick = function() {
                    dt.items.remove(index); // 該当ファイルをDataTransferから削除
                    input.files = dt.files; // ファイル入力フィールドのファイルリストを更新
                    this.parentElement.remove(); // リストアイテムを削除
                };
                li.appendChild(deleteBtn); // 削除ボタンをリストアイテムに追加
                fileList.appendChild(li); // リストアイテムをファイルリストに追加
            });
            input.files = dt.files; // ファイル入力フィールドのファイルリストを更新
        }

        /**
         * ファイルをサーバーにアップロードする関数
         */
        function submitFiles() {
            const files = document.getElementById('fileUpload').files; // ファイル入力フィールドからファイルを取得
            if (files.length === 0) {
                alert('ファイルを選択してください。'); // ファイルが選択されていない場合のアラート
                return;
            }
            const formData = new FormData();
            Array.from(files).forEach(file => {
                formData.append('files', file); // 各ファイルをFormDataに追加
            });
            formData.append('employeeId', employeeId); // 社員IDをFormDataに追加
            fetch(contextPath + '/adjustmentInfoEdit/uploadResultFiles', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errData => {
                        throw new Error(errData.message || 'アップロードに失敗しました'); // エラーメッセージを取得
                    });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message || "アップロードが成功しました！"); // 成功メッセージを表示
                location.reload(); // ページをリロード
            })
            .catch(error => {
                alert('ファイルのアップロードに失敗しました: ' + error.message); // エラーメッセージを表示
                console.error('Upload error:', error); // コンソールにエラーログを出力
            });
        }

        /**
         * 調整を確定する関数
         */
        function finalizeAdjustment() {
            fetch(contextPath + '/adjustmentInfoEdit/finalizeAdjustment?employeeId=' + employeeId, {
                method: 'POST'
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errData => {
                        throw new Error(errData.message || '調整完了に失敗しました'); // エラーメッセージを取得
                    });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message || "調整完了しました！"); // 成功メッセージを表示
                location.reload(); // ページをリロード
            })
            .catch(error => {
                alert('調整完了に失敗しました: ' + error.message); // エラーメッセージを表示
                console.error('Finalize error:', error); // コンソールにエラーログを出力
            });
        }
    </script>
</body>
</html>
