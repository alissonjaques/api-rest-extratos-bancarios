package br.com.banco.utils;

import java.time.LocalDateTime;

public class BibliotecaDeMetodos {
    /**
     * Recebe uma string que representa uma data e retorna um objeto LocalDateTime correspondente
     * a essa string.
     * @param data uma data em texto a ser transformada em date time
     * @return uma instância da classe LocalDateTime correspondente à string passada como argumento
     * */
    public static LocalDateTime stringParaLocalDateTime(String data) {
        return LocalDateTime.parse(data);
    }
}
