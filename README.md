# JITPay_UserAPI
Tech-stack used: Java 11, Spring Boot 2.6.7, JPA 2.x, H2-Database.
Built-tool used: Maven

Following are the end points for various APIs: 
			1. http://localhost:8080/user - API to save the basic details of a user - call this 			 			   			   service using POST call
				eg Payload: {
								"userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
								"createdOn": "2022-02-08T11:44:00.524",
								"email": "alex.schmid@gmail.com",
								"firstName": "Alex",
								"secondName": "Schmid"
							}
			2. http://localhost:8080/user/{userId} - API to retrieve details of any user by user_id - 			 			   call this service using GET call
			   Eg: http://localhost:8080/user/2e3b11b0-07a4-4873-8de5-d2ae2eab26b2			   
			   No payload is required for this call
		    3. http://localhost:8080/user/location - API to save the location details of user at the given 			 			   time - call this service using POST call
		       eg Payload: {    
							    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
							    "createdOn": "2022-02-08T12:05:00.124",
							    "location": {
							        "latitude": 52.25742342305784,
							        "longitude": 10.54058340174760221
							    }
							}							
		    4. http://localhost:8080/user/location/{userId} - API to get the user location with a specified 			  			   date range using user id and the request params as "from" and "to"
		       eg: http://localhost:8080/user/location/2e3b11b0-07a4-4873-8de5-d2ae2eab26b2?from=2022-10-					08T19:00:05.992&to=2022-10-08T19:59:05.992
		    5. http://localhost:8080/user/latest/location/{userId} - API to get the very recent location of a 			 			   user using user id
		       eg: http://localhost:8080/user/latest/location/2e3b11b0-07a4-4873-8de5-d2ae2eab26b2
			