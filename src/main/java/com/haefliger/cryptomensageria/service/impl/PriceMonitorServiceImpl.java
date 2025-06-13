package com.haefliger.cryptomensageria.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haefliger.cryptomensageria.model.Alerta;
import com.haefliger.cryptomensageria.model.Estrategia;
import com.haefliger.cryptomensageria.model.PriceUpdate;
import com.haefliger.cryptomensageria.service.PriceMonitorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author diego-haefliger
 * Date 6/13/25
 */

@Service
@AllArgsConstructor
@Slf4j
public class PriceMonitorServiceImpl implements PriceMonitorService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<Long, Estrategia> estrategias = new ConcurrentHashMap<>();

    @KafkaListener(topics = "strategy-updated", groupId = "price-monitor-group")
    public void onStrategyUpdated(String message) throws Exception {
        Estrategia estrategia = objectMapper.readValue(message, Estrategia.class);
        estrategias.put(estrategia.getId(), estrategia);
        System.out.println("Estratégia atualizada: " + estrategia);
    }

    @KafkaListener(topics = "price-updated", groupId = "price-monitor-group")
    public void onPriceUpdated(String message) throws Exception {
        PriceUpdate priceUpdate = objectMapper.readValue(message, PriceUpdate.class);
        for (Estrategia estrategia : estrategias.values()) {
            if (priceMatch(estrategia, priceUpdate)) {
                Alerta alerta = new Alerta();
                alerta.setEstrategiaId(estrategia.getId());
                alerta.setAtivo(estrategia.getAtivo());
                alerta.setPrecoAtual(priceUpdate.getPreco());
                alerta.setMensagem("Alvo atingido para " + estrategia.getAtivo());
                String alertaJson = objectMapper.writeValueAsString(alerta);
                kafkaTemplate.send("alert", alertaJson);
                System.out.println("Alerta emitido: " + alertaJson);
            }
        }
    }

    private boolean priceMatch(Estrategia estrategia, PriceUpdate priceUpdate) {
        if (!estrategia.getAtivo().equals(priceUpdate.getAtivo())) {
            return false;
        }
//        if ("MAIOR_QUE".equals(estrategia.getTipo())) {
//            return priceUpdate.getPreco() > estrategia.getPrecoAlvo();
//        } else if ("MENOR_QUE".equals(estrategia.getTipo())) {
//            return priceUpdate.getPreco() < estrategia.getPrecoAlvo();
//        }
        return false;
    }
}
