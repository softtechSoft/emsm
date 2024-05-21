<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="text/javascript" >

    // 更新ボタン処理
    function toUpdateJsp(){
        // 更新
        document.theForm.submit();
        //alert("更新Button clicked!");
    }

    </script>
    <title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>有給更新</h2>
	<form:form name="theForm" id="theForm" method="post" modelAttribute="yukyuFormBean" action="updateYukyu">

	<!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>


	<!-- DB更新ために、画面データ -->
	 <input type="hidden" id="employeeID" name="employeeID" value="${yukyuFormBean.employeeID}"/>
	 <input type="hidden" id="nendo" name="nendo" value="${yukyuFormBean.nendo}"/>
	 <input type="hidden" id="totalDay" name="totalDay" value="${yukyuFormBean.totalDay}"/>
	 <input type="hidden" id="insertDate" name="insertDate" value="${yukyuFormBean.insertDate}"/>
	 <input type="hidden" id="updateDate" name="updateDate" value="${yukyuFormBean.updateDate}"/>
	<!--検索フラグ　０：検索　１：全量検索-->
    <input type="hidden" id="selectFlg" name="selectFlg"
           value="${yukyuFormBean.selectFlg}"/>

    <table border="1" class="yukyulist-table"  >
        <tr style = "background-color:#dcfeeb">
            <th width="80">社員ID</th>
            <td><c:out value="${yukyuFormBean.employeeID}"/>
           </td>
		 </tr>
		 <tr style = "background-color:#dcfeeb">
            <th width="80">年度</th>
            <td><c:out value="${yukyuFormBean.nendo}"/>
            </td>
		 </tr>
		 <tr style = "background-color:#dcfeeb">
            <th width="80">総日数</th>
            <td><c:out value="${yukyuFormBean.totalDay}"/>
            </td>
		 </tr>
		 <tr style = "background-color:#dcfeeb">
            <th width="80">消化日数</th>
             <td><input type="text" id="usedDay" name="usedDay" value="${yukyuFormBean.usedDay}" />
             </td>
         </tr>
		 <tr style = "background-color:#dcfeeb">
            <th width="80">作成日</th>
            <td><fmt:parseDate value="${yukyuFormBean.insertDate}" var="insertDate" pattern="yyyyMMdd" />
			       <fmt:formatDate value="${insertDate}" pattern="yyyy/MM/dd" />
            </td>
		</tr>
		 <tr style = "background-color:#dcfeeb">
            <th width="80">更新日</th>
            <td><fmt:parseDate value="${yukyuFormBean.updateDate}" var="insertDate" pattern="yyyyMMdd" />
			       <fmt:formatDate value="${updateDate}" pattern="yyyy/MM/dd" />
           </td>
		</tr>

    </table>
    <br>
     <input type="button" name="update" value="更新" onclick="toUpdateJsp();"/>

	</form:form>
</body>
</html>
