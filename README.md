# AI-Powered Placement Preparation Platform

A full-stack placement preparation platform with AI-inspired analytics, coding practice, resume intelligence, and interview coaching.

## Tech Stack
- Frontend: HTML, CSS, JavaScript, Chart.js
- Backend: Java 17, Spring Boot, Maven, Spring Security (JWT)
- Database: MySQL

## Project Structure
- frontend/ - static frontend (pages, styles, scripts, assets)
- backend/ - Spring Boot application
- database/ - MySQL schema and sample data

## Setup

### 1) Database
1. Create a MySQL database and run:
   - database/schema.sql
   - database/sample_data.sql

### 2) Backend
1. Update backend/src/main/resources/application.properties with:
   - MySQL username and password
   - app.jwt.secret (minimum 32 characters)
   - app.ai.gemini-key (optional)
2. Start the backend:
   - mvn spring-boot:run

### 3) Frontend
1. Open frontend/index.html in a local dev server (Live Server or any static server).
2. Login or register and navigate the dashboard.

## Core API Endpoints
- POST /api/auth/register
- POST /api/auth/login
- POST /api/auth/forgot-password
- POST /api/auth/reset-password
- GET /api/auth/me

- GET /api/dashboard/summary

- GET /api/questions
- POST /api/questions (admin)
- PUT /api/questions/{id} (admin)
- DELETE /api/questions/{id} (admin)

- POST /api/submissions
- GET /api/submissions

- POST /api/resume/analyze
- GET /api/resume/history

- POST /api/interviews/feedback
- GET /api/interviews/history

- GET /api/admin/users (admin)
- GET /api/admin/activity (admin)

## Notes
- AI modules use deterministic, safe placeholder logic when external AI services are not configured.
- The resume report can be exported via the browser print to PDF flow.
