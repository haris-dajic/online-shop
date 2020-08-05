package com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String message) { super(message); }
}
