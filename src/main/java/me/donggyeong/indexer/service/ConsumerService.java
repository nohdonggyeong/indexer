package me.donggyeong.indexer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	@KafkaListener(topics = "topic", groupId = "group_1")
	public void listener(Object data) {
		System.out.println(data);
	}
}
