This project has two sections
# consuming external apis
- combining the responses of different external apis into one dto for controller api's 
# Rabbit mq
- Introduced 2 new endpoint
    1. /publish which publish message into queue
    2. /receive fetch all the messages from queue
  3. Queue name: my-queue, exchange: my-exchange, routing key:my-routing-key

# Docker
 This application is fully dockerized,</br>
  <b>prerequisite</b>- Docker should install 
- build the image, go to project root folder and run below command
```docker build -t external-api:latest .```
- To start the application on your local run
```docker-compose up```
- To stop the application on your local run
    ```docker-compose down```
          