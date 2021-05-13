<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link type="text/css" rel="stylesheet" href="src/emsm/css/salarylist.css"></link>-->

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
<h2>給料リスト</h2>
<form:form name="theForm" id="theForm" method="post" action="salarylist" >
    <p>
      <b>対象年月:<input id="month"name="month" type="text" value="${month}">
         <!--<input style="border-radius: 3px" type="submit" id="search_btn" name="search" Value="検索">
         <input style="border-radius: 3px" type="submit" name="downLoad"id="downLoad_btn" value="ダウンロード">
         -->
         <input type="button" name="search" value="検索" onclick="toSearchJsp();" />
             <c:forEach items="${errors}" var="error">
					<spring:message message="${error}" />
		     </c:forEach>
         <input type="hidden" id="downloadFlg"name="downloadFlg"/>
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoadDataJsp()" />
       </b>
    </p>

	<table border="1"class="salrylist-table">

		<tr>
		    <th rowspan="3" width="3000">社員ID</th>
            <th width="4000">社員氏名</th>
            <th width="3000">対象年月</th>
            <th width="3000">支払日</th>
            <th width="5000">基本給(単位:円)</th>
            <th width="3000">残業時間</th>
            <th width="3000">不足時間</th>
            <th width="5000">残業加算(単位:円)</th>
            <th width="5000">稼働不足減(単位:円)</th>
            <th width="5000">交通費(単位:円)</th>
             <th width="5000">手当加算(単位:円)</th>
        </tr>
        <tr>

            <th width="5000">手当減算(単位:円)</th>
            <th width="3000">手当理由</th>
            <th width="1700">厚生年金控除個人(単位:円)</th>
            <th width="1700">厚生健康控除個人(単位:円)</th>
            <th width="1700">厚生年金控除会社(単位:円)</th>
            <th width="1700">厚生健康控除会社(単位:円)</th>
            <th width="1700">厚生控除子育(会社)(単位:円)</th>
            <th width="1700">雇用保険個人負担(単位:円)</th>
            <th width="1700">雇用保険会社負担(単位:円)</th>
            <th width="1700">雇用保拠出金（会社)(単位:円)</th>
        </tr>
        <tr>

            <th width="1700">労災保険（会社負担のみ）(単位:円)</th>
            <th width="500">源泉控除(単位:円)</th>
            <th width="500">住民税控除(単位:円)</th>
            <th width="500">社宅家賃控除(単位:円)</th>
            <th width="700">社宅共益費控除(単位:円)</th>
            <th width="200">総額(単位:円)</th>
            <th width="500">総費用(単位:円)</th>
            <th colspan="3"width="500">備考</th>
		</tr>
		<c:forEach items="${list}" var="salarylist" varStatus="status" >

        <tr <c:if test="${status.count%2==0}">bgcolor="#B0E0E6"</c:if>>
				<td rowspan="3"><c:out value="${salarylist.getEmployeeID()}"/></td>
				<td><c:out value="${salarylist.getEmployeeName()}"/></td>
				<td><c:out value="${salarylist.getMonth()}"/></td>
				<td><c:out value="${salarylist.getPaymentDate()}"/></td>
				<td><c:out value="${salarylist.getBase()}"/></td>
				<td><c:out value="${salarylist.getOverTime()}"/></td>
                <td><c:out value="${salarylist.getShortage()}"/></td>
                <td><c:out value="${salarylist.getOverTimePlus()}"/></td>
                <td><c:out value="${salarylist.getShortageReduce()}"/></td>
                <td><c:out value="${salarylist.getTransportExpense()}"/></td>
                <td><c:out value="${salarylist.getAllowancePlus()}"/></td>
            </tr>
            <tr<c:if test="${status.count%2==0}">bgcolor="#B0E0E6"</c:if>>

                <td><c:out value="${salarylist.getAllowanceReduce()}"/></td>
                <td><c:out value="${salarylist.getAllowanceReason()}"/></td>
                <td><c:out value="${salarylist.getWelfarePensionSelf()}"/></td>
                <td><c:out value="${salarylist.getWelfareHealthSelf()}"/></td>
                <td><c:out value="${salarylist.getWelfarePensionComp()}"/></td>
                <td><c:out value="${salarylist.getWelfareHealthComp()}"/></td>
                <td><c:out value="${salarylist.getWelfareBaby()}"/></td>
                <td><c:out value="${salarylist.getEplyInsSelf()}"/></td>
                <td><c:out value="${salarylist.getEplyInsComp()}"/></td>
                <td><c:out value="${salarylist.getEplyInsWithdraw()}"/></td>
             </tr>
             <tr<c:if test="${status.count%2==0}">bgcolor="#B0E0E6"</c:if>>

                <td><c:out value="${salarylist.getWkAcccpsIns()}"/></td>
                <td><c:out value="${salarylist.getWithholdingTax()}"/></td>
                <td><c:out value="${salarylist.getMunicipalTax()}"/></td>
                <td><c:out value="${salarylist.getRental()}"/></td>
                <td><c:out value="${salarylist.getRentalMgmtFee()}"/></td>
                <td><c:out value="${salarylist.getSum()}"/></td>
                <td><c:out value="${salarylist.getTotalFee()}"/></td>
				<td colspan="3"><c:out value="${salarylist.getRemark()}"/></td>
			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>