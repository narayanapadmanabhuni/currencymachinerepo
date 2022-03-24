package com.currencymachine.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencymachine.dto.ErrorInfo;
import com.currencymachine.exception.InSufficientFundsException;
import com.currencymachine.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyApiService {
	@Autowired
	private CurrencyService currencyService;

	@PutMapping("/change/{bill}")
	public Map<Double, Integer> change(@PathVariable("bill") int bill) {
		return currencyService.change(bill);
	}

	@ExceptionHandler(InSufficientFundsException.class)
	public ResponseEntity<ErrorInfo> mapInSufficientFundsException(InSufficientFundsException e) {
		ErrorInfo errorInfo = null;
		errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(1000);
		errorInfo.setErrorMessage("insufficient funds");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
	}
}
