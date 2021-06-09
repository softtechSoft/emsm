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
</style>
<script type="text/javascript" >
//作成ボタンをクリック時。
function tosalaryInfoJsp(){
	//作成ボタンをクリック時、作成と登録を判断するデータ
	var Make =document.getElementById('Make');
	Make.value= "2";
	//input入力できるかどうか、判断用データ
	var loadFlg =document.getElementById('loadFlg');
	loadFlg.value= "1";
	//社員IDを転送するデータ
	var EmployeeIDb =document.getElementById('EmployeeIDb').value;
	document.theForm.submit();
}
//画面初期の時、input不可入力
function load(){

	var loadFlg =document.getElementById('loadFlg').value;
		document.getElementById('month').readOnly=true;
	if(loadFlg == "") {
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
//登録ボタンをクリック時。
function doRegist(){
	//登録ボタンをクリック時、作成と登録を判断するデータ
	var Make =document.getElementById('Make');
	Make.value= "1";
	//作成と更新を判断するデータ
	var MakeDistinction =document.getElementById('MakeDistinction').value;
	//社員IDを転送するデータ
	var EmployeeIDb =document.getElementById('EmployeeIDb').value;
	//社員氏名を転送するデータ
	var Nameb =document.getElementById('Nameb').value;
	//社員住所を転送するデータ
	var Addressb =document.getElementById('Addressb').value;
	document.theForm.submit();
}
</script>
</head >
<body onload="load()">
<h2>社員給料作成</h2>
<div id="main"  >
<div class="ctbox">
<!--  SalaryInfoController.javaと通信モデル：SalarylistBean2-->
<form:form name="theForm" id="theForm"  method="post" modelAttribute="SalarylistBean3" action="salaryInfo" >
			<!-- 作成ボタンと登録ボタンを分かれる。-->
			<input type="hidden" id="Make" name="Make" />
			<!--input入力できるかどうか、判断用データ -->
			<input type="hidden" id="loadFlg" name="loadFlg" value="${loadFlg}"/>
			<!-- 登録ボタンをクリック時、作成と登録を判断する為に-->
			<input type="hidden" id="MakeDistinction" name="MakeDistinction" value="${MakeDistinction}" />
			<!--エラーメッセージを表示する為に-->
			<p style="color: red;">
            <c:forEach  items="${errors}" var="error">
						<spring:message message="${error}" /><br/>
			</c:forEach>
			</p>
<table  bgcolor="white"; width:100%;>
	<c:forEach items="${salaryInfo}" var="salaryInfo" >
	<tr>
	<td></td>
	<td style="text-align: right;">
	<input type="button" id="search" name="search"  value="${button}" onclick="tosalaryInfoJsp()" /></td>
	</tr>
	<tr style="background-color:#bfe1ff">
	<td>社員ID：</td>
	<td><c:out value="${salaryInfo.getEmployeeID()}"/>
	<!--社員idを転送 -->
	<input type="hidden" id="EmployeeIDb" name="EmployeeIDb" value="<c:out value="${salaryInfo.getEmployeeID()}"/>"/>
	</td>
	</tr>
	<tr style="background-color:#dcfeeb">
	<td>氏名：</td>
	<td><c:out value="${salaryInfo.getEmployeeName()}"/>
	<!--社員氏名を転送 -->
	<input type="hidden" id="Nameb" name="Nameb" value="<c:out value="${salaryInfo.getEmployeeName()}"/>"/>
	</td>
	</tr>
	<tr  style="background-color:#bfe1ff">
	<td>住所：</td>
	<td><c:out value="${salaryInfo.getAddress()}"/>
	<!--社員住所を転送 -->
	<input type="hidden" id="Addressb" name="Addressb" value="<c:out value="${salaryInfo.getAddress()}"/>"/>
	</td>
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
	<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
	</tr>
	</c:forEach>
</table>
</form:form>
</div>
</div>
</body>
</html>
