package com.example.demo;

import com.example.demo.aitools.CalculatorTool;
import com.example.demo.aitools.CurrencyExchangeTool;
import com.example.demo.aitools.WeatherTool;
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
    private final CalculatorTool calculatorTool;
    private final WeatherTool weatherTool;
    private final CurrencyExchangeTool currencyExchangeTool;

    public Services(ChatClient.Builder builder, CalculatorTool calculatorTool, WeatherTool weatherTool, CurrencyExchangeTool currencyExchangeTool){
        this.chatClient = builder.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
    }

    public String chat(String message){



        history.add(new UserMessage(message));
        String SYSTEM_PROMPT = """
                You are a helpful AI assistant with access to external tools.
                
                Follow these rules:
                1. For arithmetic calculations, ALWAYS use the calculator tool.
                2. Always use calculator tool for even trivial calculation
                3. For current weather, ALWAYS use the currentWeather tool.
                4. For currency conversion or exchange rates, ALWAYS use the convertCurrency tool.
                5. You may call multiple tools when solving a multi-step request.
                6. After receiving tool results, explain the answer naturally.
                7. Never invent current weather or exchange-rate information.
                """;
        String output = chatClient.prompt()
                .messages(history)
                .system(SYSTEM_PROMPT)
                .tools(calculatorTool,currencyExchangeTool,weatherTool)
                .call()
                .content();
        history.add(new AssistantMessage(output));

        return output;
    }
}
