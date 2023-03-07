# Build the Docker images
build:
	docker-compose build

# Shortcut for building, starting, and tailing logs
start:
	docker-compose up --build -d

# Stop the containers
stop:
	docker-compose down
