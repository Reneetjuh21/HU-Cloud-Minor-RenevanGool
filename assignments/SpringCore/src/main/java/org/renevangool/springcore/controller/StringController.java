package org.renevangool.springcore.controller;

import org.renevangool.springcore.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/string")
public class StringController {

    private StringService stringService;

    @Autowired
    public StringController(
            StringService stringService
    ) {
        this.stringService = stringService;
    }

    @GetMapping(value = "/transform")
    @ResponseBody
    public String getTransformedString(@RequestParam String str) {
        return this.stringService.doEnvironmentFunction(str);
    }

    @GetMapping(value = "/count")
    @ResponseBody
    public int getCountedWords(@RequestParam String str) {
        return this.stringService.countWordsInString(str);
    }
}
