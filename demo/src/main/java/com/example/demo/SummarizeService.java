package com.example.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SummarizeService {

    private final ChatClient chatClient;

    public SummarizeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    public String summarize(String ticket){
        return chatClient.prompt().user("Summarize this support ticker :" + ticket).call().content();
    }
}
