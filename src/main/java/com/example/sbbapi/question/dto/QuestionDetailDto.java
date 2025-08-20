package com.example.sbbapi.question.dto;

import com.example.sbbapi.answer.dto.AnswerResponseDto;
import com.example.sbbapi.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@Builder
public class QuestionDetailDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private List<AnswerResponseDto> answers;

    public static QuestionDetailDto fromEntity(Question question) {
        return QuestionDetailDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .editedAt(question.getEditedAt())
                .answers(question.getAnswers()
                        .stream()
                        .map(AnswerResponseDto::fromEntry)
                        .toList())
                .build();
    }
}
