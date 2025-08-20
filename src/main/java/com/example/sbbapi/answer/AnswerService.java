package com.example.sbbapi.answer;

import com.example.sbbapi.answer.dto.AnswerRequestDto;
import com.example.sbbapi.answer.dto.AnswerResponseDto;
import com.example.sbbapi.exception.DataNotFoundException;
import com.example.sbbapi.question.Question;
import com.example.sbbapi.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public AnswerResponseDto create(Long questionId, AnswerRequestDto answerRequestDto) {
        Question question = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 질문이 존재하지 않습니다.", questionId)));
        Answer answer = Answer.builder()
                .content(answerRequestDto.getContent())
                .createdAt(LocalDateTime.now())
                .question(question)
                .build();
        Answer newAnswer = this.answerRepository.save(answer);
        return AnswerResponseDto.fromEntry(newAnswer);
    }

    @Transactional
    public AnswerResponseDto edit(Long id, AnswerRequestDto answerRequestDto) {
        Answer answer = this.answerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 딥뱐이 존재하지 않습니다.", id)));
        if (answerRequestDto.getContent() != null)
            answer = answer.toBuilder()
                    .content(answerRequestDto.getContent())
                    .build();
        answer = answer.toBuilder()
                .editedAt(LocalDateTime.now())
                .build();
        Answer editedAnswer = this.answerRepository.save(answer);
        return AnswerResponseDto.fromEntry(editedAnswer);
    }

    @Transactional
    public AnswerResponseDto delete(Long id) {
        Answer answer = this.answerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("id가 %d인 딥뱐이 존재하지 않습니다.", id)));
        this.answerRepository.delete(answer);
        return AnswerResponseDto.fromEntry(answer);
    }
}
