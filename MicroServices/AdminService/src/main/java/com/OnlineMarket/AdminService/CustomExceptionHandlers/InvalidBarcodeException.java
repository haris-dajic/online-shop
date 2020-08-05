package com.OnlineMarket.AdminService.CustomExceptionHandlers;

public class InvalidBarcodeException extends Exception {
    public InvalidBarcodeException(String message) { super(message); }
}
