package com.softtech.entity;

import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data
public class Torihiki {
    private String companyID;
    
    @NotBlank(message = "取引先名称は必須入力です。")
    private String companyName;
    
    private String companyType;
    
    @Pattern(regexp = "^\\d{7}$", message = "郵便番号は7桁の数字で入力してください。")
    private String postCode;

    private String address;
    
    @Pattern(regexp = "^\\d{8}$", message = "契約日は8桁の数字（YYYYMMDD）で入力してください。")
    private String basicContractDate;

    @Pattern(regexp = "^\\d{10,15}$", message = "電話番号は10-15桁の数字で入力してください。")
    private String phoneNumber;
    
    private String contactName;
    
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "有効なメールアドレスを入力してください。")
    private String mail;
    
    private String contractStatus;
    
    private String level;
    
    private String insertDate;
    
    private String updateDate;
} 