package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SummarizeController {

    private final SummarizeService summarizeService;


    @GetMapping("/summarize")
    public String summarize(@RequestBody String ticket){
        return summarizeService.summarize(ticket);
    }
}
