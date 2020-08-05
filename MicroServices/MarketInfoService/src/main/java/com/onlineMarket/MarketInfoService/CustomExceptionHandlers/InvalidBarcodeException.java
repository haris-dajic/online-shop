package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class InvalidBarcodeException extends Exception {
    public InvalidBarcodeException(String message) { super(message); }
}
