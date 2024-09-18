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
//登録処理
function doRegist(){
	var gamenMode =document.getElementById('gamenMode');
	gamenMode.value="1";
	document.theForm.submit();
}
//計算処理
function doCalculation(){
	var gamenMode =document.getElementById('gamenMode');
	gamenMode.value="2";
	document.theForm.submit();
}
//jsp計算処理
function myFunction(){
	var base =document.getElementById('base').value;
	document.getElementById('eplyInsSelf').value= Math.ceil(base * 0.003);
	document.getElementById('eplyInsComp').value= Math.ceil(base * 0.006);
	document.getElementById('eplyInsWithdraw').value= Math.ceil(base * 0.00002);
	document.getElementById('wkAcccpsIns').value=Math.ceil(base * 0.003);

}
</script>
</head >
<body>
<h2>福祉情報作成</h2>
<form:form name="theForm" id="theForm"  method="post" modelAttribute="welfare" action="welfareInfo" >

		 <!--判断用データ  1：登録、2：計算.-->
         <input type="hidden" id="gamenMode"name="gamenMode"/>
<table  bgcolor="white"; width:100%; height:100%;>
			<tr>
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="calculation" name="calculation" value="計算"  onclick="doCalculation()" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>社員ID：</td>
			<td>${welfare.employeeID}
			<!--社員idを転送 -->
			<input type="hidden" id="employeeID" name="employeeID" value="${welfare.employeeID}"/>
			</td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>氏名：</td>
			<td>${welfare.employeeName}
			<!--社員氏名を転送 -->
			<input type="hidden" id="employeeName" name="employeeName" value="${welfare.employeeName}"/>
			</td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>控除開始日：</td>
			<td><input id="startDate"name="startDate" type="text" value="${welfare.startDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>基本給：</td>
			<td><input id="base"name="base" type="text"  value="${welfare.base}" onchange="myFunction()"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生年金控除個人：</td>
			<td><input id="welfarePensionSelf"name="welfarePensionSelf" type="text"  value="${welfare.welfarePensionSelf}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生年金控除会社：</td>
			<td><input id="welfarePensionComp"name="welfarePensionComp" type="text"  value="${welfare.welfarePensionComp}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生健康控除会社：</td>
			<td><input id="welfareHealthComp"name="welfareHealthComp" type="text"  value="${welfare.welfareHealthComp}"></td>
			</tr>
			<tr  style="background-color:#bfe1ff">
			<td>厚生健康控除個人：</td>
			<td><input id="welfareHealthSelf"name="welfareHealthSelf" type="text"  value="${welfare.welfareHealthSelf}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生控除子育(会社)：</td>
			<td><input id="welfareBaby"name="welfareBaby" type="text"  value="${welfare.welfareBaby}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>雇用保険個人負担：</td>
			<td><input id="eplyInsSelf"name="eplyInsSelf"   type="text" value="${welfare.eplyInsSelf}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>雇用保険会社負担：</td>
			<td><input id="eplyInsComp"name="eplyInsComp" type="text"  value="${welfare.eplyInsComp}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>一般拠出金（会社のみ)：</td>
			<td><input id="eplyInsWithdraw"name="eplyInsWithdraw" type="text"  value="${welfare.eplyInsWithdraw}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>労災保険（会社負担のみ)：</td>
			<td><input id="wkAcccpsIns"name="wkAcccpsIns"  type="text"  value="${welfare.wkAcccpsIns}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>源泉控除：</td>
			<td><input id="withholdingTax"name="withholdingTax" type="text"   value="${welfare.withholdingTax}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>1月住民税控除：</td>
			<td><input id="municipalTax1"name="municipalTax1" type="text"  value="${welfare.municipalTax1}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>２月住民税控除：</td>
			<td><input id="municipalTax2"name="municipalTax2" type="text"  value="${welfare.municipalTax2}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>3月住民税控除：</td>
			<td><input id="municipalTax3"name="municipalTax3" type="text"  value="${welfare.municipalTax3}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>４月住民税控除：</td>
			<td><input id="municipalTax4"name="municipalTax4" type="text"  value="${welfare.municipalTax4}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>５月住民税控除：</td>
			<td><input id="municipalTax5"name="municipalTax5" type="text"  value="${welfare.municipalTax5}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>６月住民税控除：</td>
			<td><input id="municipalTax6"name="municipalTax6" type="text"  value="${welfare.municipalTax6}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>７月住民税控除：</td>
			<td><input id="municipalTax7"name="municipalTax7" type="text"  value="${welfare.municipalTax7}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>８月住民税控除：</td>
			<td><input id="municipalTax8"name="municipalTax8" type="text"  value="${welfare.municipalTax8}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>９月住民税控除：</td>
			<td><input id="municipalTax9"name="municipalTax9" type="text"  value="${welfare.municipalTax9}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>1０月住民税控除：</td>
			<td><input id="municipalTax10"name="municipalTax10" type="text"  value="${welfare.municipalTax10}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>1１月住民税控除：</td>
			<td><input id="municipalTax11"name="municipalTax11" type="text"  value="${welfare.municipalTax11}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>1２月住民税控除：</td>
			<td><input id="municipalTax12"name="municipalTax12" type="text"  value="${welfare.municipalTax12}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>社宅家賃控除：</td>
			<td><input id="rental"name="rental" type="text"  value="${welfare.rental}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>社宅管理費控除：</td>
			<td><input id="rentalMgmtFee"name="rentalMgmtFee" type="text"  value="${welfare.rentalMgmtFee}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>控除ステータス：</td>
			<td><input id="status"name="status" type="text" value="${welfare.status}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>作成日：</td>
			<td><input id="insertDate"name="insertDate" type="text"  value="${welfare.insertDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>作成者：</td>
			<td><input id="insertEmployee"name="insertEmployee" type="text"  value="${welfare.insertEmployee}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>更新日：</td>
			<td><input id="updateDate"name="updateDate" type="text"  value="${welfare.updateDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>更新者：</td>
			<td><input id="updateEmployee"name="updateEmployee" type="text"  value="${welfare.updateEmployee}"></td>
			<tr>
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="registRation" name="registRation" value="登録"  onclick="doRegist()" /></td>
			</tr>
	</table>
</form:form>
</body>
</html>