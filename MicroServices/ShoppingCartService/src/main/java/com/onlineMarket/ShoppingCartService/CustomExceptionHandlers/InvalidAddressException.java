package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(String message){
        super(message);
    }
}
