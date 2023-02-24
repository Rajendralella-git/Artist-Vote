a) Extract the zip file
b) import the folder as an existing maven project
c) update the below property in application.properties file as needed
   spring.datasource.url=jdbc:mysql://localhost:3306/<schema_name>
   spring.datasource.username=<db_username>
   spring.datasource.password=<db_password>
d) Run the ArtistVoteApplication as java application
e) application is available at http://localhost:<port>/artists
	
   i) Get All the artists (Http Method: GET)
   http://localhost:8080/artists/
   
   ii) Get the given artists (Http Method: GET)
   http://localhost:8080/artists/{artistLable}
   Ex: http://localhost:8080/artists/van_halen
   
   iii) perform voting (Http Method: POST)
   http://localhost:8080/artists/vote
   Request Body
   {
	"artistLable": "van_halen"	
   }
   output:
   {
    "voteId": 27,
    "artistName": "van_halen",
    "message": "SUCCESS",
    "operationTime": "2023-02-22T14:04:26.256+00:00"
   }
f) Swagger is available at 
   http://localhost:8080/swagger-ui
g) test cases are available at src/test/java
   ArtistVoteControllerRestTemplTest.java
