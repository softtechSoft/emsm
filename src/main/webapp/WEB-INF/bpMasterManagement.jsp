<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ソフトテク株式会社 - BPマスタ管理</title>
    
    <style>
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .tab-container {
            margin-bottom: 20px;
        }
        .tab-button {
            background-color: #f1f1f1;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            margin-right: 5px;
        }
        .tab-button.active {
            background-color: #007bff;
            color: white;
        }
        .tab-content {
            display: none;
            padding: 20px;
            border: 1px solid #ddd;
            border-top: none;
        }
        .tab-content:first-child {
            display: block;
        }
        .master-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        .master-table th, .master-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .master-table th {
            background-color: #f2f2f2;
        }
        .action-buttons {
            margin-top: 20px;
        }
        .action-buttons input[type="button"] {
            margin-right: 10px;
            padding: 8px 20px;
        }
        .add-button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
            margin-bottom: 10px;
        }
        .edit-button {
            background-color: #ffc107;
            color: black;
            border: none;
            padding: 4px 8px;
            cursor: pointer;
            margin-right: 5px;
        }
        .delete-button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 4px 8px;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        // タブ切り替え
        function showTab(tabName) {
            // すべてのタブコンテンツを非表示
            var tabContents = document.getElementsByClassName('tab-content');
            for (var i = 0; i < tabContents.length; i++) {
                tabContents[i].style.display = 'none';
            }
            
            // すべてのタブボタンのアクティブ状態を解除
            var tabButtons = document.getElementsByClassName('tab-button');
            for (var i = 0; i < tabButtons.length; i++) {
                tabButtons[i].classList.remove('active');
            }
            
            // 選択されたタブを表示
            document.getElementById(tabName).style.display = 'block';
            event.target.classList.add('active');
        }

        // 新規追加
        function addNew(type) {
            document.getElementById('actionType').value = 'add';
            document.getElementById('masterType').value = type;
            document.getElementById('masterForm').action = 'toInitBpMaster';
            document.getElementById('masterForm').submit();
        }

        // 編集
        function editItem(type, id) {
            document.getElementById('actionType').value = 'edit';
            document.getElementById('masterType').value = type;
            document.getElementById('itemId').value = id;
            document.getElementById('masterForm').action = 'toInitBpMaster';
            document.getElementById('masterForm').submit();
        }

        // 削除
        function deleteItem(type, id) {
            if (confirm('本当に削除しますか？')) {
                document.getElementById('masterType').value = type;
                document.getElementById('itemId').value = id;
                document.getElementById('masterForm').action = 'deleteBpMaster';
                document.getElementById('masterForm').submit();
            }
        }

        // 戻る
        function goBack() {
            window.location.href = "bpPaymentList";
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>BPマスタ管理</h2>
        
        <!-- 成功メッセージ -->
        <c:if test="${not empty successMessage}">
            <div style="color: green; margin-bottom: 15px;">
                <c:out value="${successMessage}" />
            </div>
        </c:if>
        
        <!-- エラーメッセージ -->
        <c:if test="${not empty error}">
            <div style="color: red; margin-bottom: 15px;">
                <c:out value="${error}" />
            </div>
        </c:if>
        
        <!-- タブ -->
        <div class="tab-container">
            <button class="tab-button active" onclick="showTab('employee-tab')">BP社員マスタ</button>
            <button class="tab-button" onclick="showTab('company-tab')">BP会社マスタ</button>
            <button class="tab-button" onclick="showTab('dispatch-company-tab')">派遣会社マスタ</button>
        </div>
        
        <!-- 社員マスタ -->
        <div id="employee-tab" class="tab-content">
            <h3>社員マスタ</h3>
            <input type="button" class="add-button" value="新規追加" onclick="addNew('employee');" />
            
            <c:choose>
                <c:when test="${not empty employeeList}">
                    <table class="master-table">
                        <thead>
                            <tr>
                                <th>社員ID</th>
                                <th>社員名</th>
                                <th>役割</th>
                                <th>電話番号</th>
                                <th>メール</th>
                                <th>ステータス</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${employeeList}" var="employee">
                                <tr>
                                    <td>${employee.employeeId}</td>
                                    <td>${employee.name}</td>
                                    <td>${employee.role}</td>
                                    <td>${employee.phone}</td>
                                    <td>${employee.email}</td>
                                    <td>${employee.status == '1' ? '有効' : '無効'}</td>
                                    <td>
                                        <input type="button" class="edit-button" value="編集" 
                                               onclick="editItem('employee', '${employee.employeeId}');" />
                                        <input type="button" class="delete-button" value="削除" 
                                               onclick="deleteItem('employee', '${employee.employeeId}');" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>社員データがありません。</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- BP会社マスタ -->
        <div id="company-tab" class="tab-content">
            <h3>BP会社マスタ</h3>
            <input type="button" class="add-button" value="新規追加" onclick="addNew('company');" />
            
            <c:choose>
                <c:when test="${not empty companyList}">
                    <table class="master-table">
                        <thead>
                            <tr>
                                <th>会社ID</th>
                                <th>会社名</th>
                                <th>単価</th>
                                <th>住所</th>
                                <th>電話番号</th>
                                <th>連絡先</th>
                                <th>ステータス</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${companyList}" var="company">
                                <tr>
                                    <td>${company.companyId}</td>
                                    <td>${company.companyName}</td>
                                    <td><fmt:formatNumber value="${company.unitPrice}" pattern="#,##0"/></td>
                                    <td>${company.address}</td>
                                    <td>${company.phone}</td>
                                    <td>${company.contactPerson}</td>
                                    <td>${company.status == '1' ? '有効' : '無効'}</td>
                                    <td>
                                        <input type="button" class="edit-button" value="編集" 
                                               onclick="editItem('company', '${company.companyId}');" />
                                        <input type="button" class="delete-button" value="削除" 
                                               onclick="deleteItem('company', '${company.companyId}');" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>BP会社データがありません。</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- 派遣会社マスタ -->
        <div id="dispatch-company-tab" class="tab-content">
            <h3>派遣会社マスタ</h3>
            <input type="button" class="add-button" value="新規追加" onclick="addNew('dispatch-company');" />
            
            <c:choose>
                <c:when test="${not empty dispatchCompanyList}">
                    <table class="master-table">
                        <thead>
                            <tr>
                                <th>会社ID</th>
                                <th>会社名</th>
                                <th>会社種類</th>
                                <th>郵便番号</th>
                                <th>住所</th>
                                <th>電話番号</th>
                                <th>連絡先</th>
                                <th>ステータス</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dispatchCompanyList}" var="company">
                                <tr>
                                    <td>${company.companyID}</td>
                                    <td>${company.companyName}</td>
                                    <td>${company.companyType == '0' ? '派遣会社' : 'その他'}</td>
                                    <td>${company.postCode}</td>
                                    <td>${company.address}</td>
                                    <td>${company.phoneNumber}</td>
                                    <td>${company.contactName}</td>
                                    <td>${company.contractStatus == '0' ? '有効' : '無効'}</td>
                                    <td>
                                        <input type="button" class="edit-button" value="編集" 
                                               onclick="editItem('dispatch-company', '${company.companyID}');" />
                                        <input type="button" class="delete-button" value="削除" 
                                               onclick="deleteItem('dispatch-company', '${company.companyID}');" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>派遣会社データがありません。</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- 隠しフォーム -->
        <form id="masterForm" method="post" action="">
            <input type="hidden" id="actionType" name="actionType" />
            <input type="hidden" id="masterType" name="masterType" />
            <input type="hidden" id="itemId" name="id" />
        </form>
        
        <div class="action-buttons">
            <input type="button" value="戻る" onclick="goBack();" />
        </div>
    </div>
</body>
</html>