<%--
  Created by IntelliJ IDEA.
  User: 孙晔
  Date: 2022/08/08
  Time: 16:04
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        <!--検索-->
        function toSearchJsp() {
            document.theForm.submit();
        }

        // 更新ボタン処理
        function toUpdateJsp(emplyinsrateID) {
            // 更新
            document.getElementById('insertFlg').value = '1';
            document.getElementById('emplyinsrateID').value = emplyinsrateID;
            document.theForm.action = "toinitEmplyinsrateInfo";
            document.theForm.submit();
        }

        // 新規ボタン処理
        function toInsertJsp() {
            // 新規
            document.getElementById('insertFlg').value = '0';
            document.theForm.action = "toinitEmplyinsrateInfo";
            document.theForm.submit();
        }
    </script>
    <title>ソフトテク株式会社-社内管理システム</title>
</head>
<body>
<h2>雇用保険率テーブル</h2>
<form:form name="theForm" id="theForm" method="post" modelAttribute="emplyinsrateInfoFormBean"
           action="emplyinsrateInfoList">
    <input type="hidden" id="emplyinsrateID" name="emplyinsrateID"
           value="${emplyinsrateInfoFormBean.emplyinsrateID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${emplyinsrateInfoFormBean.insertFlg}"/>

    <b>年度:</b>
    <form:select path="year">
        <form:options items="${emplyinsrateIDNameList}" itemLabel="year" itemValue="year"/>
    </form:select>
    <td></td>
    <input type="button" name="search" value="検索" onclick="toSearchJsp()"/>

    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>

    <table border="1" class="emplyinsrateInfoList-table">
        <tr>
            <th width="95">雇用保険ID</th>
            <th width="80">対象年度</th>
            <th width="150">雇用保険労働者負担料率‰</th>
            <th width="150">雇用保険事業主負担料率‰</th>
            <th width="125">雇用保険料率‰</th>
            <th width="150">労災保険料率(全額事業主)‰</th>
            <th width="125">労働保険料率‰</th>
            <th width="150">一般拠出金料率(全額事業主)‰</th>
            <th width="100">利用ステータス</th>
            <th width="70">作成日</th>
            <th width="70">更新日</th>
            <th width="45">更新</th>


        </tr>

        <c:forEach items="${list}" var="emplyinsrateInfoList" varStatus="status">
            <tr <c:if test="${status.count%2==0}"> style="background-color:#bfe1ff"</c:if>
                    <c:if test="${status.count%2!=0}"> style="background-color:#dcfeeb"</c:if>>
                <td><c:out value="${emplyinsrateInfoList.emplyinsrateID}"/></td>
                <td><c:out value="${emplyinsrateInfoList.year}"/></td>
                <td><c:out value="${emplyinsrateInfoList.laborBurdenRate*1000}"/></td>
                <td><c:out value="${emplyinsrateInfoList.employerBurdenRate*1000}"/></td>
                <td><c:out value="${emplyinsrateInfoList.employmentInsuranceRate*1000}"/></td>
                <td><c:out
                        value="${emplyinsrateInfoList.industrialAccidentInsuranceRate*1000}"/></td>
                <td><c:out value="${emplyinsrateInfoList.laborInsuranceRate*1000}"/></td>
                <td><c:out value="${emplyinsrateInfoList.contributionRate*1000}"/></td>
                <td><c:out value="${emplyinsrateInfoList.status}"/></td>
                <td><c:out value="${emplyinsrateInfoList.insertDate}"/></td>
                <td><c:out value="${emplyinsrateInfoList.updateDate}"/></td>
                <td><input type="button" name="uptade" value="更新" onclick="toUpdateJsp('<c:out
                        value="${emplyinsrateInfoList.emplyinsrateID}"/>');"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" name="insert" value="新規追加" onclick="toInsertJsp();"/>
</form:form>
</body>
</html>
