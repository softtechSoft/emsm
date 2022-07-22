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
</script>
</head>
<body>
<h2>経費管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="expensesManagementBean" action="expensesManagementSubmit" >
     <p style="color: blue;">入力したエラ</p>
    <p style="color: red;">
	    <c:forEach items="${errors}" var="error">
			<spring:message message="${error}" /> </br>
		</c:forEach>
	</p>

	<div>
	<table>
	<tr border ="1" style="background-color:#dcfeeb">
			<td>発生日：</td>
			<td><input id="accrualDate"name="accrualDate" type="text" value="2022/6/2" size="30"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>担当者：</td>
			<td><select id = "personel" name="personel">
			<option value="社員１">社員１</option>
			<option value="社員２">社員２</option></td>
		    </select>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>金額：</td>
			<td><input id="cost"name="cost" type="text"  value="200000" size="30"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>経費種別：</td>
			<td><select id = "use" name="use">
			<option value="一般経費">一般経費</option>
			<option value="家賃">家賃</option>
			<option value="水道光熱費">水道光熱費</option>
	        </select>
			<select id = "use" name="use">
			<option value="接待交際費">接待交際費</option>
			<option value="株主総会">株主総会 </option>
			<option value="役員会議">役員会議 </option>
			</td>
		    </select>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>場所：</td>
			<td><input id="address"name="address" type="text"  value="東京都豊島区池袋" size ="30"></td>
			</tr>
			<tr border ="1" style="background-color:#bfe1ff">
			<td>承認ステータス：</td>
			<td><input id="status"name="status" type="radio"  value="申請">申請<input id="status"name="status" type="radio"  value="承認">承認</td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>精算タイプ：</td>
			<td><input id="stmtlType"name="stmtlType" type="radio"  value="現金">現金 <input id="stmtlType"name="stmtlType" type="radio"  value="口座">口座</td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>精算ステータス：</td>
			<td><input id="stmtlStatus"name="stmtlStatus" type="radio"  value="未精算">未精算 <input id="stmtlStatus"name="stmtlStatus" type="radio"  value="精算">精算</td>
			</tr>
			<tr  border ="1" style="background-color:#dcfeeb">
			<td>精算日：</td>
			<td><input id="settlDay"name="settlDay" type="text"  value="2022/7/5" size="30"></td>
			</tr>
			<tr border ="1" style="background-color:#dcfeeb">
			<td>備考：</td>
			<td><input id="remarks"name="remarks" type="text"  value="申請の申し込み書" size="30"></td>
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