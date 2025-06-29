package com.haefliger.cryptomensageria.service.impl;


import com.haefliger.cryptomensageria.config.TelegramConfig;
import com.haefliger.cryptomensageria.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author diego-haefliger
 * Date 29/06/25
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class TelegramServiceImpl implements TelegramService {

    private final TelegramConfig telegramConfig;

    @Override
    public void sendMessage(String message) {
        telegramConfig.sendMessage(message);
    }

}
