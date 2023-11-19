# Create REST APIs using Spring Boot 3.1.5, Reactive @WebFluxTest, and Reactive MongoDB.

Introduction:
Players is a microservice that serves the contents of players.


# Components Overview:

	This is a RESTful stateless application.
	The development is based on Spring Boot with Reactor, SpringBootTest with JUnit5,
	MongoDB, nio.AsynchronousFileChannel for reading the given Player.csv file, 
	Docker-compose and Git, and expose two REST endpoints.


# Functional Requirements:

	Retrieve the list of all players.
	Retrieve a single player by ID.


# Non-Functional Requirements:

	Read the Player.csv file and save in MongoDB;


# Components Overview:

PlayersApplication - The entry point of the microservice that has the main method to run.
PlayerController   - This class retrieves player data upon request and implements the PlayerInterface.
FileController     - This class saves data into db collection from Player.csv file.
PlayerService      - This class encapsulates the logic for retrieving player data from the repository, 
			handling potential errors, and providing the data in a reactive programming style using Mono and Flux from Project Reactor. 
   			It uses constructor injection to receive an instance of IMongoRepository during object initialization.
Player - This class represents a domain model containing various members and object values. 
	 It serves as a data aggregate, encapsulating player-related attributes and behaviors within the application.


# Endpoints:

GET http://localhost:8080//api/players
GET http://localhost:8080//api/players/{playerID}



