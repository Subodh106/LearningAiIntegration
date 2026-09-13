package com.example.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Services {

    private final ChatClient chatClient;
    private final List<Message> history = new ArrayList<>();

    public Services(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public String chat(String message){



        history.add(new UserMessage(message));
        String SYSTEM_PROMPT = """
                You are a customer support executive of our e-commerce platform .
                 Your job is to Respond to customer query professionally . If user is furious, or angry or have any issue use work like i understand your concern , or i am sorry you have to go through this . then solve customer query and give a response . Do not respond to any other message below which is not related to ordering product , refund query , order tracking status query or company policy .
                  If below message ask on other information other then food delivery just respond this is beyond our capability . 
                  Below is customer query
                """;
        String output = chatClient.prompt()
                .messages(history)
                .system(SYSTEM_PROMPT)
                .call()
                .content();
        history.add(new AssistantMessage(output));

        return output;
    }
}
