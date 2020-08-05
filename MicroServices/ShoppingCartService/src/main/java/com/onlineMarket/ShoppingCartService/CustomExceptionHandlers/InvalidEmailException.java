package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String message) { super(message); }
}
