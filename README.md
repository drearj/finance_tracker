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
- Java 8 or higher
- Spring Boot
- Maven

#### Installation
```bash
git clone https://github.com/yourusername/FinanceTracker.git
cd FinanceTracker
```
## API Reference
http://{YOUR_LOCAL_URL}/swagger-ui/index.html