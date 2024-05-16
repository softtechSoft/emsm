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
	<%--自動計算 --%>
	<script>
    window.addEventListener('DOMContentLoaded', function() {
        document.getElementById("birthday").addEventListener("change", calculateAge);
        document.getElementById("joinedDate").addEventListener("change", calculateJoinedAge);

        calculateAge();
        calculateJoinedAge();
    });

    function calculateAge() {
        var birthday = document.getElementById("birthday").value;
        if (birthday) {
            // YYYYMMDD形式の入力をYYYY-MM-DD形式に変換
            birthday = birthday.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");
            var birthDate = new Date(birthday);
            var today = new Date();
            var age = today.getFullYear() - birthDate.getFullYear();
            var monthDiff = today.getMonth() - birthDate.getMonth();
            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                age--;
            }
            document.getElementById("calculatedAge").innerText = age;
        }
    }

    function calculateJoinedAge() {
        var joinedDate = document.getElementById("joinedDate").value;
        if (joinedDate) {
            // YYYYMMDD形式の入力をYYYY-MM-DD形式に変換
            joinedDate = joinedDate.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");
            var joinDate = new Date(joinedDate);
            var today = new Date();
            var joinedAge = today.getFullYear() - joinDate.getFullYear();
            var monthDiff = today.getMonth() - joinDate.getMonth();
            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < joinDate.getDate())) {
                joinedAge--;
            }
            document.getElementById("calculatedJoinedAge").innerText = joinedAge;
        }
    }
</script>
</head>
<form:form name="theForm" id="theForm" method="post" modelAttribute="employeeInfoFormBean"
           action="employeeInfoEdit1">
<h2>社員情報更新</h2>
 <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
<input type="hidden" id="employeeID" name="employeeID" value="${employeeInfoFormBean.employeeID}"/>


<table border="1" >
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">社員ID</td>
		<td width="250px"><c:out  value="${employeeInfoFormBean.employeeID}"/></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">社員氏名</td>
		<td width="250px"><input type="text" id="employeeName" name="employeeName"
                                     value="${employeeInfoFormBean.employeeName}" style="width: 98%;"/></td>
	</tr>


 	 <tr style = "background-color:#dcfeeb">
		<td width ="150px">ステータス</td>
		<td width="250px">
        <input type="radio" name="status"
               <c:choose>
                   <c:when test="${empty employeeInfoFormBean.status || employeeInfoFormBean.status == '0'}">
                       checked
                   </c:when>
               </c:choose>
               value="0" />在籍
        <input type="radio" name="status"
               <c:choose>
                   <c:when test="${employeeInfoFormBean.status == '1'}">
                       checked
                   </c:when>
               </c:choose>
               value="1" /> 離職
    </td>
	</tr>

	<tr style = "background-color:#dcfeeb">
		 <td width="150px">性別</td>
    <td width="250px">
        <input type="radio" name="sex"
               <c:choose>
                   <c:when test="${empty employeeInfoFormBean.sex || employeeInfoFormBean.sex == '0'}">
                       checked
                   </c:when>
               </c:choose>
               value="0" /> 男
        <input type="radio" name="sex"
               <c:choose>
                   <c:when test="${employeeInfoFormBean.sex == '1'}">
                       checked
                   </c:when>
               </c:choose>
               value="1" /> 女
    </td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">タイプ</td>
		<td width="250px">
        <input type="radio" name="epType"
               <c:choose>
                   <c:when test="${empty employeeInfoFormBean.epType || employeeInfoFormBean.epType == '0'}">
                       checked
                   </c:when>
               </c:choose>
               value="0" /> 正社員
        <input type="radio" name="epType"
               <c:choose>
                   <c:when test="${employeeInfoFormBean.epType == '1'}">
                       checked
                   </c:when>
               </c:choose>
               value="1" /> 契約社員
         <input type="radio" name="epType"
               <c:choose>
                   <c:when test="${employeeInfoFormBean.epType == '2'}">
                       checked
                   </c:when>
               </c:choose>
               value="2" /> 個人事業
    </td>
	</tr>
	<tr style="background-color:#dcfeeb">
    	<td width="150px">生年月日</td>
    	<td width="250px"><input type="text" id="birthday" name="birthday" value="${employeeInfoFormBean.birthday}" style="width: 98%;" /></td>
	</tr>
	<tr style="background-color:#dcfeeb">
    	<td width="150px">年齢</td>
    	<td width="250px"><span id="calculatedAge"><c:out value="${employeeInfoFormBean.age}" /></span></td>
	</tr>
	<tr style="background-color:#dcfeeb">
    	<td width="150px">入社年月日</td>
    	<td width="250px"><input type="text" id="joinedDate" name="joinedDate" value="${employeeInfoFormBean.joinedDate}" style="width: 98%;" /></td>
	</tr>
	<tr style="background-color:#dcfeeb">
    	<td width="150px">社齢</td>
    	<td width="250px"><span id="calculatedJoinedAge"><c:out value="${employeeInfoFormBean.joinedTime}" /></span></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">郵便番号</td>
		<td width="250px"><input type="text" id="postCode" name="postCode"
                                     value="${employeeInfoFormBean.postCode}" style="width: 98%;"/></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">住所</td>
		<td width="250px"><input type="text" id="address" name="address"
                                     value="${employeeInfoFormBean.address}" style="width: 98%;"/></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">電話番号</td>
		<td width="250px"><input type="text" id="phoneNumber" name="phoneNumber"
                                     value="${employeeInfoFormBean.phoneNumber}"style="width: 98%;" /></td>

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
                                     value="${employeeInfoFormBean.mailAdress}"style="width: 98%;" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">パスワード</td>
		<td width="250px"><input type="text" id="password" name="password"
                                     value="${employeeInfoFormBean.password}"style="width: 98%;" /></td>
	</tr>
	<tr style = "background-color:#dcfeeb">
		<td width ="150px">個人番号</td>
		<td width="250px"><input type="text" id="personNumber" name="personNumber"
                                     value="${employeeInfoFormBean.personNumber}"style="width: 98%;" /></td>
	</tr>


	</table>
	<input type="button" id="update" name="update" value="更新"  onclick="doRegist()"  />

</form:form>
</body>
</html>