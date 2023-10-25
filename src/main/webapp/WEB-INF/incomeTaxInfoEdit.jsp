<%--
  Created by IntelliJ IDEA.
  User: 孙晔
  Date: 2022/08/12
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
<form:form name="theForm" id="theForm" method="post" modelAttribute="incomeTaxInfoFormBean"
           action="incomeTaxInfoEdit">
    <h1>所得税と住民税マスター</h1>
    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
    <input type="hidden" id="incomeTaxID" name="incomeTaxID"
           value="${incomeTaxInfoFormBean.incomeTaxID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${incomeTaxInfoFormBean.insertFlg}"/>

    <table border="1">
        <tr style="background-color:#dcfeeb">
            <td width="200px">所得税ID</td>
            <td width="200px"><c:out value="${incomeTaxInfoFormBean.incomeTaxID}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">社員ID</td>
            <td width="200px"><input type="text" id="employeeID" name="employeeID"
                                     value="${incomeTaxInfoFormBean.employeeID}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${incomeTaxInfoFormBean.year}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">一月所得税</td>
            <td width="200px"><input type="text" id="incomeTax1" name="incomeTax1"
                                     value="${incomeTaxInfoFormBean.incomeTax1}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">二月所得税</td>
            <td width="200px"><input type="text" id="incomeTax2" name="incomeTax2"
                                     value="${incomeTaxInfoFormBean.incomeTax2}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">三月所得税</td>
            <td width="200px"><input type="text" id="incomeTax3" name="incomeTax3"
                                     value="${incomeTaxInfoFormBean.incomeTax3}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">四月所得税</td>
            <td width="200px"><input type="text" id="incomeTax4" name="incomeTax4"
                                     value="${incomeTaxInfoFormBean.incomeTax4}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">五月所得税</td>
            <td width="200px"><input type="text" id="incomeTax5" name="incomeTax5"
                                     value="${incomeTaxInfoFormBean.incomeTax5}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">六月所得税</td>
            <td width="200px"><input type="text" id="incomeTax6" name="incomeTax6"
                                     value="${incomeTaxInfoFormBean.incomeTax6}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">七月所得税</td>
            <td width="200px"><input type="text" id="incomeTax7" name="incomeTax7"
                                     value="${incomeTaxInfoFormBean.incomeTax7}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">八月所得税</td>
            <td width="200px"><input type="text" id="incomeTax8" name="incomeTax8"
                                     value="${incomeTaxInfoFormBean.incomeTax8}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">九月所得税</td>
            <td width="200px"><input type="text" id="incomeTax9" name="incomeTax9"
                                     value="${incomeTaxInfoFormBean.incomeTax9}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十月所得税</td>
            <td width="200px"><input type="text" id="incomeTax10" name="incomeTax10"
                                     value="${incomeTaxInfoFormBean.incomeTax10}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十一月所得税</td>
            <td width="200px"><input type="text" id="incomeTax11" name="incomeTax11"
                                     value="${incomeTaxInfoFormBean.incomeTax11}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十二月所得税</td>
            <td width="200px"><input type="text" id="incomeTax12" name="incomeTax12"
                                     value="${incomeTaxInfoFormBean.incomeTax12}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">一月住民税</td>
            <td width="200px"><input type="text" id="residentTax1" name="residentTax1"
                                     value="${incomeTaxInfoFormBean.residentTax1}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">二月住民税</td>
            <td width="200px"><input type="text" id="residentTax2" name="residentTax2"
                                     value="${incomeTaxInfoFormBean.residentTax2}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">三月住民税</td>
            <td width="200px"><input type="text" id="residentTax3" name="residentTax3"
                                     value="${incomeTaxInfoFormBean.residentTax3}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">四月住民税</td>
            <td width="200px"><input type="text" id="residentTax4" name="residentTax4"
                                     value="${incomeTaxInfoFormBean.residentTax4}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">五月住民税</td>
            <td width="200px"><input type="text" id="residentTax5" name="residentTax5"
                                     value="${incomeTaxInfoFormBean.residentTax5}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">六月住民税</td>
            <td width="200px"><input type="text" id="residentTax6" name="residentTax6"
                                     value="${incomeTaxInfoFormBean.residentTax6}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">七月住民税</td>
            <td width="200px"><input type="text" id="residentTax7" name="residentTax7"
                                     value="${incomeTaxInfoFormBean.residentTax7}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">八月住民税</td>
            <td width="200px"><input type="text" id="residentTax8" name="residentTax8"
                                     value="${incomeTaxInfoFormBean.residentTax8}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">九月住民税</td>
            <td width="200px"><input type="text" id="residentTax9" name="residentTax9"
                                     value="${incomeTaxInfoFormBean.residentTax9}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十月住民税</td>
            <td width="200px"><input type="text" id="residentTax10" name="residentTax10"
                                     value="${incomeTaxInfoFormBean.residentTax10}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十一月住民税</td>
            <td width="200px"><input type="text" id="residentTax11" name="residentTax11"
                                     value="${incomeTaxInfoFormBean.residentTax11}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十二月住民税</td>
            <td width="200px"><input type="text" id="residentTax12" name="residentTax12"
                                     value="${incomeTaxInfoFormBean.residentTax12}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">利用ステータス</td>
            <td width="200px"><input type="radio" name="status" <c:if
                    test="${incomeTaxInfoFormBean.status == '1'}">
                checked</c:if> value="1"/> する
                <input type="radio" name="status" <c:if
                        test="${incomeTaxInfoFormBean.status == '0'}">
                    checked</c:if> value="0"/> しない
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">作成日</td>
            <td width="200px">
                <c:out value="${incomeTaxInfoFormBean.insertDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">更新日</td>
            <td width="200px">
                <c:out value="${incomeTaxInfoFormBean.updateDate}"/>
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
