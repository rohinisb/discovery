## Discovery Service ##

* This is a Java maven Spring Boot project. I used Spring Boot Java Development framework as I have extensively used it in the past and it works great for creating microservices.
* I have pretty much used only Java as the primary tech stack based on MVC.
* Ideally in order to persist the instance information of the instances that are being registered with the discovery service, I would use a Database. However, for the sake of this exercise and to enable ease of testing the logic without the need to install the database I have stored everything locally. 
* This would mean that the application would not work in cluster/distributed mode and needs to be tested as a single instance only.
* I have assumed this as a valid use case - Same instance (same id) can register with multiple services.
* For the `/register` endpoint I thought of two other cases where you can update or unregister a service from the discovery service and get status by serviceName for `/discovery` endpoint. I however did not implement the same.
* Tested this application using the mock backend microservice Option 2. More specifically Option 2.2 and the `/discovery` endpoint using Postman client.


#### How to run ####

From the root directory of this project run the below command to start the service. It defaults to port `8080`.

```
mvn spring-boot:run
```

I have 


#### Time Spent ####
I spent about

* 90 mins on and off to think of the design
* 60 mins coming up with the basic logic and setting up the project
* 10 mins to understand the mock testing service
* 20 mins to test the service
* 90 mins to fix bugs, code formatting and documenting  

Total of 4.5 hours.

 







   