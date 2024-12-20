package com.example.trab_final.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.trab_final.observer.Observer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MultaService { // gerente do evento
    
    private static final float VALOR_MULTA_POR_DIA = 2.5f;
    private final List<Observer> observers = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(MultaService.class);

    public MultaService() {}

    public void registrarObserver(Observer observer) {
        observers.add(observer);
    }

    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers(long matricula, Date data, float valorMulta) {
        for (Observer observer : observers) {
            observer.notificar(matricula, data, valorMulta);
        }
    }

    public float calcularMulta(long matricula, Date data, int diasDeAtraso) {
        logger.info("Calculando multa para matricula: " + matricula + " com " + diasDeAtraso + " dias de atraso");
        logger.info("Notificando observers: {}", observers);
        float valorMulta = diasDeAtraso * VALOR_MULTA_POR_DIA;
        notificarObservers(matricula, data, valorMulta);
        return valorMulta;
    }
}
