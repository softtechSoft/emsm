<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
   <%--  <input type="hidden" id="employeeID" name="employeeID"
           value="${employeeInfoFormBean.employeeID}"/> --%>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${employeeInfoFormBean.insertFlg}"/>

<b>社員:</b>
 <form:select path="employeeID">
        <form:options items="${employeeList}" itemLabel="employeeID" itemValue="employeeID"/>
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
		<th width="100">社員ID</th>
		<th width="100">社員氏名</th>
		<!-- <th width="100">パスワード</th>
		<th width="100">ステータス</th> -->
		<th width="100">性別</th>
		<th width="100">タイプ</th>
		<th width="100">生年月日</th>
		<th width="100">年齢</th>
		<th width="100">入社年月日</th>
		<th width="100">社齢</th>
		<th width="100">郵便番号</th>
		<th width="100">住所</th>
		<th width="100">電話番号</th>
		<th width="100">権限</th>
		<th width="100">メール</th>
		<!-- <th width="100">作成日</th>
		<th width="100">更新日</th> -->
		<th width="45">更新</th>
	</tr>

	<c:forEach items="${list}" var="employeeInfoList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${employeeInfoList.employeeID}"/></td>
                <td><c:out value="${employeeInfoList.employeeName}"/></td>
                <%-- <td><c:out value="${employeeInfoList.password}"/></td>
                <td><c:out value="${employeeInfoList.status}"/></td> --%>
                <td><c:out value="${employeeInfoList.sex}"/></td>
                <td><c:out value="${employeeInfoList.epType}"/></td>
                <td><c:out value="${employeeInfoList.birthday}"/></td>
                <td><c:out value="${employeeInfoList.age}"/></td>
                <td><c:out value="${employeeInfoList.joinedDate}"/></td>
                <td><c:out value="${employeeInfoList.joinedTime}"/></td>
                <td><c:out value="${employeeInfoList.postCode}"/></td>
                <td><c:out value="${employeeInfoList.address}"/></td>
                <td><c:out value="${employeeInfoList.phoneNumber}"/></td>
                <td><c:out value="${employeeInfoList.authority}"/></td>
                <td><c:out value="${employeeInfoList.mailAdress}"/></td>
                <%-- <td><c:out value="${employeeInfoList.insertDate}"/></td>
                <td><c:out value="${employeeInfoList.updateDate}"/></td> --%>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out
                        value="${employeeInfoList.employeeID}"/>');"/></td>
            </tr>
        </c:forEach>
</table>
<input type="button" name= "insert" value="新規追加" onclick= "toInsertJsp();"/>
</form:form>
</body>
</html>