## Task Description
You should be able to start the example application by executing com.recipe.api.RecipeApiApplication, which starts a webserver on port 8080 (http://localhost:8080)
and serves Swagger available on 'http://localhost:8080/swagger-ui/index.html' where can inspect and try existing endpoints.

The project is based on a web services which uses the following technologies:

* Java 11
* Spring Boot
* Database H2 (In-Memory)
* Maven


The following conventions have been used to build the architecture of an application.

 * The architecture of the web service is built with the following components:
     * DataTransferObjects: Objects which are used for outside communication via the API
     * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
     * Service: Implements the business logic and handles the access to the DataAccessObjects.
     * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
     * DomainObjects: Functional Objects which might be persisted in the database.
     * example data has been added to resources/data.sql to load the data on application startup.
     * Search API has been working with the following parameters:
     * to search for veg recipes : recipeType=VEG
     * to include and exclude the ingredient into the search isInclude parameter is mandatory with true and false value.
     * ex: to include potatoes as an ingredient it requires isInclude=true&ingredient=potatoes and to exclude it isInclude=false&ingredient=potatoes
     * examples: http://localhost:8080/v1/recipe/search?recipeType=NONVEG&servings=5
     * http://localhost:8080/v1/recipe/search?isInclude=true&ingredient=Leek
     * http://localhost:8080/v1/recipe/search?recipeType=VEG
 


# Database

`http://localhost:8080/h2-console`

To get access to database:

**username: sa**

**password:**

# Test

Test coverage 94%

![code_coverage.png](code_coverage.png)

