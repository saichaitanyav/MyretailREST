Developer	Saichaitanya Vankadari
Product Manager	Target

Background:
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

Requirements:
The Objective of this exercise is to create an end-to-end Proof-of-Concept for a Products API, which needs to aggregate product data from multiple sources and return it as JSON to the caller


In-scope
	• Retrieve Product details along with the price information
	• Add product with price and store the product and the price details in store
	• Update product with price details
	• Endpoints:
		a. GET /shopping/products
		b. GET /shopping/products?productId=4543534
		c. POST /shopping/product
		d. PUT /shopping/product
		e. DELETE /shopping/product?productId=3432424
		
	• Automated unit test cases and show code coverage of the lines


Test data:
	1. Example product Ids: 15117729, 16483589, 16696652, 16752456, 15643793) 
	2. Example: GET Json response: 
	GET /shopping/product?productId=13860428
		{
		  "id": 13860428,
		  "name": "The Big Lebowski (Blu-ray) (Widescreen)",
		  "current_price": {
		    "value": 13.49,
		    "currency_code": "USD"
		  }
		}
		
		
Software required:
	1. Eclipse
	2. Postman
	3. PostgreSQL
	4. Java 11
	5. Spring boot - spring framework
	6. Json
	7. Swagger 
	
	
