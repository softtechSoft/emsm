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
function toSearchJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value=false;
	document.theForm.submit();
}
function toDownLoadDataJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value=true;
	document.theForm.submit();
}

</script>

</head>
<body>
<h2>請求リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="selectjyolken" action="claimlist" >
    <p>
      <b>対象年月:<input id="month"name="month" type="text" value="${month}"/>
      <b>取引先:<input id="companyName"name="companyName" type="text" value="${companyName}"/>

         <input type="button" name="search" value="検索" onclick="toSearchJsp();" />
         <input type="hidden" id="downloadFlg"name="downloadFlg"/>
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoadDataJsp()" />
          <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>
		</p>
		</b>
       </b>
    </p>

	<table border="1"class="claimlist-table">

		<tr>
		<th  width="100">請求ID</th>
		<th  width="100">契約ID</th>
		<th  width="200">取引先名称</th>
		<th  width="100">請求月</th>
		<th  width="100">社員氏名</th>
		<th  width="50">稼働時間(H）</th>
		<th  width="100">単価(円）</th>
		<th  width="50">過稼働時間(H）</th>
		<th  width="100">加算額(円）</th>
        <th  width="50">不足稼働時間(H）</th>
        <th  width="100">減算額（円)</th>
        <th  width="100">交通費（円）</th>
        <th  width="100">出張旅費(円）</th>
        <th  width="50">消費税率(%）</th>
        <th  width="100">消費税(円）</th>
        <th  width="100">合計(円）</th>
        <th  width="100">特別請求</th>
        <th  width="100">請求ステータス</th>

		</tr>
		<c:forEach items="${list}" var="claimlist" varStatus="status">
			<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
            	<td><c:out value="${claimlist.getClaimID()}"/></td>
				<td><c:out value="${claimlist.getContractID()}"/></td>
				<td><c:out value="${claimlist.getCompanyName()}"/></td>
				<td><c:out value="${claimlist.getClaimMonth()}"/></td>
				<td><c:out value="${claimlist.getEmployeeName()}"/></td>
				<td><c:out value="${claimlist.getWorkTime()}"/></td>
				<td><c:out value="${claimlist.getPrice()}"/></td>
				<td><c:out value="${claimlist.getExceTime()}"/></td>
				<td><c:out value="${claimlist.getAddpayOff()}"/></td>
				<td><c:out value="${claimlist.getDeficiTime()}"/></td>
				<td><c:out value="${claimlist.getMinusPayOff()}"/></td>
				<td><c:out value="${claimlist.getTransport()}"/></td>
				<td><c:out value="${claimlist.getBusinessTrip()}"/></td>
				<td><c:out value="${claimlist.getTaxRate()}"/></td>
				<td><c:out value="${claimlist.getConsumpTax()}"/></td>
				<td><c:out value="${claimlist.getSum()}"/></td>
				<td><c:out value="${claimlist.getSpecialClaim()}"/></td>
				<td><c:out value="${claimlist.getClaimStatus() }"/></td>

			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>