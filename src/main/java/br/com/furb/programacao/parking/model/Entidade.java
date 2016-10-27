/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import java.io.Serializable;

import br.com.furb.programacao.parking.model.enumerator.Ativo;

/**
 *
 * @author Diovani
 */
public abstract class Entidade implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6398540556483263675L;
	protected Integer ID;
    protected Ativo ativo;

    public Entidade() {
    }

    public Entidade(Integer ID, Ativo ativo) {
        setID(ID);
        setAtivo(ativo);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Entidade{" + "ID=" + ID + ", ativo=" + ativo + '}';
    }
}
