/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.model.enumerator.Ativo;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Diovani
 */
public abstract class Entidade implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6398540556483263675L;
	protected String ID;
    protected Ativo ativo;

    public Entidade() {
    }

    public Entidade(String ID, Ativo ativo) {
        this.ID = ID;
        this.ativo = ativo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.ID);
        hash = 89 * hash + Objects.hashCode(this.ativo);
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
        final Entidade other = (Entidade) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidade{" + "ID=" + ID + ", ativo=" + ativo + '}';
    }
}
