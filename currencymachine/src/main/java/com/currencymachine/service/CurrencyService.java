package com.currencymachine.service;

import java.util.Map;

public interface CurrencyService {
	public Map<Double, Integer> change(int bill);
}
