package org.example.chatgpt.presentation.response;

import jakarta.validation.constraints.NotBlank;

public record ChatGPTResponse(
        @NotBlank String answer
) {
}
