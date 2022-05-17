<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" >
	function toSearchJsp(){
		document.theForm.submit();
	}
	// 更新ボタン処理
	function toUpdateJsp(baseSalaryId){
		// 更新
		document.getElementById('insertFlg').value='1';
		document.getElementById('baseSalaryID').value=baseSalaryId;
		document.theForm.action="toInitBaseSalaryInfo";
		document.theForm.submit();
	}

	// 新規ボタン処理
	function toInsertJsp(){
		// 新規
		document.getElementById('insertFlg').value='0';
		document.theForm.action="toInitBaseSalaryInfo";
		document.theForm.submit();
	}
</script>
<title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
		<h2>基本給管理リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="baseSalaryInfoBean"  action="baseSalaryInfoList" >
	<input type="hidden" id="baseSalaryID" name="baseSalaryID" value="${baseSalaryInfoBean.baseSalaryID}"/>
	<!--新規フラグ　０　新規　１　更新-->
	<input type="hidden" id="insertFlg" name="insertFlg" value="${baseSalaryInfoBean.insertFlg}"/>

	<b>社員:</b>
	<form:select path="employeeID">
		<form:options items="${baseSalaryList}" itemLabel="employeeName"  itemValue="employeeID"/>
	</form:select>

	<input type="button" name="search" value="検索" onclick="toSearchJsp();" />
			<!--エラーメッセージ-->
		<p style="color: red;">
	    	<c:forEach items="${errors}" var="error">
			<spring:message message="${error}" />
			</c:forEach>
		</p>
	<table border="1"class="baseSalaryInfoList-table">
		   	 <tr>
        		<th width="200">基本給ID</th>
    			<th width="200">基本給</th>
    			<th width="200">稼働期間From</th>
    			<th width="200">稼働期間To</th>
    			<th width="200">不足減単価(h)</th>
  				<th width="100">残業加単価(h)</th>
   				<th width="100">利用ステータス</th>
   				<th width="100">作成日</th>
   				<th width="100">更新日</th>
    			<th width="45">更新</th>
        	</tr>

        	<c:forEach items="${list}" var="baseSalaryInfoList" varStatus="status">
				<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
					<c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
					<td><c:out value="${baseSalaryInfoList.getBaseSalaryID()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getBaseSalary()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getWkPeriodFrom()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getWkPeriodTo()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getMinusHour()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getPlusHour()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getStatus()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getInsertDate()}"/></td>
					<td><c:out value="${baseSalaryInfoList.getUpdateDate()}"/></td>
					<td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out value="${baseSalaryInfoList.getBaseSalaryID()}"/>');" /></td>
				</tr>
			</c:forEach>
	</table>
	<input type="button"  name="insert" value="新規登録" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>