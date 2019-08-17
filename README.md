# videoRentalStore


Asumption:  
```
- Asume this api is called from any kind of gateway or frontend, and I use film ids and not film name. Then I supposed that I receive these ids. Ids exists, well formed, the user only can rent the same movie one time, etc   

- Ilimited films.  

- Each film can be rented one time by the same user 

- Premium price is 3 eur basic price is 1 euro.  

- Premium price is for new releases.  

- Basic price is for regular and old films.  

- For basic price surcharges: every day extra * 1€  

- For premium price surcharges: every day extra * 3€   
```

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
Open a cmd, into the root folder's project execute 'gradle bootRun'  


## Tables    

### FILM  

Inventory of films  

### TYPE_FILM  
Type film : New releases, Regular films or Old Films.  

### USER
User 

### RENT    
Table with the relation between the user and the film  


## Examples

You can use this user ids:  
```
user1 = 1  
user2 = 2  
```

Films ids:  
```
id: 1 New release
id: 2 Regular film
id: 3 Old film
id: 4 New release
id: 5 Regular film
id: 6 Old
id: 7 New release
id: 8 New release
id: 9 Old  
```

In init data.sql there is the id user 2 with 2 movies pending to return. 1 new release and 1 regular. 4 days late.  



## Tests  
Junit tests and integration tests with Junit4 and Mockito.   


## Improving  
For the exercise I use an in memory db, but of course we can use a mongodb or sql.  



