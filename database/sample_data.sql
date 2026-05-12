USE placement_platform;

INSERT INTO users (full_name, email, password_hash, role) VALUES
('Admin User', 'admin@aipp.dev', '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'ADMIN'),
('Student One', 'student1@aipp.dev', '$2a$10$REPLACE_WITH_BCRYPT_HASH', 'USER');

INSERT INTO questions (title, description, difficulty, topic, tags) VALUES
('Two Sum', 'Return indices of two numbers adding up to target.', 'EASY', 'Arrays', 'arrays,hashmap'),
('Longest Substring Without Repeating Characters', 'Find length of longest substring without repeating characters.', 'MEDIUM', 'Strings', 'strings,sliding-window'),
('Median of Two Sorted Arrays', 'Find median of two sorted arrays.', 'HARD', 'Arrays', 'arrays,binary-search');

INSERT INTO submissions (user_id, question_id, language, code_text, status, score, time_taken_seconds) VALUES
(2, 1, 'Java', 'class Solution { }', 'PARTIAL', 55, 420);

INSERT INTO resume_reports (user_id, resume_filename, ats_score, detected_skills, missing_skills, recommendations) VALUES
(2, 'student1_resume.pdf', 72, 'Java,SQL,Spring', 'Docker,Cloud,System Design', 'Add cloud and system design projects.');

INSERT INTO interview_feedback (user_id, interview_type, questions_asked, ai_feedback, score, confidence_rating) VALUES
(2, 'MOCK', 'Tell me about yourself;Explain OOP;', 'Good structure, improve conciseness.', 78, 82);

INSERT INTO admin_activity (admin_user_id, action_type, action_details) VALUES
(1, 'QUESTION_CREATE', 'Added question Two Sum');
