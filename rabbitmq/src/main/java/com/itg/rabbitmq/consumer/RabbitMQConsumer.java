package com.itg.rabbitmq.consumer;

import com.itg.rabbitmq.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;




@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "itg_notification_new")
    public void consumeJsonMessage(Product product){
        LOGGER.info(String.format("Received JSON message -> %s", product.getId())+" " +product.getName()+" " +product.getDescription());


    }
}
