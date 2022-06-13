/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

import br.com.senac.entidade.Comportamento;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Vítor
 */
public class GeradorUtil {
    public static String gerarNumero(int qtde) {
        String numeroGerado = "";
        int indice;
        for (int i = 0; i < qtde; i++) {
            indice = (int) (Math.random() * 10);
            numeroGerado += indice;
        }
        return numeroGerado;
    }
    
    public static String gerarNome() {
        String[] nomes = {"Junior", "Marcos", "Ana", "Maria", "Silvio", "Suelen", "Joana", "Mateus",
            "Lúcio", "João", "Leandro", "Soeli"};
        int indice = (int) (Math.random() * nomes.length);
        return nomes[indice];
    }   
    
    public static String gerarAleatorio() {

        List<String> aleatorio = Arrays.asList("brasil", "argentina", "uruguai",
                "paraguai", "estados unidos", "canadá", "mexico", "bolivia",
                "peru", "chile", "Centro Histórico",
                "Ponta de Baixo", "Distrito Industrial", "Picadas do Sul",
                "Flor de Nápolis", "São Luiz", "Roçado", "Potecas", "Serraria","Santa carartina", "Parana", "São paulo",
                "Rio grande do sul", "Paraiba", "Pernanbuco", "Amazonia", "Acre",
                "Recife", "Rio de Janeiro");

        Collections.shuffle(aleatorio);

        return aleatorio.get(0);

    }
    
    public static String gerarTipo() {

        List<String> comportamento = Arrays.asList("Agressivo", "Normal", "Medroso", "Amigavel", "Agitado", "Atencioso", "Preguiçoso");

        Collections.shuffle(comportamento);

        return comportamento.get(0);

    }
    
    public static Comportamento gerarComportamento(){
        Comportamento comp = new Comportamento(gerarTipo(), gerarAleatorio(), false);
        return comp;
    }

}
