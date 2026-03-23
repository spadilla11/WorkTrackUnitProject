# WorkTrack

## Project Overview
**WorkTrack** is a Spring Boot web application designed specifically for freelancers to manage their professional workflow more efficiently. The platform centralizes the management of clients, projects, tasks, and budgets into a single system, helping users stay organized, maintain clear project structures, and consistently meet deadlines.

## Key Features
* **User Authentication**: Secure registration and login functionality powered by Spring Security, including a "remember me" feature for extended sessions.
* **Centralized Dashboard**: A primary hub where users can view their total number of projects, active clients, and pending tasks at a glance.
* **Client Management**: Full CRUD (Create, Read, Update, Delete) capabilities for client profiles, allowing freelancers to link specific projects to their respective clients.
* **Project Organization**: Tools to create and manage project details, including descriptions, start dates, and deadlines.
* **Task Tracking**: Integrated task management within projects, featuring the ability to toggle completion status and track overall project progress dynamically.
* **Budget Monitoring**: Dynamic tracking of project budgets, including total allocation, logged expenses, and remaining funds.
* **Visual Analytics**: Visual progress indicators such as task completion pie charts, project timelines, and percentage-based progress bars.

## Tech Stack
* **Backend**: Java, Spring Boot, Spring Security.
* **Database**: PostgreSQL.
* **Frontend**: HTML5, CSS3, JavaScript.
* **Development Tools**: IntelliJ IDEA, GitHub, Git.
* **Design & Planning**: Figma, Canva, Lovable, Gliphy.io.

## Project Architecture
The application follows a structured Spring Boot architecture:
* **Controllers**: Entry points that handle HTTP requests (GET, POST) and direct service logic.
* **Services**: Handle core business logic, such as data processing and security checks.
* **DTOs (Data Transfer Objects)**: Simplified objects used to transfer data between the frontend and backend without exposing sensitive entity information.
* **Entities**: Blueprints for the database tables (Users, Clients, Projects, Tasks).
* **Mappers**: Responsible for converting data between entities and DTOs.
* **Repositories**: Interface for database operations and data persistence.

## Project Team
* **Daniela Padilla**: Full Stack Development, Documentation Layout, UI/UX Design, and User Authentication.
* **Jesse Sintikakis**: Full Stack Development, Database Architecture, JavaScript Implementation, and Backend Logistics.
* **Lemuel Tzib**: Frontend Development, Documentation, Flowchart Design, and Presentation.

## Development Timeline
The project was developed between **March 11, 2026, and March 23, 2026**:
1.  **Phase I - Planning**: Goal setting, feature identification, and initial mockup design.
2.  **Phase II - Design**: Development of application flowcharts and UI/UX layouts.
3.  **Phase III - Implementation**: Development of the backend (entities, services, controllers) and frontend styling.
4.  **Phase IV - Maintenance**: Polishing UI, bug fixing (resolving mapper recursion/stack overflow issues), and final documentation.

