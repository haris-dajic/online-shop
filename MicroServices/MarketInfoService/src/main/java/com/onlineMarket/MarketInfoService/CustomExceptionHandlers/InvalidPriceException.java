package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) { super(message); }
}
