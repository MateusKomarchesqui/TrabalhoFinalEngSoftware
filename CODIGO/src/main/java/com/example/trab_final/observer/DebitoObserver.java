package com.example.trab_final.observer;

import com.example.trab_final.service.DebitoService;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class DebitoObserver implements Observer { // inscrito
    private final DebitoService debitoService;

    public DebitoObserver(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @Override
    public void notificar(long matricula, Date data, float valorMulta) {
        debitoService.criarDebito(matricula, data, valorMulta);
    }
}