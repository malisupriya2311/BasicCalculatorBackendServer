# BasicCalculatorBackendServer

Problem Statement : 
- Create an application that works like a regular calculator.
- The application should support the four arithmetic methods and each operation should be stored in a "data store", eg database/file/resource. Create a webpage where the user can initiate and present data in a clear way. As it is important that load times of the page are low and the data source can at times be slow we want to render the page before data has been fetched from backend.
- Each arithmetic method should be created as a REST endpoint and it should also be possible to fetch all stored arithmetic operations from the data store from another REST endpoint. If one operation has already been executed, read the answer from the data store and return it, do not calculate the same operation twice.
- As the data source may be subject to change we want to abstract the layer so that we can swap out the source for another.
- The backend should be written in any java and should implement industry standards and practices. It's preferred if Spring Boot and React is used.
- Please share your code through github or equivalent because Tele2 cannot receive zip files through email.

Solution :

# JDK : Java 17

# Database : inbuilt Derby Databse

# Jar Details 
jar name : BasicCalculatorBackendServer-0.0.1-SNAPSHOT.jar
jar location : /master/BasicCalculatorBackendServer-0.0.1-SNAPSHOT.jar

# Execute Jar 
command : java -jar BasicCalculatorBackendServer-0.0.1-SNAPSHOT.jar

# For Testing used : Postman
Collection url : https://www.getpostman.com/collections/74a44f93630458e1fcf1 

# Rest api :
Get : http://localhost:8989/all
Get Find by operation: http://localhost:8989/operation/subtraction
Post : 
http://localhost:8989/addition , 
http://localhost:8989/subtraction, 
http://localhost:8989/multiplication , 
http://localhost:8989/division

# Different Scenario Examples :

# URL : http://localhost:8989/multiplication
# Request: 
{
"operation":"multiplication",
"firstParam":11.0,
"secondParam":2.0,
"result":0
}
# Response: 22 , HTTP status : 200

# URL : http://localhost:8989/addition
# Request: 
{
  "operation":"+",
"firstParam":11.0,
"secondParam":2.0,
"result":0
}
# Response: Operation value is invalid, Please add valid operation: [addition, subtraction, multiplication, division]
# HTTP Status : 400 Bad Request 

# URL : http://localhost:8989/addition
# Post Request: 
{
  "operation":"division",
"firstParam":11.0,
"secondParam":2.0,
"result":0
}
# Response: Operation value is invalid, Please add valid operation: [addition, subtraction, multiplication, division]
# HTTP Status : 400 Bad Request 

# URL : http://localhost:8989/addition
# Get Find by operation: http://localhost:8989/operation/subtraction
# Response: Record not Found
# HTTP Status : 404 Not Found




