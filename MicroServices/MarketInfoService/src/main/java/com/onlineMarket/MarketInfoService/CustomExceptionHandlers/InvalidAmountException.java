package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) { super(message); }
}
