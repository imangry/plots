package com.qunar;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * Date: 18/01/22
 * User: lvshi
 */
public class KafkaQPS {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private static final LongAdder longAdder = new LongAdder();
    private static final Logger log = LoggerFactory.getLogger(KafkaQPS.class);


    public static void main(String[] args) {

        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            log.info("{} {}", System.currentTimeMillis(), longAdder.sumThenReset());
        }, 1, 1, TimeUnit.SECONDS);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "l-cooper1.wap.beta.cn0.qunar.com:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        for (int j = 0; j < 20; j++) {
            EXECUTOR.submit(() -> {
                KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
                ProducerRecord<String, String> record = new ProducerRecord<>("workerQueueH0-0", 0, "", "test");

                for (int i = 0; i < 100000000; i++) {
                    try {
                        producer.send(record).get();
                        longAdder.increment();

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public static final String str = "\"java.lang.RuntimeException: Unable to start activity ComponentInfo{com.Qunar/com.mqunar.atom.hotel.ui.activity.SchemeControlActivity}: android.view.InflateException: Binary XML file line #275: Binary XML file line #9: Error inflating class com.mqunar.atom.hotel.view.AtomCloseSelfWebView\n" +
            "\" \n";
}
