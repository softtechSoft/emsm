package com.softtech.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.common.BirthStone;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @GetMapping("/apiTest")
    @ResponseBody
    public BirthStone getBirthStone() {
    	System.out.println("aaaaaaaaaaa");
        //return "aaaaaa";
        BirthStone birthStone = new BirthStone();
        birthStone.setMonth("2月");
        birthStone.setName("アメジスト");
        birthStone.setColor("紫");
        return birthStone;
    }

}