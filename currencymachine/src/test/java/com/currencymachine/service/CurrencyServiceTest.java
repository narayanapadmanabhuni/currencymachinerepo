package com.currencymachine.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.currencymachine.dto.CurrencyHolder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CurrencyServiceTest {
	@MockBean
	private CurrencyHolder currencyHolder;

	@Autowired
	@InjectMocks
	private CurrencyServiceImpl currencyService;

	@BeforeEach
	public void setUp() {
		Map<Double, Integer> dataMap = new HashMap<Double, Integer>();
		dataMap.put(0.25, 100);
		dataMap.put(0.10, 100);
		dataMap.put(0.05, 100);
		dataMap.put(0.01, 100);

		when(currencyHolder.getWalletBalance()).thenReturn(41.0);
		when(currencyHolder.getWallet()).thenReturn(dataMap);
		when(currencyHolder.getAvailableCoins(0.25)).thenReturn(100);
		when(currencyHolder.getAvailableCoins(0.10)).thenReturn(100);
		when(currencyHolder.getAvailableCoins(0.05)).thenReturn(100);
		when(currencyHolder.getAvailableCoins(0.01)).thenReturn(100);
	}

	@Test
	public void testChange() {
		int bill = 28;
		Map<Double, Integer> expectedChange = null;
		Map<Double, Integer> actualChange = null;

		expectedChange = new HashMap<Double, Integer>();
		expectedChange.put(0.25, 100);
		expectedChange.put(0.01, 100);

		actualChange = currencyService.change(bill);
		System.out.println(actualChange);
		assertEquals(expectedChange, is(actualChange));
	}
}
