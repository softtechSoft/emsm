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
<form:form name="theForm" id="theForm" method="post" modelAttribute="welfareBabyInfoFormBean"  action="welfareBabyInfoEdit" >
    <h1>マスタ厚生子育徴収率更新</h1>
    <p style="color: red;">
        <form:errors path="year" />
        <form:errors path="area" />
        <form:errors path="rate" />

    </p>

    <input type="hidden" id="rateID" name="rateID" value="${welfareBabyInfoFormBean.rateID}"/>
    <!--新規フラグ　０　新規　１　更新-->
    <input type="hidden" id="insertFlg" name="insertFlg" value="${welfareBabyInfoFormBean.insertFlg}"/>

    <table  border="1">
        <%-- <tr style="background-color:#dcfeeb">
            <td width="200px">徴収ID</td>
            <td width="200px"><c:out  value="${welfareBabyInfoFormBean.rateID}"/></td>
        </tr> --%>

        <tr style="background-color:#dcfeeb">

            <td width="200px">対象年度</td>
            <td width="200px"><input type="text" id="year" name="year"
                                     value="${welfareBabyInfoFormBean.year}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">対象エリア</td>
            <td width="200px"><input type="text" id="area" name="area"
                                     value="${welfareBabyInfoFormBean.area}" /></td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">徴収率</td>
            <td width="200px"><input type="text" id="rate" name="rate"
                                     value="${welfareBabyInfoFormBean.rate}" /></td>

        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">利用ステータス</td>
            <td width="200px"><input type="radio" name="status" <c:if test="${welfareBabyInfoFormBean.status == '1'}">
                checked</c:if> value="1" /> する
                <input type="radio" name="status" <c:if test="${welfareBabyInfoFormBean.status == '0'}">
                    checked</c:if> value="0" /> しない</td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">作成日</td>
            <td width="200px">
                <c:out  value="${welfareBabyInfoFormBean.insertDate}"/>
            </td>
        </tr>

        <tr style="background-color:#dcfeeb">
            <td width="200px">更新日</td>
            <td width="200px">
                <c:out  value="${welfareBabyInfoFormBean.updateDate}"/>
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
