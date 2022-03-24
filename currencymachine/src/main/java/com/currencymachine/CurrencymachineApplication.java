package com.currencymachine;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencymachineApplication {

	@Bean(name = "currencyServiceLock")
	public Lock reentrantLock() {
		return new ReentrantLock();
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencymachineApplication.class, args);
		/*
		 * ApplicationContext context = null; try { context =
		 * SpringApplication.run(CurrencymachineApplication.class, args); CurrencyHolder
		 * ch = context.getBean(CurrencyHolder.class);
		 * 
		 * CurrencyService currencyService = context.getBean(CurrencyServiceImpl.class);
		 * Map<Double, Integer> changeMap = currencyService.change(40);
		 * 
		 * System.out.println("final coins : " + ch.getWallet());
		 * System.out.println("change Map : " + changeMap);
		 * 
		 * changeMap = currencyService.change(1);
		 * 
		 * System.out.println("final coins : " + ch.getWallet());
		 * System.out.println("change Map : " + changeMap); } finally {
		 * SpringApplication.exit(context); }
		 */
	}

}
