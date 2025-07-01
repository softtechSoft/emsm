<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>取引先<c:choose>
  <c:when test="${isNew}">新規登録</c:when>
  <c:otherwise>編集</c:otherwise>
</c:choose></title>
    <style>
        /* 添加输入框的提示文本样式 */
        .hint-text {
            font-size: 0.8em;
            color: #6c757d;
            margin-top: 4px;
            line-height: 1.4;
        }
        .error-message {
            color: #dc3545;
            font-size: 0.8em;
            margin-top: 4px;
        }
    </style>
    <style>
        body {
            font-family: "メイリオ", Meiryo, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
            max-width: 800px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 6px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            padding: 6px 12px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            color: white;
            text-decoration: none;
            display: inline-block;
        }
        .btn-save {
            background-color: #0275d8;
        }
        .btn-cancel {
            background-color: #6c757d;
        }
        .button-group {
            margin-top: 20px;
            text-align: center;
        }
        .button-group .btn {
            margin: 0 5px;
        }
        .required {
            color: red;
            margin-left: 3px;
        }
        .help-text {
            font-size: 0.8em;
            color: #6c757d;
        }
        .form-control[readonly] {
            background-color: #e9ecef;
            cursor: not-allowed;
        }
        .id-display {
            padding: 7px 0;
            color: #495057;
            user-select: none;
            pointer-events: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>取引先<c:choose>
  <c:when test="${isNew}">新規登録</c:when>
  <c:otherwise>編集</c:otherwise>
</c:choose></h2>
        
        <form:form id="torihiki" modelAttribute="torihiki" action="${pageContext.request.contextPath}/torihiki/save" method="post">
            <form:hidden path="companyID"/>
            <input type="hidden" name="_isNew" value="${isNew}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            
            <div class="form-group">
                <label>取引先ID</label>
                <div class="id-display">${torihiki.companyID}</div>
                <c:if test="${empty torihiki.companyID}">
                    <div class="help-text">※自動採番されます</div>
                </c:if>
            </div>
            
            <div class="form-group">
                <label>取引先名称<span class="required">*</span></label>
                <form:input path="companyName" id="companyName" maxlength="50"/>
                <form:errors path="companyName" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>種類</label>
                <form:select path="companyType" id="companyType">
                    <form:option value="0">法人</form:option>
                    <form:option value="1">個人事業</form:option>
                </form:select>
            </div>
            
            <div class="form-group">
                <label>郵便番号</label>
                <form:input path="postCode" id="postCode" cssClass="form-control" maxlength="8" autocomplete="off" oninput="formatPostCodeView(this)" onblur="validatePostCode(this)"/>
                <div class="hint-text">例: 1234567 → 123-4567</div>
                <form:errors path="postCode" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>住所</label>
                <form:input path="address" id="address" cssClass="form-control" maxlength="100"/>
                <form:errors path="address" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>契約日</label>
                <form:input path="basicContractDate" id="basicContractDate" cssClass="form-control" maxlength="8" oninput="formatDateInput(this)" onblur="validateDate(this)"/>
                <div class="hint-text">例: 20230401 (8桁の数字を入力してください)</div>
                <form:errors path="basicContractDate" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>電話番号</label>
                <form:input path="phoneNumber" id="phoneNumber" cssClass="form-control" maxlength="15" autocomplete="off" onfocus="unformatPhoneNumber(this)" onblur="reformatPhoneNumber(this);validatePhoneNumber(this)"/>
                <div class="hint-text">例: 固定電話: 0312345678 → 03-1234-5678<br>携帯電話: 09012345678 → 090-1234-5678</div>
                <form:errors path="phoneNumber" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>連絡先名</label>
                <form:input path="contactName" id="contactName" maxlength="20"/>
                <form:errors path="contactName" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>メールアドレス</label>
                <form:input path="mail" maxlength="20" />
                <form:errors path="mail" cssClass="error-message"/>
            </div>
            
            <div class="form-group">
                <label>契約状況</label>
                <form:select path="contractStatus" id="contractStatus">
                    <form:option value="0">契約中</form:option>
                    <form:option value="1">未契約</form:option>
                </form:select>
            </div>
            
            <div class="form-group">
                <label>評判レベル</label>
                <form:select path="level" id="level">
                    <form:option value="0">優良</form:option>
                    <form:option value="1">一般</form:option>
                    <form:option value="2">良くない</form:option>
                    <form:option value="3">強制取引終了</form:option>
                </form:select>
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-save">保存</button>
                <a href="${pageContext.request.contextPath}/torihiki" class="btn btn-cancel">キャンセル</a>
            </div>
        </form:form>
    </div>

    <script>
    // 电话号码实时格式化
    function formatPhoneInput(input) {
        let value = input.value.replace(/\D/g, '');
        if(value.length > 3 && value.length <= 7) {
            value = value.replace(/(\d{3})(\d+)/, "$1-$2");
        } else if(value.length > 7) {
            value = value.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
        }
        input.value = value;
    }
    
    // 邮政编码实时格式化
    function formatPostCodeInput(input) {
        let value = input.value.replace(/\D/g, '');
        if(value.length > 3) {
            value = value.replace(/(\d{3})(\d+)/, "$1-$2");
        }
        input.value = value;
    }
    
    // 表单提交前净化数据
    document.getElementById('torihiki').onsubmit = function() {
        console.log('提交前数据净化:');
        
        const phone = document.getElementById('phoneNumber');
        console.log('原始电话:', phone.value);
        phone.value = phone.value.replace(/\D/g, '');
        console.log('净化后:', phone.value);
        
        const postCode = document.getElementById('postCode');
        console.log('原始邮编:', postCode.value);
        postCode.value = postCode.value.replace(/\D/g, '');
        console.log('净化后:', postCode.value);
        
        return true;
    };
    </script>
<script>
function formatPostCodeView(input) {
    let value = input.value.replace(/[^0-9]/g, '').slice(0, 7);
    if (value.length > 3) {
        input.value = value.slice(0, 3) + '-' + value.slice(3);
    } else {
        input.value = value;
    }
}
// 提交前去除-
document.addEventListener('DOMContentLoaded', function() {
    var form = document.getElementById('torihiki');
    if(form) {
        form.addEventListener('submit', function() {
            var pc = document.getElementById('postCode');
            if(pc) pc.value = pc.value.replace(/[^0-9]/g, '');
        });
    }
});
function formatPhoneNumberView(value) {
    value = value.replace(/[^0-9]/g, '');
    if (value.length === 11) {
        return value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if (value.length === 10) {
        return value.replace(/(\d{2,3})(\d{3,4})(\d{4})/, '$1-$2-$3');
    } else {
        return value;
    }
}
function reformatPhoneNumber(input) {
    input.value = formatPhoneNumberView(input.value);
}
function unformatPhoneNumber(input) {
    input.value = input.value.replace(/[^0-9]/g, '');
}
document.addEventListener('DOMContentLoaded', function() {
    var phone = document.getElementById('phoneNumber');
    if(phone && phone.value) {
        phone.value = formatPhoneNumberView(phone.value);
    }
    var form = document.getElementById('torihiki');
    if(form) {
        form.addEventListener('submit', function() {
            var pc = document.getElementById('postCode');
            if(pc) pc.value = pc.value.replace(/[^0-9]/g, '');
            var ph = document.getElementById('phoneNumber');
            if(ph) ph.value = ph.value.replace(/[^0-9]/g, '');
        });
    }
});
</script>
</body>
</html>