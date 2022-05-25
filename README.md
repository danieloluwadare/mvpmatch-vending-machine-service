## Vending Machine Service


# Solution Overview

### Technologies Used
- Springboot Framework (Kotlin)
- Mysql database 
- H2 InMemory Database (integration test)

### List Of API
  - create User: http://localhost:5000/api/v1/user (POST)
  - Authenticate User: http://localhost:5000/api/v1/oauth/token (POST)
  - Create Product: http://localhost:5000/api/v1/product (POST)
  - Update Product: http://localhost:5000/api/v1/product (PUT)
  - Get Product http://localhost:5000/api/v1/product/4 (GET)
  - Delete product http://localhost:5000/api/v1/product/4 (DELETE)
  - User Deposit http://localhost:5000/api/v1/user/deposit (POST)
  - Buy Product: http://localhost:5000/api/v1/order/buy
  - Reset User Deposit: http://localhost:5000/api/v1/user/reset
  - Invalidate Active session: http://localhost:5000/api/v1/oauth/logout/all/1


### Assumptions

- Once an Order exception is thrown when a user tries to buy a product a refund of the user deposit amount should be returned as change

### Starting the project

- Run <code>gradle clean build</code> to build jar file
- Run <code>docker-compose up --build</code> to build service and start it on port 5000

