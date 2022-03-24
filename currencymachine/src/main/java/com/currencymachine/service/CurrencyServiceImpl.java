package com.currencymachine.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.currencymachine.dto.CurrencyHolder;
import com.currencymachine.exception.InSufficientFundsException;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	@Autowired
	private CurrencyHolder currencyHolder;
	@Autowired
	@Qualifier("currencyServiceLock")
	private Lock lock;

	@Override
	public Map<Double, Integer> change(int bill) {
		SortedSet<Double> denominations = null;
		int coinsRequired = 0;
		int coinsAvailable = 0;
		Map<Double, Integer> changeMap = null;
		double balance = 0;

		balance = currencyHolder.getWalletBalance();
		if (balance < bill) {
			throw new InSufficientFundsException("amount is not available in wallet");
		}
		changeMap = new HashMap<Double, Integer>();
		denominations = new TreeSet<Double>((Double o1, Double o2) -> {
			if (o1 < o2) {
				return 1;
			} else if (o2 > o1) {
				return -1;
			}
			return 0;
		});
		lock.lock();
		denominations.addAll(currencyHolder.getWallet().keySet());

		for (double denomination : denominations) {
			if (bill == 0) {
				break;
			}
			coinsRequired = (int) (bill / denomination);
			coinsAvailable = currencyHolder.getAvailableCoins(denomination);
			if (coinsRequired > 0) {
				if (coinsAvailable < coinsRequired) {
					bill = (bill - (int) (denomination * coinsAvailable));
					currencyHolder.updateWallet(denomination, 0);
					changeMap.put(denomination, coinsAvailable);
				} else {
					bill = (bill - (int) (denomination * coinsRequired));
					currencyHolder.updateWallet(denomination, (coinsAvailable - coinsRequired));
					changeMap.put(denomination, coinsRequired);
				}
			}
		}
		lock.unlock();
		return changeMap;
	}

}
