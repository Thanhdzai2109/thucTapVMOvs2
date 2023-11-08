package com.example.jwt.demo;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Bot getBot(@Value(value = "${simplebot.data.dir}") String path,
                      @Value(value = "${simplebot.name}") String botname) {
        Bot bot = new Bot(botname, path);
        return bot;
    }

    @Bean
    public Chat getChat(Bot bot) {
        Chat chatSession = new Chat(bot);
        return chatSession;
    }

}

