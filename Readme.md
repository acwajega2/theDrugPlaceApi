# ERP System

This is a simple ERP (Enterprise Resource Planning) system designed to manage branches, staff, and daily sales and expenses within an organization. The system is represented using a Database Markup Language (DBML) schema.

## Database Structure

The ERP system's database structure is defined using DBML, and it consists of the following tables:

- **branches:** Stores information about different branches of the organization, including branch name, location, manager ID, opening date, and creation timestamp.

- **staff:** Contains details about the staff working in various branches. Staff information includes name, role, email, phone, hire date, and creation timestamp. Staff members are associated with branches.

- **daily_sales:** Records daily sales submissions, including staff ID, sale date, sale amount, payment method, transaction reference, and creation timestamp.

- **daily_expenses:** Tracks daily expense submissions with staff ID, expense date, expense amount, expense category, receipt image URL, and creation timestamp.

## Dependencies

This ERP system does not specify a particular technology stack or framework. Depending on your implementation, you may need to set up a database system (e.g., MySQL, PostgreSQL) and an application layer to interact with the database.

## Getting Started

1. Create a database and set up the necessary tables based on the provided DBML schema.

2. Implement the application layer to interact with the database, allowing you to manage branches, staff, and daily sales and expenses.

3. Customize the system to meet your specific business requirements.

## Usage

You can use this ERP system to:

- Manage information about different branches in your organization, including location, branch manager, and opening date.

- Maintain staff details, such as name, role, email, phone, and hire date.

- Record daily sales submissions with information about sale amount, payment method, and transaction reference.

- Track daily expenses submissions, including expense amount, category, and receipt image URL.
