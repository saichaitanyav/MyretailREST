# MyRetail: 
Exposes  REST api endpoints,  for creating/updating/retrieving products with price. 

Technologies Used:

| Technology    | Version       |
| ------------- | ------------- |
| Java          | 11  |
| SpringBoot    | 2.4.3|
|Postgres       | 13|
|Maven          |3.6.3


## Steps to run the project on a local machine:
Pre-requisite:  Postgres need to be installed
1. Clone the project  
2. Update the `application.yaml` file under resources directory with respective Postgres details.  
3. Run the `MyretailApplication`, this will bring up the application and can be accessed via below URL:  

http://localhost:8080/shopping/products
http://localhost:8080/shopping/product?productId=16752456

End points: 
- GET:  /shopping/products/  fetches all product details  
- GET: /shopping/product?productId={id} fetches single product details if found  
- POST: /shopping/product creates a new product  
- PUT: /shopping/product?productId={id} updates the product with new details  
- DELETE: /shopping/product?productId={id} deletes the product  

For more details please refer to [myretail-sepcification](./api-spec/myretail-swagger.yaml)  

The following actions can be performed:  
* Responds to an HTTP GET request at "/products/" to retrive all product details and "/products/{id}" to retrieve specific product details and delivers the product data as JSON, where {id} will be a number.  
* Example response:

{
    "id": 16752456, 
    "name": "The plasma screen Big-test",
    "current_price": {
        "value": 540.0,
        "currency_code": "USD"
    },
}
```
  
* Performs an HTTP GET to retrieve the product name from database 
    * Reads pricing information from a NoSQL data store,      
    * Reads the product id and product name from the HTTP request and  
    * Aggregates the both the data objects into one single response object in JSON 
    
* Similarly performs HTTP POST/PUT/DELETE opeations to create/update the product name from an internal resource and reads pricing information from a NoSQL data store and produces the single respose in JSON.   





