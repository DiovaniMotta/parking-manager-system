/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model.enumerator;

/**
 *
 * @author Diovani
 */
public enum Ativo {

    SIM("SIM"),
    NAO("NAO");
    
    private final String tipo;
    
    private Ativo(String nome){
        tipo = nome;
    }
}
