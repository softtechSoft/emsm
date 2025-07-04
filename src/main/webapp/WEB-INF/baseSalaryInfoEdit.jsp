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

	function toKeisan(){
		//基本給取得
		var baseSalary=document.getElementById('baseSalary').value;

		//残業単価
		if (baseSalary != null && baseSalary != "") {
		    //稼働To取得
			var wkPeriodTo=document.getElementById('wkPeriodTo').value;

			if (wkPeriodTo != null && wkPeriodTo != "") {
				var overtimePay=baseSalary/wkPeriodTo;
				document.getElementById('overtimePay').value=overtimePay;
			}
		}
		//控除単価
		if (baseSalary != null && baseSalary != "") {
		    //稼働From取得
			var wkPeriodFrom=document.getElementById('wkPeriodFrom').value;

			if (wkPeriodFrom != null && wkPeriodFrom != "") {
				var insufficienttimePay=baseSalary/wkPeriodFrom;
				document.getElementById('insufficienttimePay').value=insufficienttimePay;
			}
		}

	}

</script>
</head>

<body>
<form:form name="theForm" id="theForm" method="post" modelAttribute="baseSalaryInfoBean"  action="baseSalaryInfoEdit" >
				<h1>基本給情報更新</h1>
 <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/><br>
        </c:forEach>
    </p>
     <c:if test="${not empty successMessage}">
    <div style="color: red;">
        <p><c:out value="${successMessage}" /></p>
    </div>
</c:if>


		<input type="hidden" id="baseSalaryID" name="baseSalaryID" value="${baseSalaryInfoBean.baseSalaryID}"/>
		<!--新規フラグ　０　新規　１　更新-->
		<input type="hidden" id="insertFlg" name="insertFlg" value="${baseSalaryInfoBean.insertFlg}"/>


		<table  border="1">
			<%-- <tr style="background-color:#dcfeeb">
				<td width="200px">基本給ID</td>
				<td width="200px"><c:out  value="${baseSalaryInfoBean.baseSalaryID}"/></td>
			</tr> --%>

			<tr style="background-color:#dcfeeb">

				<td width="200px">社員</td>
				<td width="200px">
					<form:select path="employeeID">
						<form:options items="${employeeList}" itemLabel="employeeName"  itemValue="employeeID"/>
					</form:select>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">基本給</td>
				<td width="200px">
				 <input type="text" id="baseSalary" name="baseSalary"
				 					value="${baseSalaryInfoBean.baseSalary}" onchange="toKeisan()"/>
				</td>
			</tr>
			<tr style="background-color:#dcfeeb">
			 <td width="200px">対象年度</td>
				<td width="200px">
				<input type="text" id="year" name="year"
						value="${baseSalaryInfoBean.year}" /></td>
			</tr>



			<tr style="background-color:#dcfeeb">
				<td width="200px">稼働時間From</td>
				<td width="200px">
				<input type="text" id="wkPeriodFrom" name="wkPeriodFrom"
									value="${baseSalaryInfoBean.wkPeriodFrom}" onchange="toKeisan()" />
			 </td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">稼働時間To</td>
				<td width="200px"> <input type="text" id="wkPeriodTo" name="wkPeriodTo"
						value="${baseSalaryInfoBean.wkPeriodTo}" onchange="toKeisan()"/>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">残業単価</td>
				<td width="200px">
				 <input type="text" id="overtimePay" name="overtimePay"
									value="${baseSalaryInfoBean.overtimePay}" />

				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">控除単価</td>
				<td>
				<input type ="text" id ="insufficienttimePay" name = "insufficienttimePay"
				                    value="${baseSalaryInfoBean.insufficienttimePay}"/>
				</td>
			</tr>


			<tr style="background-color:#dcfeeb">
				<td width="200px">進行ステータス</td>
				<td width="200px"><input type="radio" name="status" <c:if test="${baseSalaryInfoBean.status == '1'}">
									checked</c:if> value="1" /> する
									<input type="radio" name="status" <c:if test="${baseSalaryInfoBean.status == '0'}">
									checked</c:if> value="0" /> しない</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">作成日</td>
				<td width="200px">
				<c:out  value="${baseSalaryInfoBean.insertDate}"/>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
				<td width="200px">更新日</td>
				<td width="200px">
				<c:out  value="${baseSalaryInfoBean.updateDate}"/>
				</td>
			</tr>

			<tr style="background-color:#dcfeeb">
			<td></td>
			<td style="text-align: right;">
			<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
			</tr>

		</table>

</form:form>
</body>
</html>
