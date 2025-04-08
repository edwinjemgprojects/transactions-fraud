# Transaction API
Transaction Management and Fraud Detection API

## Description
Set of API for managing financial transactions with fraud detection.

## Getting Started
### Dependencies
* Spring Boot 3.4.4 
* Java 17
* MongoDb latest version
* Bruno API Testing or Postman

### Installing
* Unzip the file transaction.zip and open with IntelliJ or other IDE
* MongoDB will run in 27017 port, see application.yml file
* Application will run in 8083 port, see application.yml file

### Executing program
* Once the application was loaded execute it to test the API
* **API's List:**
    * **SaveTransaction**
      - Persist transaction into Data Base
      - Rule: Stop transaction if the total amount of transactions for any account exceeds a predefined threshold.
    * **getTransactions**
      - Retrieve all transaction
    * **getTransactionById**
      - Retrieve specific transaction by Id
    * **getTransactionByAccountNumber**
      - Retrieve specific transaction by AccountNumber
    * **deleteTransactionById**
      - Delete specific transaction by Id
      - Rule: Transactions older than 24 hours cannot be deleted
    * Note: Collection's folder /Collection/Transaction-Service.json
* **About Test Cases:**
    * It was made one case for each API
    * Just Execute the test cases within IDE

## Authors
Contributors names and contact info:
Edwin Mendoza G.
edwinjemg@gmail.com

## Commits History
* 0.5 Readme
* 0.4 Global exception
* 0.3 Uploading docker files
* 0.2 Test Cases  
* 0.1 First commit

## License
This project is licensed under the @EMG License.