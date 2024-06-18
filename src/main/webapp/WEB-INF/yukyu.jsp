<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>有給管理</h2>
	<div style="color: red;">
    <p>${updateMsg}</p>
	</div>
	<form:form name="theForm" id="theForm" method="post" modelAttribute="yukyuFormBean" action="searchYukyu">
	<!--検索フラグ　０：検索　１：全量検索-->
    <input type="hidden" id="selectFlg" name="selectFlg"
    	   value="${yukyuFormBean.selectFlg}"/>

	<!-- employeeIDSelect更新 -->
	<input type="hidden" id="employeeIDSelect" name="employeeIDSelect" value=""/>
	<input type="hidden" id="nendoSelect" name="nendoSelect" value=""/>
	<b>社員:</b>

	 <form:select path="employeeID" >
    	<form:options items="${elist}" itemLabel="employeeName" itemValue="employeeID"/>
    </form:select>


	 <td></td>
    <input type="button" name="search" value="検索" onclick="toSearchJsp()"/>
    <input type="button" name="search" value="全量検索" onclick="toSearchJsp1()"/>


	<!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
    <table border="1" class="yukyulist-table">
        <tr>
            <th width="80">社員ID</th>
            <th width="80">年度</th>
            <th width="80">総日数</th>
            <th width="80">消化日数</th>
            <th width="80">作成日</th>
            <th width="80">更新日</th>

            <th width="45">更新</th>
        </tr>
        <c:forEach items="${yukyulist}" var="yk" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${yk.employeeID}"/></td>

		        <td><c:out value="${yk.nendo}"/></td>
		        <td><c:out value="${yk.totalDay}"/></td>
		        <td><c:out value="${yk.usedDay}"/></td>
		       <!-- <fmt:parseDate value="${yk.insertDate}" var="insertDate" pattern="yyyyMMdd" />
			       <fmt:formatDate value="${insertDate}" pattern="yyyy/MM/dd" />
			       <fmt:parseDate value="${yk.updateDate}" var="updateDate" pattern="yyyyMMdd" />
			    	<fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd" />
			    	 -->
		       <td>
			       <fmt:parseDate value="${yk.insertDate}" var="insertDate" pattern="yyyyMMdd" />
			       <fmt:formatDate value="${insertDate}" pattern="yyyy/MM/dd" />
		       </td>
		        <td>
		        	<fmt:parseDate value="${yk.updateDate}" var="updateDate" pattern="yyyyMMdd" />
			    	<fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd" />
		        </td>

                <td><input type="button" name="update" value="更新" onclick="toUpdateJsp('<c:out value="${yk.employeeID}"/>');"/></td>
            </tr>
        </c:forEach>
    </table>

	</form:form>
</body>
<script type="text/javascript" >
    <!--検索-->
    function toSearchJsp(){
    	document.getElementById('selectFlg').value='0';
    	document.getElementById('theForm').submit();
    	//alert("検索Button clicked!");
    }
    function toSearchJsp1() {
        document.getElementById('selectFlg').value = '1';
        document.getElementById('theForm').submit();
       // alert("全量検索Button clicked!");
    }
    // 更新ボタン処理
    function toUpdateJsp(employeeID,nendo){
        document.getElementById('employeeIDSelect').value = employeeID;
        document.getElementById('nendoSelect').value = nendo;
        document.theForm.action="yukyuInfo";
        document.theForm.submit();
        //alert("更新Button clicked!");
    }

    </script>
</html>
