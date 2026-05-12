CREATE DATABASE IF NOT EXISTS placement_platform;
USE placement_platform;

CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  last_login_at TIMESTAMP NULL,
  reset_token VARCHAR(255) NULL,
  reset_token_expires_at TIMESTAMP NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_users_email (email),
  INDEX idx_users_role (role)
);

CREATE TABLE questions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(160) NOT NULL,
  description TEXT NOT NULL,
  difficulty ENUM('EASY','MEDIUM','HARD') NOT NULL,
  topic VARCHAR(80) NOT NULL,
  tags VARCHAR(255) NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_questions_difficulty (difficulty),
  INDEX idx_questions_topic (topic)
);

CREATE TABLE submissions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  language VARCHAR(40) NOT NULL,
  code_text MEDIUMTEXT NOT NULL,
  status ENUM('PASSED','FAILED','PARTIAL') NOT NULL,
  score INT NOT NULL DEFAULT 0,
  time_taken_seconds INT NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_submissions_user (user_id),
  INDEX idx_submissions_question (question_id),
  CONSTRAINT fk_submissions_user FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_submissions_question FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE resume_reports (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  resume_filename VARCHAR(255) NOT NULL,
  ats_score INT NOT NULL,
  detected_skills TEXT NOT NULL,
  missing_skills TEXT NOT NULL,
  recommendations TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_resume_reports_user (user_id),
  CONSTRAINT fk_resume_reports_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE interview_feedback (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  interview_type ENUM('MOCK','TECHNICAL') NOT NULL,
  questions_asked TEXT NOT NULL,
  ai_feedback TEXT NOT NULL,
  score INT NOT NULL,
  confidence_rating INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_interview_feedback_user (user_id),
  CONSTRAINT fk_interview_feedback_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE admin_activity (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  admin_user_id BIGINT NOT NULL,
  action_type VARCHAR(120) NOT NULL,
  action_details TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_admin_activity_admin (admin_user_id),
  CONSTRAINT fk_admin_activity_admin FOREIGN KEY (admin_user_id) REFERENCES users (id)
);
