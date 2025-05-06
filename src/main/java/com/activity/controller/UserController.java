package com.activity.controller;

import com.activity.dto.User;
import com.activity.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private KafkaProducer producer;

    @PostMapping
    public String send(@RequestBody Map<String, String> req) {
        User user = new User(
                Long.parseLong(req.get("id")),
                req.get("name"),
                req.get("email")
        );
        producer.sendUser(user);
        return "Sent!";
    }

}
