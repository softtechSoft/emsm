<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link type="text/css" rel="stylesheet" href="src/emsm/css/salarylist.css"></link>-->
<script type="text/javascript" >
	function toSearchJsp(){
		document.theForm.submit();
	}
</script>
<title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
		<h2>契約情報管理リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="contractInfoBean"  action="contractInfoList" >

	<b>社員:</b>
	<form:select path="employeeID">
		<form:options items="${contractList}" itemLabel="employeeName"  itemValue="employeeID"/>
	</form:select>

	<input type="button" name="search" value="検索" onclick="toSearchJsp();" />
			<!--エラーメッセージ-->
		<p style="color: red;">
	    	<c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> </br>
			</c:forEach>
		</p>

	<table border="1"class="contractInfoList-table">
		   	 <tr>
        		<th width="200">契約ID</th>
        		<th width="400">契約名称</th>
        		<th width="400">社員ID</th>
        		<th width="400">社員名</th>
        		<th width="400">取引先ID</th>
        		<th width="400">取引先名称</th>
        		<th width="300">単価</th>
        		<th width="300">精算タイプ</th>
        		<th width="300">契約下限</th>
        		<th width="500">控除単価</th>
        		<th width="300">契約上限</th>
				<th width="300">残業単価</th>
        		<th width="300">契約開始日</th>
        		<th width="300">契約終了日</th>
        		<th width="500">支払サイト</th>
        		<th width="300">原本郵送フラグ</th>
        		<th width="500">タイムレポートパス</th>
        		<th width="300">請求書名称</th>
				<th width="300">進行ステータス</th>
        		<th width="300">作成日</th>
        		<th width="300">更新日</th>
        		<th width="300">更新へ</th>
        	</tr>

        	<c:forEach items="${list}" var="contractInfoList" varStatus="status">
				<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
					<c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
					<td><c:out value="${contractInfoList.getContractID()}"/></td>
					<td><c:out value="${contractInfoList.getContractName()}"/></td>
					<td><c:out value="${contractInfoList.getEmployeeID()}"/></td>
					<td><c:out value="${contractInfoList.getEmployeeName()}"/></td>
					<td><c:out value="${contractInfoList.getCompanyID()}"/></td>
					<td><c:out value="${contractInfoList.getCompanyName()}"/></td>
					<td><c:out value="${contractInfoList.getPrice()}"/></td>
					<td><c:out value="${contractInfoList.getPayOff()}"/></td>
					<td><c:out value="${contractInfoList.getLowerTime()}"/></td>
					<td><c:out value="${contractInfoList.getLowerPrice()}"/></td>
					<td><c:out value="${contractInfoList.getUpperTime()}"/></td>
					<td><c:out value="${contractInfoList.getUpperPrice()}"/></td>
					<td><c:out value="${contractInfoList.getContractBeginDate()}"/></td>
					<td><c:out value="${contractInfoList.getContractEndDate()}"/></td>
					<td><c:out value="${contractInfoList.getPaymentTerm()}"/></td>
					<td><c:out value="${contractInfoList.getPostNeed()}"/></td>
					<td><c:out value="${contractInfoList.getTimeReportPath()}"/></td>
					<td><c:out value="${contractInfoList.getInvoice()}"/></td>
					<td><c:out value="${contractInfoList.getStatus()}"/></td>
					<td><c:out value="${contractInfoList.getInsertDate()}"/></td>
					<td><c:out value="${contractInfoList.getUpdateDate()}"/></td>
					<td><input type="button" name="uptade" value="更新"  /></td>
				</tr>
			</c:forEach>
	</table>
</form:form>
</body>
</html>