package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidVerificationCodeException extends Exception {
    public InvalidVerificationCodeException(String message) { super(message); }
}
