# Test REST APIs using Spring Boot 3.1.5, Reactive @WebFluxTest, WebTestClient, and Reactive @DataMongoTest.


# Introduction:
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

GET http://localhost:8080/api/read
GET http://localhost:8080//api/players
GET http://localhost:8080//api/players/{playerID}

# MongoDB:
I am using mongosh to reach a collection via the terminal.

`use admin
admin> db.auth("umitay","secret");

use players
players> db.player.find().pretty()
[
  {
    _id: ObjectId("654c9d5926c40a1567e81781"),
    playerID: 'abbated01',
    bbrefID: 'abbated01',
    retroID: 'abbae101',
    birthAddress: { country: 'USA', state: 'PA', city: 'Latrobe' },
    birthDate: { year: '1877', month: '1877', day: '15' },
    fullName: { name: 'Ed', nameGiven: 'Edward James', nameLast: 'Abbaticchio' },
    _class: 'com.intuit.players.model.Player'
  },
  {
    _id: ObjectId("654c9d5926c40a1567e81782"),
    playerID: 'abbeybe01',
    bbrefID: 'abbeybe01',
    retroID: 'abbeb101',
    birthAddress: { country: 'USA', state: 'VT', city: 'Essex' },
    birthDate: { year: '1869', month: '1869', day: '11' },
    fullName: { name: 'Bert', nameGiven: 'Bert Wood', nameLast: 'Abbey' },
    _class: 'com.intuit.players.model.Player'
  }
  ......
]`


# ContextLoads Tests:

`Assertions.assertNotNull(reactiveRepository);
Assertions.assertNotNull(playerController);
Assertions.assertNotNull(playerService);
`

# Run the app:

`cd  players

docker-compose up -d 

docker run --hostname=c635704d8c36 --env=MONGO_PACKAGE=mongodb-org --env=MONGO_MAJOR=7.0 --env=MONGO_INITDB_DATABASE=players --env=MONGO_INITDB_ROOT_USERNAME=umitay --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=JSYAML_VERSION=3.13.1 --env=HOME=/data/db --env=MONGO_INITDB_ROOT_PASSWORD=secret --env=GOSU_VERSION=1.16 --env=MONGO_REPO=repo.mongodb.org --env=MONGO_VERSION=7.0.2 --volume=/data/configdb --volume=/data/db --network=players_default -p 27017:27017 --restart=no --label='com.docker.compose.config-hash=0fd79e4ac1eb0b734965590ea6ff93ff53904977cd24e6366b1893fb043280a7' --label='com.docker.compose.container-number=1' --label='com.docker.compose.depends_on=' --label='com.docker.compose.image=sha256:8b10e7ef02081409e232661b3a0be9178cd8af9702c52f02cc3e746ba819a7ca' --label='com.docker.compose.oneoff=False' --label='com.docker.compose.project=players' --label='com.docker.compose.project.config_files=/Users/ut/Desktop/projects/Players/compose.yaml' --label='com.docker.compose.project.working_dir=/Users/ut/Desktop/projects/Players' --label='com.docker.compose.service=mongodb' --label='com.docker.compose.version=2.20.0' --label='org.opencontainers.image.ref.name=ubuntu' --label='org.opencontainers.image.version=22.04' --runtime=runc -d mongo:latest
`
Or run it using the docker-compose.yaml file located in the project's root directory.

