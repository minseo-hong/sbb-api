package com.example.sbbapi.question;

import com.example.sbbapi.exception.DataNotFoundException;
import com.example.sbbapi.question.dto.QuestionDetailDto;
import com.example.sbbapi.question.dto.QuestionRequestDto;
import com.example.sbbapi.question.dto.QuestionListItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionListItemDto> getArticles() {
        return this.questionRepository.findAll()
                .stream().map(QuestionListItemDto::fromEntity).toList();
    }

    public QuestionDetailDto getArticle(Long id) {
        Question foundedQuestion = this.questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 질문이 존재하지 않습니다.", id)));
        Question result = foundedQuestion.toBuilder()
                .build();
        return QuestionDetailDto.fromEntity(result);
    }

    @Transactional
    public QuestionDetailDto create(QuestionRequestDto questionRequestDto) {
        Question question = Question.builder()
                .title(questionRequestDto.getTitle())
                .content(questionRequestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        Question newQuestion = this.questionRepository.save(question);
        return QuestionDetailDto.fromEntity(newQuestion);
    }

    @Transactional
    public QuestionDetailDto edit(Long id, QuestionRequestDto questionRequestDto) {
        Question question = this.questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 질문이 존재하지 않습니다.", id)));
        if (questionRequestDto.getTitle() != null)
            question = question.toBuilder()
                    .title(questionRequestDto.getTitle())
                    .build();
        if (questionRequestDto.getContent() != null)
            question = question.toBuilder()
                    .content(questionRequestDto.getContent())
                    .build();
        question = question.toBuilder()
                .editedAt(LocalDateTime.now())
                .build();
        Question editedQuestion = this.questionRepository.save(question);
        return QuestionDetailDto.fromEntity(editedQuestion);
    }

    @Transactional
    public QuestionDetailDto delete(Long id) {
        Question question = this.questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 질문이 존재하지 않습니다.", id)));
        this.questionRepository.delete(question);
        return QuestionDetailDto.fromEntity(question);
    }
}
