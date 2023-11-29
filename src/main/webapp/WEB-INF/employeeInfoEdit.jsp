<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2023/10/20
  Time:
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> ソフトテク株式会社-社内管理システム </title>
	<script type="text/javascript" >
		function doRegist() {
    		document.theForm.submit();
		}

	</script>
</head>
<form:form name="theForm" id="theForm" method="post" modelAttribute="employeeInfoFormBean"
           action="employeeInfoEdit">
<h2>社員情報</h2>
 <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
<input type="hidden" id="employeeID" name="employeeID" value="${employeeInfoFormBean.employeeID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${employeeInfoFormBean.insertFlg}"/>
	<!--検索フラグ　０：検索　１：全量検索-->
    <input type="hidden" id="selectFlg" name="selectFlg"
           value="${employeeInfoFormBean.selectFlg}"/>

<table border="1" >
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">社員ID</td>
		<td width="250px"><c:out  value="${employeeInfoFormBean.employeeID}"/></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">社員氏名</td>
		<td width="250px"><input type="text" id="employeeName" name="employeeName"
                                     value="${employeeInfoFormBean.employeeName}" /></td>
	</tr>
<%-- 	 <tr style = "background-color:#dcfeeb">
		<td width ="150px">パスワード</td>
		<td width="250px"><input type="text" id="password" name="password"
                                     value="${employeeInfoFormBean.password}" /></td>
	</tr> --%>

 	 <tr style = "background-color:#dcfeeb">
		<td width ="150px">ステータス</td>
		<td width="250px"><input type="radio" name="status" <c:if test="${employeeInfoFormBean.status == '0'}">
                checked</c:if> value="0" /> 在籍

                <input type="radio" name="status" <c:if test="${employeeInfoFormBean.status == '1'}">
                    checked</c:if> value="1" /> 離職</td>
	</tr>

	<tr style = "background-color:#dcfeeb">
		<td width ="150px">性別</td>
		<td width="250px"><input type="radio" name="sex" <c:if test="${employeeInfoFormBean.sex == '0'}">
                checked</c:if> value="0"   />男

                <input type="radio" name="sex" <c:if test="${employeeInfoFormBean.sex == '1'}">
                    checked</c:if> value="1"  /> 女</td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">タイプ</td>
		<td width="250px">
		<input type="radio" name="epType" <c:if test="${employeeInfoFormBean.epType == '0'}">
                    checked</c:if> value="0" />正社員
                <input type="radio" name="epType" <c:if test="${employeeInfoFormBean.epType == '1'}">
                checked</c:if> value="1" />契約社員
                <input type="radio" name="epType" <c:if test="${employeeInfoFormBean.epType == '2'}">
                    checked</c:if> value="2" />個人事業
        </td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">生年月日</td>
		<td width="250px"><input type="text" id="birthday" name="birthday"
                                     value="${employeeInfoFormBean.birthday}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">年齢</td>
		<td width="250px"><input type="text" id="age" name="age"
                                     value="${employeeInfoFormBean.age}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">入社年月日</td>
		<td width="250px"><input type="text" id="joinedDate" name="joinedDate"
                                     value="${employeeInfoFormBean.joinedDate}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">社齢</td>
		<td width="250px"><input type="text" id="joinedTime" name="joinedTime"
                                     value="${employeeInfoFormBean.joinedTime}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">郵便番号</td>
		<td width="250px"><input type="text" id="postCode" name="postCode"
                                     value="${employeeInfoFormBean.postCode}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">住所</td>
		<td width="250px"><input type="text" id="address" name="address"
                                     value="${employeeInfoFormBean.address}" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">電話番号</td>
		<td width="250px"><input type="text" id="phoneNumber" name="phoneNumber"
                                     value="${employeeInfoFormBean.phoneNumber}" /></td>

	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">権限</td>
		<td width="250px"><input type="radio" name="authority" <c:if test="${employeeInfoFormBean.authority == '0'}">
                checked</c:if> value="0" /> 普通
                <input type="radio" name="authority" <c:if test="${employeeInfoFormBean.authority== '1'}">
                    checked</c:if> value="1" /> 管理者</td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">メール</td>
		<td width="250px"><input type="text" id="mailAdress" name="mailAdress"
                                     value="${employeeInfoFormBean.mailAdress}" /></td>
	</tr>
<%-- 	<tr style = "background-color:#dcfeeb">
		<td width ="150px">作成日</td>
		<td width="250px">
                <c:out  value="${employeeInfoFormBean.insertDate}"/>
            </td>
	</tr> --%>
<%-- 	<tr style = "background-color:#dcfeeb">
		<td width ="150px">更新日</td>
		<td width="250px">
                <c:out  value="${employeeInfoFormBean.updateDate}"/>
            </td>
	</tr> --%>

	</table>
	<input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()"  />

</form:form>
</body>
</html>