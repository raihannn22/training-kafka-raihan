package com.activity.producer;

import com.activity.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducer {
    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, User> template;

        public void sendUser(User user) {
            template.send(topicName, UUID.randomUUID().toString(),user);
        }

}
