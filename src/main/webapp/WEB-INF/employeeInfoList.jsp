<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" >
    <!--検索-->
    function toSearchJsp(){
    	document.getElementById('selectFlg').value='0';
    	document.theForm.submit();
    }
    function toSearchJsp1(){
    	document.getElementById('selectFlg').value='1';
    	document.theForm.submit();
   }
    // 更新ボタン処理
    function toUpdateJsp(employeeID){
        // 更新
        document.getElementById('insertFlg').value='1';
        document.getElementById('employeeID').value=employeeID;
        document.theForm.action="toinitEmployeeInfo";
        document.theForm.submit();
    }
 // 新規ボタン処理
    function toInsertJsp(){
        // 新規
        document.getElementById('insertFlg').value='0';
        document.theForm.action="toinitEmployeeInfo";
        document.theForm.submit();
    }
    </script>
    <title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>社員情報</h2>
	<form:form name="theForm" id="theForm" method="post" modelAttribute="employeeInfoFormBean"
           action="employeeInfoList">
    <!--検索フラグ　０：検索　１：全量検索-->
    <input type="hidden" id="selectFlg" name="selectFlg"
           value="${employeeInfoFormBean.selectFlg}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${employeeInfoFormBean.insertFlg}"/>
<b>社員:</b>
 <form:select path="employeeID">
        <form:options items="${employeeList}" itemLabel="employeeName" itemValue="employeeID"/>
    </form:select>
	 <td></td>
    <input type="button" name="search" value="検索" onclick="toSearchJsp()"/>
    <input type="button" name="search" value="全量検索" onclick="toSearchJsp1()"/>

	<!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
<table border="1" class="employeeInfoList-table">
	<tr>
		<th width="50">社員ID</th>
		<th width="150">社員氏名</th>
		<th width="33">性別</th>
		<th width="70">タイプ</th>
		<th width="75">生年月日</th>
		<th width="33">年齢</th>
		<th width="75">入社年月日</th>
		<th width="33">社齢</th>
		<th width="75">郵便番号</th>
		<th width="200">住所</th>
		<th width="100">電話番号</th>
		<th width="50">権限</th>
		<th width="200">メール</th>
		<!-- <th width="100">作成日</th>
		<th width="100">更新日</th> -->
		<th width="45">更新</th>
	</tr>
	<c:forEach items="${list}" var="employeeInfoList" varStatus="status">
    <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
        <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
       <td><c:out value="${empty employeeInfoList.employeeID ? '' : employeeInfoList.employeeID}"/></td>
<td><c:out value="${empty employeeInfoList.employeeName ? '' : employeeInfoList.employeeName}"/></td>
<td><c:out value="${empty employeeInfoList.sex ? '' : employeeInfoList.sex}"/></td>
<td>
    <c:out value="${empty employeeInfoList.epType ? '' :
        (employeeInfoList.epType == '0' ? '正社員' :
        (employeeInfoList.epType == '1' ? '契約社員' :
        (employeeInfoList.epType == '2' ? '個人事業' :
        (employeeInfoList.epType == '9' ? '契約社員' : ''))))}"/>
</td>

<td>
    <c:choose>
        <c:when test="${empty employeeInfoList.birthday}">
            <c:out value=""/>
        </c:when>
        <c:otherwise>
            <span style="white-space: nowrap;">
                <c:out value="${employeeInfoList.birthday.substring(0, 4)}"/>/
                <c:out value="${employeeInfoList.birthday.substring(4, 6)}"/>/
                <c:out value="${employeeInfoList.birthday.substring(6)}"/>
            </span>
        </c:otherwise>
    </c:choose>
</td>

<td><c:out value="${empty employeeInfoList.age ? '' : employeeInfoList.age}"/></td>

<td>
    <c:choose>
        <c:when test="${empty employeeInfoList.joinedDate}">
            <c:out value=""/>
        </c:when>
        <c:otherwise>
            <span style="white-space: nowrap;">
                <c:out value="${employeeInfoList.joinedDate.substring(0, 4)}"/>/
                <c:out value="${employeeInfoList.joinedDate.substring(4, 6)}"/>/
                <c:out value="${employeeInfoList.joinedDate.substring(6)}"/>
            </span>
        </c:otherwise>
    </c:choose>
</td>

<td><c:out value="${empty employeeInfoList.joinedTime ? '' : employeeInfoList.joinedTime}"/></td>

<td>
    <c:choose>
        <c:when test="${empty employeeInfoList.postCode}">
            <c:out value=""/>
        </c:when>
        <c:otherwise>
            <span style="white-space: nowrap;">
                <c:out value="${employeeInfoList.postCode.substring(0, 3)}"/>-
                <c:out value="${employeeInfoList.postCode.substring(3, 7)}"/>
            </span>
        </c:otherwise>
    </c:choose>
</td>

<td><c:out value="${empty employeeInfoList.address ? '' : employeeInfoList.address}"/></td>

<td>
    <c:choose>
        <c:when test="${empty employeeInfoList.phoneNumber}">
            <c:out value=""/>
        </c:when>
        <c:when test="${fn:length(employeeInfoList.phoneNumber) == 10}">
        <!-- XX-XXXX-XXXX -->
            <span style="white-space: nowrap;">
                <c:out value="${employeeInfoList.phoneNumber.substring(0, 2)}"/>-
                <c:out value="${employeeInfoList.phoneNumber.substring(2, 6)}"/>-
                <c:out value="${employeeInfoList.phoneNumber.substring(6)}"/>
            </span>
        </c:when>
        <c:when test="${fn:length(employeeInfoList.phoneNumber) == 11}">
            <!-- XXX-XXXX-XXXX -->
            <span style="white-space: nowrap;">
                <c:out value="${employeeInfoList.phoneNumber.substring(0, 3)}"/>-
                <c:out value="${employeeInfoList.phoneNumber.substring(3, 7)}"/>-
                <c:out value="${employeeInfoList.phoneNumber.substring(7)}"/>
            </span>
        </c:when>
    </c:choose>
</td>

<td><c:out value="${empty employeeInfoList.authority ? '' : (employeeInfoList.authority == '0' ? '普通' : '管理者')}"/></td>
<td><c:out value="${empty employeeInfoList.mailAdress ? '' : employeeInfoList.mailAdress}"/></td>
<td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('${empty employeeInfoList.employeeID ? '' : employeeInfoList.employeeID}');"/></td>

    </tr>
</c:forEach>
</table>
<input type="button" name= "insert" value="新規追加" onclick= "toInsertJsp();"/>
</form:form>
</body>
</html>