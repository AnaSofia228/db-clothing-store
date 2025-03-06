This is a Spring Boot application for managing a clothing store.  This application incluying entitys as Product, Customer, Order, Category and Order Items, it uses MySQL as the database and runs on Docker.

1. Create and run the Dockerfile and docker-compose.yml
2. Start the db in the port 5500
3. Run the SpringBoot application in the port 4500
4. API EndPoints
   Products: http://localhost:4500/api/products
   Categories: http://localhost:4500/api/categories
   Customers: http://localhost:4500/api/customers
   Orders: http://localhost:4500/api/orders
5. To stop the application and database services, run:
   docker-compose down
   
The database connection is configured in application.properties
