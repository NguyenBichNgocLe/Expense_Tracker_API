# Keane - Expense Tracker API
## Overview
Keane is an Expense Tracking platform built using Java Spring. It is designed to help users manage their financial records by tracking expenses and incomes. The API supports CRUD (Create, Read, Update, Delete) operations for both expenses and incomes, along with various filtering and aggregation features. This project can be used as a backend for financial tracking applications, enabling users to monitor their spending habits and income streams over time.

## Features

**Expense Management:**
- Add, update, delete expenses.
- Retrieve all expenses.
- Retrieve expenses based on predefined time ranges:
    - Last week
    - Last month
    - Last three months
    - Custom date range (specify start and end dates)
- Retrieve total expenses for last month.

**Income Management:**
- Add, update, delete incomes.
- Retrieve all incomes.
- Retrieve last month incomes.
- Retrieve total incomes for last month.

## Prerequisites
- Java: Ensure you have Java Development Kit (JDK) version 17 or higher installed. 
- [Docker](https://www.docker.com/): Docker is required to run the database in a container.
- [DBeaver](https://dbeaver.io/): Optional but recommended to visually interact with the MySQL database.
- [Postman](https://www.postman.com/): Used to test API endpoints.

## Usage
1. Clone the repository.
2. Set up the database: You'll need to start the MySQL database using Docker. A helper script is available at `scripts/start-db.sh`.
3. Verify database is running: To check if the MySQL database is running, use the following command `sudo docker ps`. Ensure that you see a container named `mysql` in the output.
4. Start the API by `./mvnw spring-boot:run`.
5. Interact with the Database: You can use [DBeaver](https://dbeaver.io/) to connect to the MySQL database for an easier way to manage and inspect your data.
6. Test API Endpoints: Use [Postman](https://www.postman.com/) to interact with the API.

## Planned Features
- Stats and graphs: Provide visual representations of income and expenses data over specified periods.
- User Authentication and Authorization: Implement user registration, login, and role-based access control.
- Recurring Transactions: Allow users to set up recurring expenses or incomes (e.g., monthly rent, salary).
- Budget Management: Enable users to set monthly or weekly budgets for different expense categories.
- Analytics and Insights: Provide insights on spending habits, such as the largest expenses or most frequent categories.