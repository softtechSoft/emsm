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
<form:form name="theForm" id="theForm" method="post" modelAttribute="companyInfoBean"  action="companyAdd" >
				<h1>取引先情報登録</h1>
		<p style="color: red;">

	    <c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> <br>
		</c:forEach>
	</p>
	 <c:if test="${not empty message}">
    <div style="color: red;">
        <p><c:out value="${message}" /></p>
    </div>
</c:if>
		<table  border="1">
			<tr style="background-color:#dcfeeb">
				<td width="200px">取引先ID</td>
				<td width="200px"><input  type="text" id="companyID" name="companyID"
				 value="${companyInfoBean.companyID}" maxlength = "6" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">取引先名称</td>
				<td width="200px"><input type="text" id="companyName" name="companyName"
									value="${companyInfoBean.companyName}" /></td>
			</tr>

			<tr style="background-color:#dcfeeb">

				<td width="200px">取引先種類</td>
				<td width="200px"><input type="radio" name="companyType" <c:if test="${companyInfoBean.companyType == '0'}">
				checked</c:if> value="0" /> 法人
				<input type="radio" name="companyType" <c:if test="${companyInfoBean.companyType == '1'}">
				checked</c:if> value="1" /> 個人事業</td>

			</tr>


			<tr style="background-color:#dcfeeb">
				<td width="200px">郵便番号</td>
				<td width="200px"><input type="text" id="postCode" name="postCode"
									value="${companyInfoBean.postCode}" maxlength = "7"  /></td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">住所</td>
				<td width="200px"><input type="text" id="address" name="address"
									value="${companyInfoBean.address}" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">基本契約日</td>
				<td width="200px"><input type="text" id="basicContractDate" name="basicContractDate"
									value="${companyInfoBean.basicContractDate}"  maxlength = "8" /></td>
			</tr>
				<tr style="background-color:#dcfeeb">
				<td width="200px">電話番号</td>
				<td width="200px"><input type="text" id="phoneNumber" name="phoneNumber"
									value="${companyInfoBean.phoneNumber}" maxlength = "10"  /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">連絡先名</td>
				<td width="200px"><input type="text" id="contactName" name="contactName"
									value="${companyInfoBean.contactName}" maxlength = "20"  /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">メールアドレス</td>
				<td width="200px"> <input type="text" id="mail" name="mail"
									value="${companyInfoBean.mail}" maxlength = "20" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
			<td width="200px">取引ステータス</td>
			<td width="200px"><input type="radio" name="contractStatus" <c:if test="${companyInfoBean.contractStatus == '0'}">
								checked</c:if> value="0" /> 契約中
								<input type="radio" name="contractStatus" <c:if test="${companyInfoBean.contractStatus == '1'}">
				checked</c:if> value="1" /> 未契約</td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">評判レベル</td>
				<td width="200px"><input type="radio" name="level" <c:if test="${companyInfoBean.level == '0'}">
				checked</c:if> value="0" /> 優良
				<input type="radio" name="level" <c:if test="${companyInfoBean.level == '1'}">
				checked</c:if> value="1" /> 一般
				<input type="radio" name="level" <c:if test="${companyInfoBean.level == '1'}">
				checked</c:if> value="2" />良くない
				<input type="radio" name="level" <c:if test="${companyInfoBean.level == '1'}">
				checked</c:if> value="3" />強制取引終了</td>

			</tr>


			<tr style="background-color:#dcfeeb">
				<td width="200px">作成日</td>
	            <td width="200px"> <input type="text" id="insertDate" name="insertDate"
									value="${companyInfoBean.insertDate}" maxlength = "8" /></td>
			</tr>
			<tr style="background-color:#dcfeeb">
				<td width="200px">更新日</td>
	            <td width="200px"> <input type="text" id="updateDate" name="updateDate"
									value="${companyInfoBean.updateDate}" maxlength = "8"  /></td>
			</tr>
		</table>
		<table  border="0">
			<tr>
				<td width="200px"></td>
				<td width="200px" style="text-align: right;">
					<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
			</tr>
		</table>

</form:form>
</body>
</html>
