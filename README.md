# CryptoMensageria


## Descrição

CryptoMensageria é um serviço de mensageria desenvolvido em Java, utilizando Spring Boot, que integra Kafka e Telegram. Seu propósito é monitorar mensagens publicadas em um tópico Kafka (`strategy`) e encaminhá-las automaticamente para um chat do Telegram via bot, permitindo o envio de notificações em tempo real.

## Funcionalidades

- **Escuta de mensagens Kafka:** O serviço consome mensagens do tópico `strategy` utilizando um consumidor Kafka.
- **Envio automatizado para Telegram:** Todas as mensagens recebidas são enviadas para um chat do Telegram através de um bot configurado no projeto.
- **Notificações em tempo real:** Ideal para cenários onde atualizações, alertas ou eventos precisam ser rapidamente comunicados (ex: estratégias financeiras, alertas de sistemas).

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Apache Kafka
- Telegram Bots API

## Como funciona

1. Mensagens são publicadas no tópico Kafka `strategy`.
2. O serviço `CryptoMensageria` consome estas mensagens.
3. Cada mensagem é encaminhada para o Telegram, utilizando os dados de autenticação do bot definidos nas configurações.

## Possíveis usos

- Notificações de operações ou estratégias financeiras.
- Alertas automatizados de sistemas.
- Qualquer cenário que exija integração entre fluxos de eventos Kafka e alertas via Telegram.

---

Desenvolvido por Diego Haefliger.
