#### Discovery Hub for Micro Services ####

* This is a Java maven Spring Boot project. I used Spring Boot Java Development framework as I have extensively used it in the past and I believe would work great especially for creating microservices.
* I have pretty much used only Java as the primary tech stack. 
* Ideally in order to persist the instance information of the instances that are being registered with the discovery service, I would use a Database. However, for the sake of this exercise and to enable ease of testing the logic without the need to install the database I have stored everything locally. 
* This would mean that the application would not work in cluster/distributed mode and needs to be tested as a single instance only.

#### How to run ####

From the root directory of this project run the below command to start the service. It defaults to port `8080`.

`mvn spring-boot:run`





   