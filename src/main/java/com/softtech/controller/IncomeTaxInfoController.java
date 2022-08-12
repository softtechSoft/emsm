package com.softtech.controller;

import com.softtech.service.IncomTaxInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-12
 * @return:
 */
@Controller
public class IncomeTaxInfoController {
    //IOC
    @Autowired
    private IncomTaxInfoService incomTaxInfoService;
    //logger
    static protected Logger logger = LogManager.getLogger(IncomeTaxInfoController.class);
}
