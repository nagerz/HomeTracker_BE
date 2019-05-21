# README

## Description
HomeTrackr is a web application designed to aid in tracking the production of home and other residential construction projects. The backend service enables reading and writing database access to a users projects, including rooms and materials used during the construcitn of those rooms. HomeTrackr is Java application utilizing with Spring Boot, PostgreSQL, and GraphQL.

#### [**_View HomeTrackr in Production_**](https://hometrackr.herokuapp.com/) </br>

### Schema

## Getting Started

To run HomeTrackr on a local machine, navigate to the directory in which you would like the project to be located in, then execute the following commands:

```
$ git clone 
$ cd 
$ mvn install # Installs dependencies
```

If Postgres was locally installed using homebrew, run the command:
```
/usr/local/opt/postgres/bin/createuser -s postgres
$ CREATE DATABSE app_name; # Creates PostgreSQL Database
```
This will create a new postgres user by the name of postgres. This enables all collaborators to utilize the same username and enables the `config.json` file functionality for the database username.

## Running Tests

To run the test suite... . The tests will automatically run each time an update is made to the application.

## Test Coverage
To run a test coverage report execute the command: .

## Deployment

To view HomeTrackr in development, execute the following command from the project directory: `mvn spring-boot:run`. To view the application in a web browser, visit `localhost:8080` and navigate the the desired endpoint.

## Available Endpoints
The application provides the following endpoints:

### User Endpoints (NOT YET AVAILABLE)
#### User Registration

A user can be created and saved in the database in order to track meals and calorie intake. A user is created via a `POST` request to the `/api/v1/users` endpoint. A unique email, password, and matching password_confirmation must be provided, formatted as follows:

```
{
  "email": "example@email.com",
  "password": "password",
  "password_confirmation": "password"
}
```

If the request is successful, the application will return a unique api_key for the user, along with a status code of 200.

``` HTTP
status: 200
body:

{
    "api_key": "1234567abcdef"
}
```

#### User Session

A user can be 'logged in' or a 'session' created to retrieve a users api_key. A session is created via a `POST` request to the `/api/v1/sessions` endpoint. A correctly matching user email and password must be provided, formatted as follows:

```
{
  "email": "example@email.com",
  "password": "password"
}
```

If the request is successful, the application will return the users unique api_key, along with a status code of 200.

``` HTTP
status: 200
body:

{
    "api_key": "1234567abcdef"
}
```
### Project Endpoints

### Room Endpoints
#### Room Show

An individual room currently saved in the database can be retrieved via a `GET` request to the `/api/v1/rooms/:id` endpoint.

If the request is successful, the application will return the requested room object, along with a status code of 200.

``` HTTP
status: 200
body:

{
  "id": 1,
  "name": "Living Room 1",
  "type": "Living Room",
  "description": "Northeast living room"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Room Index
All room items currently saved in the database can be retrieved via a `GET` request to the `/api/v1/rooms` endpoint.

If the request is successful, the application will return an array containing room objects, along with a status code of 200.

``` HTTP
status: 200
body:

[
  {
    "id": 1,
    "name": "Living Room 1",
    "type": "Living Room",
    "description": "Northeast living room"
  },
  {
    "id": 2,
    "name": "Room 2",
    "type": "Kitchen",
    "description": "Big Kitchen"
  },
  {
    "id": 3,
    "name": "Room 3",
    "type": "Bedroom",
    "description": "Small bedroom"
  }
]
```

#### Room Create

A new room item can be created and saved in the database via a `POST` request to the `/api/v1/rooms` endpoint. The request must contain a room name and type, and may contain an optional description. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "New Bedroom",
  "type": "Bedroom",
  "description": "The new bedroom",
}
```

If the request is successful, the application will return the created room object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 4,
    "name": "New Bedroom",
    "type": "Bedroom",
    "description": "The new bedroom"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Room Update

An existin rooms information can be updated in the database via a `PUT` request to the `/api/v1/rooms/:id` endpoint. The request must contain a room name and type, and may contain an optional description. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "New Bedroom",
  "type": "Bedroom",
  "description": "The new bedroom",
}
```

If the request is successful, the application will return the created room object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 4,
    "name": "New Bedroom",
    "type": "Bedroom",
    "description": "The new bedroom"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.


#### Meal Food Deletion
To delete a food item on a meal, a successful `DELETE` request to the endpoint `/api/v1/meals/:meal_id/foods/:food_id` will delete the applicable MealFood record in the database and return a status code of `204`. An unsuccessful request will return the following:
``` HTTP
status: 404
body:
{
    "error": "Request does not match any records."
}
```

### Recipe Endpoints
#### Recipe Index

All recipe items currently saved in the database can be retrieved via a `GET` request to the `/api/v1/recipes` endpoint.

If the request is successful, the application will return an array containing recipe objects, along with a status code of 200.

``` HTTP
status: 200
body:

[
  {
    "id": 1,
    "name": "recipe name",
    "calories": 100,
    "url": "example_url"
  },
  {...}
]
```

#### Recipe Show

An individual recipe item currently saved in the database can be retrieved via a `GET` request to the `/api/v1/recipes/:id` endpoint.

If the request is successful, the application will return the requested recipe object, along with a status code of 200.

``` HTTP
status: 200
body:

{
  "id": 1,
  "name": "recipe name",
  "calories": 100,
  "url": "example_url"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

``` HTTP
status: 400
body:

{
  "error": "Requested recipe could not be found."
}
```

#### Recipe Deletion
A recipe can be deleted from the database via a `DELETE` request to `/api/v1/recipes/:id`, utilizing the `id` of an existing recipe in the database. A successful response will return a `204` status code.

A unsuccessful response due to an `id` not found in the database will return:
 ``` HTTP
 status: 404
 body:
 {"error": "The requested recipe could not be found and was therefore not deleted."}
 ```

#### Recipe Search
A list of recipes can be retrieved (utilizing a [recipe search microservice](https://github.com/Mackenzie-Frey/recipe_service)) for use in meal recipe creation via a `GET` request to `/api/v1/search/recipes?mealType=MEALTYPE&query=QUERY`. Query parameter values of `mealType` and `query` must be provided as follows:

```
MEALTYPE = "boring" (regular recipes), "bang-for-your-buck" (recipes optimaized for shortest cook time and maximum claories, or "heart-attack" (maximum calories, minimum health factor).
QUERY = meal search query, i.e. "chicken", "pasta", or "berries"
```

A successful response will return a `200` status code and an array of 10 collections of recipe information:

```
[
    {
        "id": 162,
        "name": "Neiman Marcus Cafe Chicken Tortilla Soup Recipe",
        "url": "http://recipeofhealth.com/recipe/neiman-marcus-cafe-chicken-tortilla-soup-220132rb",
        "yield": "1",
        "calories": 6224,
        "image": "https://www.edamam.com/web-img/cb6/cb6687b4785e5a1da2e65e6b7a010bb5.jpg",
        "totalTime": "1",
        "updatedAt": "2019-05-14T22:22:23.238Z",
        "createdAt": "2019-05-14T22:22:23.238Z"
    },{...}
]
```

A unsuccessful response will return a `404` error and a message:
 ``` HTTP
 status: 404
 body:
 {"error": "Missing mealType and/or query."}
 ```
## Tools
* Postman
* dotenv
* node-fetch
* pryjs
* babel-jest
* nodemon
* scriptjs
* shelljs
* supertest
* beautify
* hat
* nyc
* bcrypt
* Circle CI


## How to Contribute

##### Contributing Code:
1. Fork the project.
2. Write a failing test.
3. Commit that failing tests.
4. Commit changes that fix the tests.
4. Submit a pull request detailing the change that was made.

##### Submitting a Bug:
1. Search the existing [issues](https://github.com/nagerz/quantified_self/issues).
2. Create a new issue if applicable, or contribute to an existing issue.

### Related Links:
###### * [**_Agile Project Board_**](https://github.com/nagerz/quantified_self/projects/1)
###### * [**_Project Specifications_**](http://backend.turing.io/module4/projects/quantified_self/qs_server_side)
###### * [**_Project Rubric_**](http://backend.turing.io/module4/projects/quantified_self/rubric)

### Authors
* [Mackenzie Frey](https://github.com/Mackenzie-Frey)
* [Zach Nager](https://github.com/nagerz)

### Special Recognition
* [Dione Wilson](https://github.com/dionew1)
* [Cory Westerfield](https://github.com/corywest)
