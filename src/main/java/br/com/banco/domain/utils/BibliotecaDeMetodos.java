package br.com.banco.domain.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BibliotecaDeMetodos {
    /**
     * @return um número flutuante aletório entre 0 e 100
     */
    public static double getAleatorio() {
        Random random = new Random();
        return random.nextDouble() * 100;
    }
}
