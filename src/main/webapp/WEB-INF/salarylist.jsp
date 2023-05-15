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
function toSearchJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value= 1;
	document.theForm.submit();
}
function toDownLoadDataJsp(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value= 2;
	document.theForm.submit();
}
function tosalaryInfoJsp(salaryInfoID){
	var EmployeeIDFlg =document.getElementById('EmployeeIDFlg');
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value= 3;
	EmployeeIDFlg.value= salaryInfoID;
	document.theForm.submit();
}
function toCreate(){
	var downloadFlg =document.getElementById('downloadFlg');
	downloadFlg.value= 4;
	document.theForm.submit();
}


</script>
</head>
<body>
<h2>給料リスト</h2>
<form:form name="theForm" id="theForm" method="post" action="salarylist" >
    <p>
      <b>対象年月:<input id="month"name="month" type="text" value="${month}">
         <!--<input style="border-radius: 3px" type="submit" id="search_btn" name="search" Value="検索">
         <input style="border-radius: 3px" type="submit" name="downLoad"id="downLoad_btn" value="ダウンロード">
         -->
         <input type="button" name="search" value="検索" onclick="toSearchJsp();" />
         <input type="hidden" id="EmployeeIDFlg"name="EmployeeIDFlg"/>
         <input type="hidden" id="downloadFlg"name="downloadFlg"/>
         <input type="button" name="downLoad" value="ダウンロード"onclick="toDownLoadDataJsp()" />
		 <input style="float:right" type="button" name="create" value="一括作成" onclick="toCreate()">
       </b>
       <p style="color: red;">
          <c:forEach items="${errors}" var="error">
						<spring:message message="${error}" />
					</c:forEach>

		</p>

		 <p style="color: red; text-align:center ;">
          <c:out value="${create}" />
		</p>


	<table border="1"class="salrylist-table">
		<tr>
		    <th rowspan="3" width="200">社員ID</th>
            <th width="100">社員氏名</th>
            <th width="100">対象年月</th>
            <th width="100">支払日</th>
            <th width="100">基本給</th>
            <th width="50">残業時間</th>
            <th width="50">不足時間</th>
            <th width="200">残業加算</th>
            <th width="200">稼働不足減</th>
            <th width="200">交通費</th>
            <th width="200">手当加算</th>
            <th width="200">手当減算</th>
            <th width="1000">手当理由</th>
            <th width="200">厚生年金控除個人</th>
        </tr>
        <tr>

            <th width="100">厚生健康控除個人</th>
            <th width="100">厚生年金控除会社</th>
            <th width="100">厚生健康控除会社</th>
            <th width="100">厚生控除子育(会社)</th>
            <th width="50">雇用保険個人負担</th>
            <th width="50">雇用保険会社負担</th>
            <th width="200">雇用保拠出金（会社)</th>
             <th width="200">労災保険（会社負担のみ）</th>
            <th width="200">源泉控除</th>
            <th width="200">住民税控除</th>
            <th width="200">社宅家賃控除</th>
            <th width="500">社宅共益費控除</th>
            <th width="200">総額</th>
        </tr>
        <tr>

            <th width="100">総費用</th>
            <th colspan="12"width="5000">備考</th>
		</tr>
		<c:forEach items="${list}" var="salarylist" varStatus="status" >

        <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
            <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>

				<td rowspan="3"><input type="button"  value="<c:out value="${salarylist.getEmployeeID()}"/>"onclick="tosalaryInfoJsp('${salarylist.getEmployeeID()}')" /></td>
				<td><c:out value="${salarylist.getEmployeeName()}"/></td>
				<td><c:out value="${salarylist.getMonth()}"/></td>
				<td><c:out value="${salarylist.getPaymentDate()}"/></td>
				<td><c:out value="${salarylist.getBase()}"/></td>
				<td><c:out value="${salarylist.getOverTime()}"/></td>
                <td><c:out value="${salarylist.getShortage()}"/></td>
                <td><c:out value="${salarylist.getOverTimePlus()}"/></td>
                <td><c:out value="${salarylist.getShortageReduce()}"/></td>
                <td><c:out value="${salarylist.getTransportExpense()}"/></td>
                <td><c:out value="${salarylist.getAllowancePlus()}"/></td>
                <td><c:out value="${salarylist.getAllowanceReduce()}"/></td>
                <td><c:out value="${salarylist.getAllowanceReason()}"/></td>
                <td><c:out value="${salarylist.getWelfarePensionSelf()}"/></td>
       </tr>
           <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>


                <td><c:out value="${salarylist.getWelfareHealthSelf()}"/></td>
                <td><c:out value="${salarylist.getWelfarePensionComp()}"/></td>
                <td><c:out value="${salarylist.getWelfareHealthComp()}"/></td>
                <td><c:out value="${salarylist.getWelfareBaby()}"/></td>
                <td><c:out value="${salarylist.getEplyInsSelf()}"/></td>
                <td><c:out value="${salarylist.getEplyInsComp()}"/></td>
                <td><c:out value="${salarylist.getEplyInsWithdraw()}"/></td>
                <td><c:out value="${salarylist.getWkAcccpsIns()}"/></td>
                <td><c:out value="${salarylist.getWithholdingTax()}"/></td>
                <td><c:out value="${salarylist.getMunicipalTax()}"/></td>
                <td><c:out value="${salarylist.getRental()}"/></td>
                <td><c:out value="${salarylist.getRentalMgmtFee()}"/></td>
                <td><c:out value="${salarylist.getSum()}"/></td>
       </tr>
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>


                <td><c:out value="${salarylist.getTotalFee()}"/></td>
				<td colspan="12"><c:out value="${salarylist.getRemark()}"/></td>
			</tr>
		</c:forEach>
	</table>
</form:form>
</body>
</html>