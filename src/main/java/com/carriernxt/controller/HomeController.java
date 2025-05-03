package com.carriernxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/api")
    @ResponseBody
    public Map<String, String> apiHome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Career NXT API");
        response.put("status", "running");
        return response;
    }
    
    // Forward all routes not starting with /api to index.html for client-side routing
    @GetMapping(value = {"/", "/home", "/about", "/contact", "/login", "/profile"})
    public String forwardToIndex() {
        return "forward:/index.html";
    }
} 