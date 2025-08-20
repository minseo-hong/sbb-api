package com.example.sbbapi.answer;

import com.example.sbbapi.answer.dto.AnswerRequestDto;
import com.example.sbbapi.answer.dto.AnswerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PatchMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> editAnswer(@PathVariable Long id,
                             @RequestBody AnswerRequestDto answerRequestDto) {
        AnswerResponseDto result = this.answerService.edit(id, answerRequestDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> deleteAnswer(@PathVariable Long id) {
        AnswerResponseDto result = this.answerService.delete(id);
        return ResponseEntity.ok(result);
    }
}
