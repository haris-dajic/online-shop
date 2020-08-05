package com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(String message){
        super(message);
    }
}
