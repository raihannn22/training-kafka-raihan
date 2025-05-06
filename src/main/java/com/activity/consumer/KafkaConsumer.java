package com.activity.consumer;

import com.activity.dto.User;
import com.activity.entity.UsersEntity;
import com.activity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "${topic.name}")
    public void consume(User user) {
        System.out.println("Received user from Avro: " + user.toString());
            UsersEntity entity = new UsersEntity();
            if (userRepository.existsById(user.getId())) {
                entity = userRepository.findById(user.getId()).orElse(null);
            }
            entity.setId(user.getId());
            entity.setName(user.getName().toString());
            entity.setEmail(user.getEmail().toString());

            UsersEntity saved = userRepository.save(entity);
            System.out.println("User saved: " + saved);

    }
}
