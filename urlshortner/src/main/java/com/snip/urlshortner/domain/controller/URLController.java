package com.snip.urlshortner.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String initREST() {
        return "<center><h2>Welcome to SNIP URL Shortner REST API</h2></center>";
    }
}
