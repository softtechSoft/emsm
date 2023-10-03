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

        // 更新ボタン処理
        function toUpdateJsp(rateID){
            // 更新
            document.getElementById('insertFlg').value='1';
            document.getElementById('rateID').value=rateID;
            document.theForm.action="toinitWelfarebabyInfo";
            document.theForm.submit();
        }

        // 新規ボタン処理
        function toInsertJsp(){
            // 新規
            document.getElementById('insertFlg').value='0';
            document.theForm.action="toinitWelfarebabyInfo";
            document.theForm.submit();
        }
    </script>
    <title> ソフトテク株式会社-社内管理システム </title>
</head>
<body>
<h2>厚生子育徴収率マスタ管理</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfarebabyInfoFormBean"  action="welfarebabyInfoList" >
    <input type="hidden" id="rateID" name="rateID" value="${welfarebabyInfoFormBean.rateID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${welfarebabyInfoFormBean.insertFlg}"/>

    <b>年度:</b>
    <form:select path="year">
        <form:options items="${welfarebabyIDNameList}" itemLabel="year"  itemValue="year"/>
    </form:select>
    <td></td>
    <!--<td><span>収入:<input type="text" size="8" ></span></td>-->
    <input type="button" name="search" value="検索" onclick="toSearchJsp()" />


    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}" />
        </c:forEach>
    </p>
    <table border="1"class="welfarebabyInfoList-table">
        <tr>
            <th width="100">徴収ID</th>
            <th width="100">対象年度</th>
            <th width="100">対象エリア</th>
            <th width="100">徴収率</th>
            <th width="100">利用ステータス</th>
            <th width="100">作成日</th>
            <th width="100">更新日</th>
            <th width="45">更新</th>
        </tr>

        <c:forEach items="${list}" var="welfarebabyInfoList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${welfarebabyInfoList.getRateID()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getYear()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getArea()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getRate()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getStatus()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getInsertDate()}"/></td>
                <td><c:out value="${welfarebabyInfoList.getUpdateDate()}"/></td>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out value="${welfarebabyInfoList.getRateID()}"/>');" /></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button"  name="insert" value="新規追加" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>