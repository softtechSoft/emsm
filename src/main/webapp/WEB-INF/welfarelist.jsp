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
//検索処理
function toSearch(){
	var wholeFlg =document.getElementById('wholeFlg');
	wholeFlg.value="1";
	document.theForm.submit();
}
//ダウンロード処理
function toDownLoad(){
	var wholeFlg =document.getElementById('wholeFlg');
	wholeFlg.value="2";
	document.theForm.submit();
}
//社員IDを押す処理
function toWelfareJsp(employeeID,startDate){

	var wholeFlg =document.getElementById('wholeFlg');
	wholeFlg.value="3";

	var makeEmployeeID =document.getElementById('makeEmployeeID');
	makeEmployeeID.value= employeeID;

	document.getElementById('startDate').value= startDate;

	document.theForm.submit();
}
</script>
</head>
<body>
<h2>福祉情報リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfareBean" action="welfarelist" >
    <p>
      <b>社員ID:<input id="employeeID"name="employeeID" type="text" value="${employeeID}"/>
      	 <!--検索ボタン-->
         <input type="button" name="search" value="検索" onclick="toSearch()" />
         <!--判断用データ  1：検索、2：ダウンロード、3：社員ID-->
         <input type="hidden" id="wholeFlg"name="wholeFlg"/>
         <!--社員IDを押す時、社員IDを転送用-->
         <input type="hidden" id="makeEmployeeID"name="makeEmployeeID"/>
         <!--開始日-->
         <input type="hidden" id="startDate"name="startDate"/>
         <!--ダウンロードボタン -->
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoad()" />
          <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>
		</p>
       </b>
    </p>

	<table border="1"class="welfarelist-table" >
		<tr>
		    <th rowspan="2" width="100">社員ID</th>
            <th rowspan="2" width="100">社員氏名</th>
            <th >控除開始日</th>
            <th >基本給</th>
            <th >厚生年金控除個人</th>
            <th >厚生年金控除会社</th>
            <th >厚生健康控除会社</th>
            <th >厚生健康控除個人</th>
            <th >厚生控除子育(会社)</th>
            <th >雇用保険個人負担</th>
            <th >雇用保険会社負担</th>
            <th >雇用保拠出金（会社)</th>
        </tr>
        <tr>
            <th >労災保険（会社負担のみ）</th>
            <th >源泉控除</th>
            <th >住民税控除一月</th>
            <th >住民税控除二月</th>
            <th >住民税控除三月</th>
            <th >住民税控除四月</th>
            <th >住民税控除五月</th>
            <th >住民税控除六月</th>
            <th >住民税控除七月</th>
            <th >住民税控除八月</th>
            <th >住民税控除九月</th>
            <th >住民税控除十月</th>
            <th >住民税控除十一月</th>
            <th >住民税控除十二月</th>
            <th >社宅家賃控除</th>
            <th >社宅管理費控除</th>
            <th >控除ステータス</th>
            <th >作成日</th>
            <th >作成者</th>
            <th >更新日</th>
            <th >更新者</th>
		</tr>
		<c:forEach items="${list}" var="welfare" varStatus="status"  >
		<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
            <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
				<td rowspan="2" ><input type="button"  value="<c:out value="${welfare.getEmployeeID()}"/>"onclick="toWelfareJsp('${welfare.getEmployeeID()}','${welfare.getStartDate()}')"/></td>
				<td rowspan="2" ><c:out value="${welfare.getEmployeeName()}"/></td>
				<td><c:out value="${welfare.getStartDate()}"/></td>
				<td><c:out value="${welfare.getBase()}"/></td>
				<td><c:out value="${welfare.getWelfarePensionSelf()}"/></td>
				<td><c:out value="${welfare.getWelfarePensionComp()}"/> </td>
				<td><c:out value="${welfare.getWelfareHealthComp()}"/> </td>
				<td><c:out value="${welfare.getWelfareHealthSelf()}"/> </td>
				<td><c:out value="${welfare.getWelfareBaby()}"/> </td>
				<td><c:out value="${welfare.getEplyInsSelf()}"/> </td>
				<td><c:out value="${welfare.getEplyInsComp()}"/> </td>
				<td><c:out value="${welfare.getEplyInsWithdraw()}"/></td>
		</tr>
		<tr<c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
            <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
				<td><c:out value="${welfare.getWkAcccpsIns()}"/> </td>
				<td><c:out value="${welfare.getWithholdingTax()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax1()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax2()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax3()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax4()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax5()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax6()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax7()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax8()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax9()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax10()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax11()}"/> </td>
				<td><c:out value="${welfare.getMunicipalTax12()}"/> </td>
				<td><c:out value="${welfare.getRental()}"/></td>
				<td><c:out value="${welfare.getRentalMgmtFee()}"/> </td>
				<td><c:out value="${welfare.getStatus()}"/> </td>
				<td><c:out value="${welfare.getInsertDate()}"/> </td>
				<td><c:out value="${welfare.getInsertEmployee()}"/> </td>
				<td><c:out value="${welfare.getUpdateDate()}"/> </td>
				<td><c:out value="${welfare.getUpdateEmployee()}"/> </td>
		</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>