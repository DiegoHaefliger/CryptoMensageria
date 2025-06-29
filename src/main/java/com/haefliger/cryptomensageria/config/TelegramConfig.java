package com.haefliger.cryptomensageria.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Author diego-haefliger
 * Date 29/06/25
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramConfig extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.username}")
    private String userName;

    @Value("${telegram.bot.chatId}")
    private Long chatId;

    private static final String PARSE = "HTML";

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = "No momento não há comandos disponíveis.";

            sendMessage(message);
        }
    }

    public void sendMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        sendMessage.setParseMode(PARSE);
        sendMessage.setDisableWebPagePreview(true);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Failed to send message via Telegram API", e);
        }
    }

}
