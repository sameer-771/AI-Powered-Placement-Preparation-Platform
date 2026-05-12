package com.aipp.platform.service;

import com.aipp.platform.dto.QuestionRequestDto;
import com.aipp.platform.dto.QuestionResponseDto;
import com.aipp.platform.exception.ResourceNotFoundException;
import com.aipp.platform.model.Difficulty;
import com.aipp.platform.model.Question;
import com.aipp.platform.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public Page<QuestionResponseDto> listQuestions(Difficulty difficulty, String topic, String search, Pageable pageable) {
        return questionRepository.searchQuestions(difficulty, topic, search, pageable)
                .map(this::toDto);
    }

    public QuestionResponseDto createQuestion(QuestionRequestDto request) {
        Question question = new Question();
        applyRequest(question, request);
        return toDto(questionRepository.save(question));
    }

    public QuestionResponseDto updateQuestion(Long id, QuestionRequestDto request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        applyRequest(question, request);
        return toDto(questionRepository.save(question));
    }

    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        questionRepository.delete(question);
    }

    private void applyRequest(Question question, QuestionRequestDto request) {
        question.setTitle(request.getTitle());
        question.setDescription(request.getDescription());
        question.setDifficulty(request.getDifficulty());
        question.setTopic(request.getTopic());
        question.setTags(request.getTags());
        if (request.getActive() != null) {
            question.setActive(request.getActive());
        }
    }

    private QuestionResponseDto toDto(Question question) {
        return new QuestionResponseDto(question.getId(), question.getTitle(), question.getDescription(),
                question.getDifficulty(), question.getTopic(), question.getTags(), question.isActive(),
                question.getCreatedAt());
    }
}
