/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.exceptions;

public class NotImplementationException extends Exception{

    public NotImplementationException() {
    }

    public NotImplementationException(String message) {
        super(message);
    }

    public NotImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementationException(Throwable cause) {
        super(cause);
    }

    public NotImplementationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
