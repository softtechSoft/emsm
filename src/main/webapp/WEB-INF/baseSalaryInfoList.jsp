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
//検索処理
function toSearch(){
	var baseFlg =document.getElementById('baseFlg');
	baseFlg.value="1";
	document.theForm.submit();
}

//社員ID検索を押す処理
function toBaseSalaryInfoJsp(employeeID,startDate){

	var baseFlg =document.getElementById('baseFlg');
	baseFlg.value="3";

	var gamenEmployeeID =document.getElementById('gamenEmployeeID');
	gamenEmployeeID.value= employeeID;

	document.getElementById('startDate').value= startDate;

	document.theForm.submit();
}
</script>
</head>
<body>
<h2>基本給リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="BaseSalaryInfoBean" action="baseSalaryInfo" >
    <p>
      <b>社員ID:<input id="employeeID"name="employeeID" type="text" value="${employeeID}"/>
      	 <!--検索ボタン-->
         <input type="button" name="search" value="検索" onclick="toSearch()" />
         <!--社員IDを押す時、社員IDを転送用-->
         <input type="hidden" id="makeEmployeeID"name="makeEmployeeID"/>
         <!--開始日-->
         <input type="hidden" id="startDate"name="startDate"/>
          <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>
		</p>
       </b>
    </p>

	<table border="1"class="baseSalaryInfo-table" >
		<tr>
		    <th rowspan="2" width="100">社員ID</th>
            <th >基本給ID</th>
            <th >基本給</th>
            <th >稼働期間From</th>
            <th >稼働期間To</th>
            <th >不足減単価(h)</th>
            <th >残業加単価(h)</th>
            <th >利用ステータス</th>
            <th >作成日</th>
            <th >更新日</th>
        </tr>
		<c:forEach items="${hashMap}" var="baseSalaryInfo" varStatus="status"  >
		<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
            <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
				<td rowspan="2" ><input type="button"  value="<c:out value="${BaseSalaryInfoBean.getEmployeeID()}"/>"onclick="toBaseSalaryInfoJsp('${BaseSalaryInfoBean.getEmployeeID()}','${BaseSalaryInfoBean.getStartDate()}')"/></td>
				<td rowspan="2" ><c:out value="${BaseSalaryInfoBean.getBaseSalary()}"/></td>
				<td><c:out value="${BaseSalaryInfoBean.getMinusHour()}"/></td>
				<td><c:out value="${BaseSalaryInfoBean.getPlusHour()}"/></td>
				<td><c:out value="${BaseSalaryInfoBean.getWkPeriodFrom()}"/></td>
				<td><c:out value="${BaseSalaryInfoBean.getWkPeriodTo()}"/> </td>
				<td><c:out value="${BaseSalaryInfoBean.getStatus()}"/> </td>
				<td><c:out value="${BaseSalaryInfoBean.getInsertDate()}"/> </td>
				<td><c:out value="${BaseSalaryInfoBean.getUpdateDate()}"/> </td>
				<td><c:out value="${BaseSalaryInfoBean.getBaseSalaryID()}"/> </td>
		</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>