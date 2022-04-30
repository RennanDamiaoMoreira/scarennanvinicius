package br.com.nanner.gadoleiteiro.exception;

public class PassInvalidExpired extends Throwable {
    public PassInvalidExpired() {
        super("Senha inválida");
    }
}
