# MyRetail: 
Publishes  REST API endpoints,  for creating/updating/retrieving products with price. 

Technologies Used:

| Technology    | Version       |
| ------------- | ------------- |
| Java          | 11  |
| SpringBoot    | 2.4.3|
|Postgres       | 13|
|Maven          |3.6.3


## Steps to run the project 
Pre-requisite:  Postgres need to be installed
1. Clone the project  
2. Update the `application.yaml` file under resources directory with respective Postgres details.  
3. Run the `MyretailApplication`, this will bring up the application and can be accessed via below URL:  

-http://localhost:8080/shopping/products
-http://localhost:8080/shopping/product?productId=16752456

End points: 
- GET:  /shopping/products/  retreives all product details  
- GET: /shopping/product?productId={id} retreive single product details if found  
- POST: /shopping/product creates new product  
- PUT: /shopping/product?productId={id} updates the product with new information provided in the request  
- DELETE: /shopping/product?productId={id} deletes the product  

For more details please refer to src/api-spec/myRetail-swagger.yaml

The following actions can be performed:  
* Responds to an HTTP GET request  "/shopping/products/" to retrieve all product details and "/product?productId={id}" to retrieve specific product details and delivers the product data as JSON, where {id} will be a number.  
* Example response:

{
    "id": 16752456, 
    "name": "The plasma screen Big-test",
    "current_price": {
        "value": 13.4,
        "currency_code": "USD"
    },
}
 
*GET operation with product id retrieves information of product and corresponding price from datastore
  
*Similary HTTP POST/PUT/DELETE opeation to create/update/delete the product  are performed in the datastore and returned





