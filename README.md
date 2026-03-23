# WorkTrack

## Project Overview
[cite_start]**WorkTrack** is a Spring Boot web application designed specifically for freelancers to manage their professional workflow more efficiently[cite: 2, 20]. [cite_start]The platform centralizes the management of clients, projects, tasks, and budgets into a single system, helping users stay organized, maintain clear project structures, and consistently meet deadlines[cite: 2, 21].

## Key Features
* [cite_start]**User Authentication**: Secure registration and login functionality powered by Spring Security, including a "remember me" feature for extended sessions[cite: 9, 11, 122, 130].
* [cite_start]**Centralized Dashboard**: A primary hub where users can view their total number of projects, active clients, and pending tasks at a glance[cite: 9, 43].
* [cite_start]**Client Management**: Full CRUD (Create, Read, Update, Delete) capabilities for client profiles, allowing freelancers to link specific projects to their respective clients [cite: 20, 28, 31-39, 96].
* [cite_start]**Project Organization**: Tools to create and manage project details, including descriptions, start dates, and deadlines[cite: 20, 44, 72, 98].
* [cite_start]**Task Tracking**: Integrated task management within projects, featuring the ability to toggle completion status and track overall project progress dynamically [cite: 22, 61-70, 108, 114].
* [cite_start]**Budget Monitoring**: Dynamic tracking of project budgets, including total allocation, logged expenses, and remaining funds[cite: 2, 20, 59, 72].
* [cite_start]**Visual Analytics**: Visual progress indicators such as task completion pie charts, project timelines, and percentage-based progress bars[cite: 3, 22, 50, 70].

## Tech Stack
* [cite_start]**Backend**: Java, Spring Boot, Spring Security[cite: 2, 9, 19, 130, 147].
* [cite_start]**Database**: PostgreSQL[cite: 19].
* [cite_start]**Frontend**: HTML5, CSS3, JavaScript[cite: 124, 139, 145, 148].
* [cite_start]**Development Tools**: IntelliJ IDEA, GitHub, Git[cite: 4, 19, 144, 146].
* [cite_start]**Design & Planning**: Figma, Canva, Lovable, Gliphy.io[cite: 19].

## Project Architecture
The application follows a structured Spring Boot architecture:
* [cite_start]**Controllers**: Entry points that handle HTTP requests (GET, POST) and direct service logic[cite: 137, 138].
* [cite_start]**Services**: Handle core business logic, such as data processing and security checks [cite: 95-119].
* [cite_start]**DTOs (Data Transfer Objects)**: Simplified objects used to transfer data between the frontend and backend without exposing sensitive entity information[cite: 139, 140].
* [cite_start]**Entities**: Blueprints for the database tables (Users, Clients, Projects, Tasks) [cite: 76-81, 142].
* [cite_start]**Mappers**: Responsible for converting data between entities and DTOs [cite: 83-89, 149].
* [cite_start]**Repositories**: Interface for database operations and data persistence [cite: 90-94].

## Project Team
* [cite_start]**Daniela Padilla**: Full Stack Development, Documentation Layout, UI/UX Design, and User Authentication[cite: 1, 121, 122].
* [cite_start]**Jesse Sintikakis**: Full Stack Development, Database Architecture, JavaScript Implementation, and Backend Logistics[cite: 1, 123, 124].
* [cite_start]**Lemuel Tzib**: Frontend Development, Documentation, Flowchart Design, and Presentation[cite: 1, 124].

## Development Timeline
[cite_start]The project was developed between **March 11, 2026, and March 23, 2026**[cite: 1, 5]:
1.  [cite_start]**Phase I - Planning**: Goal setting, feature identification, and initial mockup design[cite: 2, 6].
2.  [cite_start]**Phase II - Design**: Development of application flowcharts and UI/UX layouts[cite: 2, 22].
3.  [cite_start]**Phase III - Implementation**: Development of the backend (entities, services, controllers) and frontend styling [cite: 2, 23-119].
4.  [cite_start]**Phase IV - Maintenance**: Polishing UI, bug fixing (resolving mapper recursion/stack overflow issues), and final documentation[cite: 2, 120, 131].

## Glossary
* [cite_start]**Attribute**: Extra details inside a tag that change how it works[cite: 136].
* [cite_start]**Class**: A reusable CSS identifier for styling multiple elements[cite: 137].
* [cite_start]**Spring Security**: A framework used to secure the application and manage user access[cite: 130, 135].
* [cite_start]**URL Routing**: System connecting URLs to specific functions or controllers[cite: 149].
