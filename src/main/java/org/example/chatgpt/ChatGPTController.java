package org.example.chatgpt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.chatgpt.application.ChatGPTService;
import org.example.chatgpt.presentation.request.ChatGPTRequest;
import org.example.chatgpt.presentation.response.ChatGPTResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatgpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping
    public ResponseEntity<ChatGPTResponse> getAnswer(
            @Valid @RequestBody ChatGPTRequest request
    ) {
        String answer = chatGPTService.getAnswer(request.role(), request.question());
        return ResponseEntity.ok(new ChatGPTResponse(answer));
    }
}
