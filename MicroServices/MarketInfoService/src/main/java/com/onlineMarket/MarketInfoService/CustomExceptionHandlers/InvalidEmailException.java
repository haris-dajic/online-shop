package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) { super(message); }
}
