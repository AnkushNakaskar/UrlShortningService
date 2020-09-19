# Url Shortening Service
* This project is about Generating Short Url
* Design
    * High level design
        * Please find the filed named as  ```High Level Diagram.png``` in project
* Assumptions
    * Used HashMap to avoid Database and Redis cache
        * HashMap can be replaced by Database(MySQL) and Cache(Redis).
        
* Steps to run application
    * Checkout the project
    * Run below command 
        ```./gradlew clean bootRun```    
    * Import  ```https://www.getpostman.com/collections/57e25363446f51f8a81f```  into Postman
        * Follow below steps from API point of view to see the behaviour         
            * 'Generating Short Url' : to generate short url
            * Use the above generated url in any browser, it will be re-directed to original
* Application covered under Test cases for RestAPI & Junit.
    
* How to scale the application
    * Use Database partitioning to store highly consumed(Regional) URL hash.
    * Use Caching cluster(Redis) to get the hash mapping with original Url value.
        * LRU policy can be useful here.
    * We can have docker  image of spring application, deploy it on kubernetes.
* Limitation
    * Hash generated using MD5, which is large if compare,
        * we can use the fixed number to combination generated already, like if hash value is field(6 letters).
        * Pre-compute those, and stored in two separate table , used and unused hash,
            * While generating hash, used the any hash from available hash
            * To get URL from hash, check only in not available hash.  
                   
                 
