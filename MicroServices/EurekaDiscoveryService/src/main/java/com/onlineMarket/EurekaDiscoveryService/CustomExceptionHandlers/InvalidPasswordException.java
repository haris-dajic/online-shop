package com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message) { super(message); }
}
