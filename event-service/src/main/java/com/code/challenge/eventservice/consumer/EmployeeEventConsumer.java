package com.code.challenge.eventservice.consumer;

import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.service.EmployeeEventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ragab Belal
 */
@Component
public class EmployeeEventConsumer {

    private final EmployeeEventService employeeEventService;

    @Autowired
    public EmployeeEventConsumer(EmployeeEventService employeeEventService) {
        this.employeeEventService = employeeEventService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(EmployeeEventDTO employeeEventDTO) throws Exception{
        employeeEventService.saveEmployeeEvent(employeeEventDTO);

    }
}
