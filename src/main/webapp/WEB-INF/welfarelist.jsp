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
//作成処理
function toMake(){
	var wholeFlg =document.getElementById('wholeFlg');
	wholeFlg.value="3";
	document.theForm.submit();
}
</script>
</head>
<body>
<h2>福祉控除リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfareBean" action="welfarelist" >
    <p>
      <b>社員ID:<input id="employeeID"name="employeeID" type="text" value="${employeeID}"/>
      	 <!--検索ボタン-->
         <input type="button" name="search" value="検索" onclick="toSearch();" />
         <!--判断用データ  1：検索、2：ダウンロード、3：作成、4：社員ID-->
         <input type="hidden" id="wholeFlg"name="wholeFlg"/>
         <!--ダウンロードボタン -->
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoad()" />
         <!--作成ボタン-->
         <input type="button" name="make" value="作成"onclick="toMake()" />
          <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>
		</p>
       </b>
    </p>

	<table border="1"class="welfarelist-table">
		<tr>
		    <th >社員ID</th>
            <th >社員氏名</th>
            <th >控除開始日</th>
            <th >厚生控除会社</th>
            <th >厚生控除子育(会社)</th>
            <th >雇用保険会社負担</th>
            <th >雇用保拠出金（会社)</th>
            <th >源泉控除</th>
            <th >住民税控除</th>
            <th >社宅家賃控除</th>
            <th >社宅管理費控除</th>
            <th >控除ステータス</th>
            <th >作成日</th>
            <th >更新日</th>
		</tr>
		<c:forEach items="${list}" var="welfare" varStatus="status"  >
		<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
            <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
				<td><c:out value="${welfare.getEmployeeID()}"/></td>
				<td><c:out value="${welfare.getEmployeeName()}"/></td>
				<td><c:out value="${welfare.getStartDate()}"/></td>
				<td><c:out value="${welfare.getWelfareComp()}"/></td>
				<td><c:out value="${welfare.getWelfareBaby()}"/></td>
				<td><c:out value="${welfare.getEplyInsComp()}"/></td>
				<td><c:out value="${welfare.getEplyInsWithdraw()}"/></td>
				<td><c:out value="${welfare.getWithholdingTax()}"/></td>
				<td><c:out value="${welfare.getMunicipalTax()}"/></td>
				<td><c:out value="${welfare.getRental()}"/></td>
				<td><c:out value="${welfare.getRentalMgmtFee()}"/></td>
				<td><c:out value="${welfare.getStatus()}"/></td>
				<td><c:out value="${welfare.getInsertDate()}"/></td>
				<td><c:out value="${welfare.getUpdateDate()}"/></td>
		</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>