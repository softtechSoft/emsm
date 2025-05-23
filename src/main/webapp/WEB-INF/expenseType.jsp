<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>経費種別管理</title>
<style>
.table-container {
    width: 90%;
    margin: 0 auto;
    margin-bottom: 20px;
}

h1 {
    text-align: left;
    font-size: 24px;
    font-weight: bold;
}

h2 {
    text-align: left;
    font-size: 20px;
    font-weight: bold;
    margin-top: 30px;
    margin-bottom: 10px;
    border-left: 5px solid #3c78d8;
    padding-left: 10px;
}

.form-group {
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
}

label {
    margin-right: 10px;
    font-weight: bold;
}

select {
    margin-right: 20px;
    padding: 5px;
}

button {
    padding: 5px 10px;
    font-size: 14px;
    cursor: pointer;
    margin-right: 5px;
}

.expense-table {
    width: 100%;
    margin-top: 10px;
    border-collapse: collapse;
    table-layout: fixed;
}

.add-row {
    background-color: #f8f8f8;
}

.add-expense-btn {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
}

.add-expense-btn:hover {
    background-color: #45a049;
}

th, td {
    border: 2px solid #b3cbde;
    padding: 8px;
    text-align: center;
    vertical-align: middle;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

th {
    background-color: #f2f2f2;
}

tr:nth-child(even) {
    background-color: #fafafa;
}

.expense-table th:nth-child(1),
.expense-table td:nth-child(1) {
    width: 5%;
}

.expense-table th:nth-child(2),
.expense-table td:nth-child(2) {
    width: 20%;
}

.expense-table th:nth-child(3),
.expense-table td:nth-child(3),
.expense-table th:nth-child(5),
.expense-table td:nth-child(5) {
    width: 10%;
}

.expense-table th:nth-child(4),
.expense-table td:nth-child(4),
.expense-table th:nth-child(6),
.expense-table td:nth-child(6) {
    width: 15%;
}

.expense-table th:nth-child(7),
.expense-table td:nth-child(7) {
    width: 10%;
}

.col-expenseName {
    position: relative;
}

.col-expenseName:hover::after {
    content: attr(title);
    position: absolute;
    left: 0;
    top: 100%;
    z-index: 1;
    background: white;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
    white-space: normal;
    width: 200px;
    display: block;
}

.no-data-message {
    margin-top: 20px;
    padding: 10px;
    text-align: center;
    font-style: italic;
    color: #777;
}

.pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    list-style-type: none;
    padding: 0;
}

.pagination li {
    margin: 0 5px;
}

.pagination li a {
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #ddd;
    color: #333;
    border-radius: 3px;
}

.pagination li.active a {
    background-color: #3c78d8;
    color: white;
    border-color: #3c78d8;
}

.pagination li a:hover {
    background-color: #f2f2f2;
}

.loader {
    border: 4px solid #f3f3f3;
    border-top: 4px solid #3c78d8;
    border-radius: 50%;
    width: 20px;
    height: 20px;
    animation: spin 2s linear infinite;
    display: none;
    margin: 10px auto;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

.message {
    padding: 10px;
    margin: 10px 0;
    border-radius: 4px;
    display: none;
}

.message.success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
}

.message.error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}
</style>
</head>
<body>
    <div class="table-container">
        <h1>経費種別管理</h1>

        <div id="messageArea" class="message"></div>

        <div id="loader" class="loader"></div>

        <form id="searchForm" action="${pageContext.request.contextPath}/expenseType/search" method="get">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="form-group">
                <label for="expensesType">経費種別:</label> 
                <select name="expensesType" id="expensesType">
                    <option value="">全て</option>
                    <c:forEach var="type" items="${expenseTypeList}">
                        <option value="<c:out value="${type.typeCode}"/>" <c:if test="${param.expensesType eq type.typeCode}">selected</c:if>><c:out value="${type.typeName}"/></option>
                    </c:forEach>
                </select>
                <button type="submit">検索</button>

                <button type="button" id="newButton">新規</button>
            </div>
        </form>
        
        <c:if test="${empty expenseTypeGroups}">
            <div class="no-data-message">データがありません。</div>
        </c:if>
        
        <c:if test="${not empty expenseTypeGroups}">
            <c:forEach var="group" items="${expenseTypeGroups}" varStatus="groupStatus">
                <h2 data-type="${group.key}">
                    <c:out value="${group.value[0].expensesTypeName}"/>
                </h2>
                <table class="expense-table" data-type="<c:out value="${group.value[0].expensesTypeName}"/>">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>経費名称</th>
                            <th>登録者</th>
                            <th>登録日時</th>
                            <th>更新者</th>
                            <th>更新日時</th>
                            <th>編集</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="expense" items="${group.value}" varStatus="status">
                            <tr id="row_<c:out value="${expense.id}"/>">
                                <td>${status.index + 1}</td>
                                <td class="col-expenseName" title="<c:out value="${expense.expenseName}"/>">
                                    <c:out value="${expense.expenseName}" />
                                </td>
                                <td><c:out value="${expense.createdBy}" /></td>
                                <td>${expense.insertDate.toLocalDate()}</td>
                                <td><c:out value="${expense.updatedBy}" /></td>
                                <td>${expense.updateDate != null ? expense.updateDate.toLocalDate() : ''}</td>
                                <td>
                                    <!-- 编辑按钮 -->
                                    <button type="button" class="edit-button" 
                                        data-id="<c:out value="${expense.id}"/>" 
                                        data-type="<c:out value="${expense.expensesType}"/>">
                                        編集
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <c:if test="${group.value.size() > 10}">
                    <ul class="pagination" data-type="<c:out value="${group.value[0].expensesTypeName}"/>">
                        <li class="active"><a href="#" data-page="1">1</a></li>
                        <c:forEach var="i" begin="2" end="${(group.value.size() + 9) / 10}">
                            <li><a href="#" data-page="${i}">${i}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </c:forEach>
        </c:if>
    </div>

	<script>
	/**
	 * CSRF令牌の取得
	 * @details Spring Securityのセキュリティトークンを取得
	 */
	const csrfHeader = "${_csrf.headerName}";
	const csrfToken = "${_csrf.token}";
	
	/**
	 * DOM読み込み完了後の初期処理
	 * @details 各種イベントハンドラーの登録を行う
	 */
	document.addEventListener('DOMContentLoaded', function() {
	    // 新規ボタンのクリックイベント登録
	    document.getElementById('newButton').addEventListener('click', function() {
	        navigateToEdit();
	    });
	    
	    // 全ての編集ボタンにイベントハンドラーを登録
	    document.querySelectorAll('.edit-button').forEach(button => {
	        button.addEventListener('click', function() {
	            const id = this.getAttribute('data-id');
	            const type = this.getAttribute('data-type');
	            navigateToEdit(id, type);
	        });
	    });
	    
	    // ページネーションリンクのイベント登録
	    document.querySelectorAll('.pagination a').forEach(link => {
	        link.addEventListener('click', function(e) {
	            e.preventDefault();
	            const page = this.getAttribute('data-page');
	            const typeElement = this.closest('.pagination').getAttribute('data-type');
	            changePage(typeElement, page);
	        });
	    });
	    
	    // 経費種別選択変更時の検索実行
	    document.getElementById('expensesType').addEventListener('change', function() {
	        performSearch();
	    });
	    
	    // 検索フォーム送信イベントの登録
	    document.getElementById('searchForm').addEventListener('submit', function(e) {
	        e.preventDefault();
	        performSearch();
	    });
	});
	
	/**
	 * 編集画面へ遷移する
	 * @param {string} id - 経費項目ID（任意）
	 * @param {string} type - 経費種別コード（任意）
	 * @details
	 * - IDとtypeが指定されている場合は編集モード
	 * - 指定されていない場合は新規登録モード
	 */
	function navigateToEdit(id, type) {
	    let url = '${pageContext.request.contextPath}/expenseType/edit';
	    
	    // 編集モードの場合はパラメータを追加
	    if (id && type) {
	        url = url + "?id=" + encodeURIComponent(id) + "&type=" + encodeURIComponent(type);
	    }
	    
	    // 編集画面へ遷移
	    window.location.href = url;
	}
	
	/**
	 * ページネーション処理を実行する
	 * @param {string} typeCode - 経費種別コード
	 * @param {string} page - 表示するページ番号
	 * @details
	 * - 指定されたページの項目のみを表示
	 * - 1ページあたり10件表示
	 * - ページネーションボタンの状態を更新
	 */
	function changePage(typeCode, page) {
	    // 対象となるテーブルを取得
	    const table = document.querySelector('.expense-table[data-type="' + typeCode + '"]');
	    if (!table) return;
	    
	    // テーブル内の全行を取得
	    const rows = table.querySelectorAll('tbody tr');
	    
	    // ページング計算
	    const pageSize = 10;
	    const startIndex = (page - 1) * pageSize;
	    const endIndex = Math.min(startIndex + pageSize, rows.length);
	    
	    // 全ての行を一旦非表示にする
	    rows.forEach(row => {
	        row.style.display = 'none';
	    });
	    
	    // 現在のページに該当する行のみ表示
	    for (let i = startIndex; i < endIndex; i++) {
	        if (rows[i]) {
	            rows[i].style.display = '';
	        }
	    }
	    
	    // ページネーションボタンの状態を更新
	    const paginationLinks = document.querySelectorAll('.pagination[data-type="' + typeCode + '"] li');
	    paginationLinks.forEach(li => {
	        li.classList.remove('active');
	    });
	    
	    // 現在のページボタンをアクティブにする
	    const currentPageLink = document.querySelector('.pagination[data-type="' + typeCode + '"] a[data-page="' + page + '"]');
	    if (currentPageLink) {
	        currentPageLink.parentElement.classList.add('active');
	    }
	}
	
	/**
	 * 検索処理を実行する
	 * @details
	 * - 選択された経費種別でフィルタリング
	 * - ローディング表示を行う
	 * - 検索結果画面へリダイレクト
	 */
	function performSearch() {
	    // ローディング表示を開始
	    document.getElementById('loader').style.display = 'block';
	    
	    // 検索条件の取得
	    const expensesType = document.getElementById('expensesType').value;
	    
	    // 検索URLの構築
	    const baseUrl = "${pageContext.request.contextPath}/expenseType/search";
	    const url = baseUrl + "?expensesType=" + encodeURIComponent(expensesType);
	    
	    // 検索結果画面へリダイレクト
	    window.location.href = url;
	}
	
	/**
	 * メッセージを表示する
	 * @param {string} message - 表示するメッセージ内容
	 * @param {string} type - メッセージタイプ ('success' または 'error')
	 * @details
	 * - 成功・エラーメッセージを画面上部に表示
	 * - 5秒後に自動的に非表示にする
	 */
	function showMessage(message, type) {
	    const messageArea = document.getElementById('messageArea');
	    messageArea.textContent = message;
	    messageArea.className = 'message ' + type;
	    messageArea.style.display = 'block';
	    
	    // 5秒後に自動非表示
	    setTimeout(() => {
	        messageArea.style.display = 'none';
	    }, 5000);
	}
	
	/**
	 * イベントハンドラーを再登録する
	 * @details
	 * - 動的に追加された要素に対してイベントハンドラーを設定
	 * - Ajax更新後などに使用
	 */
	function registerEventHandlers() {
	    // 編集ボタンのイベント再登録
	    document.querySelectorAll('.edit-button').forEach(button => {
	        button.addEventListener('click', function() {
	            const id = this.getAttribute('data-id');
	            const type = this.getAttribute('data-type');
	            navigateToEdit(id, type);
	        });
	    });
	    
	    // ページネーションのイベント再登録
	    document.querySelectorAll('.pagination a').forEach(link => {
	        link.addEventListener('click', function(e) {
	            e.preventDefault();
	            const page = this.getAttribute('data-page');
	            const typeElement = this.closest('.pagination').getAttribute('data-type');
	            changePage(typeElement, page);
	        });
	    });
	    
	    // 検索フォームのイベント再登録
	    document.getElementById('searchForm').addEventListener('submit', function(e) {
	        e.preventDefault();
	        performSearch();
	    });
	    
	    // 新規ボタンのイベント再登録
	    const newButton = document.getElementById('newButton');
	    if (newButton) {
	        newButton.addEventListener('click', function() {
	            navigateToEdit();
	        });
	    }
	}
	
	/**
	 * 初期表示時のページネーション設定
	 * @details 各テーブルの最初のページを表示
	 */
	document.querySelectorAll('.expense-table').forEach(table => {
	    const typeCode = table.getAttribute('data-type');
	    changePage(typeCode, 1);
	});
	</script>
</body>
</html>