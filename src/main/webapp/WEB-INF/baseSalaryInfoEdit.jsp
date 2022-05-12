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
<form:form name="theForm" id="theForm" method="post" modelAttribute="baseSalaryInfoBean"  action="baseSalaryInfoEdit" >

				<h1>基本給情報更新</h1>
		<input type="hidden" id="baseSalaryID" name="baseSalaryID" value="${baseSalaryInfoList.getBaseSalaryID()}"/>
		<input type="hidden" id="insertDate" name="insertDate" value="${baseSalaryInfoList.insertDate}"/>
		<input type="hidden" id="updateDate" name="updateDate" value="${baseSalaryInfoList.updateDate}"/>
		<table  border="1">
		<c:forEach items="${list}" var="baseSalaryInfoList" varStatus="status">
			<tr style="background-color:#dcfeeb">
				<td width="200px">基本給ID</td>
				<td width="200px"><c:out  value="${baseSalaryInfoList.getBaseSalaryID()}"/></td>
			</tr>


			<tr style="background-color:#dcfeeb">

				<td width="200px">社員ID</td>
				<td width="200px">
					<form:select path="employeeID">
						<form:options items="${employeeList}" itemLabel="employeeName"  itemValue="employeeID"/>
					</form:select>
				</td>
			</tr>


			<tr style="background-color:#dcfeeb">
				<td width="200px">基本給</td>
				<td width="200px"><input type="text" id="baseSalary" name="baseSalary"
									value="${baseSalaryInfoList.baseSalary}" /></td>

			</tr>
				<tr style="background-color:#dcfeeb">
				<td width="200px">稼働期間From</td>
				<td width="200px"><input type="text" id="wkPeriodFrom" name="wkPeriodFrom"
									value="${baseSalaryInfoList.wkPeriodFrom}" /></td>

			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">稼働期間To</td>
				<td width="200px"><input type="text" id="wkPeriodTo" name="wkPeriodTo"
									value="${baseSalaryInfoList.wkPeriodTo}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">不足減単価(h)</td>
				<td width="200px"> <input type="text" id="minusHour" name="minusHour"
									value="${baseSalaryInfoList.minusHour}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">残業加単価(h)</td>
				<td width="200px">  <input type="text" id="plusHour" name="plusHour"
									value="${baseSalaryInfoList.plusHour}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">作成日</td>
				<td width="200px"><c:out  value="${baseSalaryInfoList.insertDate}"/>
				<input type="hidden" id="insertDate" name="insertDate"value="${baseSalaryInfoList.insertDate}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">更新日</td>
				<td width="200px"><c:out  value="${baseSalaryInfoList.updateDate}"/>
				<input type="hidden" id="updateDate" name="updateDate"value="${baseSalaryInfoList.updateDate}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">利用ステータス</td>
				<td width="200px"><input type="radio" name="status" <c:if test="${baseSalaryInfoList.status == '1'}">
									checked</c:if> value="1" /> する
									<input type="radio" name="status" <c:if test="${baseSalaryInfoList.status == '0'}">
									checked</c:if> value="0" /> しない</td>
			</tr>

			<tr>
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
			</tr>
		</c:forEach>
		</table>

</form:form>
</body>
</html>