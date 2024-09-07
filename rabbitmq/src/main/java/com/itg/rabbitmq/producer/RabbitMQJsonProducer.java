package com.itg.rabbitmq.producer;

import com.itg.rabbitmq.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingJsonKey;


    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    //@Autowired  //RabbitTemplate nesne oluştur
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //constructor injection
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Product product){
        LOGGER.info(String.format("Json message sent -> %s", product.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, product);
    }

}