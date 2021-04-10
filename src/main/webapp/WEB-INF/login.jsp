<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員ログイン</title>
<script type="text/javascript" src="/testPath.js"></script>
</head>
<body onload="testPath()">
	<form:form name="theForm" id="theForm" modelAttribute="loginBean"
		method="post" action="login" >
		<table bgcolor="lightskyblue" width="480" height="400">
			<tr>
				<td colspan="2" rowspan="1">ソフトテク株式会社</td>
			</tr>
						<tr style="color: red;" align="center">
				<td colspan="2" rowspan="1" height="60">
				    <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach></td>
			</tr>
			<tr align="center">
				<td colspan="2" rowspan="1" height="60">社内管理システム</td>
			</tr>
			<tr>
				<td align="right" width="200" height="50">メールアカウント：</td>
				<td><form:input path="employeeID" type="text"
					Value=""/></td>
			</tr>
			<tr>
				<td align="right"  width="200">パスワード：</td>
				<td><input name="password" type="password"
					Value=""></td>
			</tr>
			<tr>
				<td align="right" width="200"><input style="border-radius: 3px" type="submit" id="login_btn" name="login"
					Value="ログイン"></td>
				<td align="center"><input style="border-radius: 3px" type="submit" name="resetpswd"
					id="reset_btn" value="パスワード変更"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>