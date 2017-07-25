package Kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

/**
 * Created by zhangguijiang on 2017/5/10.
 */
public class DemoProducer {

    static private final String TOPIC = "topic4";
    static private final String BROKER_LIST = "10.91.38.30:9092";

    public static void main(String[] args) throws Exception {
        Producer<String, String> producer = initProducer();
        sendOne(producer, TOPIC);
    }

    private static Producer<String, String> initProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", BROKER_LIST);
        //props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.class", StringEncoder.class.getName());
        props.put("partitioner.class", HashPartitioner.class.getName());
        //props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        // props.put("compression.codec", "0");
        props.put("producer.type", "sync");
        props.put("batch.num.messages", "1");
        props.put("queue.buffer.max.ms", "1000");
        props.put("queue.buffering.max.messages", "1000");
        props.put("queue.enqueue.timeout.ms", "20000");

        ProducerConfig config = new ProducerConfig(props);
        return new Producer<String, String>(config);
    }

    private static void sendOne(Producer<String, String> producer, String topic) throws InterruptedException {
        KeyedMessage<String, String> message1 = new KeyedMessage<String, String>(topic, "1", "test 31");
        producer.send(message1);
        System.out.println(message1.toString());
        Thread.sleep(500);
        KeyedMessage<String, String> message2 = new KeyedMessage<String, String>(topic, "2", "test 32");
        producer.send(message2);
        Thread.sleep(500);
        KeyedMessage<String, String> message3 = new KeyedMessage<String, String>(topic, "3", "test 33");
        producer.send(message3);
        Thread.sleep(500);
        KeyedMessage<String, String> message4 = new KeyedMessage<String, String>(topic, "4", "test 34");
        producer.send(message4);
        Thread.sleep(500);
        KeyedMessage<String, String> message5 = new KeyedMessage<String, String>(topic, "5", "test 35");
        producer.send(message5);
        Thread.sleep(500);
        producer.close();
    }
}
