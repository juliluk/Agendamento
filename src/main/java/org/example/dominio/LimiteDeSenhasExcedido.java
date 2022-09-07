package org.example.dominio;

public class LimiteDeSenhasExcedido extends Exception {
    public LimiteDeSenhasExcedido(){
        super("Limite de Senhas Excedido");
    }
}
