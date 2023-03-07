# Define variables
APP_NAME=myapp
DOCKER_COMPOSE_FILE=docker-compose.yaml

# Build the Docker images
build:
	docker-compose -f $(DOCKER_COMPOSE_FILE) build

# Start the containers
up:
	docker-compose -f $(DOCKER_COMPOSE_FILE) up -d

# Stop the containers
down:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down

# Restart the containers
restart:
	docker-compose -f $(DOCKER_COMPOSE_FILE) restart

# View the logs
logs:
	docker-compose -f $(DOCKER_COMPOSE_FILE) logs -f

# Run tests inside the container
test:
	docker-compose -f $(DOCKER_COMPOSE_FILE) run --rm app mvn test

# Clean up Docker resources
clean:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down --rmi all --remove-orphans
	docker volume prune -f

# Shortcut for building, starting, and tailing logs
start: build up logs

# Shortcut for stopping the containers and cleaning up Docker resources
stop: down clean
