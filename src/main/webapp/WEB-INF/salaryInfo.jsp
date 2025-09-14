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
//数値表示方を変更する。２０００→２，０００
function chageNumberDisp(textObject){
	var num = textObject.value;
	num=toNumberDisp(num);
	textObject.value = String(num).replace( /(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');

}
//文字列を数字に変換
function toNumberDisp(strNumber){
	var num=0;
	if (!(strNumber == null || strNumber == '')) {
		//,を除く
		num = Number(strNumber.replaceAll(",",""));
	}
	return num;
}

//登録処理
function doRegist(){

	//登録処理に設定する。1:作成処理、2:登録処理
	var make =document.getElementById('make');
	make.value= "2";
	document.theForm.submit();
}
//計算1
function doCalc(calType){

	var make =document.getElementById('make');
    //計算１
	if(calType == 1) {
	  make.value= "3";
	//計算２
	} else if (calType == 2) {
	  make.value= "4";
	}
	document.theForm.submit();
}

</script>
</head >
<body >

<h2>社員給料作成</h2>

<form:form name="theForm" id="theForm"  method="post" modelAttribute="salaryInfoBean" action="salaryInfo" >
	<!-- 作成ボタンと登録ボタンを分かれる。-->
	<input type="hidden" id="make" name="make" />
	<!--input入力できるかどうか、判断用データ -->
	<!--  input type="hidden" id="gamenMode" name="gamenMode" value="${salaryInfoBean.gamenMode}"/ -->

	<!--エラーメッセージ-->
 	<p style="color: red;">
    	<c:forEach  items="${errors}" var="error">
			<spring:message message="${error}" /><br/>
		</c:forEach>
	</p>

	<table  bgcolor="white"; width:100%;>

			<tr style="background-color:#bfe1ff">
			<td>社員ID：</td>
			<td>${salaryInfoBean.employeeID}
			<!--社員idを転送 -->
			<input type="hidden" id="employeeID" name="employeeID" value="${salaryInfoBean.employeeID}"/>
			</td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>氏名：</td>
			<td>${salaryInfoBean.employeeName}
			<!--社員氏名を転送 -->
			<input type="hidden" id="employeeName" name="employeeName" value="${salaryInfoBean.employeeName}"/>
			</td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>対象月(※)：</td>
			<td><input id="month"name="month" type="text" value="${salaryInfoBean.month}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>支払(予定)日：</td>
			<td><input id="paymentDate"name="paymentDate" type="text"  value="${salaryInfoBean.paymentDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>基本給(円)：</td>
			<td><input id="base"name="base" type="text" value="${salaryInfoBean.base}" onchange="chageNumberDisp(this)" ></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>残業時間(H)：</td>
			<td><input id="overTime"name="overTime" type="text"  value="${salaryInfoBean.overTime}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>不足時間(H)：</td>
			<td><input id="shortage"name="shortage" type="text"  value="${salaryInfoBean.shortage}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr  style="background-color:#bfe1ff">
			<tr style="background-color:#bfe1ff">
			<td>交通費(円)：</td>
			<td><input id="transportExpense"name="transportExpense"   type="text" value="${salaryInfoBean.transportExpense}" onchange="chageNumberDisp(this)"></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>手当加算(円)：</td>
			<td><input id="allowancePlus"name="allowancePlus" type="text"  value="${salaryInfoBean.allowancePlus}" onchange="chageNumberDisp(this)"></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>-----------------</td>
			<td><input type="button" id="Regist1" name="Regist1" value="厚生計算"  onclick="doCalc(1)" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>残業加算(円)：</td>
			<td><input id="overTimePlus"name="overTimePlus" type="text"  value="${salaryInfoBean.overTimePlus}"onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>稼働不足減(円)：</td>
			<td><input id="shortageReduce"name="shortageReduce" type="text"  value="${salaryInfoBean.shortageReduce}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>特別加算(円)：</td>
			<td><input id="specialAddition"name="specialAddition"   type="text" value="${salaryInfoBean.specialAddition}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>手当減算(円)：</td>
			<td><input id="allowanceReduce"name="allowanceReduce" type="text"  value="${salaryInfoBean.allowanceReduce}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>手当理由：</td>
			<td><input id="allowanceReason"name="allowanceReason"  type="text"  value="${salaryInfoBean.allowanceReason}"></td>
			</tr>

			<tr style="background-color:#bfe1ff">
			<td>厚生年金控除個人(円)：</td>
			<td><input id="welfarePensionSelf"name="welfarePensionSelf" type="text"   value="${salaryInfoBean.welfarePensionSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生健康控除個人(円)：</td>
			<td><input id="welfareHealthSelf"name="welfareHealthSelf" type="text"  value="${salaryInfoBean.welfareHealthSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生年金控除会社(円)：</td>
			<td><input id="welfarePensionComp"name="welfarePensionComp" type="text"   value="${salaryInfoBean.welfarePensionComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生健康控除会社(円)：</td>
			<td><input id="welfareHealthComp"name="welfareHealthComp" type="text"  value="${salaryInfoBean.welfareHealthComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生控除子育(会社）(円)：</td>
			<td><input id="welfareBaby"name="welfareBaby" type="text"  value="${salaryInfoBean.welfareBaby}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>雇用保険個人負担(円)：</td>
			<td><input id="eplyInsSelf"name="eplyInsSelf" type="text"  value="${salaryInfoBean.eplyInsSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>雇用保険会社負担(円)：</td>
			<td><input id="eplyInsComp"name="eplyInsComp" type="text" value="${salaryInfoBean.eplyInsComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>一般拠出金（会社のみ)(円)：</td>
			<td><input id="eplyInsWithdraw"name="eplyInsWithdraw" type="text"  value="${salaryInfoBean.eplyInsWithdraw}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>労災保険（会社負担のみ）(円)：</td>
			<td><input id="wkAcccpsIns"name="wkAcccpsIns" type="text"  value="${salaryInfoBean.wkAcccpsIns}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>源泉控除(円)：</td>
			<td><input id="withholdingTax"name="withholdingTax" type="text"  value="${salaryInfoBean.withholdingTax}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>住民税控除(円)：</td>
			<td><input id="municipalTax"name="municipalTax" type="text"  value="${salaryInfoBean.municipalTax}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>社宅家賃控除(円)：</td>
			<td><input id="rental"name="rental" type="text"  value="${salaryInfoBean.rental}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>社宅共益費控除(円)：</td>
			<td><input id="rentalMgmtFee"name="rentalMgmtFee" type="text"  value="${salaryInfoBean.rentalMgmtFee}" onchange="chageNumberDisp(this)"></td>
			</tr>

			<tr style="background-color:#bfe1ff">
			<td>特別控除(円)：</td>
			<td><input id="specialReduce"name="specialReduce" type="text"  value="${salaryInfoBean.specialReduce}" onchange="chageNumberDisp(this)"></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>====================</td>
			<td><input type="button" id="Regist2" name="Regist2" value="合計計算"  onclick="doCalc(2)" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>総額(円)：</td>
			<td>
			 <input id="sum"name="sum" type="text"  value="${salaryInfoBean.sum}" />

			 </td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>総費用(円)：</td>
			<td>
			  <!-- input id="totalFee"name="totalFee" type="text"  value="${salaryInfoBean.totalFee}" onchange="chageNumberDisp(this)" -->
			  <input id="totalFee"name="totalFee" type="text"  value="${salaryInfoBean.totalFee}" />
			  </td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>備考：</td>
			<td><input id="remark"name="remark" type="text"  value="${salaryInfoBean.remark}"></td>
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
