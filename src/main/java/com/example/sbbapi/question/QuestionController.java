package com.example.sbbapi.question;

import com.example.sbbapi.answer.Answer;
import com.example.sbbapi.answer.dto.AnswerRequestDto;
import com.example.sbbapi.answer.dto.AnswerResponseDto;
import com.example.sbbapi.answer.AnswerService;
import com.example.sbbapi.question.dto.QuestionDetailDto;
import com.example.sbbapi.question.dto.QuestionRequestDto;
import com.example.sbbapi.question.dto.QuestionListItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<QuestionListItemDto>> getArticles() {
        List<QuestionListItemDto> result = this.questionService.getArticles();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetailDto> getArticle(@PathVariable Long id) {
        QuestionDetailDto result = this.questionService.getArticle(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<QuestionDetailDto> createArticle(@RequestBody QuestionRequestDto questionRequestDto) {
        QuestionDetailDto result = this.questionService.create(questionRequestDto);
        URI uri = URI.create("/questions");
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionDetailDto> editArticle(@PathVariable Long id,
                                                           @RequestBody QuestionRequestDto questionRequestDto) {
        QuestionDetailDto result = this.questionService.edit(id, questionRequestDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionDetailDto> deleteArticle(@PathVariable Long id) {
        QuestionDetailDto result = this.questionService.delete(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("{id}/answers")
    public ResponseEntity<AnswerResponseDto> createAnswer(@PathVariable Long id,
                                                          @RequestBody AnswerRequestDto answerRequestDto) {
        AnswerResponseDto result = this.answerService.create(id, answerRequestDto);
        URI uri = URI.create(String.format("/questions/%d", id));
        return ResponseEntity.created(uri).body(result);
    }
}
