/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.enumerator.Ativo;

/**
 *
 * @author Diovani
 */
public class Vaga extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474249896156859055L;
	private Integer codigo;
	private Integer numeroVaga;
	private String referencia;

	public Vaga() {
	}

	public Vaga(Integer ID, Ativo ativo) {
		super(ID, ativo);
	}

	public Vaga(Integer codigo, Integer numeroVaga, String referencia) throws ValidatePropertyException {
		setCodigo(codigo);
		setNumeroVaga(numeroVaga);
		setReferencia(referencia);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getNumeroVaga() {
		return numeroVaga;
	}

	public void setNumeroVaga(Integer numeroVaga) throws ValidatePropertyException {
		if (numeroVaga == null || numeroVaga == 0)
			throw new ValidatePropertyException("O número da vaga deve ser informado");
		this.numeroVaga = numeroVaga;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) throws ValidatePropertyException {
		if (referencia == null || referencia.isEmpty())
			throw new ValidatePropertyException("A referência deve ser informado");
		this.referencia = referencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vaga{" + "codigo=" + codigo + ", numeroVaga=" + numeroVaga + ", referencia=" + referencia + '}';
	}
}
