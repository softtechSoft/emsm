<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> ソフトテク株式会社-社内管理システム </title>
    <script type="text/javascript" >
        //登録ボタンのクリック処理
        function doRegist(){
            document.theForm.submit();
        }

    </script>
</head>
<body>
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfarefeeInfoFormBean"  action="welfarefeeInfoEdit" >
    <h1>厚生保険料マスタ更新</h1>
    <p style="color: red;">
        <form:errors path="year" />
        <form:errors path="area" />
        <form:errors path="standSalary" />
        <form:errors path="salaryFrom" />
        <form:errors path="salaryTo" />
        <form:errors path="notCareRatio" />
        <form:errors path="careRatio" />
        <form:errors path="annuityRatio" />
    </p>

    <input type="hidden" id="welfarefeeID" name="welfarefeeID" value="${welfarefeeInfoFormBean.welfarefeeID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${welfarefeeInfoFormBean.insertFlg}"/>

    <table  border="1">
        <tr style="background-color:#dcfeeb">
            <td width="200px">厚生保険料ID</td>
            <td width="200px"><c:out  value="${welfarefeeInfoFormBean.welfarefeeID}"/></td>
        </tr>

        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${welfarefeeInfoFormBean.year}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">対象エリア</td>
            <td width="200px"><input type="text" id="area" name="area"
                                     value="${welfarefeeInfoFormBean.area}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">計算用標準報酬</td>
            <td width="200px"><input type="text" id="standSalary" name="standSalary"
                                     value="${welfarefeeInfoFormBean.standSalary}" /></td>

        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">給料From</td>
            <td width="200px"><input type="text" id="salaryFrom" name="salaryFrom"
                                     value="${welfarefeeInfoFormBean.salaryFrom}" /></td>

        </tr>
        <tr style="background-color:#dcfeeb">
            <td width="200px">給料To</td>
            <td width="200px"><input type="text" id="salaryTo" name="salaryTo"
                                     value="${welfarefeeInfoFormBean.salaryTo}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">介護必要ない料率%</td>
            <td width="200px"> <input type="text" id="notCareRatio" name="notCareRatio"
                                      value="${welfarefeeInfoFormBean.notCareRatio}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">介護必要料率%</td>
            <td width="200px"> <input type="text" id="careRatio" name="careRatio"
                                      value="${welfarefeeInfoFormBean.careRatio}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">厚生年金保険料率%</td>
            <td width="200px"> <input type="text" id="annuityRatio" name="annuityRatio"
                                      value="${welfarefeeInfoFormBean.annuityRatio}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">利用ステータス</td>
            <td width="200px"><input type="radio" name="status" <c:if test="${welfarefeeInfoFormBean.status == '1'}">
                checked</c:if> value="1" /> する
                <input type="radio" name="status" <c:if test="${welfarefeeInfoFormBean.status == '0'}">
                    checked</c:if> value="0" /> しない</td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">作成日</td>
            <td width="200px">
                <c:out  value="${welfarefeeInfoFormBean.insertDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">更新日</td>
            <td width="200px">
                <c:out  value="${welfarefeeInfoFormBean.updateDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td></td>
            <td style="text-align: right;">
                <input type="button" id="Registration" name="Registration" value="登録"  onclick="doRegist()" /></td>
        </tr>

    </table>

</form:form>
</body>
</html>
