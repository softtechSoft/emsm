<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ソフトテク株式会社-社内管理システム </title>
<script type="text/javascript" >
//登録ボタンのクリック処理
function doRegist(){
	document.theForm.submit();
}
</script>
</head>
<body>
<h2>一般経費</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="expensesBean" action="expensesRegit" >

	<p style="color: red;">
	    <c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> </br>
		</c:forEach>
	</p>

	<table border="1"class="expenses-table" >

	<tr style="background-color:#dcfeeb">
			<td>発生日：</td>
			<td><input id="accrualDate"name="accrualDate" type="text" value="${expensesBean.accrualDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>担当者：</td>
			<td><input id="personel"name="personel" type="text" value="${expensesBean.personel}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>金額：</td>
			<td><input id="cost"name="cost" type="text"  value="${expensesBean.cost}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>承認：</td>
			<td><input id="confirmStaus"name="confirmStaus" type="text"  value="${expensesBean.confirmStaus}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>承認日：</td>
			<td><input id="confirmDate"name="confirmDate" type="text"  value="${expensesBean.confirmDate}"></td>
			</tr>
			<tr  style="background-color:#bfe1ff">
			<td>承認者：</td>
			<td><input id="confirmer"name="confirmer" type="text"  value="${expensesBean.confirmer}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>精算：</td>
			<td><input id="stmtlStaus"name="stmtlStaus" type="text"  value="${expensesBean.stmtlStaus}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>精算日：</td>
			<td><input id="stmtlDate"name="stmtlDate" type="text"  value="${expensesBean.stmtlDate}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>出金タイプ：</td>
			<td><input id="paymentType"name="paymentType" type="text"  value="${expensesBean.paymentType}"></td>
			</tr>

			<tr>
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
			</tr>

	</table>
</form:form>
</body>
</html>