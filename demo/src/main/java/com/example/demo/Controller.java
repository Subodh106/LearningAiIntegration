package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    private final Services services;

    @PostMapping("/chat")
    public String chat(@RequestBody String chat){
        return services.chat(chat) ;
    }

}
