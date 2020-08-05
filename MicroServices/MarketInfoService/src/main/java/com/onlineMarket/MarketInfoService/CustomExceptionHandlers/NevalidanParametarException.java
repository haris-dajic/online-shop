package com.onlineMarket.MarketInfoService.CustomExceptionHandlers;

public class NevalidanParametarException extends Exception{
    public NevalidanParametarException(String message){
        super(message);
    }
}
