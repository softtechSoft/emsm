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

//作成処理
function doMake(){

	//作成処理の設定。1:登録処理、2:作成処理
	var make =document.getElementById("make");
	make.value= "2";

	document.theForm.submit();
}

//画面のonLoad処理
function init(){

	//対象年月変更不可に設定。
	document.getElementById('month').readOnly=true;
    //input入力判断用: 0:画面初期表示、１：給料データ新規作成、２：給料データ更新
	var gamenMode =document.getElementById('gamenMode').value;
	//初期表示に、全項目変更不可に設定。
	if(gamenMode == "0") {
		document.getElementById('base').readOnly=true;
		document.getElementById('paymentDate').readOnly=true;
		document.getElementById('overTime').readOnly=true;
		document.getElementById('shortage').readOnly=true;
		document.getElementById('overTimePlus').readOnly=true;
		document.getElementById('shortageReduce').readOnly=true;
		document.getElementById('transportExpense').readOnly=true;
		document.getElementById('allowancePlus').readOnly=true;
		document.getElementById('allowanceReduce').readOnly=true;
		document.getElementById('allowanceReason').readOnly=true;
		document.getElementById('welfareSelf').readOnly=true;
		document.getElementById('welfareComp').readOnly=true;
		document.getElementById('welfareBaby').readOnly=true;
		document.getElementById('eplyInsSelf').readOnly=true;
		document.getElementById('eplyInsComp').readOnly=true;
		document.getElementById('eplyInsWithdraw').readOnly=true;
		document.getElementById('wkAcccpsIns').readOnly=true;
		document.getElementById('withholdingTax').readOnly=true;
		document.getElementById('municipalTax').readOnly=true;
		document.getElementById('rental').readOnly=true;
		document.getElementById('rentalMgmtFee').readOnly=true;
		document.getElementById('sum').readOnly=true;
		document.getElementById('totalFee').readOnly=true;
		document.getElementById('remark').readOnly=true;
		//初期画面の登録ボタン使用禁止
		document.getElementById("Registration").disabled=true;
	  }
	//画面モード 0:画面初期表示、1：給料情報の新規作成。2：給料情報の更新。
	if(gamenMode=="2"){
		//作成ボタンと変更ボタンの変更
		document.getElementById('makeSalary').value="変更";
	}
}

//登録処理
function doRegist(){

	//登録処理に設定する。1:登録処理、2:作成処理
	var make =document.getElementById('make');
	make.value= "1";

	document.theForm.submit();
}
</script>
</head >
<body onload="init()">

<h2>社員給料作成</h2>

<form:form name="theForm" id="theForm"  method="post" modelAttribute="salaryInfoBean" action="salaryInfo" >
	<!-- 作成ボタンと登録ボタンを分かれる。-->
	<input type="hidden" id="make" name="make" />
	<!--input入力できるかどうか、判断用データ -->
	<input type="hidden" id="gamenMode" name="gamenMode" value="${gamenMode}"/>
	<!--エラーメッセージ-->
	<p style="color: red;">
    	<c:forEach  items="${errors}" var="error">
			<spring:message message="${error}" /><br/>
		</c:forEach>
	</p>

	<table  bgcolor="white"; width:100%;>
			<tr>
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="makeSalary" name="makeSalary"  value="作成" onclick="doMake()" /></td>
			</tr>
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
			<tr  style="background-color:#bfe1ff">
			<td>住所：</td>
			<td>${salaryInfoBean.address}
			<!--社員住所を転送 -->
			<input type="hidden" id="address" name="address" value="${salaryInfoBean.address}"/>
			</td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>対象月：</td>
			<td><input id="month"name="month" type="text" value="${salaryInfoBean.month}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>基本給：</td>
			<td><input id="base"name="base" type="text" value="${salaryInfoBean.base}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>支払日：</td>
			<td><input id="paymentDate"name="paymentDate" type="text"  value="${salaryInfoBean.paymentDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>残業時間：</td>
			<td><input id="overTime"name="overTime" type="text"  value="${salaryInfoBean.overTime}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>不足時間：</td>
			<td><input id="shortage"name="shortage" type="text"  value="${salaryInfoBean.shortage}"></td>
			</tr>
			<tr  style="background-color:#bfe1ff">
			<td>残業加算：</td>
			<td><input id="overTimePlus"name="overTimePlus" type="text"  value="${salaryInfoBean.overTimePlus}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>稼働不足減：</td>
			<td><input id="shortageReduce"name="shortageReduce" type="text"  value="${salaryInfoBean.shortageReduce}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>交通費：</td>
			<td><input id="transportExpense"name="transportExpense"   type="text" value="${salaryInfoBean.transportExpense}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>手当加算：</td>
			<td><input id="allowancePlus"name="allowancePlus" type="text"  value="${salaryInfoBean.allowancePlus}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>手当減算：</td>
			<td><input id="allowanceReduce"name="allowanceReduce" type="text"  value="${salaryInfoBean.allowanceReduce}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>手当理由：</td>
			<td><input id="allowanceReason"name="allowanceReason"  type="text"  value="${salaryInfoBean.allowanceReason}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生控除個人：</td>
			<td><input id="welfareSelf"name="welfareSelf" type="text"   value="${salaryInfoBean.welfareSelf}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生控除会社：</td>
			<td><input id="welfareComp"name="welfareComp" type="text"  value="${salaryInfoBean.welfareComp}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生控除子育(会社）：</td>
			<td><input id="welfareBaby"name="welfareBaby" type="text"  value="${salaryInfoBean.welfareBaby}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>雇用保険個人負担：</td>
			<td><input id="eplyInsSelf"name="eplyInsSelf" type="text"  value="${salaryInfoBean.eplyInsSelf}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>雇用保険会社負担：</td>
			<td><input id="eplyInsComp"name="eplyInsComp" type="text" value="${salaryInfoBean.eplyInsComp}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>雇用保拠出金（会社)：</td>
			<td><input id="eplyInsWithdraw"name="eplyInsWithdraw" type="text"  value="${salaryInfoBean.eplyInsWithdraw}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>労災保険（会社負担のみ）：</td>
			<td><input id="wkAcccpsIns"name="wkAcccpsIns" type="text"  value="${salaryInfoBean.wkAcccpsIns}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>源泉控除：</td>
			<td><input id="withholdingTax"name="withholdingTax" type="text"  value="${salaryInfoBean.withholdingTax}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>住民税控除：</td>
			<td><input id="municipalTax"name="municipalTax" type="text"  value="${salaryInfoBean.municipalTax}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>社宅家賃控除：</td>
			<td><input id="rental"name="rental" type="text"  value="${salaryInfoBean.rental}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>社宅共益費控除：</td>
			<td><input id="rentalMgmtFee"name="rentalMgmtFee" type="text"  value="${salaryInfoBean.rentalMgmtFee}"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>総額：</td>
			<td><input id="sum"name="sum" type="text"  value="${salaryInfoBean.sum}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>総費用：</td>
			<td><input id="totalFee"name="totalFee" type="text"  value="${salaryInfoBean.totalFee}"></td>
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
