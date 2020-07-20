# spring-microservice-demo-with-api-gateway

MS API Gateway:
===============

Netflix Zuul & spring cloud gateway

API gateway where MS will get registered.
And it will route the request from the clients to corresponding service.

Client has to know the address of APi gateway only. Then pass some additional information/ket to pass/route the 
request to desired MS.

                               MS2
					   API    /
  Client ===========> Gateway  ==> MS1
        makes request         \
        with some token        MS3 
                               

 1. Depdendencies: web, actuator, Zuul
 
 2. Add to main class to make your service as a router service:
      @EnableZuulProxy   = Gets circuit breaker by default (Fault Tolerance)
 
 3. Application properties:
    
    
    server:
  		port: 8100
  
    zuul:
    
	  routes:
	    some=service=1:
	      url: http://localhost:8081
	    some=service=2:
	      url: http://localhost:8082
	    some=service=3:
	      url: http://localhost:8083
	      
	  host:
	    connect=timeout=millis: 2000
	    socket=timeout=millis: 5000
         
 
   4. Client can make request as: http://api=server=host:8100/some=service=1/some=action=for=service=1    
                                         ===============      =============
                                                              Routing=token
                                                              
  Zuul Internal Architecture:
  ===========================
  
  HTTP Request
      | 
  Zuul Servlet ============================
      |                                   |
  Zuul Filter Runner                      |
  ===========================             |
  | Groovey Filters         |      |=================|
  | JVM based Language      |      | Request Context |
  |                         | <==> |   Object        |
  | Pre    Routing   Post   |      |=================|
  | Filter Filter    Filter |
  ===========================
  
  ==================================================================================
                           ================
				           | HTTP Request |
				           ================
				              /     \
				  (1)        /       \
				 /==========/         \=========================\
			    /                                                \ (7)
      (2)      /                                                  \
  ===============     (3)    ==================     (6)      ==================
  | Pre Filters |   =======> | Routing Filters |  =======>   |  Post Filters  |        
  ===============            ==================              ==================  
        | (2.1)                    |    |                          |
        |                      (4) |    | (5)                =================   
  | Custom Filters |               |    |                    | Error Filters |
                                   |    |                    =================
                          ==============================       
                          | Original Services / servers |
                          ===============================
    
  ===================================================================================
  
  Zuul Fitlers:
  ============
  
    Types:
      = Pre
      = Post
      = Error
      = Route
      = custom
   
    Execution Order: (of filters)
    ================  
                          
    Criteria:
    =========
    Which filter to pick is decided based on criteria
   
    Action:
    =======
    action to perform                      
                          
    Example:
    =======
   
    Class AppFilter extends ZuulFilter {
     ....
    }
   
  Monitoring with Actuators:
  ==========================
   
    https://cloud.spring.io/spring=cloud=netflix/multi/multi__router_and_filter_zuul.html
    
    Application properties file:
    =============================
    management:
	  endpoints:
	    web:
	      exposure:
	        include: '*'
	  endpoint:
	    health:
	      show=details: ALWAYS
    
	GET /routes. 
	============
	A GET to the routes endpoint at /routes returns a list of the mapped routes:
	{
	  /stores/**: "http://localhost:8081"
	}
   
    POST /routes. 
    ============
    A POST to /routes forces a refresh of the existing routes (for example, when there have been changes in the service catalog).
    You can disable this endpoint by setting endpoints.routes.enabled to false.
   
    GET /filters:
    ============
    A GET to the filters endpoint at /filters returns a map of Zuul filters by type. 
    For each filter type in the map, you get a list of all the filters of that type, along with their details.                       
                          
  
  Timeout:
  =======
  	
  zuul:
	  host:
	    connect=timeout=millis: 2000
	    socket=timeout=millis: 5000
                          
  Circuit Breaker:
  ================
  
  
  Security:
  =========
  
    Dependency: spring=security
                              
