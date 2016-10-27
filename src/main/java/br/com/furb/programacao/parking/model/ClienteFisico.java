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
public class ClienteFisico extends Cliente{
    
    /**
	 * 
	 */
	private String cpf;
	private static final long serialVersionUID = 2070256036465299460L;
	
    public ClienteFisico() {
    }

    public ClienteFisico(Integer ID, Ativo ativo) {
        super(ID, ativo);
    }

    public ClienteFisico(String nome, String endereco, String cnh, String telefone, String celular, List<Reserva> reservas, Integer ID, Ativo ativo) throws ValidatePropertyException {
        super(nome, endereco, cnh, telefone, celular, reservas, ID, ativo);
    }

    @Override
    public void setDocumento(String documento) {
        this.cpf = documento;
    }

    @Override
    public String getDocumento() {
        return cpf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.cpf);
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
        final ClienteFisico other = (ClienteFisico) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClienteFisico{" + "cpf=" + cpf + '}';
    }
}
