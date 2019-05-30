# README

## Description
HomeTracker is a web application designed to aid in tracking the production of home and other residential construction projects. The backend service enables reading and writing database access to a users projects, including rooms and materials used during the construction of those rooms. HomeTracker is a Java application utilizing Spring Boot, PostgreSQL, and GraphQL.

#### [**_View HomeTracker BE in Production_**](https://hometrackr.herokuapp.com/) </br>
#### [**_View the Full HomeTracker App_**](https://your-home.herokuapp.com/) </br>

### Schema

## Getting Started

To run HomeTracker on a local machine, you must have Java and Java SDK installed on your machine. You can check your system for both with the command :
```
$ java -version
```
If not present, download online.

Additionally, the Maven dependency manager and PostgreSQL are required. To install using homebrew:
```
$ brew install maven
$ brew install postgresql
```
Navigate to the directory in which you would like the project to be located in, clone the repository and navigate into the project file:
```
$ git clone git@github.com:nagerz/MyHome_BE.git
$ cd MyHome_BE
```
Check for an existing PostgresQL user named 'postgres' (exit postgres with '\q'):
```
$ psql
$ \du
```
If no 'postgres' user exists, run the command (from within psql):
```
$ /usr/local/opt/postgres/bin/createuser -s postgres
```
Once created, create a local development database (from within psql, ending semicolon is required):
```
$ CREATE DATABASE my_home_be_development;
```
This creates a local development database by the name of my_home_be_development and new postgres user by the name of postgres so that Spring can identify and access the database (these are set and can be altered in src/main/resources/application.properties).

Finally, install dependencies and build the app.
```
$ mvn install
```
Once this setup is complete, you can run the app locally with the command:
```
$ mvn spring-boot:run
```
And navigate to:
```
$ http://localhost:8080
```

## Running Tests

To run the test suite use the command:
```
$ mvn clean test
```
The status of all tests run will be displayed. A more thourough test coverage report can be opened using:
```
$ open target/site/jacoco/index.html
```
All tests are automatically run each time an update is made to the application and comitted to the remote repository. Upon a successful branch merge, updates are automatically pushed to production on Heroku. (Continuous integration through CircleCI).

## Available Endpoints
The application is hosted at `https://hometrackr.herokuapp.com` and provides both traditional RESTful API endpoints, as well as GraphQL endpoints.

## REST Endpoints
Traditional RESTful endpoints can be utilized as follows:

### User Endpoints <b>(NOT YET AVAILABLE)</b>
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
#### Project Show

An individual project currently saved in the database can be retrieved via a `GET` request to the `/api/v1/projects/:id` endpoint.

If the request is successful, the application will return the requested project object, along with a status code of 200.

``` HTTP
status: 200
body:

{
  "id": 1,
  "name": "House 1",
  "description": "Big, white house",
  "address": "123 Fake St.",
  "city": "Denver",
  "state": "CO",
  "zip_code": "80205",
  "rooms": [
    {
      "id": 1,
      "name": "Room 1",
      ...,
      "roomMaterials": [
        {
          "id": 1,
          "element_type": "Shower",
          "material": {
            "id": 1,
            ...,
          }
        },
        {
          "id": 2,
          "element_type": "Shower",
          "material": {
            "id": 2,
            ...,
          }
        }
      ]
    },
    {
      "id": 2,
      "name": "Room 2",
      ...
    }
  ]
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Project Index
All project items currently saved in the database can be retrieved via a `GET` request to the `/api/v1/projects` endpoint.

If the request is successful, the application will return an array containing project objects, along with a status code of 200.

``` HTTP
status: 200
body:

[
  {
    "id": 1,
    "name": "Project 1",
    "description": "Mayors House",
    "address": "123 Fake St.",
    "city": "Denver",
    "state": "CO",
    "zip_code": "80205",
    "rooms": [...]
  },
  {...}
]
```

#### Project Create

A new project item can be created and saved in the database via a `POST` request to the `/api/v1/projects` endpoint. The request must contain a project name, and may optionally contain an updated description, address, city, state, or zip code. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "New Project",
  "description": "The new project",
  "address": "123 Fake St.",
  "city": "Denver",
  "state": "CO",
  "zip_code": "80304"
}
```

If the request is successful, the application will return the created project object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 4,
    "name": "New Project",
    "description": "The new project",
    "address": "123 Fake St.",
    "city": "Denver",
    "state": "CO",
    "zip_code": "80304"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Project Update

An existing project's information can be updated in the database via a `PATCH` request to the `/api/v1/projects/:id` endpoint. The request may contain any field to be updated. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "Updated project",
  "description": "The updated project",
  "address": "new address"
}
```

If the request is successful, the application will return the updated project object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 1,
    "name": "Updated project",
    "description": "The updated project",
    "address": "new address"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 404.


#### Project Deletion
To delete an existing project, send a `DELETE` request to the endpoint `/api/v1/projects/:id`. A successful request will delete the applicable Room record in the database and return a status code of `204`. An unsuccessful request will return the following:
``` HTTP
status: 404
body:
{
    "error": "Request does not match any records."
}
```

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
  "description": "The new bedroom"
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

An existing room's information can be updated in the database via a `PATCH` request to the `/api/v1/rooms/:id` endpoint. The request may contain any field to be updated. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "Updated Bedroom",
  "type": "Bedroom",
  "description": "The updated bedroom",
}
```

If the request is successful, the application will return the updated room object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 4,
    "name": "Updated Bedroom",
    "type": "Bedroom",
    "description": "The updated bedroom"
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 404.


#### Room Deletion
To delete an existing room, send a `DELETE` request to the endpoint `/api/v1/rooms/:id`. A successful request will delete the applicable Room record in the database and return a status code of `204`. An unsuccessful request will return the following:
``` HTTP
status: 404
body:
{
    "error": "Request does not match any records."
}
```

### Material Endpoints
#### Material Show

An individual material currently saved in the database can be retrieved via a `GET` request to the `/api/v1/materials/:id` endpoint.

If the request is successful, the application will return the requested material object, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 1,
    "name": "Material name",
    "vendor": "Store 1",
    "brand": "Maker A",
    "model_number": "abc123",
    "manual_url": "example@url.com",
    "notes": "Material for the shower.",
    "quantity": 50,
    "unit_price": 60
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Material Index
All material items currently saved in the database can be retrieved via a `GET` request to the `/api/v1/materials` endpoint.

If the request is successful, the application will return an array containing material objects, along with a status code of 200.

``` HTTP
status: 200
body:

[
  {
    "id": 1,
    "name": "Material name",
    "vendor": "Store 1",
    "brand": "Maker A",
    "model_number": "abc123",
    "manual_url": "example@url.com",
    "notes": "Material for the shower.",
    "quantity": 50,
    "unit_price": 60
  },
  {...}
]
```

#### Material Create

A new material item can be created and saved in the database via a `POST` request to the `/api/v1/materials` endpoint. The request must contain a material name, and may contain optional fields of vendor, brand, model number, manual url location, notes, quantity, and unit price (in cents). Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "Material name",
  "vendor": "Store 1",
  "brand": "Maker A",
  "model_number": "abc123",
  "manual_url": "example@url.com",
  "notes": "Material for the shower.",
  "quantity": 50,
  "unit_price": 60
}
```

If the request is successful, the application will return the created material object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 1,
    "name": "Material name",
    "vendor": "Store 1",
    "brand": "Maker A",
    "model_number": "abc123",
    "manual_url": "example@url.com",
    "notes": "Material for the shower.",
    "quantity": 50,
    "unit_price": 60
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

#### Material Update

An existing material's information can be updated in the database via a `PATCH` request to the `/api/v1/materials/:id` endpoint. The request must contain any field ot be updated. Request should match the format provided below.

``` HTTP
POST /api/v1/homes
Content-Type: application/json
Accept: application/json

{
  "name": "Updated Material name",
  "vendor": "Store 1",
  "brand": "Maker A",
  "model_number": "abc123",
  "manual_url": "example@url.com",
  "notes": "Material for the shower.",
  "quantity": 50,
  "unit_price": 60
}
```

If the request is successful, the application will return the updated material object in the format below, along with a status code of 200.

``` HTTP
status: 200
body:
{
    "id": 1,
    "name": "Updated Material name",
    "vendor": "Store 1",
    "brand": "Maker A",
    "model_number": "abc123",
    "manual_url": "example@url.com",
    "notes": "Material for the shower.",
    "quantity": 50,
    "unit_price": 60
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 404.

#### Material Deletion
To delete an existing material, send a `DELETE` request to the endpoint `/api/v1/materials/:id`. A successful request will delete the applicable Material record in the database and return a status code of `204`. An unsuccessful request will return the following:
``` HTTP
status: 404
body:
{
    "error": "Request does not match any records."
}
```

## GraphQL Endpoints
Alternatively, the endpoints above as well as additional endpoints can be accessed with additional flexibility through GraphQL. These endpoints are all used via `POST` requests to a single `/api/v1/graphql` endpoint. They are distinguished by different query objects sent in the body of the POST request. Additionally, a graphQL interface can be used to aid in the formatting of custom requests. This interface can be found at the `/graphiql` endpoint. The available endpoints and their associated request structures are as follow:

### Project Endpoints
#### Project Show

An individual project currently saved in the database can be retrieved with a project query and by supplying an appropriate project id. Retrievable project fields include id, name, description, address, city, state, and zip code, as well as any associated rooms, and roomMaterials.

A successful request following the format below will return the requested project object, along with a status code of 200.
``` HTTP
POST /api/v1/graphql
Content-Type: application/json
Accept: application/json

{
  project (id: 1) {
    id
    name
    description
    address
    rooms {
      name
      type
      description
    }
  }
}
```

``` HTTP
status: 200
body:

{
  "data": {
    "project": {
      "id": 1,
      "name": "House 1",
      "description": "Big, white house",
      "address": "123 Fake St.",
      "rooms": [
        {
          "name": "Living Room 1",
          "type": "Living Room",
          "description": "Northeast living room"
        },
        {
          "name": "Room 2",
          "type": "Kitchen",
          "description": "Big Kitchen"
        }
      ]
    }
  }
}
```

In the event that the request is unsuccessful, the application will return an error message, along with a status code of 400.

## Tools
* Java
* Spring Boot
* GraphQL
* Maven
* JUnit
* PostgreSQL
* JPA/Hibernate
* Circle CI
* Heroku

## How to Contribute

##### Contributing Code:
1. Fork the project.
2. Write a failing test.
3. Commit that failing tests.
4. Commit changes that fix the tests.
4. Submit a pull request detailing the change that was made.

##### Submitting a Bug:
1. Search the existing backend [issues](https://github.com/easbell/Your-Home/issues).
2. Create a new issue if applicable, or contribute to an existing issue.

### Related Links:
###### * [**_Agile Project Board_**](https://github.com/easbell/Your-Home/projects/1)

### Authors
* [Zach Nager](https://github.com/nagerz)

### Special Recognition
* [Dione Wilson](https://github.com/dionew1)
* [Cory Westerfield](https://github.com/corywest)
