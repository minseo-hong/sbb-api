package com.example.sbbapi.question.dto;

import com.example.sbbapi.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class QuestionListItemDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

    public static QuestionListItemDto fromEntity(Question question) {
        return QuestionListItemDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .editedAt(question.getEditedAt())
                .build();
    }
}
