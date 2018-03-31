package de.rieckpil.listener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SimpleKafkaListener implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String, String> template;

	private final CountDownLatch latch = new CountDownLatch(3);

	@Override
	public void run(String... args) throws Exception {
		this.template.send("myTopic", "foo1");
		this.template.send("myTopic", "foo2");
		ListenableFuture<SendResult<String, String>> send = this.template.send("myTopic", "foo3");
		send.addCallback((success) -> {
			log.info(success.getProducerRecord().topic());
		}, (failure) -> {
			log.info(failure.getMessage());
		});
		latch.await(60, TimeUnit.SECONDS);
		log.info("All received");
	}

	@KafkaListener(topics = "myTopic")
	public void listen(ConsumerRecord<?, ?> cr) throws Exception {
		log.info(cr.toString());
		latch.countDown();
	}
}