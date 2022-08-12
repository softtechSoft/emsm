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
	//登録ボタンのクリック処理
	function doRegist(){
		document.theForm.submit();
	}

	//初期表示時、経費明細selectを非活性に設定する
	function onInit() {
		 document.getElementById( "expensesTypeDetail" ).disabled=true;
	}

	//経費が選択される場合、経費明細selectを設定する
	function setDetail(){
		//経費に一般経費設定された場合、経費詳細を活性する
		if(document.getElementById( "expensesType" ).value == '4'){
			 document.getElementById( "expensesTypeDetail" ).disabled=false;
		//以外の場合、経費詳細を非活性する
		}else{
			 document.getElementById( "expensesTypeDetail" ).disabled=true;
		}
	}

</script>
</head>
<body onload="onInit()">
<h2>経費管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="expensesManagementBean" action="expensesManagementSubmit" >

    <p style="color: red;">
	    <c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> </br>
		</c:forEach>
	</p>

	<div>
	<table>
	<tr border ="1" style="background-color:#dcfeeb">
			<td>発生日(YYYY/MM/DD)：</td>
			<td><input id="accrualDate"name="accrualDate" type="text" value="${expensesManagementBean.accrualDate }" size="45"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>担当者：</td>
			<td>
			<form:select path="employeeID">
		    <form:options items="${employeeList}" itemLabel="employeeName"  itemValue="employeeID"/>
	        </form:select>
		    </td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>金額(円)：</td>
			<td><input id="cost"name="cost" type="text"  value="${expensesManagementBean.cost }" size="45"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>経費種別：</td>
			<td><form:select path="expensesType" onChange="setDetail();">
			<form:options items="${expensesList}" itemLabel="expensesName"  itemValue="expensesID"/>
			</form:select>
			<form:select path="expensesTypeDetail">
			<form:options items="${expensesList1}" itemLabel="expensesName"  itemValue="expensesID"/>
			</form:select></td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>場所：</td>
			<td><input id="happenAddress"name="happenAddress" type="text"  value="${expensesManagementBean.happenAddress}" size ="45"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>承認ステータス：</td>
			<td><input id="confirmStaus"name="confirmStaus" type="radio"  <c:if test="${expensesManagementBean.confirmStaus == '0'}">checked</c:if> value="0">申請<input id="confirmStaus"name="confirmStaus" type="radio" <c:if test="${expensesManagementBean.confirmStaus == '1'}">checked</c:if> value="1">承認</td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>精算タイプ：
			<td><input id="stmtlType" name="stmtlType" type="radio"  <c:if test="${expensesManagementBean.stmtlType == '1'}">checked</c:if> value="1">現金 <input id="stmtlType" name="stmtlType" type="radio"   <c:if test="${expensesManagementBean.stmtlType == '2'}">checked</c:if> value="2">口座</td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>精算ステータス：</td>
			<td><input id="stmtlStaus"name="stmtlStaus" type="radio" <c:if test="${expensesManagementBean.stmtlStaus == '0'}">checked</c:if> value="0">未精算 <input id="stmtlStaus"name="stmtlStaus" type="radio" <c:if test="${expensesManagementBean.stmtlStaus == '1'}">checked</c:if> value="1">精算</td>
			</tr>
			<tr  border ="1" style="background-color:#dcfeeb">
			<td>精算日(YYYY/MM/DD)：</td>
			<td><input id="stmtlDate"name="stmtlDate" type="text"  value="${expensesManagementBean.stmtlDate}" size="45"></td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>備考：</td>
			<td><input id="remark"name="remark" type="text"  value="${expensesManagementBean.remark}" size="45"></td>
			</tr>
			<tr>
            <td></td>
			<td style="text-align:right"><input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()"></td>
		    </tr>
	</table>
	</div>

</form:form>
</div>
</body>
</html>