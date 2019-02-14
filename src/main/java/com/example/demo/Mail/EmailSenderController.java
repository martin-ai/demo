package com.example.demo.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @ResponseBody
    @RequestMapping(value = "/send-to-default", method = RequestMethod.POST)
    public String test(@RequestBody Map<String, String> param) {
        emailSenderService.sendToDefault(param.getOrDefault("subject", "test"), param.getOrDefault("html", "test"));
        return "success";
    }

}
