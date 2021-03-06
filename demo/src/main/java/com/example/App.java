package com.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Kafka Simple Producer");

        // create Producer properties
        // https://kafka.apache.org/documentation/#producerconfigs

        String server = "172.20.229.109:9092";
        // String server = "172.25.145.150:9092";
        String topic = "first_topic";
        String message = "hello world!";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create a safe producer
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        // the next configs are automatically set
        // ProducerConfig.ACKS_CONFIG = "all" or "-1"
        // ProducerConfig.RETRIES_CONFIG  = Integer.toString(Integer.MAX_VALUE)
        // ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = "5"

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // create a producer record
        ProducerRecord<String, String> record = new ProducerRecord<String,String>(topic, message);

        // send data async
        producer.send(record);

        // producer.flush(); // just flush, or
        producer.close(); // flush and close
    }
}
