package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message) { super(message); }
}
