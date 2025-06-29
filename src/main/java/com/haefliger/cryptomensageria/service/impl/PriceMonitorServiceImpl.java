package com.haefliger.cryptomensageria.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haefliger.cryptomensageria.service.PriceMonitorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Author diego-haefliger
 * Date 13/06/25
 */

@Service
@AllArgsConstructor
@Slf4j
public class PriceMonitorServiceImpl implements PriceMonitorService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "strategy", groupId = "price-monitor-group")
    public void onSendMessage(String message) {
        log.info("Received message: {}", message);
    }

}
