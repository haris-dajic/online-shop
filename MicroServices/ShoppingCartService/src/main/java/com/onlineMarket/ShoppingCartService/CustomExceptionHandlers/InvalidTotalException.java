package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidTotalException extends Exception{
    public InvalidTotalException(String message){
        super(message);
    }
}
