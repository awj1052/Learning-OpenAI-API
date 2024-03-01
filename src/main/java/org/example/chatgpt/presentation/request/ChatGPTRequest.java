package org.example.chatgpt.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record ChatGPTRequest(
        @NotBlank String role,
        @NotBlank String question
) {
}
