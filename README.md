<div align="center">

  <!-- -->
  <img src="https://icongr.am/feather/clock.svg?size=110&color=A1A1A6" alt="Scheduling Engine Icon" style="margin-bottom: 20px;" />

  <h1><b>Task Orchestrator Service</b></h1>
  <p style="color: #A1A1A6;"><i>Core Engine for Cron Execution and Task Lifecycle Management</i></p>

  <a href="https://github.com/pedroforbeck/agendador-tarefas">
    <!--  -->
    <img src="https://readme-typing-svg.demolab.com?font=-apple-system,BlinkMacSystemFont,San+Francisco,Helvetica+Neue&weight=400&size=14&duration=4000&pause=1000&color=A1A1A6&center=true&vCenter=true&width=600&lines=Task+Lifecycle+%26+State+Machine;Automated+Cron+Job+Execution;Asynchronous+Event+Emission;Core+Domain+Logic" alt="Typing SVG" />
  </a>

  <br><br>

  <!-- Core Tech Stack  -->
  <img src="https://img.shields.io/badge/Java_17-1C1C1E?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-1C1C1E?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-1C1C1E?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />

  <br><br>

  <!-- Architecture Badges  -->
  <img src="https://img.shields.io/badge/Role-Core%20Engine-1C1C1E?style=for-the-badge&logo=apachespark&logoColor=white" alt="Role" />
  <img src="https://img.shields.io/badge/Architecture-Event%20Emitter-1C1C1E?style=for-the-badge&logo=apachekafka&logoColor=white" alt="Architecture" />
  <img src="https://img.shields.io/badge/Network-Port%208082-1C1C1E?style=for-the-badge&logo=ngrok&logoColor=white" alt="Port" />

</div>

<br><br>

> **Abstract**<br>
> This repository contains the **Task Orchestrator Service**, acting as the primary domain engine for the ecosystem. It is responsible for managing the complete lifecycle of user tasks, executing automated background jobs via internal cron scheduling, and emitting state-change events to trigger downstream notifications.

<br>

## <img src="https://icongr.am/feather/layers.svg?size=24&color=A1A1A6" align="absmiddle" /> Table of Contents

- [Service Architecture](#-service-architecture)
-[Core Capabilities](#-core-capabilities)
- [Deployment & Setup](#-deployment--setup)
-[API Endpoints Overview](#-api-endpoints-overview)

---

## <img src="https://icongr.am/feather/cpu.svg?size=24&color=A1A1A6" align="absmiddle" /> Service Architecture

This microservice receives validated commands from the BFF Gateway, persists task states, and autonomously emits asynchronous events to the Notification Service whenever a task changes its status (e.g., Pending to Completed).

<br>

<details>
<summary><b style="color: #A1A1A6; cursor: pointer;">View Component Topology (Glass/Wireframe Diagram)</b></summary>
<br>

```mermaid
graph LR;
    %% Glassmorphism / Apple Aesthetic Styling
    classDef default fill:none,stroke:#A1A1A6,stroke-width:1px,color:#A1A1A6,rx:8,ry:8;
    classDef highlight fill:none,stroke:#FFFFFF,stroke-width:2px,color:#FFFFFF,rx:12,ry:12;
    classDef db fill:none,stroke:#007AFF,stroke-width:1px,color:#007AFF,rx:4,ry:4;

    %% Nodes
    Gateway[BFF Gateway / Client]:::default
    TaskService{Task Engine\nPort 8082}:::highlight
    Notify[Notification Service]:::default
    DB[(PostgreSQL\nTasks Schema)]:::db

    %% Connections
    Gateway -- "Task Commands\n(Create, Update)" --> TaskService
    TaskService -- "Persists State" --> DB
    TaskService -. "Emits Task Event\n(Async)" .-> Notify
```
</details>

---

## <img src="https://icongr.am/feather/command.svg?size=24&color=A1A1A6" align="absmiddle" /> Core Capabilities

| Feature | Description |
| :--- | :--- |
| <img src="https://icongr.am/feather/check-square.svg?size=18&color=A1A1A6" align="absmiddle" /> **Lifecycle Management** | Complete CRUD operations and state machine handling for scheduling entities. |
| <img src="https://icongr.am/feather/watch.svg?size=18&color=A1A1A6" align="absmiddle" /> **Cron Execution** | Robust background job processing using Spring's scheduling annotations. |
| <img src="https://icongr.am/feather/radio.svg?size=18&color=A1A1A6" align="absmiddle" /> **Event Emission** | Decoupled architecture that dispatches alerts when deadlines or completions occur. |
| <img src="https://icongr.am/feather/user-check.svg?size=18&color=A1A1A6" align="absmiddle" /> **Ownership Context** | Associates tasks securely to specific user IDs provided by the Gateway's JWT claims. |
| <img src="https://icongr.am/feather/database.svg?size=18&color=A1A1A6" align="absmiddle" /> **Data Isolation** | Maintains an independent PostgreSQL schema strictly for the task domain. |

---

## <img src="https://icongr.am/feather/terminal.svg?size=24&color=A1A1A6" align="absmiddle" /> Deployment & Setup

To run this microservice in isolation, ensure you have **Java 17+**, **Maven 3.8+**, and **PostgreSQL** installed.

### 1. Database Configuration
Create a dedicated database/schema in your PostgreSQL instance for this service (e.g., `db_agendador`).

### 2. Environment Variables
Configure your `application.properties` or `application.yml` with your local credentials. The required variables are:

```yaml
# Server Configuration
server.port: 8082

# Database Configuration
spring.datasource.url: jdbc:postgresql://localhost:5432/db_agendador
spring.datasource.username: your_postgres_user
spring.datasource.password: your_postgres_password
spring.jpa.hibernate.ddl-auto: update

# Microservice Routing (Event Emission)
service.notificacao.url: http://localhost:8083
```

### 3. Build & Execute
Navigate to the project root directory and start the Spring Boot application:

```bash
# Clone the repository
git clone https://github.com/pedroforbeck/agendador-tarefas.git

# Navigate to the directory
cd agendador-tarefas

# Run the application
./mvnw spring-boot:run
```

---

## <img src="https://icongr.am/feather/globe.svg?size=24&color=A1A1A6" align="absmiddle" /> API Endpoints Overview

Although this service runs on `http://localhost:8082`, requests should ideally be routed through the BFF Gateway. Below are the primary domain endpoints exposed by this service:

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/tasks` | Creates a new scheduled task. |
| `GET` | `/tasks` | Retrieves all tasks owned by the requesting user. |
| `GET` | `/tasks/{id}` | Retrieves details of a specific task. |
| `PUT` | `/tasks/{id}` | Updates task information or state. |
| `DELETE`| `/tasks/{id}` | Removes a scheduled task. |

*(Note: Full interactive API documentation is accessible via Swagger UI when the service is running).*

---

<div align="center">
  <br>
  <p style="color: #A1A1A6;">Architected and maintained by <b><a href="https://github.com/pedroforbeck" style="color: #A1A1A6; text-decoration: none;">Pedro Forbeck</a></b>.</p>
  <p>
    <a href="https://github.com/pedroforbeck">
      <img src="https://img.shields.io/badge/GitHub-1C1C1E?style=flat-square&logo=github&logoColor=white" alt="GitHub" />
    </a>
    <a href="https://www.linkedin.com/in/pedroforbeck/">
      <img src="https://img.shields.io/badge/LinkedIn-1C1C1E?style=flat-square&logo=linkedin&logoColor=white" alt="LinkedIn" />
    </a>
  </p>
</div>
