package com.haefliger.cryptomensageria.service.impl;


import com.haefliger.cryptomensageria.service.StrategyMonitorService;
import com.haefliger.cryptomensageria.service.TelegramService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Author diego-haefliger
 * Date 13/06/25
 */

@Service
@AllArgsConstructor
@Slf4j
public class StrategyMonitorServiceImpl implements StrategyMonitorService {

    private final TelegramService telegramService;

    @KafkaListener(topics = "strategy", groupId = "price-monitor-group")
    public void onSendMessage(String message) {
        log.info("Received message: {}", message);
        telegramService.sendMessage(message);
    }

}
