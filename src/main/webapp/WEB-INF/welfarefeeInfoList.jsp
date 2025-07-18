<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" >
        <!--検索-->
        function toSearchJsp(){
            document.theForm.submit();
        }

        //function selectData(){
            //var enterSalary=document.getElementById("enterSalary").value;
            //var year = document.getElementById("year").value;
            //if (enterSalary !== null){
               // document.theForm.submit();
            //}
            //if (year !== null){
                //document.theForm.submit();
            //}
        //}

        // 更新ボタン処理
        function toUpdateJsp(welfarefeeID){
            // 更新
            document.getElementById('insertFlg').value='1';
            document.getElementById('welfarefeeID').value=welfarefeeID;
            document.theForm.action="toinitWelfarefeeInfo";
            document.theForm.submit();
        }

        // 新規ボタン処理
        function toInsertJsp(){
            // 新規
            document.getElementById('insertFlg').value='0';
            document.theForm.action="toinitWelfarefeeInfo";
            document.theForm.submit();
        }

     	// 全量検索ボタン処理
        function toSearchAllJsp(){
            document.theForm.action="welfarefeeInfoListAll";
            document.theForm.submit();
        }
        
    </script>
    <title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>厚生保険料マスタ管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfarefeeInfoFormBean"  action="welfarefeeInfoList" >
    <input type="hidden" id="welfarefeeID" name="welfarefeeID" value="${welfarefeeInfoFormBean.welfarefeeID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${welfarefeeInfoFormBean.insertFlg}"/>

    <b>年度:</b>
    <form:select path="year">
        <form:options items="${listIDNameList}" itemLabel="year"  itemValue="year"/>
    </form:select>
    <td></td>
    <!--<td><span>収入:<input type="text" size="8" ></span></td>-->
    <input type="button" name="search" value="検索" onclick="toSearchJsp()" />
    <input type="button" name="searchAll" value="全量検索" onclick="toSearchAllJsp()" />


    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}" />
        </c:forEach>
    </p>
    <table border="1"class="welfarefeeInfoList-table">
        <tr>
            <th width="100">厚生保険料ID</th>
            <th width="100">対象年度</th>
            <th width="100">対象エリア</th>
            <th width="100">標準報酬</th>
            <th width="100">給料From</th>
            <th width="100">給料To</th>
            <th width="150">介護必要ない料率%</th>
            <th width="150">介護必要料率%</th>
            <th width="150">厚生年金保険料率%</th>
            <th width="100">利用ステータス</th>
            <th width="100">作成日</th>
            <th width="100">更新日</th>
            <th width="45">更新</th>
        </tr>

        <c:forEach items="${list}" var="welfarefeeInfoList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${welfarefeeInfoList.getWelfarefeeID()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getYear()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getArea()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getStandSalary()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getSalaryFrom()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getSalaryTo()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getNotCareRatio()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getCareRatio()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getAnnuityRatio()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getStatus() == '0' ? '未使用' : '使用中'}"/></td>
                <td><c:out value="${welfarefeeInfoList.getInsertDate()}"/></td>
                <td><c:out value="${welfarefeeInfoList.getUpdateDate()}"/></td>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out value="${welfarefeeInfoList.getWelfarefeeID()}"/>');" /></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button"  name="insert" value="新規追加" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>