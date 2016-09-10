/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.model.enumerator.Ativo;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Diovani
 */
public abstract class Cliente extends Entidade{
    
    /**
	 * 
	 */
	protected String nome;
    protected String endereco;
    protected String cnh;
    protected String telefone;
    protected String celular;
    protected List<Reserva> reservas;
    private static final long serialVersionUID = 6748700199342308464L;
	
    public Cliente() {
    }

    public Cliente(String ID, Ativo ativo) {
        super(ID, ativo);
    }

    public Cliente(String nome,String endereco, String cnh, String telefone, String celular, List<Reserva> reservas, String ID, Ativo ativo) {
        super(ID, ativo);
        this.nome = nome;
        this.endereco = endereco;
        this.cnh = cnh;
        this.telefone = telefone;
        this.celular = celular;
        this.reservas = reservas;
    }

    public abstract void setDocumento(String documento);
    
    public abstract String getDocumento();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.endereco);
        hash = 59 * hash + Objects.hashCode(this.cnh);
        hash = 59 * hash + Objects.hashCode(this.telefone);
        hash = 59 * hash + Objects.hashCode(this.celular);
        hash = 59 * hash + Objects.hashCode(this.reservas);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.cnh, other.cnh)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{nome=" + nome + ", endereco=" + endereco + ", cnh=" + cnh + ", telefone=" + telefone + ", celular=" + celular + ", reservas=" + reservas + '}';
    }
}
