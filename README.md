# Coding-Challenge
Description: This application contains a search api that returns a list of restaurants
     based on 5 optional search params (name, rating, distance,price and cuisine),
     the return size is limited to 5 entries and is sorted by multiple fields(distance, rating, price) in order.
             
How to Run the App: 
     Mavn install the app and Run, Server will be listening on port 8080

How to invoke the Api:
      request type: GET.             
      port: 8080.            
      Path: /best-matched-restaurants              
      optional params (name, rating, distance, price and cuisine)            
      example:              
          http://localhost:8080/best-matched-restaurants?name=deli&distance=5&price=30&rating=3&cuisine=chinese
          
      note: *** name and cuisine params will be used to filter by contains case-insensitively(therefore it supports partial string input case      insensitively filter), and unmatched cuisine param will default to CUISINE.OTHER ***
      
      http://localhost:8080/best-matched-restaurants?name=deli&distance=5&price=30&rating=3&cuisine=invalid-cuisine-type
      is the same as 
      http://localhost:8080/best-matched-restaurants?name=deli&distance=5&price=30&rating=3&cuisine=other
      
Please let me know if you have any question. Thanks      
      
      
            
            
            
