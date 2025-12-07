<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2025/11/05
  Time: 11:33
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        <!--検索-->
        function toSearchJsp() {
            document.theForm.submit();
        }

        // 更新ボタン処理
        function toUpdateJsp(holdingTaxID) {
            // 更新
            document.getElementById('insertFlg').value = '1';
            document.getElementById('holdingTaxID').value = holdingTaxID;
            document.theForm.action = "toInitHoldingTaxInfo";
            document.theForm.submit();
        }

        // 新規ボタン処理
        function toInsertJsp() {
            // 新規
            document.getElementById('insertFlg').value = '0';
            document.theForm.action = "toInitHoldingTaxInfo";
            document.theForm.submit();
        }
    </script>
    <title>ソフトテク株式会社-社内管理システム</title>
</head>
<body>
<h2>所得税マスター管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="holdingTaxInfoFormBean" action="holdingTaxInfoList">
    <input type="hidden" id="holdingTaxID" name="holdingTaxID" value="${holdingTaxInfoFormBean.holdingTaxID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${holdingTaxInfoFormBean.insertFlg}"/>

    <b>社員:</b>
    <form:select path="employeeID">
        <%-- <form:options items="${employeeList}" itemLabel="employeeID" itemValue="employeeID"/> --%>
        <form:option value="">全社員</form:option>
        <form:options items="${employeeList}" itemLabel="employeeName" itemValue="employeeID"/>
    </form:select>
    <b>年度:</b>
	<form:select path="year">
	    <form:option value="">全年度</form:option>
	    <form:options items="${yearList}"/>
	</form:select>
    <td></td>
    <input type="button" name="search" value="検索" onclick="toSearchJsp()"/>

    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>

    <table border="1" class="incomeTaxInfoList-table">
        <tr>
            <th width="95">所得税ID</th>
            <th width="100">社員名</th>
            <th width="80">対象年度</th>
            <th width="125">一月住民税</th>
            <th width="150">二月住民税</th>
            <th width="125">三月住民税</th>
            <th width="150">四月住民税</th>
            <th width="125">五月住民税</th>
            <th width="150">六月住民税</th>
            <th width="125">七月住民税</th>
            <th width="150">八月住民税</th>
            <th width="125">九月住民税</th>
            <th width="150">十月住民税</th>
            <th width="125">十一月住民税</th>
            <th width="150">十二月住民税</th>
            <th width="100">利用ステータス</th>
            <th width="70">作成日</th>
            <th width="70">更新日</th>
            <th width="45">更新</th>
        </tr>

        <c:forEach items="${list}" var="holdingTaxList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${holdingTaxList.holdingTaxID}"/></td>
                <td><c:out value="${holdingTaxList.employeeName}"/></td>
                <td><c:out value="${holdingTaxList.year}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax1}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax2}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax3}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax4}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax5}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax6}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax7}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax8}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax9}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax10}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax11}"/></td>
                <td><c:out value="${holdingTaxList.incomeTax12}"/></td>
                <td><c:out value="${holdingTaxList.status == '0' ? '使用完了' : '使用中'}"/></td>
                <td><c:out value="${holdingTaxList.insertDate}"/></td>
                <td><c:out value="${holdingTaxList.updateDate}"/></td>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out value="${holdingTaxList.holdingTaxID}"/>');"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" name="insert" value="新規追加" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>

