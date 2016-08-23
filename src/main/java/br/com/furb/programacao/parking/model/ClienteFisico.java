/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import java.util.Objects;

/**
 *
 * @author Diovani
 */
public class ClienteFisico extends Cliente{
    
    private String cpf;

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
