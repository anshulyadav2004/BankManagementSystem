## SmartBank Dashboard  
##Preview Video- https://youtu.be/BTcGfq-p1iE?si=fzJPVyYHXkMW3g4N     Use 2x Speed

A secure, web-based banking dashboard application built with core Java technologies. The platform provides essential banking operations such as account authentication, real-time balance tracking, deposits, and fund transfers, engineered with transactional concurrency safeguards.

---

## Features

* **Authentication Module:** Secure login and logout session management for bank users.
* **Dashboard Overview:** Displays real-time current account balances.
* **Deposit System:** Allows users to credit funds to their account seamlessly.
* **Fund Transfer:** Enables direct account-to-account money transfers.
* **Transaction Concurrency & Safety:** Implements thread-safe logic using Java Collections and core concurrency principles to prevent race conditions during simultaneous deposit or transfer operations.

---

## Tech Stack

* **Backend Logic:** Java, Java Servlets
* **Data Management:** Java Collections Framework (In-Memory / Concurrent Data Structures)
* **Frontend:** JSP (JavaServer Pages), HTML5, CSS3
* **Web Server:** Apache Tomcat 10+
* **IDE / Build Tools:** Eclipse IDE / Apache Maven

---

## Project Structure

```text
SmartBankDashboard/
├── src/
│   └── main/
│       ├── java/          
│       └── webapp/        # JSP files, Web Content, static assets
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── login.jsp
│           └── dashboard.jsp
├
├── .gitignore
└── README.md

```

---

## Getting Started

### Prerequisites

* **Java Development Kit (JDK):** Version 17 or higher
* **Application Server:** Apache Tomcat 10.x
* **IDE:** Eclipse IDE for Enterprise Java Developers
 
 
 

 
