# kafka-producer-java-hello-world
A simple Java Kafka producer.

## Dependencies (`pom.xml`)

- [kafka-clients](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients/2.8.0)
- [slf4j-simple](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple/1.7.30)

```xml
        <!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-clients</artifactId>
            <version>2.8.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-simple -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.30</version>
            <!-- <scope>test</scope> -->
        </dependency>
```

## Code

```java
String server = "<you.server.ip.address>:9092";
String topic = "first_topic";
String message = "hello world!";

Properties properties = new Properties();
properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
ProducerRecord<String, String> record = new ProducerRecord<String,String>(topic, message);
producer.send(record);
producer.close();
```

## Test

Start a consumer, like:

```
bin/kafka-console-consumer.sh --bootstrap-server <you.server.ip.address>:9092 --topic first_topic
```

Run this app.