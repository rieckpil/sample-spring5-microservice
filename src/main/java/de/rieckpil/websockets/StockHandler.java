package de.rieckpil.websockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StockHandler {

	private final TaskScheduler taskScheduler;
	private final SimpMessagingTemplate simpMessagingTemplate;

	private List<Stock> stocks = new ArrayList<Stock>();
	private Random random = new Random(System.currentTimeMillis());

	public StockHandler(TaskScheduler taskScheduler, SimpMessagingTemplate simpMessagingTemplate) {
		this.taskScheduler = taskScheduler;
		this.simpMessagingTemplate = simpMessagingTemplate;

		stocks.add(new Stock("SHA", 13.0));
		stocks.add(new Stock("ADI", 131.0));
		stocks.add(new Stock("FRA", 42.5));
		stocks.add(new Stock("FLA", 3.0));

	}

	private void updateAndBroadcastPrices() {
		for (Stock stock : stocks) {
			stock.setPrice(stock.getPrice() + (getUpdatedStockPrice() * stock.getPrice()));
			stock.setTimestamp(System.currentTimeMillis());
		}

		simpMessagingTemplate.convertAndSend("/topic/price", stocks);
	}

	private double getUpdatedStockPrice() {
		double priceChange = random.nextDouble() * 5.0;

		if (random.nextInt(2) == 1) {
			priceChange = -priceChange;
		}

		return priceChange / 100.0;
	}

	@MessageMapping("/addStock")
	public void sayHello(Stock stock) {

		if (stock.getCode() == null || stock.getCode().isEmpty() || stock.getPrice() == 0.0) {
			log.warn(String.format("Unable to add stock: %s", stock.toString()));
		} else {
			stocks.add(stock);
			updateAndBroadcastPrices();
		}

	}
	
	@MessageMapping("/application")
	public String testAppEndpoint(Message msg) {
		System.out.println(msg);
		return "helloWorld!";
	}

	@SubscribeMapping("/metadata/{widgetData}/{machineName}")
	public ObjectNode onSubscribeMetaData(@DestinationVariable("widgetData") String widgetData,
			@DestinationVariable("machineName") String machineName) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode userInformation = mapper.createObjectNode();
		userInformation.put("userId", widgetData + "/" + machineName);
		return userInformation;
	}

	@PostConstruct
	private void broadcastPeriodically() {
		taskScheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				updateAndBroadcastPrices();
			}
		}, 1000);
	}

}
