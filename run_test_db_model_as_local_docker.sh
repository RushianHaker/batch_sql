docker stop test-docker-model-db
docker rm test-docker-model-db
docker image rm -f test-docker-model-db

docker build -f Dockerfile_TestDbModelInDocker -t test-docker-model-db ./

#Database will be launched at port 7432 on localhost with user, password and scheme equals with postgres
docker run --rm --name test-docker-model-db -d -p 7432:5432 -v $HOME/pg-data:/var/lib/postgresql/data test-docker-model-db
