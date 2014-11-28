package com.oauth.controllers;

import com.oauth.domain.EchoInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by german on 27/11/14.
 */
@Controller
public class EchoController {

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public @ResponseBody EchoInfo echo() {
        return new EchoInfo("hello world!");
    }

}
