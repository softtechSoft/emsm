
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> ソフトテク株式会社-社内管理システム </title>
	<script type="text/javascript" >
		function doRegist() {
    		document.theForm.submit();
		}

	</script>
</head>
<form:form name="theForm" id="theForm" method="post" modelAttribute="employeeInfoFormBean"
           action="employeeInfoEdit">
<h2>社員新規登録</h2>
 <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/><br>
        </c:forEach>
    </p>
	<input type="hidden" id="status" name="status" value="0"/>
	<input type="hidden" id="authority" name="authority" value="0"/>


<table border="1" >
	<tr style = "background-color:#f4f4f4">
		<td width ="150px">社員ID</td>
		<td  width ="250px"><input type="text" id="employeeID" name="employeeID"
                                     value="${employeeInfoFormBean.employeeID}" style="width: 98%;" /></td>
	</tr>
	<tr style = "background-color:#f4f4f4">
		<td width ="150px">社員氏名</td>
		<td  width ="250px"><input type="text" id="employeeName" name="employeeName"
                                     value="${employeeInfoFormBean.employeeName}" style="width: 98%;" /></td>
	</tr>



	<tr style = "background-color:#f4f4f4">
		<td width ="150px">メール</td>
		<td  width ="150px"><input type="text" id="mailAdress" name="mailAdress"
                                     value="${employeeInfoFormBean.mailAdress}" style="width: 98%;" /></td>
	</tr>
	<tr style = "background-color:#f4f4f4">
		<td width ="150px">初期パスワード</td>
		<td  width ="250px"><input type="password" id="password" name="password"
                                     value="${employeeInfoFormBean.password}"   style="width: 98%;"/></td>
	</tr>




	</table>
	<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()"  />

</form:form>
 <c:if test="${not empty successMessage}">
    <div style="color: red;">
        <p><c:out value="${successMessage}" /></p>
    </div>
</c:if>
</body>
</html>
