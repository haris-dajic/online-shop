package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidBarcodeException extends Exception {
    public InvalidBarcodeException(String message) { super(message); }
}
