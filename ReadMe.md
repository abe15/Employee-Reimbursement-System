# Expense Reimbursement System (ERS) Java

## Project Description

The Expense Reimbursement System will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java 8
* JDBC
* Javalin
* Jackson Databind
* Junit
* Mockito
* Maven
* Postman
* Docker
* DBeaver
* PostgreSQL

## Getting Started
   
* git clone https://github.com/abe15/Employee-Reimbursement-System.git
* Import the project into a Java compatible IDE such as Visual Studio Code.
* Create a database instance for the project.
* Run the ersModel.sql script on the database to create the schema and add test data.
* Configure the following environment variables to provide credentials for the project to connect to your database instance.
* Configure enviroment variables for the properties below
* DATABASE_ENDPOINT - The endpoint and port of your database instance.
* DATABASE_USERNAME - The username for your database account.
* DATABASE_PASSWORD - The password for your database account.

## Usage

* Start the Javalin server to launch the project.
* Browse to the server url to reach the login page.
* Enter a username and password to login to the application.
* The user will be redirected to the manager or employee dashboard based on their role.
* An invalid username or password will redirect the user to the badlogin page where they can return to the login page to try again.

