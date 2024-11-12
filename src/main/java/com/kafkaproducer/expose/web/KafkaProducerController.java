package com.kafkaproducer.expose.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kafkaproducer.kafka.KafkaProducer;
import com.kafkaproducer.model.api.Client;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka-producer")
@Slf4j
public class KafkaProducerController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostMapping(value = "/sendMessage", produces = {"application/json"})
    public String sendMessage(@RequestBody String documentNumber) {
		log.info("Numero de documento = "+ documentNumber);
        this.kafkaProducer.sendMessage(documentNumber);
		return "Se envio el numero de documento del cliente a procesar";
    }

	@PostMapping(value = "/sendClientMessage", produces = {"application/json"})
    public String sendClientMessage(@RequestBody Client client) {
		Gson gson = new GsonBuilder().create();
		String message = gson.toJson(client);
		log.info("message= "+ message);
        this.kafkaProducer.sendMessage(message);
		return "Se envio datos del cliente a procesar";
    }
}
