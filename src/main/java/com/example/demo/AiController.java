package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai-test")
public class AiController {

    @Autowired
    private AiRemoteService aiRemoteService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test() {
        return aiRemoteService.getToken();
    }

}
