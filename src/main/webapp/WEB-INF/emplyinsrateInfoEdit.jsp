<%--
  Created by IntelliJ IDEA.
  User: 孙晔
  Date: 2022/08/08
  Time: 17:03
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
<form:form name="theForm" id="theForm" method="post" modelAttribute="emplyinsrateInfoFormBean"
           action="emplyinsrateInfoEdit">
    <h1>雇用保険率テーブル更新</h1>
    <!--エラーメッセージ-->
    <p style="color: red;">
        <c:forEach items="${errors}" var="error">
            <spring:message message="${error}"/>
        </c:forEach>
    </p>
    <input type="hidden" id="emplyinsrateID" name="emplyinsrateID"
           value="${emplyinsrateInfoFormBean.emplyinsrateID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg"
           value="${emplyinsrateInfoFormBean.insertFlg}"/>

    <table border="1">
        <tr style="background-color:#dcfeeb">
            <td width="200px">雇用保険ID</td>
            <td width="200px"><c:out value="${emplyinsrateInfoFormBean.emplyinsrateID}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${emplyinsrateInfoFormBean.year}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">雇用保険労働者負担料率‰</td>
            <td width="200px"><input type="text" id="laborBurdenRate" name="laborBurdenRate"
                                     value="${emplyinsrateInfoFormBean.laborBurdenRate}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">雇用保険事業主負担料率‰</td>
            <td width="200px"><input type="text" id="employerBurdenRate" name="employerBurdenRate"
                                     value="${emplyinsrateInfoFormBean.employerBurdenRate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">雇用保険料率‰</td>
            <td width="200px"><c:out
                    value="${emplyinsrateInfoFormBean.employmentInsuranceRate}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">労災保険料率(全額事業主)‰</td>
            <td width="200px"><input type="text" id="industrialAccidentInsuranceRate"
                                     name="industrialAccidentInsuranceRate"
                                     value="${emplyinsrateInfoFormBean.industrialAccidentInsuranceRate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">労働保険料率‰</td>
            <td width="200px"><c:out
                    value="${emplyinsrateInfoFormBean.laborInsuranceRate}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">一般拠出金料率(全額事業主)‰</td>
            <td width="200px"><input type="text" id="contributionRate" name="contributionRate"
                                     value="${emplyinsrateInfoFormBean.contributionRate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">利用ステータス</td>
            <td width="200px"><input type="radio" name="status" <c:if
                    test="${emplyinsrateInfoFormBean.status == '1'}">
                checked</c:if> value="1"/> する
                <input type="radio" name="status" <c:if
                        test="${emplyinsrateInfoFormBean.status == '0'}">
                    checked</c:if> value="0"/> しない
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">作成日</td>
            <td width="200px">
                <c:out value="${emplyinsrateInfoFormBean.insertDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">更新日</td>
            <td width="200px">
                <c:out value="${emplyinsrateInfoFormBean.updateDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td></td>
            <td style="text-align: right;">
                 <input type="button" id="Registration" name="Registration" value="登録"
                       onclick="doRegist()"/>

            </td>


        </tr>

    </table>

</form:form>
</body>
</html>
