<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ソフトテク株式会社-社内管理システム </title>
<style>
#main{ width:1500px; float:left; padding:10px 0;border:1px }
.ctbox{ margin:0 auto; width:350px;}
</style>
<script type="text/javascript" >
function tosalaryInfoJsp(salaryInfoID){
	var loadFlg =document.getElementById('loadFlg');
	loadFlg.value= "1";
	var EmployeeIDFlg =document.getElementById('EmployeeIDFlg');
	EmployeeIDFlg.value= salaryInfoID;
	document.theForm.submit();
}
function load(){

	var loadFlg =document.getElementById('loadFlg').value;
	if(loadFlg == "") {
		document.getElementById('month').readOnly=true;
		document.getElementById('Base').readOnly=true;
		document.getElementById('PaymentDate').readOnly=true;
		document.getElementById('OverTime').readOnly=true;
		document.getElementById('Shortage').readOnly=true;
		document.getElementById('OverTimePlus').readOnly=true;
		document.getElementById('ShortageReduce').readOnly=true;
		document.getElementById('TransportExpense').readOnly=true;
		document.getElementById('AllowancePlus').readOnly=true;
		document.getElementById('AllowanceReduce').readOnly=true;
		document.getElementById('AllowanceReason').readOnly=true;
		document.getElementById('WelfareSelf').readOnly=true;
		document.getElementById('WelfareComp').readOnly=true;
		document.getElementById('WelfareBaby').readOnly=true;
		document.getElementById('EplyInsSelf').readOnly=true;
		document.getElementById('EplyInsComp').readOnly=true;
		document.getElementById('EplyInsWithdraw').readOnly=true;
		document.getElementById('WkAcccpsIns').readOnly=true;
		document.getElementById('WithholdingTax').readOnly=true;
		document.getElementById('MunicipalTax').readOnly=true;
		document.getElementById('Rental').readOnly=true;
		document.getElementById('RentalMgmtFee').readOnly=true;
		document.getElementById('Sum').readOnly=true;
		document.getElementById('TotalFee').readOnly=true;
		document.getElementById('Remark').readOnly=true;
	  }
}
function tocJsp(){
	var c =document.getElementById('c').value;
	var b =document.getElementById('b');
	if(c == "新規追加") {
		b.value= "新規追加";
		document.theForm.submit();

	}else if (c == "更新"){
		b.value= "更新";
		document.theForm.submit();

	}
}
</script>
</head >
<body onload="load()">
<h2>社員給料作成</h2>
<div id="main"  >
<div class="ctbox">
<form:form name="theForm" id="theForm"  method="post" modelAttribute="salaryInfo2" action="salaryInfo" >
			<input type="hidden" id="EmployeeIDFlg" name="EmployeeIDFlg"/>
			<input type="hidden" id="loadFlg" name="loadFlg" value="${loadFlg}"/>
			<input type="hidden" id="c" name="c" value="${cFlg}"/>
			<input type="hidden" id="b" name="b" />

<table  bgcolor="white">
	<c:forEach items="${salaryInfo}" var="salaryInfo" >
	<tr>
	<td></td>
	<td style="text-align: right;">
	<input type="button" id="search" name="search"  value="${button}" onclick="tosalaryInfoJsp('${salaryInfo.getEmployeeID()}')" /></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>社員ID：</td>
	<td><c:out value="${salaryInfo.getEmployeeID()}"/></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>氏名：</td>
	<td><c:out value="${salaryInfo.getEmployeeName()}"/></td>
	</tr>
	<tr  style="background-color:#bfe1ff">
	<td>住所：</td>
	<td><c:out value="${salaryInfo.getAddress()}"/></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>対象月：</td>
	<td><input id="month"name="month" type="text" value="${month}"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>基本給：</td>
	<td><input id="Base"name="Base" type="text" value="<c:out value="${salaryInfo.getBase()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>支払日：</td>
	<td><input id="PaymentDate"name="PaymentDate" type="text"  value="<c:out value="${salaryInfo.getPaymentDate()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>残業時間：</td>
	<td><input id="OverTime"name="OverTime" type="text"  value="<c:out value="${salaryInfo.getOverTime()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>不足時間：</td>
	<td><input id="Shortage"name="Shortage" type="text"  value="<c:out value="${salaryInfo.getShortage()}"/>"></td>
	</tr>
	<tr  style="background-color:#bfe1ff">
	<td>残業加算：</td>
	<td><input id="OverTimePlus"name="OverTimePlus" type="text"  value="<c:out value="${salaryInfo.getOverTimePlus()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>稼働不足減：</td>
	<td><input id="ShortageReduce"name="ShortageReduce" type="text"  value="<c:out value="${salaryInfo.getShortageReduce()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>交通費：</td>
	<td><input id="TransportExpense"name="TransportExpense"   type="text" value="<c:out value="${salaryInfo.getTransportExpense()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>手当加算：</td>
	<td><input id="AllowancePlus"name="AllowancePlus" type="text"  value="<c:out value="${salaryInfo.getAllowancePlus()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>手当減算：</td>
	<td><input id="AllowanceReduce"name="AllowanceReduce" type="text"  value="<c:out value="${salaryInfo.getAllowanceReduce()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>手当理由：</td>
	<td><input id="AllowanceReason"name="AllowanceReason"  type="text"  value="<c:out value="${salaryInfo.getAllowanceReason()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>厚生控除個人：</td>
	<td><input id="WelfareSelf"name="WelfareSelf" type="text"   value="<c:out value="${salaryInfo.getWelfareSelf()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>厚生控除会社：</td>
	<td><input id="WelfareComp"name="WelfareComp" type="text"  value="<c:out value="${salaryInfo.getWelfareComp()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>厚生控除子育(会社）：</td>
	<td><input id="WelfareBaby"name="WelfareBaby" type="text"  value="<c:out value="${salaryInfo.getWelfareBaby()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>雇用保険個人負担：</td>
	<td><input id="EplyInsSelf"name="EplyInsSelf" type="text"  value="<c:out value="${salaryInfo.getEplyInsSelf()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>雇用保険会社負担：</td>
	<td><input id="EplyInsComp"name="EplyInsComp" type="text" value="<c:out value="${salaryInfo.getEplyInsComp()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>雇用保拠出金（会社)：</td>
	<td><input id="EplyInsWithdraw"name="EplyInsWithdraw" type="text"  value="<c:out value="${salaryInfo.getEplyInsWithdraw()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>労災保険（会社負担のみ）：</td>
	<td><input id="WkAcccpsIns"name="WkAcccpsIns" type="text"  value="<c:out value="${salaryInfo.getWkAcccpsIns()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>源泉控除：</td>
	<td><input id="WithholdingTax"name="WithholdingTax" type="text"  value="<c:out value="${salaryInfo.getWithholdingTax()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>住民税控除：</td>
	<td><input id="MunicipalTax"name="MunicipalTax" type="text"  value="<c:out value="${salaryInfo.getMunicipalTax()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>社宅家賃控除：</td>
	<td><input id="Rental"name="Rental" type="text"  value="<c:out value="${salaryInfo.getRental()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>社宅共益費控除：</td>
	<td><input id="RentalMgmtFee"name="RentalMgmtFee" type="text"  value="<c:out value="${salaryInfo.getRentalMgmtFee()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>総額：</td>
	<td><input id="Sum"name="Sum" type="text"  value="<c:out value="${salaryInfo.getSum()}"/>"></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>総費用：</td>
	<td><input id="TotalFee"name="TotalFee" type="text"  value="<c:out value="${salaryInfo.getTotalFee()}"/>"></td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>備考：</td>
	<td><input id="Remark"name="Remark" type="text"  value="<c:out value="${salaryInfo.getRemark()}"/>"></td>
	</tr>
	<tr>
	<td></td>
	<td style="text-align: right;">
	<input type="button" name="search" value="登録"  onclick="tocJsp()"/></td>
	</tr>
	</c:forEach>
</table>
</form:form>
</div>
</div>
</body>
</html>
