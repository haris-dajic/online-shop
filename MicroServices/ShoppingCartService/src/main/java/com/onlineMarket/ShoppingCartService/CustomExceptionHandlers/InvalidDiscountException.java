package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidDiscountException extends Exception {
    public InvalidDiscountException(String message){
        super(message);
    }
}
