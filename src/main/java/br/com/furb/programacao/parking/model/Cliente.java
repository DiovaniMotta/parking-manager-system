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

    public Cliente(Integer ID, Ativo ativo) {
        super(ID, ativo);
    }

    public Cliente(String nome,String endereco, String cnh, String telefone, String celular, List<Reserva> reservas, Integer ID, Ativo ativo) throws ValidatePropertyException {
        super(ID, ativo);
        setNome(nome);
        setEndereco(endereco);
        setCnh(cnh);
        setTelefone(telefone);
        setCelular(celular);
        setReservas(reservas);
    }

    public abstract void setDocumento(String documento);
    
    public abstract String getDocumento();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValidatePropertyException {
        if(nome==null || nome.isEmpty())
        	throw new ValidatePropertyException("O nome deve ser informada");
    	this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) throws ValidatePropertyException {
    	if(endereco == null || endereco.isEmpty())
        	throw new ValidatePropertyException("O endereço deve ser informada");
    	this.endereco = endereco;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) throws ValidatePropertyException {
    	if(cnh == null || cnh.isEmpty())
        	throw new ValidatePropertyException("A CNH deve ser informada");
    	this.cnh = cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ValidatePropertyException {
    	if(telefone == null || telefone.isEmpty())
    		throw new ValidatePropertyException("O telefone deve ser informada");
    	this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) throws ValidatePropertyException {
    	if(celular == null || celular.isEmpty())
    		throw new ValidatePropertyException("O telefone deve ser informada");
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
