package com.aipp.platform.repository;

import com.aipp.platform.model.Difficulty;
import com.aipp.platform.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.active = true "
            + "AND (:difficulty IS NULL OR q.difficulty = :difficulty) "
            + "AND (:topic IS NULL OR q.topic = :topic) "
            + "AND (:search IS NULL OR LOWER(q.title) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Question> searchQuestions(@Param("difficulty") Difficulty difficulty,
                                   @Param("topic") String topic,
                                   @Param("search") String search,
                                   Pageable pageable);
}
