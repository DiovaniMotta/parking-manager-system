/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Diovani
 */
public class ClienteJuridico extends Cliente{

 
    /**
	 * 
	 */
	private static final long serialVersionUID = 648362989506747872L;
	private String cnpj;

    public ClienteJuridico() {
    }

    public ClienteJuridico(String ID, Ativo ativo) {
        super(ID, ativo);
    }

    public ClienteJuridico(String nome, String endereco, String cnh, String telefone, String celular, List<Reserva> reservas, String ID, Ativo ativo) throws ValidatePropertyException {
        super(nome, endereco, cnh, telefone, celular, reservas, ID, ativo);
    }
    
    @Override
    public void setDocumento(String documento) {
        this.cnpj = documento;
    }

    @Override
    public String getDocumento() {
        return cnpj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.cnpj);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClienteJuridico other = (ClienteJuridico) obj;
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClienteJuridico{" + "cnpj=" + cnpj + '}';
    }
}
