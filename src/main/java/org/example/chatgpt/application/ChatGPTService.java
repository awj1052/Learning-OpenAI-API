package org.example.chatgpt.application;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {

    private final OpenAiService openAiService;

    @Autowired
    public ChatGPTService(@Value("${openai.apikey}") final String apikey) {
        this.openAiService = new OpenAiService(apikey);
    }

    public String getAnswer(String role, String question) {
        List<ChatMessage> messageList = getChatMessages(role, question);
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messageList)
                .temperature(0.9)
                .maxTokens(1000)
                .n(1)
                .build();
        return openAiService.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
    }

    private List<ChatMessage> getChatMessages(String role, String question) {
        List<ChatMessage> messageList = new ArrayList<>();
        messageList.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are a helpful assistant."));
        messageList.add(new ChatMessage(ChatMessageRole.ASSISTANT.value(), role));
        messageList.add(new ChatMessage(ChatMessageRole.USER.value(), question));
        return messageList;
    }
}
