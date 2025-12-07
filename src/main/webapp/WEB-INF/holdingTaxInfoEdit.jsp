<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2025/11/05
  Time: 11:51
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> ソフトテク株式会社-社内管理システム </title>
    <script type="text/javascript">
        //登録ボタンのクリック処理
        function doRegist() {
            document.theForm.submit();
        }

    </script>
</head>
<form:form name="theForm" id="theForm" method="post" modelAttribute="holdingTaxInfoFormBean" action="holdingTaxInfoEdit">
    <h1>所得税マスター</h1>
    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
    <input type="hidden" id="holdingTaxID" name="holdingTaxID" value="${holdingTaxInfoFormBean.holdingTaxID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${holdingTaxInfoFormBean.insertFlg}"/>

    <table border="1">
        <%-- <tr style="background-color:#dcfeeb">
            <td width="200px">所得税ID</td>
            <td width="200px"><c:out value="${holdingTaxInfoFormBean.incomeTaxID}"/></td>
        </tr> --%>

        <tr style="background-color:#dcfeeb">
            <td width="200px">社員</td>
            <td width="200px"><%-- <input type="text" id="employeeID" name="employeeID"
                                     value="${holdingTaxInfoFormBean.employeeID}"/> --%>
                <c:choose>
            		<c:when test="${holdingTaxInfoFormBean.insertFlg == '0'}">
                		<form:select path="employeeID">
	                    <form:options items="${employeeList}"
	                                 itemLabel="employeeName"
	                                 itemValue="employeeID"/>
                		</form:select>
            		</c:when>
		            <c:otherwise>
		                <c:out value="${holdingTaxInfoFormBean.employeeName}"/>
		                <input type="hidden" id="employeeID" name="employeeID"
		                       value="${holdingTaxInfoFormBean.employeeID}"/>
		            </c:otherwise>
		        </c:choose>
               </td>
        </tr>
        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${holdingTaxInfoFormBean.year}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">一月所得税</td>
            <td width="200px"><input type="text" id="incomeTax1" name="incomeTax1"
                                     value="${holdingTaxInfoFormBean.incomeTax1}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">二月所得税</td>
            <td width="200px"><input type="text" id="incomeTax2" name="incomeTax2"
                                     value="${holdingTaxInfoFormBean.incomeTax2}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">三月所得税</td>
            <td width="200px"><input type="text" id="incomeTax3" name="incomeTax3"
                                     value="${holdingTaxInfoFormBean.incomeTax3}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">四月所得税</td>
            <td width="200px"><input type="text" id="incomeTax4" name="incomeTax4"
                                     value="${holdingTaxInfoFormBean.incomeTax4}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">五月所得税</td>
            <td width="200px"><input type="text" id="incomeTax5" name="incomeTax5"
                                     value="${holdingTaxInfoFormBean.incomeTax5}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">六月所得税</td>
            <td width="200px"><input type="text" id="incomeTax6" name="incomeTax6"
                                     value="${holdingTaxInfoFormBean.incomeTax6}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">七月所得税</td>
            <td width="200px"><input type="text" id="incomeTax7" name="incomeTax7"
                                     value="${holdingTaxInfoFormBean.incomeTax7}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">八月所得税</td>
            <td width="200px"><input type="text" id="incomeTax8" name="incomeTax8"
                                     value="${holdingTaxInfoFormBean.incomeTax8}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">九月所得税</td>
            <td width="200px"><input type="text" id="incomeTax9" name="incomeTax9"
                                     value="${holdingTaxInfoFormBean.incomeTax9}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十月所得税</td>
            <td width="200px"><input type="text" id="incomeTax10" name="incomeTax10"
                                     value="${holdingTaxInfoFormBean.incomeTax10}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十一月所得税</td>
            <td width="200px"><input type="text" id="incomeTax11" name="incomeTax11"
                                     value="${holdingTaxInfoFormBean.incomeTax11}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十二月所得税</td>
            <td width="200px"><input type="text" id="incomeTax12" name="incomeTax12"
                                     value="${holdingTaxInfoFormBean.incomeTax12}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">利用ステータス</td>
            <td width="200px"><input type="radio" name="status" <c:if
                    test="${holdingTaxInfoFormBean.status == '1'}">
                checked</c:if> value="1"/> する
                <input type="radio" name="status" <c:if
                        test="${holdingTaxInfoFormBean.status == '0'}">
                    checked</c:if> value="0"/> しない
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">作成日</td>
            <td width="200px">
                <c:out value="${holdingTaxInfoFormBean.insertDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">更新日</td>
            <td width="200px">
                <c:out value="${holdingTaxInfoFormBean.updateDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td></td>
            <td style="text-align: right;">
                <input type="button" id="Registration" name="Registration" value="登録"
                       onclick="doRegist()"/></td>
        </tr>

    </table>

</form:form>
</body>
</html>
