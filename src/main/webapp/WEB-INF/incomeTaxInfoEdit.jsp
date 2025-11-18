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
    <h1>住民税社宅マスター</h1>
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
        <%-- <tr style="background-color:#dcfeeb">
            <td width="200px">所得税ID</td>
            <td width="200px"><c:out value="${incomeTaxInfoFormBean.incomeTaxID}"/></td>
        </tr> --%>

        <tr style="background-color:#dcfeeb">
            <td width="200px">社員</td>
            <td width="200px"><%-- <input type="text" id="employeeID" name="employeeID"
                                     value="${incomeTaxInfoFormBean.employeeID}"/> --%>
                <c:choose>
            		<c:when test="${incomeTaxInfoFormBean.insertFlg == '0'}">
                		<form:select path="employeeID">
	                    <form:options items="${employeeList}"
	                                 itemLabel="employeeName"
	                                 itemValue="employeeID"/>
                		</form:select>
            		</c:when>
		            <c:otherwise>
		                <c:out value="${incomeTaxInfoFormBean.employeeName}"/>
		                <input type="hidden" id="employeeID" name="employeeID"
		                       value="${incomeTaxInfoFormBean.employeeID}"/>
		            </c:otherwise>
		        </c:choose>
               </td>
        </tr>
        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${incomeTaxInfoFormBean.year}"/></td>
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
            <td width="200px">一月社宅家賃</td>
            <td width="200px"><input type="text" id="rental01" name="rental01"
                                     value="${incomeTaxInfoFormBean.rental01}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">二月社宅家賃</td>
            <td width="200px"><input type="text" id="rental02" name="rental02"
                                     value="${incomeTaxInfoFormBean.rental02}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">三月社宅家賃</td>
            <td width="200px"><input type="text" id="rental03" name="rental03"
                                     value="${incomeTaxInfoFormBean.rental03}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">四月社宅家賃</td>
            <td width="200px"><input type="text" id="rental04" name="rental04"
                                     value="${incomeTaxInfoFormBean.rental04}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">五月社宅家賃</td>
            <td width="200px"><input type="text" id="rental05" name="rental05"
                                     value="${incomeTaxInfoFormBean.rental05}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">六月社宅家賃</td>
            <td width="200px"><input type="text" id="rental06" name="rental06"
                                     value="${incomeTaxInfoFormBean.rental06}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">七月社宅家賃</td>
            <td width="200px"><input type="text" id="rental07" name="rental07"
                                     value="${incomeTaxInfoFormBean.rental07}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">八月社宅家賃</td>
            <td width="200px"><input type="text" id="rental08" name="rental08"
                                     value="${incomeTaxInfoFormBean.rental08}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">九月社宅家賃</td>
            <td width="200px"><input type="text" id="rental09" name="rental09"
                                     value="${incomeTaxInfoFormBean.rental09}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十月社宅家賃</td>
            <td width="200px"><input type="text" id="rental10" name="rental10"
                                     value="${incomeTaxInfoFormBean.rental10}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十一月社宅家賃</td>
            <td width="200px"><input type="text" id="rental11" name="rental11"
                                     value="${incomeTaxInfoFormBean.rental11}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十二月社宅家賃</td>
            <td width="200px"><input type="text" id="rental12" name="rental12"
                                     value="${incomeTaxInfoFormBean.rental12}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">一月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee01" name="rentalMgmtFee01"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee01}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">二月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee02" name="rentalMgmtFee02"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee02}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">三月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee03" name="rentalMgmtFee03"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee03}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">四月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee04" name="rentalMgmtFee04"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee04}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">五月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee05" name="rentalMgmtFee05"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee05}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">六月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee06" name="rentalMgmtFee06"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee06}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">七月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee07" name="rentalMgmtFee07"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee07}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">八月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee08" name="rentalMgmtFee08"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee08}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">九月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee09" name="rentalMgmtFee09"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee09}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee10" name="rentalMgmtFee10"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee10}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十一月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee11" name="rentalMgmtFee11"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee11}"/></td>
        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">十二月共益費</td>
            <td width="200px"><input type="text" id="rentalMgmtFee12" name="rentalMgmtFee12"
                                     value="${incomeTaxInfoFormBean.rentalMgmtFee12}"/></td>
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
