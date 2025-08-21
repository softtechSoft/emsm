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

	//作成処理の設定。1:作成処理、2:登録処理
	var make =document.getElementById("make");
	make.value= "1";

	document.theForm.submit();
}

//画面のonLoad処理
function init(){

	//対象年月変更不可に設定。
	//document.getElementById('month').readOnly=true;
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
		//特別加算
		document.getElementById('specialAddition').readOnly=true;
		document.getElementById('allowancePlus').readOnly=true;
		document.getElementById('allowanceReduce').readOnly=true;
		document.getElementById('allowanceReason').readOnly=true;
		document.getElementById('welfarePensionSelf').readOnly=true;
		document.getElementById('welfareHealthSelf').readOnly=true;
		document.getElementById('welfarePensionComp').readOnly=true;
		document.getElementById('welfareHealthComp').readOnly=true;
		document.getElementById('welfareBaby').readOnly=true;
		document.getElementById('eplyInsSelf').readOnly=true;
		document.getElementById('eplyInsComp').readOnly=true;
		document.getElementById('eplyInsWithdraw').readOnly=true;
		document.getElementById('wkAcccpsIns').readOnly=true;
		document.getElementById('withholdingTax').readOnly=true;
		document.getElementById('municipalTax').readOnly=true;
		document.getElementById('rental').readOnly=true;
		document.getElementById('rentalMgmtFee').readOnly=true;
		//特別控除
		document.getElementById('specialReduce').readOnly=true;
		document.getElementById('sum').readOnly=true;
		document.getElementById('totalFee').readOnly=true;
		document.getElementById('remark').readOnly=true;

		//初期画面の登録ボタン使用禁止
		document.getElementById("Registration").disabled=true;
	  }else{
		//作成ボタンを1回だけEnable
		//document.getElementById("makeSalary").disabled=true;
	  }

	//画面モード 0:画面初期表示、1：給料情報の新規作成。2：給料情報の更新。
	if(gamenMode=="2"){
		//作成ボタンと変更ボタンの変更
		//document.getElementById('makeSalary').value="変更";
	}
}

//登録処理
function doRegist(){
	//登録処理に設定する。1:作成処理、2:登録処理
	var make =document.getElementById('make');
	make.value= "2";
	//setSum();　//20250821
	document.theForm.submit();
}
//数値表示方を変更する。２０００→２，０００
function chageNumberDisp(textObject){
	var num = textObject.value;
	num=toNumberDisp(num);
	textObject.value = String(num).replace( /(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');

}

//総額、総費用を再計算
function setSum(){
	//サーバ側に計算する
	make.value= "3";
	document.theForm.submit();
/*
	//税率を設定
	var laborBurden = toNumberDisp(document.getElementById('laborBurdenRate').value);  // 雇用保険個人負担
    var employerBurden = toNumberDisp(document.getElementById('employerBurdenRate').value);  // 雇用保険会社負担
    var employmentInsurance = toNumberDisp(document.getElementById('employmentInsurance').value);  // 雇用保拠出金（会社)
    var industrialAccidentInsurance = toNumberDisp(document.getElementById('industrialAccidentInsuranceRate').value);  // 労災保険（会社負担のみ）



	//基本給の値を取得
	var base = document.getElementById('base').value;
	//数字化する
	base=toNumberDisp(base);

	//残業加算の値を取得
	var overTimePlus = document.getElementById('overTimePlus').value;
	//数字化する
	overTimePlus=toNumberDisp(overTimePlus);

	//稼働不足減の値を取得
	var shortageReduce = document.getElementById('shortageReduce').value;
	//数字化する
	shortageReduce=toNumberDisp(shortageReduce);

	//交通費の値を取得
	var transportExpense = document.getElementById('transportExpense').value;
	//数字化する
	transportExpense=toNumberDisp(transportExpense);

	//特別加算
	var specialAddition = document.getElementById('specialAddition').value;
	//数字化する
	specialAddition=toNumberDisp(specialAddition);

	//手当加算の値を取得
	var allowancePlus = document.getElementById('allowancePlus').value;
	//数字化する
	allowancePlus=toNumberDisp(allowancePlus);

	//手当減算の値を取得
	var allowanceReduce = document.getElementById('allowanceReduce').value;
	//数字化する
	allowanceReduce=toNumberDisp(allowanceReduce);
	//雇用保険の対象額(修正後：基本給＋交通費＋残業金額＋手当)
	var hoKenSalary = base + transportExpense + overTimePlus + allowancePlus;

	//厚生年金控除個人の値を取得
	var welfarePensionSelf = document.getElementById('welfarePensionSelf').value;
	//数字化する
	welfarePensionSelf=toNumberDisp(welfarePensionSelf);

	//厚生健康控除個人の値を取得
	var welfareHealthSelf = document.getElementById('welfareHealthSelf').value;
	//数字化する
	welfareHealthSelf=toNumberDisp(welfareHealthSelf);

	//雇用保険個人負担の値を取得(対象額)
	var eplyInsSelf = document.getElementById('eplyInsSelf').value;
	//数字化する
	//eplyInsSelf=toNumberDisp(eplyInsSelf);
	eplyInsSelf=hoKenSalary * laborBurden;
	document.getElementById('eplyInsSelf').value=eplyInsSelf;

	//源泉控除の値を取得
	var withholdingTax = document.getElementById('withholdingTax').value;
	//数字化する
	withholdingTax=toNumberDisp(withholdingTax);

	//住民税控除の値を取得
	var municipalTax = document.getElementById('municipalTax').value;
	//数字化する
	municipalTax=toNumberDisp(municipalTax);

	//社宅家賃控除の値を取得
	var rental = document.getElementById('rental').value;
	//数字化する
	rental=toNumberDisp(rental);

	//社宅共益費控除の値を取得
	var rentalMgmtFee = document.getElementById('rentalMgmtFee').value;
	//数字化する
	rentalMgmtFee=toNumberDisp(rentalMgmtFee);

	//特別控除
	var specialReduce = document.getElementById('specialReduce').value;
	//数字化する
	specialReduce=toNumberDisp(specialReduce);

	//総額を計算し、設定する。
	// 総額 = 基本給
	// 		 + 残業加算
	// 		 + 交通費
	// 		 + 手当加算
	// 		 + 特別加算
	// 		 - 稼働不足減
	// 		 - 手当減算
	// 		 - 厚生年金控除個人
	// 		 - 厚生健康控除個人
	// 		 - 雇用保険個人
	// 		 - 源泉控除
	// 		 - 住民税
	// 		 - 社宅家賃
	// 		 - 社宅共益費
	// 		 - 特別控除
	var salarySum=base
				+overTimePlus
				+transportExpense
				+allowancePlus
				+specialAddition
				-shortageReduce
				-allowanceReduce
				-welfarePensionSelf
				-welfareHealthSelf
				-eplyInsSelf
				-withholdingTax
				-municipalTax
				-rental
				-rentalMgmtFee
				-specialReduce;

	//document.getElementById('sum').value=String(salarySum).replace( /(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
	document.getElementById('sum').value=salarySum;

	//厚生年金控除会社の値を取得
	var welfarePensionComp = document.getElementById('welfarePensionComp').value;
	//数字化する
	welfarePensionComp=toNumberDisp(welfarePensionComp);

	//厚生健康控除会社の値を取得
	var welfareHealthComp = document.getElementById('welfareHealthComp').value;
	//数字化する
	welfareHealthComp=toNumberDisp(welfareHealthComp);

	//厚生控除子育(会社）の値を取得
	var welfareBaby = document.getElementById('welfareBaby').value;
	//数字化する
	welfareBaby=toNumberDisp(welfareBaby);

	//雇用保険会社負担の値を取得(対象額)
	var eplyInsComp = document.getElementById('eplyInsComp').value;
	//数字化する
	//eplyInsComp=toNumberDisp(eplyInsComp);
	eplyInsComp=hoKenSalary * employerBurden;
	document.getElementById('eplyInsComp').value=eplyInsComp;

	//一般拠出金（会社のみ)の値を取得(対象額)
	var eplyInsWithdraw = document.getElementById('eplyInsWithdraw').value;
	//数字化する
	//eplyInsWithdraw=toNumberDisp(eplyInsWithdraw);
	eplyInsWithdraw=hoKenSalary * employmentInsurance;
	document.getElementById('eplyInsWithdraw').value=eplyInsWithdraw;

	//労災保険（会社負担のみ）の値を取得(対象額)
	var wkAcccpsIns = document.getElementById('wkAcccpsIns').value;
	//数字化する
	//wkAcccpsIns=toNumberDisp(wkAcccpsIns);
	wkAcccpsIns=hoKenSalary * industrialAccidentInsurance;
	document.getElementById('wkAcccpsIns').value=wkAcccpsIns;

	//総費用を計算し、設定する。
	//．総費用 = 基本給
	// 			+残業加算
	// 			+交通費
	// 			+手当加算
	// 			+厚生年金控除会社
	// 			+厚生健康控除会社
	// 			+厚生控除子育(会社）
	// 			+雇用保険会社
	// 			+一般拠出金（会社のみ)
	// 			+労災保険（会社負担のみ）
	// 			+特別加算
	// 			-稼働不足減
	// 			-手当減算
	// 			-特別減
	var fee=base
				+overTimePlus
				+transportExpense
				+allowancePlus
				+welfarePensionComp
				+welfareHealthComp
				+welfareBaby
				+eplyInsComp
				+eplyInsWithdraw
				+wkAcccpsIns
				+specialAddition
				-shortageReduce
				-allowanceReduce
				-specialReduce;

	//document.getElementById('totalFee').value=String(salarySum).replace( /(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
	document.getElementById('totalFee').value=fee
	*/
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
// 対象月変更されたら、住民税を手動入力するためクリアする
function chageMonth(){
	document.getElementById('municipalTax').value="0";
}
//入力バリデーション修正
function validateTimeInput(input) {
    // 小数点以下1桁までの数値かチェック
    if (!/^\d*\.?\d{0,1}$/.test(input.value)) {
        input.value = input.value.substring(0, input.value.length-1);
    }
}
</script>
</head >
<body onload="init()">

<h2>社員給料作成</h2>

<form:form name="theForm" id="theForm"  method="post" modelAttribute="salaryInfoBean" action="salaryInfo" >
	<!-- 作成ボタンと登録ボタンを分かれる。-->
	<input type="hidden" id="make" name="make" />
	<!--input入力できるかどうか、判断用データ -->
	<input type="hidden" id="gamenMode" name="gamenMode" value="${salaryInfoBean.gamenMode}"/>

	<!-- 其他费率也类似添加 -->
	<input type="hidden" id="laborBurdenRate" value="${laborBurdenRate}">
	<input type="hidden" id="employerBurdenRate" value="${employerBurdenRate}">
	<input type="hidden" id="industrialAccidentInsuranceRate" value="${industrialAccidentInsuranceRate}">
	<input type="hidden" id="employmentInsurance" value="${employmentInsurance}">
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
			<!--  input type="button" id="makeSalary" name="makeSalary"  value="作成" onclick="doMake()" / -->

			</td>
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

			<tr style="background-color:#dcfeeb">
			<td>対象月(※)：</td>
			<td><input id="month"name="month" type="text" value="${salaryInfoBean.month}" onchange="chageMonth()"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>基本給(算)：</td>
			<td><input id="base"name="base" type="text" value="${salaryInfoBean.base}" onchange="chageNumberDisp(this);setSum()" ></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>支払日：</td>
			<td><input id="paymentDate"name="paymentDate" type="text"  value="${salaryInfoBean.paymentDate}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>残業時間(算)：</td>
			<td><input id="overTime"name="overTime" type="text"  value="${salaryInfoBean.overTime}" onchange="chageNumberDisp(this);setSum()"
			oninput="validateTimeInput(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>不足時間(算)：</td>
			<td><input id="shortage"name="shortage" type="text"  value="${salaryInfoBean.shortage}" onchange="chageNumberDisp(this);setSum()"
           oninput="validateTimeInput(this)"></td>
			</tr>
			<tr  style="background-color:#bfe1ff">
			<td>残業加算：</td>
			<td><input id="overTimePlus"name="overTimePlus" type="text"  value="${salaryInfoBean.overTimePlus}"onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>稼働不足減：</td>
			<td><input id="shortageReduce"name="shortageReduce" type="text"  value="${salaryInfoBean.shortageReduce}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>交通費(算)：</td>
			<td><input id="transportExpense"name="transportExpense"   type="text" value="${salaryInfoBean.transportExpense}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>

			<tr style="background-color:#bfe1ff">
			<td>特別加算(算)：</td>
			<td><input id="specialAddition"name="specialAddition"   type="text" value="${salaryInfoBean.specialAddition}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>手当加算(算)：</td>
			<td><input id="allowancePlus"name="allowancePlus" type="text"  value="${salaryInfoBean.allowancePlus}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>手当減算(算)：</td>
			<td><input id="allowanceReduce"name="allowanceReduce" type="text"  value="${salaryInfoBean.allowanceReduce}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>手当理由：</td>
			<td><input id="allowanceReason"name="allowanceReason"  type="text"  value="${salaryInfoBean.allowanceReason}"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生年金控除個人：</td>
			<td><input id="welfarePensionSelf"name="welfarePensionSelf" type="text"   value="${salaryInfoBean.welfarePensionSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生健康控除個人：</td>
			<td><input id="welfareHealthSelf"name="welfareHealthSelf" type="text"  value="${salaryInfoBean.welfareHealthSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生年金控除会社：</td>
			<td><input id="welfarePensionComp"name="welfarePensionComp" type="text"   value="${salaryInfoBean.welfarePensionComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>厚生健康控除会社：</td>
			<td><input id="welfareHealthComp"name="welfareHealthComp" type="text"  value="${salaryInfoBean.welfareHealthComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>厚生控除子育(会社）：</td>
			<td><input id="welfareBaby"name="welfareBaby" type="text"  value="${salaryInfoBean.welfareBaby}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>雇用保険個人負担：</td>
			<td><input id="eplyInsSelf"name="eplyInsSelf" type="text"  value="${salaryInfoBean.eplyInsSelf}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>雇用保険会社負担：</td>
			<td><input id="eplyInsComp"name="eplyInsComp" type="text" value="${salaryInfoBean.eplyInsComp}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>一般拠出金（会社のみ)：</td>
			<td><input id="eplyInsWithdraw"name="eplyInsWithdraw" type="text"  value="${salaryInfoBean.eplyInsWithdraw}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>労災保険（会社負担のみ）：</td>
			<td><input id="wkAcccpsIns"name="wkAcccpsIns" type="text"  value="${salaryInfoBean.wkAcccpsIns}" onchange="chageNumberDisp(this)"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>源泉控除(算)：</td>
			<td><input id="withholdingTax"name="withholdingTax" type="text"  value="${salaryInfoBean.withholdingTax}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>住民税控除(算)：</td>
			<td><input id="municipalTax"name="municipalTax" type="text"  value="${salaryInfoBean.municipalTax}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td>社宅家賃控除(算)：</td>
			<td><input id="rental"name="rental" type="text"  value="${salaryInfoBean.rental}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>社宅共益費控除(算)：</td>
			<td><input id="rentalMgmtFee"name="rentalMgmtFee" type="text"  value="${salaryInfoBean.rentalMgmtFee}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>

			<tr style="background-color:#bfe1ff">
			<td>特別控除(算)：</td>
			<td><input id="specialReduce"name="specialReduce" type="text"  value="${salaryInfoBean.specialReduce}" onchange="chageNumberDisp(this);setSum()"></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td>総額：</td>
			<td>
			 <!-- input id="sum"name="sum" type="text"  value="${salaryInfoBean.sum}" onchange="chageNumberDisp(this)" -->
			 <input id="sum"name="sum" type="text"  value="${salaryInfoBean.sum}" />

			 </td>
			</tr>
			<tr style="background-color:#bfe1ff">
			<td>総費用：</td>
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
