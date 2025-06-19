<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link type="text/css" rel="stylesheet" href="src/emsm/css/salarylist.css"></link>-->
<script type="text/javascript" >
	//検索ボタン処理
	function toSearchJsp(){
		document.theForm.submit();
	}
	// 更新ボタン処理
	function toUpdateJsp(contractId){
		// 更新
		document.getElementById('insertFlg').value='1';
		document.getElementById('contractID').value=contractId;
		document.theForm.action="toInitContractInfo";
		document.theForm.submit();
	}

	// 新規ボタン処理
	function toMakeJsp(){
		// 新規
		document.getElementById('insertFlg').value='0';
		document.theForm.action="toInitContractInfo";
		document.theForm.submit();
	}

</script>
<title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
		<h2>契約情報管理リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="contractInfoBean"  action="contractInfoList" >
	<!--契約ID-->
	<input type="hidden" id="contractID" name="contractID" value="${contractInfoBean.contractID}"/>
	<!--新規フラグ　０　新規　１　更新-->
	<input type="hidden" id="insertFlg" name="insertFlg" value="${contractInfoBean.insertFlg}"/>

	<b>社員:</b>
	<form:select path="employeeID">
    	<form:option value="">全社員</form:option>
		<form:options items="${contractList}" itemLabel="employeeName"  itemValue="employeeID"/>
	</form:select>

	<input type="button" name="search" value="検索" onclick="toSearchJsp();" />
			<!--エラーメッセージ-->
		<p style="color: red;">
	    	<c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> </br>
			</c:forEach>
		</p>

	<table border="1"class="contractInfoList-table">
		   	 <tr>
        		<th width="200">契約ID</th>
        		<th width="400">契約名称</th>
        		<th width="400">社員ID</th>
        		<th width="400">社員名</th>
        		<th width="400">取引先ID</th>
        		<th width="400">取引先名称</th>
        		<th width="300">単価</th>
        		<th width="300">精算タイプ</th>
        		<th width="300">契約下限</th>
        		<th width="500">控除単価</th>
        		<th width="300">契約上限</th>
				<th width="300">残業単価</th>
        		<th width="300">契約開始日</th>
        		<th width="300">契約終了日</th>
        		<th width="500">支払サイト</th>
        		<th width="300">原本郵送フラグ</th>
        		<th width="500">タイムレポートパス</th>
        		<th width="300">請求書名称</th>
				<th width="300">進行ステータス</th>
        		<th width="300">作成日</th>
        		<th width="300">更新日</th>
        		<th width="300">更新へ</th>
        	</tr>

        	<c:forEach items="${list}" var="contractInfoList" varStatus="status">
				<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
					<c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
					<td><c:out value="${contractInfoList.getContractID()}"/></td>
					<td><c:out value="${contractInfoList.getContractName()}"/></td>
					<td><c:out value="${contractInfoList.getEmployeeID()}"/></td>
					<td><c:out value="${contractInfoList.getEmployeeName()}"/></td>
					<td><c:out value="${contractInfoList.getCompanyID()}"/></td>
					<td><c:out value="${contractInfoList.getCompanyName()}"/></td>
					<td><c:out value="${contractInfoList.getPrice()}"/></td>
					<td><c:out value="${contractInfoList.getPayOff() == '0' ? '清算あり' : '固定額'}"/></td>
					<td><c:out value="${contractInfoList.getLowerTime()}"/></td>
					<td><c:out value="${contractInfoList.getLowerPrice()}"/></td>
					<td><c:out value="${contractInfoList.getUpperTime()}"/></td>
					<td><c:out value="${contractInfoList.getUpperPrice()}"/></td>
					<td><c:out value="${contractInfoList.getContractBeginDate()}"/></td>
					<td><c:out value="${contractInfoList.getContractEndDate()}"/></td>
					<td><c:out value="${contractInfoList.getPaymentTerm()}"/></td>
					<td><c:out value="${contractInfoList.getPostNeed() == '0' ? '要郵送' : '不要'}"/></td>
					<td><c:out value="${contractInfoList.getTimeReportPath()}"/></td>
					<td><c:out value="${contractInfoList.getInvoice()}"/></td>
					<td><c:out value="${contractInfoList.getStatus() == '1' ? '進行中' : '終了'}"/></td>
					<td><c:out value="${contractInfoList.getInsertDate()}"/></td>
					<td><c:out value="${contractInfoList.getUpdateDate()}"/></td>
					<!-- ①ボタンにする -->
					<!-- ②ボタンをクリックしたら　formのactionをtoInitContractInfoに設定-->
					<!-- ③submitをする -->
					<td><input type="button"  name="update" value="更新" onclick="toUpdateJsp
					 ('<c:out value="${contractInfoList.getContractID()}"/>');" /></td>
				</tr>

			</c:forEach>

	</table>


				<input type="button"  name="make" value="新規" onclick="toMakeJsp();"/>
</form:form>
</body>
</html>