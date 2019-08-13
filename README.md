# videoRentalStore


Asumption:  
´´´
- Asume this api is called from any kind of gateway or frontend, and I use film ids and not film name. Then I supposed that I receive these ids. Ids exists, well formed, etc   

- Ilimited films. 

- Premium price is 3 eur basic price is 1 euro.  

- Premium price is for new releases.  

- Basic price is for regular and old films.  

- For basic price surcharges: every day extra * 1€  

- For premium price surcharges: every day extra * 3€   
´´´

**Feel free to modify data.sql to initialize the db as you want.
 
## Tech stack  
Springboot  
Hibernate	
JPA	
Mockito	  
DB: H2 in memory  


## Swagger url

http://localhost:8081/swagger-ui.html  


## DB url  
  
http://localhost:8081/h2-console  
user:sa  
password:  
In the browser, change the JDBC URL to: jdbc:h2:mem:testdb   

## Run  


## TODO


## TABLE

### FILM  

### TYPE_FILM  

### USER

### RENT    


## Examples

You can use this user ids:  
user1 = 1  
user3 = 2  

Films ids:  


## Improving
For the exercise I use an in memory db, but of course we can use a mongodb or sql.  



