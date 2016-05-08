package com.kaishengit.exception;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ServiceException extends RuntimeException{
    public ServiceException(){};

    public ServiceException(Throwable th){super(th);}

    public ServiceException(String message){super(message);}

    public ServiceException(String message,Throwable th){super(message,th);}
}
