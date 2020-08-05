package com.onlineMarket.ShoppingCartService.CustomExceptionHandlers;

public class InvalidCategoryException extends Exception {
    public InvalidCategoryException(String message) { super(message); }
}
