/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.model.enumerator.Ativo;
import java.util.Objects;

/**
 *
 * @author Diovani
 */
public class Veiculo extends Entidade{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6924404662807929201L;
	private Integer codigo;
    private String descricao;
    private String marcar;
    private String modelo;

    public Veiculo() {
    }

    public Veiculo(String ID, Ativo ativo) {
        super(ID, ativo);
    }

    public Veiculo(Integer codigo, String descricao, String marcar, String modelo) {
        setCodigo(codigo);
        setDescricao(descricao);
    	setMarcar(marcar);
        setModelo(modelo);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarcar() {
        return marcar;
    }

    public void setMarcar(String marcar) {
        this.marcar = marcar;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.marcar);
        hash = 97 * hash + Objects.hashCode(this.modelo);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.marcar, other.marcar)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "codigo=" + codigo + ", descricao=" + descricao + ", marcar=" + marcar + ", modelo=" + modelo + '}';
    }
}
