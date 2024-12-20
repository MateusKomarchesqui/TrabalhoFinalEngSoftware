package com.example.trab_final.observer;

import java.util.Date;

public interface Observer {
    public void notificar(long matricula, Date data, float valorMulta);
}
