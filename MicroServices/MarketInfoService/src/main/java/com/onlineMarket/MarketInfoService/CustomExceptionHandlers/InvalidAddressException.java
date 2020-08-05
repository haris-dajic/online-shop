package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class InvalidAddressException extends Exception {
    public InvalidAddressException(String message) { super(message); }
}
