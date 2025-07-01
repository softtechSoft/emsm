<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>取引先リスト</title>
    <style>
        body {
            font-family: "メイリオ", Meiryo, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        h2 {
            margin: 0 0 20px 0;
            color: #333;
        }
        .search-area {
            background-color: #f8f9fa;
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .search-box {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .search-left {
            display: flex;
            align-items: center;
        }
        .search-label {
            margin-right: 10px;
        }
        .search-input {
            padding: 6px;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-right: 10px;
        }
        .btn {
            padding: 6px 12px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            color: white;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
        }
        .btn-search {
            background-color: #0275d8;
        }
        .btn-new {
            background-color: #0275d8;
        }
        .btn-edit {
            background-color: #0275d8;
            margin-right: 5px;
        }
        .btn-delete {
            background-color: #dc3545;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            margin-top: 10px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #0275d8;
            color: white;
        }
        tr:nth-child(odd) {
            background-color: #dcfeeb;
        }
        tr:nth-child(even) {
            background-color: #bfe1ff;
        }
        .action-buttons {
            white-space: nowrap;
        }
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .alert-success {
            color: #155724;
            background-color: #d4edda;
            border-color: #c3e6cb;
        }
        .alert-danger {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }
    </style>
</head>
<body>
    <h2>取引先リスト</h2>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
    
    <div class="search-area">
        <form action="${pageContext.request.contextPath}/torihiki/search" method="get" class="search-box">
            <div class="search-left">
                <label class="search-label">取引先</label>
                <input type="text" 
                       name="keyword" 
                       value="${keyword}" 
                       class="search-input" 
                       placeholder="取引先ID・取引先名称で検索"
                       maxlength="50"
                >
                <button type="submit" class="btn btn-search">検索</button>
            </div>
            <a href="${pageContext.request.contextPath}/torihiki/new" class="btn btn-new">新規登録</a>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>取引先ID</th>
                <th>取引先名称</th>
                <th>種類</th>
                <th>郵便番号</th>
                <th>住所</th>
                <th>契約日</th>
                <th>電話番号</th>
                <th>連絡先名</th>
                <th>メールアドレス</th>
                <th>契約状況</th>
                <th>評判レベル</th>
                <th>作成日</th>
                <th>更新日</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${torihikiList}" var="torihiki">
                <tr>
                    <td>${torihiki.companyID}</td>
                    <td>${torihiki.companyName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${torihiki.companyType == '0'}">法人</c:when>
                            <c:when test="${torihiki.companyType == '1'}">個人事業</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:set var="postCode" value="${torihiki.postCode}" />
                        <c:if test="${fn:length(postCode) == 7}">
                            ${fn:substring(postCode, 0, 3)}-${fn:substring(postCode, 3, 7)}
                        </c:if>
                        <c:if test="${fn:length(postCode) != 7}">
                            ${postCode}
                        </c:if>
                    </td>
                    <td>${torihiki.address}</td>
                    <td>
                        <c:set var="date" value="${torihiki.basicContractDate}" />
                        <c:if test="${fn:length(date) == 8}">
                            ${fn:substring(date, 0, 4)}/${fn:substring(date, 4, 6)}/${fn:substring(date, 6, 8)}
                        </c:if>
                        <c:if test="${fn:length(date) != 8}">
                            ${date}
                        </c:if>
                    </td>
                    <td>
  <c:set var="phone" value="${torihiki.phoneNumber}" />
  <c:choose>
    <c:when test="${fn:length(phone) == 11}">
      ${fn:substring(phone, 0, 3)}-${fn:substring(phone, 3, 7)}-${fn:substring(phone, 7, 11)}
    </c:when>
    <c:when test="${fn:length(phone) == 10}">
      ${fn:substring(phone, 0, 2)}-${fn:substring(phone, 2, 6)}-${fn:substring(phone, 6, 10)}
    </c:when>
    <c:otherwise>
      ${phone}
    </c:otherwise>
  </c:choose>
</td>
                    <td>${torihiki.contactName}</td>
                    <td>${torihiki.mail}</td>
                    <td>
                        <c:choose>
                            <c:when test="${torihiki.contractStatus == '0'}">契約中</c:when>
                            <c:when test="${torihiki.contractStatus == '1'}">未契約</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${torihiki.level == '0'}">優良</c:when>
                            <c:when test="${torihiki.level == '1'}">一般</c:when>
                            <c:when test="${torihiki.level == '2'}">良くない</c:when>
                            <c:when test="${torihiki.level == '3'}">強制取引終了</c:when>
                        </c:choose>
                    </td>
                    <td>${torihiki.insertDate}</td>
                    <td>${torihiki.updateDate}</td>
                    <td class="action-buttons">
                        <a href="${pageContext.request.contextPath}/torihiki/edit/${torihiki.companyID}" 
                           class="btn btn-edit">編集</a>
                        <a href="${pageContext.request.contextPath}/torihiki/delete/${torihiki.companyID}" 
                           class="btn btn-delete" 
                           onclick="return confirm('本当に削除しますか？')">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html> 