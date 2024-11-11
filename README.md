# CEMS - Computerized Examination Management System

Welcome to the **CEMS (Computerized Examination Management System)**, a comprehensive solution designed to streamline and automate exam management for educational institutions.

## Table of Contents

- [Project Overview](#project-overview)
- [Screenshots](#screenshots)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [System Architecture](#system-architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)

## Project Overview

CEMS is a software system developed to centralize and automate all aspects of exam management. It enhances the efficiency and reliability of handling different types of exams through features like automated grading, data analysis, anti-cheating mechanisms, and a user-friendly interface. The system is designed to be modular, scalable, and adaptable for future requirements.

## Screenshots

For a visual overview of the application’s screens and flow, please refer to the [screenshots.pdf](screenshots.pdf) file.

## Features

1. **Question Bank Management**:
   - Allows instructors to create and manage a bank of questions.
   - Supports multiple subjects and courses with uniquely identifiable questions.

2. **Exam Creation**:
   - Instructors can generate exams by selecting questions from the question bank.
   - Each exam has a unique identifier, defined duration, point allocations, and customizable instructions.

3. **Exam Execution**:
   - **Manual Mode**: Provides students with downloadable exam files for offline completion.
   - **Automated Mode**: Allows students to answer questions directly in the system UI.

4. **Automated Grading and Anti-Cheating**:
   - Automatic grading functionality with configurable scoring.
   - Anti-cheating mechanism through pattern detection in student answers.

5. **Data Processing and Analytics**:
   - Statistical calculations (average, median, score distribution) for exams.
   - Detects and flags potentially identical errors across submissions.

6. **User Management and Security**:
   - User authentication and role-based access control.
   - Import utility for integrating user data from external systems.

## Technologies Used

- **Backend**: Java
- **Frontend**: JavaFX for user interface components
- **Database**: Relational database for user data, exams, and analytics
- **Network**: TCP/IP for local network operations (initial phase)

## System Architecture

This project follows a **full-stack architecture**:

- **Frontend**: JavaFX-based UI that allows interaction with the system.
- **Backend**: Java backend logic and a relational database handling data persistence and business logic.
- **Network Configuration**: Operates over a LAN in its first phase, with future expansions to support web-based access.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/CEMS.git
   cd CEMS
2. **Setup Database**:

   * Configure a relational database to store user and exam data.
   * Import any necessary table structures or use the provided scripts if available.

3. **Run the Application**:

   * Open the project in Eclipse or any compatible Java IDE.
   * Compile and run the main application.

## Usage

1. **User Login**: Authenticate using provided credentials.
2. **Managing Exams**:
   * Use the question bank to create or edit questions.
   * Generate exams by selecting questions and specifying test parameters.
3. **Exam Execution**: Choose between manual or automated exam modes.
4. **Grading**: The system grades exams automatically, with options for instructor input.

## Project Structure

```plaintext
├── client/             # Client-side logic for network communication
├── controllers/        # Controllers managing exam creation, user management, etc.
├── entities/           # Data entities representing questions, exams, users, etc.
├── gui/                # JavaFX user interface components
├── JDBC/               # Database connection and operations
├── notifications/      # Notification and messaging components
├── server/             # Server-side logic and exam processing
└── README.md           # Project documentation
```

