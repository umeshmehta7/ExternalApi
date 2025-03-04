This project has two sections
# consuming external apis
- combining the responses of different external apis into one dto for controller api's 
# Rabbit mq
- Introduced 2 new endpoint
    1. /publish which publish message into queue
    2. /receive fetch all the messages from queue
  3. Queue name: my-queue, exchange: my-exchange, routing key:my-routing-key
        