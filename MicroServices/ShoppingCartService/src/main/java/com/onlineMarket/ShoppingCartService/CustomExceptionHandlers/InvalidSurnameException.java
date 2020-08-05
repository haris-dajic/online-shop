package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidSurnameException extends Exception {
    public InvalidSurnameException(String message) { super(message); }
}
