package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) { super(message); }
}
