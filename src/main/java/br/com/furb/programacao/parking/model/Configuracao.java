/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import java.io.Serializable;

/**
 *
 * @author Diovani
 */
public class Configuracao extends Entidade implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1089720707928394570L;
	private Boolean validarDocumento;
    private static final Configuracao CONFIGURACAO = new Configuracao();
    
    private Configuracao(){
        super();
    }
    
    public static Configuracao getInstance(){
        return CONFIGURACAO;
    }

    public Boolean getValidarDocumento() {
        return validarDocumento;
    }

    public void setValidarDocumento(Boolean validarDocumento) {
        this.validarDocumento = validarDocumento;
    }
}
