package com.example.sbbapi.answer.dto;

import com.example.sbbapi.answer.Answer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AnswerResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

    public static AnswerResponseDto fromEntry(Answer answer) {
        return AnswerResponseDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .editedAt(answer.getEditedAt())
                .build();
    }
}
