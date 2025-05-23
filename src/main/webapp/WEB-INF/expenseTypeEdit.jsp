<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>経費種別編集</title>
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

.category-header {
    display: flex;
    align-items: center;
    margin-top: 30px;
    margin-bottom: 10px;
}

.category-title {
    font-size: 20px;
    font-weight: bold;
    border-left: 5px solid #3c78d8;
    padding-left: 10px;
    margin-right: 20px;
}

.category-title-input {
    font-size: 20px;
    font-weight: bold;
    padding: 5px;
    margin-right: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    display: none;
}

.error-message {
    color: red;
    text-align: center;
    margin-top: 10px;
}

.expense-table {
    width: 100%;
    margin-top: 10px;
    border-collapse: collapse;
    table-layout: fixed;
}

.add-expense-table {
    width: 100%;
    margin-top: 30px;
    border-collapse: collapse;
}

.add-category-table {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 30px;
    border-collapse: collapse;
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
    width: 8%;
}

.expense-table th:nth-child(2),
.expense-table td:nth-child(2) {
    width: 72%;
}

.expense-table th:nth-child(3),
.expense-table td:nth-child(3) {
    width: 20%;
}
button {
    padding: 5px 10px;
    font-size: 14px;
    cursor: pointer;
    margin-right: 5px;
    background-color: #f2f2f2;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button:hover {
    background-color: #e6e6e6;
}

.add-item-row {
    background-color: #e8f5e9;
}

.add-item-row td input {
    width: 90%;
    padding: 5px;
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

.btn-container {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}

input[type="text"] {
    width: 90%;
    padding: 5px;
}

.editing {
    background-color: #fff9c4;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
    border-radius: 5px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

.hidden-file-input {
    display: none;
}

.col-expenseName {
    position: relative;
}

.thumb-img {
    max-width: 100px;
    max-height: 100px;
    border: 1px solid #ccc;
    display: none;
    margin: 5px auto;
}

.new-category-section {
    margin-top: 30px;
    margin-bottom: 30px;
    border: 1px solid #ddd;
    padding: 15px;
    border-radius: 5px;
    background-color: #f9f9f9;
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
        <h1>経費種別編集</h1>

        <div id="messageArea" class="message"></div>
        

        <div id="loader" class="loader"></div>


        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>


        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


        <div class="new-category-section">
            <div class="category-header">
                <div class="category-title">新規経費種別追加</div>
                <button type="button" id="showNewCategoryFormBtn">追加</button>
            </div>

            <div id="newCategoryForm" style="display: none; margin-top: 15px;">
                <table class="add-category-table">
                    <thead>
                        <tr>
                            <th>経費種別</th>
                            <th>経費名称</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" id="newCategoryName" name="expenseName" required style="width: 90%;" maxlength="50">
                            </td>
                            <td>
                                <input type="text" id="newExpenseItemName" name="expenseItemName" required style="width: 90%;" maxlength="100">
                            </td>
                            <td>
                                <button type="button" id="addCategoryBtn">追加</button>
                                <button type="button" id="cancelCategoryBtn">キャンセル</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <c:forEach var="group" items="${expenseTypeGroups}">
            <div class="category-header" id="category-header-<c:out value="${group.key}"/>">
                <div class="category-title" id="category<c:out value="${group.key}"/>-title"><c:out value="${group.value[0].expensesTypeName}"/></div>
                <input type="text" class="category-title-input" id="category<c:out value="${group.key}"/>-title-input" value="<c:out value="${group.value[0].expensesTypeName}"/>" maxlength="50">
                <button type="button" class="edit-category-btn" data-category-id="${group.key}">編集</button>
                <button type="button" class="delete-category-btn" data-category-id="${group.key}">削除</button>
            </div>
            <table class="expense-table" id="category<c:out value="${group.key}"/>-table">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>経費名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="expense" items="${group.value}" varStatus="status">
                        <tr id="row_<c:out value="${expense.id}"/>" data-id="<c:out value="${expense.id}"/>">
                            <td>${status.index + 1}</td>
                            <td>
                                <input type="text" class="expense-name" value="<c:out value="${expense.expenseName}"/>" readonly maxlength="100">
                            </td>
                            <td>
                                <button type="button" class="edit-expense-btn" data-expense-id="${expense.id}">編集</button>
                                <button type="button" class="delete-expense-btn" data-expense-id="${expense.id}">削除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <tr id="add-item-row-<c:out value="${group.key}"/>" class="add-item-row" style="display: none;">
                        <td></td>
                        <td>
                            <input type="hidden" id="new-item-type-name-<c:out value="${group.key}"/>" 
                                   value="<c:out value="${group.value[0].expensesTypeName}"/>">
                            <input type="text" id="new-item-name-<c:out value="${group.key}"/>" 
                                   placeholder="新規経費名称" maxlength="100">
                        </td>
                        <td>
                            <button type="button" class="save-new-item-btn" data-category-id="<c:out value="${group.key}"/>">保存</button>
                            <button type="button" class="cancel-new-item-btn" data-category-id="<c:out value="${group.key}"/>">キャンセル</button>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <button type="button" class="add-item-btn" data-category-id="<c:out value="${group.key}"/>">新規経費項目追加</button>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </c:forEach>

        <div class="btn-container">
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/expenseType'">戻る</button>
        </div>
    </div>

    <script>
	/**
	 * 簡略化されたDOM要素選択ヘルパー関数
	 * @param {string} sel - CSSセレクター
	 * @param {Element} ctx - 検索コンテキスト（デフォルトはdocument）
	 * @returns {Element} 選択された要素
	 */
	const $ = (sel, ctx = document) => ctx.querySelector(sel);
	
	/**
	 * コンテキストパスを取得する
	 * @returns {string} アプリケーションのコンテキストパス
	 */
	const ctxPath = (() => '/' + location.pathname.split('/')[1])();
	
	/**
	 * メッセージを表示する
	 * @param {string} text - 表示するメッセージ
	 * @param {string} type - メッセージタイプ（'success' または 'error'）
	 */
	function msg(text, type='success') {
	  const box = $('#messageArea');
	  if (!box) return alert(text);
	  box.textContent = text;
	  box.className = 'message ' + type;
	  box.style.display = 'block';
	  setTimeout(()=> box.style.display='none', 4000);
	}
	
	// ローディング表示要素の取得
	const loader = $('#loader');
	
	/**
	 * ローディング表示を開始する
	 */
	const loaderOn  = () => loader && (loader.style.display='block');
	
	/**
	 * ローディング表示を終了する
	 */
	const loaderOff = () => loader && (loader.style.display='none');
	
	/**
	 * ドキュメント全体のクリックイベントを委譲で処理
	 * @details
	 * - ボタンクリックを一元的に処理
	 * - イベント委譲パターンを使用して動的に追加された要素にも対応
	 */
	document.addEventListener('click', e => {
	  const btn = e.target.closest('button');
	  if (!btn) return;
	
	  /* ------ 新規経費種別関連 ------ */
	  // 新規経費種別追加フォームの表示
	  if (btn.id === 'showNewCategoryFormBtn') { 
	    $('#newCategoryForm').style.display='block'; 
	    btn.style.display='none'; 
	    return; 
	  }
	  // 新規経費種別追加のキャンセル
	  if (btn.id === 'cancelCategoryBtn') { 
	    $('#newCategoryForm').style.display='none'; 
	    $('#showNewCategoryFormBtn').style.display='inline-block'; 
	    return; 
	  }
	  // 新規経費種別の追加実行
	  if (btn.id === 'addCategoryBtn') return addNewCategory();
	
	  /* ------ 経費項目：追加／取消／保存 ------ */
	  // 新規経費項目追加行の表示
	  if (btn.classList.contains('add-item-btn')) return showAddItemRow(btn.dataset.categoryId);
	  // 新規経費項目の保存
	  if (btn.classList.contains('save-new-item-btn')) return saveNewItem(btn.dataset.categoryId);
	  // 新規経費項目追加のキャンセル
	  if (btn.classList.contains('cancel-new-item-btn')) return cancelAddItem(btn.dataset.categoryId);
	
	  /* ------ 経費項目：編集／取消／保存／削除 ------ */
	  // 経費項目の編集開始
	  if (btn.classList.contains('edit-expense-btn')) return editExpense(btn.dataset.expenseId);
	  // 経費項目の保存
	  if (btn.classList.contains('save-expense-btn')) return saveExpense(btn.dataset.expenseId);
	  // 経費項目編集のキャンセル
	  if (btn.classList.contains('cancel-expense-btn')) return cancelEdit(btn.dataset.expenseId);
	  // 経費項目の削除
	  if (btn.classList.contains('delete-expense-btn')) return deleteExpense(btn.dataset.expenseId);
	
	  /* ------ 経費種別：編集／保存／削除 ------ */
	  // 経費種別名の編集開始
	  if (btn.classList.contains('edit-category-btn')) return editCategoryTitle(btn.dataset.categoryId);
	  // 経費種別名の保存
	  if (btn.classList.contains('save-category-btn')) return saveCategoryTitle(btn.dataset.categoryId);
	  // 経費種別の削除
	  if (btn.classList.contains('delete-category-btn')) return deleteCategory(btn.dataset.categoryId);
	});
	
	/**
	 * 新規経費種別を追加する
	 * @details
	 * - 入力値の検証
	 * - サーバーへの非同期通信
	 * - 成功時は画面をリロード
	 */
	function addNewCategory() {
	  // 入力値の取得と検証
	  const expenseName = $('#newCategoryName').value.trim();
	  const expenseItemName = $('#newExpenseItemName').value.trim();
	  
	  // 必須項目チェック
	  if (!expenseName) return msg('経費種別名称を入力してください','error');
	  if (!expenseItemName) return msg('経費名称を入力してください','error');
	  
	  // 確認ダイアログ
	  if (!confirm('新しい経費種別を追加しますか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  // サーバーへのデータ送信
	  fetch(ctxPath+'/expenseType/addCategory',{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      expenseName: expenseName, 
	      expenseItemName: expenseItemName,
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>r.json())
	  .then(d=>{
	    if(d.status==='ok'){ 
	      msg('追加しました'); 
	      location.reload(); 
	    }
	    else throw new Error(d.message||'追加に失敗');
	  })
	  .catch(err=>msg(err.message,'error'))
	  .finally(loaderOff);
	}
	
	/**
	 * 経費項目追加行を表示する
	 * @param {string} cid - 経費種別ID
	 * @details
	 * - 追加行を表示
	 * - 入力フィールドをクリアしてフォーカス
	 * - 序号を再計算
	 */
	function showAddItemRow(cid){
	  const row = $('#add-item-row-'+cid);
	  if(!row) return;
	  
	  // 追加行を表示
	  row.style.display='table-row';
	  
	  // 入力フィールドをクリアしてフォーカス
	  $('#new-item-name-'+cid).value='';
	  $('#new-item-name-'+cid).focus();
	  
	  // 序号を再計算（既存の項目数 + 1）
	  const idx = $('#category'+cid+'-table tbody').querySelectorAll('tr:not(.add-item-row)').length;
	  row.querySelector('td').textContent = idx+1;
	}
	
	/**
	 * 経費項目追加をキャンセルする
	 * @param {string} cid - 経費種別ID
	 */
	function cancelAddItem(cid){ 
	  $('#add-item-row-'+cid).style.display='none'; 
	}
	
	/**
	 * 新規経費項目を保存する
	 * @param {string} cid - 経費種別ID
	 * @details
	 * - 入力値の検証
	 * - サーバーへの非同期通信
	 * - 成功時は画面をリロード
	 */
	function saveNewItem(cid){
	  // 入力値の取得
	  const name = $('#new-item-name-'+cid).value.trim();
	  const typeName = $('#new-item-type-name-'+cid).value;
	  
	  // 必須項目チェック
	  if(!name) return msg('経費名称を入力してください','error');
	  
	  // 確認ダイアログ
	  if(!confirm('追加しますか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  // サーバーへのデータ送信
	  fetch(ctxPath+'/expenseType/addItem',{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      expensesType: cid, 
	      expensesTypeName: typeName, 
	      expenseName: name,
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>r.json())
	  .then(d=>{
	    if(d.status==='ok'){ 
	      msg('追加しました'); 
	      location.reload(); 
	    }
	    else throw new Error(d.message||'追加に失敗');
	  })
	  .catch(err=>msg(err.message,'error'))
	  .finally(loaderOff);
	}
	
	/**
	 * 経費項目の編集を開始する
	 * @param {string} id - 経費項目ID
	 * @details
	 * - 該当行を編集モードに変更
	 * - 入力フィールドを編集可能にする
	 * - 操作ボタンを編集用に変更
	 */
	function editExpense(id){
	  console.log('editExpense called with id:', id);  // デバッグ用
	  const row = $('#row_'+id);
	  console.log('Found row:', row);  // デバッグ用
	  
	  // 行の存在確認
	  if(!row) {
	    console.error('Row not found for id:', id);
	    console.log('Available rows:');
	    document.querySelectorAll('tr[id^="row"]').forEach(r => {
	      console.log('- Found row with id:', r.id);
	    });
	    return;
	  }
	  
	  // 編集モードのスタイルを適用
	  row.classList.add('editing');
	  
	  // 入力フィールドを編集可能にする
	  const inp = row.querySelector('.expense-name'); 
	  inp.readOnly = false; 
	  inp.focus();
	  
	  // 操作ボタンを更新
	  const td = row.lastElementChild;
	  td.innerHTML =
	    '<button type="button" class="save-expense-btn"  data-expense-id="' + id + '">保存</button>' +
	    '<button type="button" class="cancel-expense-btn" data-expense-id="' + id + '">キャンセル</button>';
	}
	
	/**
	 * 経費項目の編集をキャンセルする
	 * @param {string} id - 経費項目ID
	 * @details 画面をリロードして変更を破棄
	 */
	function cancelEdit(id){ 
	  location.reload(); 
	}
	
	/**
	 * 経費項目の変更を保存する
	 * @param {string} id - 経費項目ID
	 * @details
	 * - 入力値の検証
	 * - サーバーへの非同期通信
	 * - 成功時は画面をリロード
	 */
	function saveExpense(id){
	  console.log('saveExpense called with id:', id);  // デバッグ用
	  const row = $('#row_'+id);
	  console.log('Found row:', row);  // デバッグ用
	  
	  // 行の存在確認
	  if(!row) {
	    console.error('Row not found for id:', id);
	    return;
	  }
	  
	  // 入力値の取得
	  const name = row.querySelector('.expense-name').value.trim();
	  console.log('Expense name:', name);  // デバッグ用
	  
	  // 必須項目チェック
	  if(!name) return msg('経費名称を入力してください','error');
	  
	  // 確認ダイアログ
	  if(!confirm('保存しますか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'Content-Type': 'application/x-www-form-urlencoded',
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  console.log('Sending request to:', ctxPath+'/expenseType/updateItem');  // デバッグ用
	  console.log('Request data:', {id:id, expenseName:name, '_csrf': csrfToken});  // デバッグ用
	  
	  // サーバーへのデータ送信
	  fetch(ctxPath+'/expenseType/updateItem',{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      id: id, 
	      expenseName: name,
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>{
	    console.log('Response status:', r.status);  // デバッグ用
	    if (!r.ok) {
	      throw new Error('HTTP Error: ' + r.status);
	    }
	    return r.json();
	  })
	  .then(d=>{
	    console.log('Response data:', d);  // デバッグ用
	    if(d.status==='ok'){ 
	      msg('更新しました'); 
	      location.reload(); 
	    } else {
	      throw new Error(d.message||'更新に失敗');
	    }
	  })
	  .catch(err=>{
	    console.error('Update error:', err);
	    msg(err.message,'error');
	  })
	  .finally(loaderOff);
	}
	
	/**
	 * 経費項目を削除する
	 * @param {string} id - 経費項目ID
	 * @details
	 * - 削除確認ダイアログ表示
	 * - サーバーへの非同期通信（論理削除）
	 * - 成功時は画面をリロード
	 */
	function deleteExpense(id){
	  // 削除確認
	  if(!confirm('削除しますか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  // サーバーへの削除リクエスト
	  fetch(ctxPath+'/expenseType/deleteItem/'+id,{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>r.json())
	  .then(d=>{
	    if(d.status==='ok'){ 
	      msg('削除しました'); 
	      location.reload(); 
	    }
	    else throw new Error(d.message||'削除に失敗');
	  })
	  .catch(err=>msg(err.message,'error'))
	  .finally(loaderOff);
	}
	
	/**
	 * 経費種別名の編集を開始する
	 * @param {string} cid - 経費種別ID
	 * @details
	 * - タイトル表示を非表示
	 * - 入力フィールドを表示
	 * - ボタンを保存用に変更
	 */
	function editCategoryTitle(cid){
	  // タイトル表示を非表示
	  $('#category'+cid+'-title').style.display='none';
	  
	  // 入力フィールドを表示してフォーカス
	  const inp = $('#category'+cid+'-title-input');
	  inp.style.display='inline-block'; 
	  inp.focus();
	  
	  // ボタンを保存用に変更
	  const btn = $('.edit-category-btn[data-category-id="'+cid+'"]');
	  btn.textContent='保存'; 
	  btn.classList.replace('edit-category-btn','save-category-btn');
	}
	
	/**
	 * 経費種別名の変更を保存する
	 * @param {string} cid - 経費種別ID
	 * @details
	 * - 入力値の検証
	 * - サーバーへの非同期通信
	 * - 成功時は画面をリロード
	 */
	function saveCategoryTitle(cid){
	  // 入力値の取得
	  const newName = $('#category'+cid+'-title-input').value.trim();
	  
	  // 必須項目チェック
	  if(!newName) return msg('経費種別名を入力してください','error');
	  
	  // 確認ダイアログ
	  if(!confirm('保存しますか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  // サーバーへのデータ送信
	  fetch(ctxPath+'/expenseType/updateCategory',{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      id: cid, 
	      expenseName: newName,
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>r.json())
	  .then(d=>{
	    if(d.status==='ok'){ 
	      msg('更新しました'); 
	      location.reload(); 
	    }
	    else throw new Error(d.message||'更新に失敗');
	  })
	  .catch(err=>msg(err.message,'error'))
	  .finally(loaderOff);
	}
	
	/**
	 * 経費種別を削除する
	 * @param {string} cid - 経費種別ID
	 * @details
	 * - 関連する経費項目も含めて削除される旨を警告
	 * - サーバーへの非同期通信（論理削除）
	 * - 成功時は画面をリロード
	 */
	function deleteCategory(cid){
	  // 削除確認（関連項目も削除される旨の警告）
	  if(!confirm('関連する経費項目も削除されます。よろしいですか？')) return;
	  
	  // ローディング表示開始
	  loaderOn();
	  
	  // CSRF令牌の取得
	  const csrfToken = document.querySelector('input[name="_csrf"]')?.value || '';
	  
	  // リクエストヘッダーの設定
	  const headers = {
	    'X-Requested-With': 'XMLHttpRequest'
	  };
	  
	  // サーバーへの削除リクエスト
	  fetch(ctxPath+'/expenseType/deleteCategory/'+cid,{
	    method:'POST',
	    headers: headers,
	    body: new URLSearchParams({
	      '_csrf': csrfToken
	    })
	  })
	  .then(r=>r.json())
	  .then(d=>{
	    if(d.status==='ok'){ 
	      msg('削除しました'); 
	      location.reload(); 
	    }
	    else throw new Error(d.message||'削除に失敗');
	  })
	  .catch(err=>msg(err.message,'error'))
	  .finally(loaderOff);
	}
	</script>

</body>
</html>