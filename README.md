# Vacation Pay Calculator
This repository contains a microservice for calculating vacation pay. The service is built using Java and Spring Boot.

## Features
- Calculates the vacation pay based on the average monthly salary, the number of vacation days, and the start date.
- Exposes a REST API endpoint for calculating vacation pay.
- Handles holidays and weekends by excluding them from the calculation.
- Follows the labor code of the Russian Federation (by default) regarding the maximum duration of vacation.

## Usage
To calculate the vacation pay, make a GET request to the /calculacte endpoint with the following parameters:

- averageSalary: The average monthly salary of the employee during a year (12 months).
- vacationDays: The number of vacation days (between 1 and 186).
- startDay (can be null): The start date of the vacation (defaults to the current date if is null).

Example request:
```http
GET /calculate?averageSalary=5000&vacationDays=20&startDay=2022-01-01
```

Example response:
```json
{
  "vacationPay": "30000.00"
}
```
