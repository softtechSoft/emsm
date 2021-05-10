<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>社内管理システム MENU</h2>
<form:form name="theForm" id="theForm" modelAttribute="list" method="post" action="menu" >
	<table>
		<tr>
				<c:forEach items="${list}" var="Ofcfunction">
				   <!--
				      <td><c:out value="${Ofcfunction.getFunctionText()}"/></td>
				      <td><c:out value="${Ofcfunction.getFunctionLink()}"/></td>
				            -->

				       <td> <input type="button" value="<c:out value='${Ofcfunction.getFunctionText()}'/>" /></td>
				       <td> <input type="button" value="<c:out value='${Ofcfunction.getFunctionLink()}'/>" /></td>
				</c:forEach>
			</tr>
	</table>

</form:form>


</body>
</html>