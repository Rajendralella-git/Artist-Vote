a) Extract the zip file from this repository  
b) Import to your IDE as an existing maven project  
   [Database, tables, and starting rows need to be created manually]  

c) Update the below property in application.properties file as needed  
   spring.datasource.url=jdbc:mysql://localhost:3306/<schema_name>  
   spring.datasource.username=<db_username>  
   spring.datasource.password=<db_password>  
d) Run the ArtistVoteApplication as a Java application  
e) application is available at http://localhost:{port}/artists
	
   i) Get All the artists (Http Method: GET)
   http://localhost:8080/artists/
   
   ii) Get the given artists (Http Method: GET)
   http://localhost:8080/artists/{artistLable}
   Ex: http://localhost:8080/artists/van_halen
   
   iii) Perform voting (Http Method: POST)
   http://localhost:8080/artists/vote
   ```
   Request Body:

   {
      "artistLabel": "van_halen"	
   }  

   Sample Call (with Curl):
      curl -s -X POST http://localhost:8080/artists/vote -H 'Content-Type: application/json' -d '{"artistLabel":"van_halen"}'  

   Sample Output:
   {
      "voteId": 27,
      "artistName": "van_halen",
      "message": "SUCCESS",
      "operationTime": "2023-02-22T14:04:26.256+00:00"
   }
   ```
f) Swagger is available at 
   http://localhost:8080/swagger-ui  
g) Test cases are available at `src/test/java/ArtistVoteControllerRestTemplTest.java`
