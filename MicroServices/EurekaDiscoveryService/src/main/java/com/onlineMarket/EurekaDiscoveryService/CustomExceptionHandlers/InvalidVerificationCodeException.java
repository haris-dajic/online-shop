package com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers;

public class InvalidVerificationCodeException extends Exception {
    public InvalidVerificationCodeException(String message) { super(message); }
}
