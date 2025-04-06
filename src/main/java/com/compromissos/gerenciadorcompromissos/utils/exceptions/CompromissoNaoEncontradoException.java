package com.compromissos.gerenciadorcompromissos.utils.exceptions;

public class CompromissoNaoEncontradoException extends RuntimeException {

    public CompromissoNaoEncontradoException(String compromissoId) {
        super("Compromisso de ID: " + compromissoId + " não localizado.");
    }
}