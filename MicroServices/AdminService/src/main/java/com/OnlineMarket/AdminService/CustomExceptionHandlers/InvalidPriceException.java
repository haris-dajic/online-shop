package com.OnlineMarket.AdminService.CustomExceptionHandlers;

public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) { super(message); }
}
