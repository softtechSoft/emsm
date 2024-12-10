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
            list-style: none; /* リストマーカーを無しに設定 */
            padding: 0; /* パディングを0に設定 */
            margin-top: 10px; /* 上マージンを10pxに設定 */
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
            border-radius: px; /* ボーダーの半径を設定（値が未指定） */
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
        
        /* リクエストファイルのリストスタイル設定 */
        #requestFiles ul {
            list-style-type: disc; /* リストマーカーをディスクに設定 */
            padding-left: 20px; /* 左パディングを20pxに設定 */
            margin: 0; /* マージンを0に設定 */
        }
        
        /* リクエストファイルのリストアイテムのスタイル設定 */
        #requestFiles li {
            margin-bottom: 5px; /* 下マージンを5pxに設定 */
        }
        
        /* ファイルがない場合のメッセージスタイル設定 */
        .no-file-message {
            font-weight: bold; /* フォントを太字に設定 */
        }
    </style>
</head>
<body>
    <div class="table-container">
        <h1>年末調整</h1>
        <!-- 年度とアップロードセクションを表示するテーブル -->
        <table>
            <tr>
                <th>年度</th>
                <td>${currentYear}</td> <!-- 現在の年度を表示 -->
            </tr>
            <tr>
                <th>アップロード</th>
                <td>
                    <div class="upload-area">
                        <!-- ファイル選択フィールド -->
                        <input type="file" id="fileUpload" multiple class="file-input"
                            onchange="updateFileList()" />
                        <!-- ファイル選択ボタン -->
                        <button type="button"
                            onclick="document.getElementById('fileUpload').click();">ファイルを選択</button>
                        <!-- アップロードボタン -->
                        <button type="button" onclick="submitFiles()">アップロード</button>
                    </div>
                    <!-- 選択したファイルのリストを表示する領域 -->
                    <ul id="fileList" class="file-list"></ul>
                </td>
            </tr>
            <tr>
                <th>テンプレート</th>
                <td id="requestFiles">
                    <c:choose>
                        <c:when test="${not empty files}">
                            <!-- テンプレートファイルが存在する場合のリスト表示 -->
                            <ul>
                                <c:forEach var="file" items="${files}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/adjustmentInfoEdit/downloadFileDirect?filePath=${file.filePath}">
                                            ${file.fileName} <!-- ファイル名を表示 -->
                                        </a>
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

        <!-- 社員リスト表 -->
        <table>
            <thead>
                <tr>
                    <th colspan="3" style="text-align: center;">社員リスト</th> <!-- テーブル見出し -->
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
                        <td><a
                            href="${pageContext.request.contextPath}/adjustmentInfoEdit?employeeId=${employee.employeeID}">
                                ${employee.employeeName} </a></td>

                        <!-- アップロード状態を表示 -->
                        <td><c:choose>
                                <c:when test="${employee.uploadStatus == '1'}">アップロード完了</c:when>
                                <c:otherwise>アップロード中</c:otherwise>
                            </c:choose></td>

                        <!-- 調整状態を表示 -->
                        <td><c:choose>
                                <c:when test="${employee.adjustmentStatus == '1'}">調整済み</c:when>
                                <c:otherwise>調整中</c:otherwise>
                            </c:choose></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        // コンテキストパスをJavaScript変数に設定
        var contextPath = '${pageContext.request.contextPath}';

        /**
         * ファイルリストを更新する関数
         */
        function updateFileList() {
            const input = document.getElementById('fileUpload'); // ファイル入力フィールドを取得
            const fileList = document.getElementById('fileList'); // ファイルリスト表示領域を取得
            fileList.innerHTML = ''; // リストをクリア

            // 選択されたファイルをリストに追加
            Array.from(input.files).forEach((file, index) => {
                const li = document.createElement('li');
                li.textContent = file.name; // ファイル名を表示
                li.classList.add('file-item'); // ファイルアイテムのクラスを追加

                // 削除ボタンを作成
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '削除'; // ボタンのテキストを設定
                deleteBtn.className = 'delete-btn'; // ボタンのクラスを設定

                // 削除ボタンのクリックイベントを設定
                deleteBtn.onclick = function() {
                    const dt = new DataTransfer(); // 新しいDataTransferオブジェクトを作成
                    const filesArray = Array.from(input.files); // ファイルリストを配列に変換
                    filesArray.splice(index, 1); // 指定したインデックスのファイルを削除
                    filesArray.forEach(file => dt.items.add(file)); // 残りのファイルをDataTransferに追加
                    input.files = dt.files; // ファイル入力フィールドのファイルリストを更新
                    this.parentElement.remove(); // リストアイテムを削除
                };

                li.appendChild(deleteBtn); // 削除ボタンをリストアイテムに追加
                fileList.appendChild(li); // リストアイテムをファイルリストに追加
            });
        }

        /**
         * ファイルをサーバーにアップロードする関数
         */
        function submitFiles() {
            const input = document.getElementById('fileUpload'); // ファイル入力フィールドを取得
            const files = input.files; // 選択されたファイルを取得
            if (files.length === 0) {
                alert('ファイルを選択してください。'); // ファイルが選択されていない場合のアラート
                return;
            }

            const formData = new FormData(); // 新しいFormDataオブジェクトを作成
            for (let i = 0; i < files.length; i++) {
                formData.append('files', files[i]); // 各ファイルをFormDataに追加
            }

            // ファイルをサーバーに送信
            fetch(contextPath + '/uploadFile', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    // エラーメッセージを取得して例外を投げる
                    return response.json().then(errData => {
                        throw new Error(errData.message || 'アップロードに失敗しました');
                    });
                }
                return response.json(); // レスポンスをJSON形式で取得
            })
            .then(data => {
                alert(data.message || "アップロードが成功しました！"); // 成功メッセージを表示
                location.reload(); // ページをリロード
            })
            .catch(error => {
                console.error('エラー:', error); // コンソールにエラーログを出力
                alert('ファイルのアップロードに失敗しました: ' + error.message); // エラーメッセージを表示
            });
        }
    </script>
</body>
</html>
