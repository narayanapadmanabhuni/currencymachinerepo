package com.currencymachine.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class CurrencyHolder {
	private Map<Double, Integer> wallet;

	public void updateWallet(double denomination, int coins) {
		wallet.put(denomination, coins);
	}

	public int getAvailableCoins(double denomination) {
		return wallet.get(denomination);
	}

	public double getWalletBalance() {
		double balance = 0;

		for (double denomination : wallet.keySet()) {
			balance += (denomination * wallet.get(denomination));
		}
		return balance;
	}

	public void setWallet(Map<Double, Integer> wallet) {
		this.wallet = wallet;
	}

	public Map<Double, Integer> getWallet() {
		return wallet;
	}

}
