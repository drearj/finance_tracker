# FinanceTracker

FinanceTracker is a Java-based application designed to manage and convert purchase transactions to different currencies using the Treasury Reporting Rates of Exchange API. The application provides a convenient way to store, retrieve, and convert purchase transactions while adhering to specific field requirements and currency conversion rules.

## Objectives

The primary objectives of FinanceTracker include:

- **Purchase Transaction Management:** Store and manage purchase transactions with essential details, such as description, transaction date, and purchase amount in USD.

- **Currency Conversion:** Retrieve stored purchase transactions and convert them to currencies supported by the Treasury Reporting Rates of Exchange API based on the exchange rate active for the transaction date.

## Features

1. **Store a Purchase Transaction:**
    - Accept and persist purchase transactions with a description, valid transaction date, and positive purchase amount rounded to the nearest cent.
    - Assign a unique identifier to each stored purchase transaction.

2. **Retrieve a Purchase Transaction in a Specified Country’s Currency:**
    - Retrieve stored purchase transactions and convert them to supported currencies based on the exchange rate for the transaction date.
    - Include details such as identifier, description, transaction date, original USD purchase amount, exchange rate used, and converted amount in the specified currency.


### 1. Getting Started

#### Prerequisites
- Docker: The FinanceTracker project runs within a Docker container. If you haven’t already installed Docker, follow the installation instructions for your operating system [here](https://docs.docker.com/engine/install).

#### Installation and Execution
1. Clone the FinanceTracker repository
```bash
git clone https://github.com/yourusername/FinanceTracker.git
cd FinanceTracker
```
2. Build the Docker image:
```bash
docker-compose build
```

3. Run the container:
```bash
docker-compose up -d
```

> [!WARNING]  
> If you decide to use gradle instead of Docker to build and upload the project, you will need to provide a MySql server and configure credentials in [application.properties](https://github.com/drearj/finance_tracker/blob/master/src/main/resources/application.properties)

You can access a basic API User Interface at:  http://localhost:8080/ui

![finance-tracker-ui](https://github.com/drearj/finance_tracker/blob/master/doc/img/finance-tracker-ui.jpeg)


You can access the Api Reference at:  http://localhost:8080/swagger-ui/index.html

![e6836b15-1775-4791-b8ca-7143937ee959](https://github.com/drearj/finance_tracker/assets/16170239/6c5446d5-f320-4c52-bbfb-d81e7a05839e)


### 2. Collections
- You can use the project collections on path doc/collections to integrate with Postman
