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
</head>
<body>
<form:form name="theForm" id="theForm" method="post" modelAttribute="contractInfoBean"  action="contractInfoEdit" >
				<h1>契約情報更新</h1>
		<table  border="1">
		<c:forEach items="${list}" var="contractInfoList" varStatus="status">
			<tr style="background-color:#dcfeeb">
				<td width="200px">契約ID</td>
				<td width="200px"><c:out value="${contractInfoList.getContractID()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約名称</td>
				<td width="200px"><c:out value="${contractInfoList.getContractName()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">社員ID</td>
				<td width="200px"><c:out value="${contractInfoList.getEmployeeID()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">社員名</td>
				<td width="200px"><c:out value="${contractInfoList.getEmployeeName()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">取引先ID</td>
				<td width="200px"> <c:out value="${contractInfoList.getCompanyID()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">取引先名称</td>
				<td width="200px"> <c:out value="${contractInfoList.getCompanyName()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">単価</td>
				<td width="200px"><c:out value="${contractInfoList.getPrice()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">精算タイプ</td>
				<td width="200px"> <c:out value="${contractInfoList.getPayOff()}"/>
			<input type="radio" name="yesorno" id="yes" value="yes">
	            				   <label for="yes" class="btn">清算あり</label>
	           					   <input type="radio" name="yesorno" id="no" value="no">
	            				   <label for="no" class="btn">固定額</label>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約下限</td>
				<td width="200px"> <c:out value="${contractInfoList.getLowerTime()}"/></td>
			</tr>
				<tr style="background-color:#dcfeeb">
				<td width="200px">控除単価</td>
				<td width="200px"> <c:out value="${contractInfoList.getLowerPrice()}"/></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">契約上限</td>
				<td width="200px"> <c:out value="${contractInfoList.getUpperTime()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">残業単価</td>
				<td width="200px"> <c:out value="${contractInfoList.getUpperPrice()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約開始日</td>
				<td width="200px"> <c:out value="${contractInfoList.getContractBeginDate()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">契約終了日</td>
				<td width="200px"><c:out value="${contractInfoList.getContractEndDate()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">支払サイト</td>
	            <td width="200px"><c:out value="${contractInfoList.getPaymentTerm()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td width="200px">原本郵送フラグ</td>
			<td width="200px"><c:out value="${contractInfoList.getPostNeed()}"/>
			<input type="radio" name="yesorno" id="yes" value="yes">
				<label for="yes" class="btn">要郵送</label>
			<input type="radio" name="yesorno" id="no" value="no">
				<label for="no" class="btn">不要</label></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">タイムレポートパス</td>
				<td width="200px"><c:out value="${contractInfoList.getTimeReportPath()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">請求書名称</td>
				<td width="200px"><c:out value="${contractInfoList.getInvoice()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">進行ステータス</td>
				<td width="200px"><c:out value="${contractInfoList.getStatus()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">作成日</td>
				<td width="200px"><c:out value="${contractInfoList.getInsertDate()}"/></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">更新日</td>
				<td width="200px"><c:out value="${contractInfoList.getUpdateDate()}"/></td>
			</tr>
		</c:forEach>
		</table>
		<button type="button"   class="btn btn-primary">登録</button>
</form:form>
</body>
</html>
