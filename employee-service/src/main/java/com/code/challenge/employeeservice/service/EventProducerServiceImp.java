package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.dto.EmployeeEventDTO;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Ragab Belal
 */
@Service
public class EventProducerServiceImp implements EventProducerService {
    private final static Logger logger = LoggerFactory.getLogger(EventProducerServiceImp.class);

    private final AmqpTemplate amqpTemplate;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Autowired
    public EventProducerServiceImp(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * produce and send employee action
     * @param employeeEventDTO
     */
    @Override
    public void produceEventAction(EmployeeEventDTO employeeEventDTO) {
        logger.info().message("product event action....").log();
        amqpTemplate.convertAndSend(queueName, employeeEventDTO);
    }
}
