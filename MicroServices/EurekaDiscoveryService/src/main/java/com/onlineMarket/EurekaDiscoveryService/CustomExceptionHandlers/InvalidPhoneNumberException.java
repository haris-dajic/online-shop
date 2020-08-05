package com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException(String message) { super(message); }
}
