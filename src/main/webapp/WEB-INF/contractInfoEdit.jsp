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
		//登録ボタンのクリック処理
		function doRegist(){
			document.theForm.submit();
	}

</script>
</head>
<body>
<form:form name="theForm" id="theForm" method="post" modelAttribute="contractInfoBean"  action="contractInfoEdit" >
				<h1>契約情報更新</h1>
		<p style="color: red;">
			<c:forEach items="${errors}" var="error">
				<spring:message message="${error}" /> </br>
			</c:forEach>
		</p>

		<input type="hidden" id="contractID" name="contractID" value="${contractInfoBean.contractID}"/>
		<!--新規フラグ　０　新規　１　更新-->
		<input type="hidden" id="insertFlg" name="insertFlg" value="${contractInfoBean.insertFlg}"/>

		<table  border="1">
			<tr style="background-color:#dcfeeb">
				<td width="200px">契約ID</td>
				<td width="200px"><c:out  value="${contractInfoBean.getContractID()}"/></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">契約名称</td>
				<td width="200px"><input type="text" id="contractName" name="contractName"
									value="${contractInfoBean.contractName}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">

				<td width="200px">社員</td>
				<td width="200px">
					<form:select path="employeeID">
						<form:options items="${employeeList}" itemLabel="employeeName"  itemValue="employeeID"/>
					</form:select>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">取引先名称</td>
				<td width="200px">
					<form:select path="companyID">
						<form:options items="${companyList}" itemLabel="companyName"  itemValue="companyID"/>
					</form:select>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">単価</td>
				<td width="200px"><input type="text" id="price" name="price"
									value="${contractInfoBean.price}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">

				<td width="200px">精算タイプ</td>
				<td width="200px"><input type="radio" name="payOff" <c:if test="${contractInfoBean.payOff == '0'}">
				checked</c:if> value="0" /> 清算あり
				<input type="radio" name="payOff" <c:if test="${contractInfoBean.payOff == '1'}">
				checked</c:if> value="1" /> 固定額</td>

			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約下限</td>
				<td width="200px"><input type="text" id="lowerTime" name="lowerTime"
									value="${contractInfoBean.lowerTime}" /></td>

			</tr>
				<tr style="background-color:#dcfeeb">
				<td width="200px">控除単価</td>
				<td width="200px"><input type="text" id="lowerPrice" name="lowerPrice"
									value="${contractInfoBean.lowerPrice}" /></td>

			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">契約上限</td>
				<td width="200px"><input type="text" id="upperTime" name="upperTime"
									value="${contractInfoBean.upperTime}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">残業単価</td>
				<td width="200px"> <input type="text" id="upperPrice" name="upperPrice"
									value="${contractInfoBean.upperPrice}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約開始日</td>
				<td width="200px">
				 <!--  input type="text" id="contractBeginDate" name="contractBeginDate"	value="${contractInfoBean.contractBeginDate}" / -->
					<input type="date" id="contractBeginDateS" name="contractBeginDateS"	value="${contractInfoBean.contractBeginDate}" />
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約終了日</td>
				<td width="200px">
					<input type="date" id="contractEndDateS" name="contractEndDateS"	value="${contractInfoBean.contractEndDate}" />
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">支払サイト</td>
	            <td width="200px"> <input type="text" id="paymentTerm" name="paymentTerm"
									value="${contractInfoBean.paymentTerm}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td width="200px">原本郵送フラグ</td>
			<td width="200px"><input type="radio" name="postNeed" <c:if test="${contractInfoBean.postNeed == '0'}">
								checked</c:if> value="0" /> 要郵送
								<input type="radio" name="postNeed" <c:if test="${contractInfoBean.postNeed == '1'}">
				checked</c:if> value="1" /> 不要</td>

			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">タイムレポートパス</td>
				<td width="200px"><input type="text" id="timeReportPath" name="timeReportPath"
									value="${contractInfoBean.timeReportPath}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">請求書名称</td>
				<td width="200px"><input type="text" id="invoice" name="invoice"
									value="${contractInfoBean.invoice}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">進行ステータス</td>
				<td width="200px"><input type="radio" name="status" <c:if test="${contractInfoBean.status == '1'}">
									checked</c:if> value="1" /> 進行中
									<input type="radio" name="status" <c:if test="${contractInfoBean.status == '9'}">
									checked</c:if> value="9" /> 終了</td>
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
