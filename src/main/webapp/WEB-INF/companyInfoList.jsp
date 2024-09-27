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
    	document.getElementById('selectFlg').value='0';
    	document.theForm.submit();
    }
    function toSearchJsp1(){
    	document.getElementById('selectFlg').value='1';
    	document.theForm.submit();
   }
	// 更新ボタン処理
	function toUpdateJsp(cpmpanyID){
		// 更新
		document.getElementById('insertFlg').value='1';

		document.theForm.action="toInitCompanyInfo";
		document.theForm.submit();
	}

	// 新規ボタン処理
	function toMakeJsp(){
		// 新規
		document.getElementById('insertFlg').value='0';
		document.theForm.action="toInitCompanyInfo";
		document.theForm.submit();
	}

</script>
<title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
		<h2>取引先情報リスト</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="companyInfoBean"  action="companyInfoList" >

	<!--新規フラグ　０　新規　１　更新-->
	<input type="hidden" id="insertFlg" name="insertFlg" value="${companyInfoBean.insertFlg}"/>
	<input type="hidden" id="selectFlg" name="selectFlg" value="${companyInfoBean.selectFlg}"/>
<b>取引名称：</b>
	<form:select path="companyID">
		<form:options items="${companyList}" itemLabel="companyName"  itemValue="companyID"/>
	</form:select>
 <input type="button" name="search" value="検索" onclick="toSearchJsp()"/>
    <input type="button" name="search" value="全量検索" onclick="toSearchJsp1()"/>
			<!--エラーメッセージ-->
		<p style="color: red;">
	    	<c:forEach items="${errors}" var="error">
			<spring:message message="${error}" />
			</c:forEach>
		</p>




	<table border="1"class="companyInfoList-table">
		   	 <tr>
        		<th width="200">取引先ID</th>
        		<th width="400">取引先名称</th>
        		<th width="400">取引先種類</th>
        		<th width="400">郵便番号</th>
        		<th width="400">住所</th>
        		<th width="400">基本契約日</th>
        		<th width="300">電話番号</th>
        		<th width="300">連絡先名</th>
        		<th width="300">メールアドレス</th>
        		<th width="500">取引ステータス</th>
        		<th width="300">評判レベル</th>
				<th width="300">作成日</th>
        		<th width="300">更新日</th>
        		<th width="300">更新へ</th>
        	</tr>

        	<c:forEach items="${list}" var="companyInfoList" varStatus="status">
				<tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
					<c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
					<td><c:out value="${companyInfoList.companyID}"/></td>
        <td><c:out value="${companyInfoList.companyName}"/></td>


        <td><c:choose>
            <c:when test="${companyInfoList.companyType == '0'}">法人</c:when>
            <c:otherwise>個人事業</c:otherwise>
        </c:choose></td>

      <td>
    <c:choose>

        <c:when test="${not empty companyInfoList.postCode}">

            <c:out value="${companyInfoList.postCode.substring(0, 3)}"/>-
            <c:out value="${companyInfoList.postCode.substring(3)}"/>
        </c:when>

        <c:otherwise>
        </c:otherwise>
    </c:choose>
</td>
        <td><c:out value="${companyInfoList.address}"/></td>


        <td>
    <c:choose>

        <c:when test="${not empty companyInfoList.basicContractDate}">

            <c:out value="${companyInfoList.basicContractDate.substring(0, 4)}"/>/
            <c:out value="${companyInfoList.basicContractDate.substring(4, 6)}"/>/
            <c:out value="${companyInfoList.basicContractDate.substring(6, 8)}"/>
        </c:when>

        <c:otherwise>
        </c:otherwise>
    </c:choose>
</td>
      <td>
    <c:choose>

        <c:when test="${not empty companyInfoList.phoneNumber}">

            <c:out value="${companyInfoList.phoneNumber.substring(0, 3)}"/> -
            <c:out value="${companyInfoList.phoneNumber.substring(3, 7)}"/> -
            <c:out value="${companyInfoList.phoneNumber.substring(7)}"/>
        </c:when>

        <c:otherwise>
        </c:otherwise>
    </c:choose>
</td>

        <td><c:out value="${companyInfoList.contactName}"/></td>
        <td><c:out value="${companyInfoList.mail}"/></td>


        <td>
        <c:choose>
            <c:when test="${companyInfoList.contractStatus == '0'}">契約中</c:when>
            <c:otherwise>未契約</c:otherwise>
        </c:choose>
        </td>


        <td><c:choose>
            <c:when test="${companyInfoList.level == '0'}">優良</c:when>
            <c:when test="${companyInfoList.level == '1'}">一般</c:when>
            <c:when test="${companyInfoList.level == '2'}">良くない</c:when>
            <c:otherwise>強制取引終了</c:otherwise>
        </c:choose></td>

<td>
    <c:choose>

        <c:when test="${not empty companyInfoList.insertDate}">

            <c:out value="${companyInfoList.insertDate.substring(0, 4)}"/>/
            <c:out value="${companyInfoList.insertDate.substring(4, 6)}"/>/
            <c:out value="${companyInfoList.insertDate.substring(6, 8)}"/>
        </c:when>

        <c:otherwise>
        </c:otherwise>
    </c:choose>
</td>

<td>
    <c:choose>

        <c:when test="${not empty companyInfoList.updateDate}">

            <c:out value="${companyInfoList.updateDate.substring(0, 4)}"/>/
            <c:out value="${companyInfoList.updateDate.substring(4, 6)}"/>/
            <c:out value="${companyInfoList.updateDate.substring(6, 8)}"/>
        </c:when>

        <c:otherwise>
        </c:otherwise>
    </c:choose>
</td>
					<td><input type="button"  name="update" value="更新" onclick="toUpdateJsp
					 ('<c:out value="${companyInfoList.getCompanyID()}"/>');" /></td>
				</tr>

			</c:forEach>

	</table>


				<input type="button"  name="make" value="新規" onclick="toMakeJsp();"/>
</form:form>
</body>
</html>