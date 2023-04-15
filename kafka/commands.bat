rem create topic
docker exec -it kafka-broker kafka-topics.sh --create --bootstrap-server kafka-broker:9093 --topic quickstart-events 
rem create topic with partitions
docker exec -it kafka-broker kafka-topics.sh --create --bootstrap-server kafka-broker:9093 --partitions 3 --topic quickstart-events 
rem describe topic
docker exec -it kafka-broker kafka-topics.sh --describe --bootstrap-server kafka-broker:9093 --topic quickstart-events 
rem list of topics
docker exec -it kafka-broker kafka-topics.sh --bootstrap-server kafka-broker:9093 --list
rem produce messages
docker exec -it kafka-broker kafka-console-producer.sh --bootstrap-server kafka-broker:9093 --topic quickstart-events
rem consume messages 
docker exec -it kafka-broker kafka-console-consumer.sh --bootstrap-server kafka-broker:9093 --topic quickstart-events --from-beginning
rem delete topic
docker exec -it kafka-broker kafka-topics.sh --delete --bootstrap-server kafka-broker:9093 --topic quickstart-events