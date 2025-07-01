package com.softtech.util;

public class FormatUtil {
    
    public String formatPostCode(String postCode) {
        if (postCode == null || postCode.trim().isEmpty()) {
            return "";
        }
        String numbers = postCode.replaceAll("[^0-9]", "");
        if (numbers.length() == 7) {
            return numbers.substring(0, 3) + "-" + numbers.substring(3);
        }
        return postCode;
    }
    
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "";
        }
        String numbers = phoneNumber.replaceAll("[^0-9]", ""); // 清理为纯数字

        if (numbers.length() == 10) {
            // 10位电话号码格式化 (例如: 03-1234-5678)
            return numbers.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "$1-$2-$3");
        } else if (numbers.length() == 11) {
            // 11位电话号码格式化 (例如: 090-1234-5678)
            return numbers.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        }
        // 对于其他长度的数字，直接返回清理后的纯数字
        return numbers;
    }
    
    public String formatDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return "";
        }
        String numbers = date.replaceAll("[^0-9]", "");
        if (numbers.length() == 8) {
            return numbers.substring(0, 4) + "/" + 
                   numbers.substring(4, 6) + "/" + 
                   numbers.substring(6);
        }
        return date;
    }
    
    public static String formatDateForDB(String date) {
        if (date == null || date.trim().isEmpty()) {
            return "";
        }
        String numbers = date.replaceAll("[^0-9]", "");
        if (numbers.length() == 8) {
            return numbers;
        }
        return date;
    }
} 