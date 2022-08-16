<%--
  Created by IntelliJ IDEA.
  User: 孙晔
  Date: 2022/08/12
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
        function toUpdateJsp(incomeTaxID) {
            // 更新
            document.getElementById('insertFlg').value = '1';
            document.getElementById('incomeTaxID').value = incomeTaxID;
            document.theForm.action = "toinitIncomeTaxInfo";
            document.theForm.submit();
        }

        // 新規ボタン処理
        function toInsertJsp() {
            // 新規
            document.getElementById('insertFlg').value = '0';
            document.theForm.action = "toinitIncomeTaxInfo";
            document.theForm.submit();
        }
    </script>
    <title>ソフトテク株式会社-社内管理システム</title>
</head>
<body>
<h2>所得税と住民税マスター管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="incomeTaxInfoFormBean"
           action="incomeTaxInfoList">
    <input type="hidden" id="incomeTaxID" name="incomeTaxID"
           value="${incomeTaxInfoFormBean.incomeTaxID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${incomeTaxInfoFormBean.insertFlg}"/>

    <b>社員ID:</b>
    <form:select path="employeeID">
        <form:options items="${employeeList}" itemLabel="employeeName" itemValue="employeeID"/>
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
            <th width="80">対象年度</th>
            <th width="150">一月所得税</th>
            <th width="150">二月所得税</th>
            <th width="125">三月所得税</th>
            <th width="150">四月所得税</th>
            <th width="125">五月所得税</th>
            <th width="150">六月所得税</th>
            <th width="125">七月所得税</th>
            <th width="150">八月所得税</th>
            <th width="125">九月所得税</th>
            <th width="150">十月所得税</th>
            <th width="125">十一月所得税</th>
            <th width="150">十二月所得税</th>
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

        <c:forEach items="${list}" var="incomeTaxInfoList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${incomeTaxInfoList.incomeTaxID}"/></td>
                <td><c:out value="${incomeTaxInfoList.year}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax1}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax2}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax3}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax4}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax5}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax6}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax7}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax8}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax9}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax10}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax11}"/></td>
                <td><c:out value="${incomeTaxInfoList.incomeTax12}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax1}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax2}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax3}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax4}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax5}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax6}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax7}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax8}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax9}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax10}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax11}"/></td>
                <td><c:out value="${incomeTaxInfoList.residentTax12}"/></td>
                <td><c:out value="${incomeTaxInfoList.status}"/></td>
                <td><c:out value="${incomeTaxInfoList.insertDate}"/></td>
                <td><c:out value="${incomeTaxInfoList.updateDate}"/></td>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out
                        value="${incomeTaxInfoList.incomeTaxID}"/>');"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" name="insert" value="新規追加" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>

