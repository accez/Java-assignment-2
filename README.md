# Java assignment for Noroff - Access and expose a database 

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
[![Docker Image CI](https://github.com/accez/Java-assignment-2/actions/workflows/docker-image.yml/badge.svg)](https://github.com/accez/Java-assignment-2/actions/workflows/docker-image.yml)
[![Deploy to heroku.](https://github.com/accez/Java-assignment-2/actions/workflows/heroku-deployment.yml/badge.svg)](https://github.com/accez/Java-assignment-2/actions/workflows/heroku-deployment.yml)
[![web](https://img.shields.io/static/v1?logo=heroku&message=Online&label=Heroku&color=430098)](https://spring-test-java-assignment.herokuapp.com/)
[![container](https://img.shields.io/static/v1?logo=docker&message=Registry&label=Docker&color=2496ED)](https://github.com/accez/Java-assignment-2/pkgs/container/springtest)






Java Spring Application that will show songs and manage customers.

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [API](#api)
- [Maintainers](#maintainers)
- [Contributing](#contributing)
- [License](#license)

## Background

### Goal: On completion of this assignment you will be able to:

- Configure REST endpoints with Spring.
- Create templated html pages with Thymeleaf.
- Publish Dockerized Java applications to Heroku.
- Perform SQL queries with JOIN, ORDERBY, and MAX.

Instructions: You are to create a Spring Boot Web API, add the
SQL Lite JDBC dependency to your project, and create classes to interact with the Chinook database.

The application consists of the following:
- The home page view, showing the 5 random artists, 5 random songs, and 5 random genres. And a search results page that will show the query the user has made. This is all built using Thymeleaf.
- An OpenAPI configuration endpoint at ``/v3/api-docs``
- The Swagger documentation UI at ``/swagger-ui.html``
- API endpoint for Customer and Music controllers.

The CI-pipeline will:

- Package the built application into a Docker image, proceeding if successful.
- Push the Docker image to the local registry on Github.
- Trigger the build pipeline on Heroku to pull the latest image and replace the current running dyno.


  [Application Demo](https://spring-test-java-assignment.herokuapp.com/)

## Install
Clone the git repository.

Gradle will automatically initialize itself and download necessary dependencies the first time the wrapper is run. No explicit installation necessary.


## Usage
For Linux/macOS users, open a terminal and run:
```
./gradlew bootRun
```
For Windows users, use ``/gradlew.bat`` instead of ``gradlew`` in PowerShell.

## API
Customer controller
```
GET api/customers
GET api/customers/:customerId
GET api/customers/name
GET api/customers/:customerId/popular/genre
GET api/customers/totalPerCountry
GET api/customers/order/highestSpender
GET GET api/customers/limitAndOffset
POST api/customers/update/:customerId
POST api/customers/add
```

Music controller
```
GET track/:name
GET track/random
GET genre/random
GET artist/random
```

## Maintainers

[Simon Palmgren Arvidsson (@accez)](https://github.com/accez)

[Andreas Hellstr??m(@AHells)](https://github.com/AHells)

## Contributing



Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

MIT ?? 2022 Simon Palmgren Arvidsson
