/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import java.util.Objects;

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

	public Vaga(String ID, Ativo ativo) {
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
		int hash = 5;
		hash = 23 * hash + Objects.hashCode(this.codigo);
		hash = 23 * hash + Objects.hashCode(this.numeroVaga);
		hash = 23 * hash + Objects.hashCode(this.referencia);
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
		final Vaga other = (Vaga) obj;
		if (!Objects.equals(this.referencia, other.referencia)) {
			return false;
		}
		if (!Objects.equals(this.codigo, other.codigo)) {
			return false;
		}
		if (!Objects.equals(this.numeroVaga, other.numeroVaga)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vaga{" + "codigo=" + codigo + ", numeroVaga=" + numeroVaga + ", referencia=" + referencia + '}';
	}
}
