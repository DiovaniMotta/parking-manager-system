/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.model;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.enumerator.Ativo;
import br.com.furb.programacao.parking.model.enumerator.Situacao;
import br.com.furb.programacao.parking.model.placas.Placa;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Diovani
 */
public class Reserva extends Entidade{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3913891271253964374L;
	private Cliente cliente;
    private Vaga vaga;
    private Veiculo veiculo;
    private Placa placa;
    private String cor;
    private String observacao;
    private Situacao situacao;
    private Date horarioinicio;
    private Date horariofinal;
    private Date dataEntrada;
    private Date dateSaida;
    private Double valorHora;
    private Double totalhoras;
    private Double totalReserva;

    public Reserva() {
    }

    public Reserva(Integer ID, Ativo ativo) {
        super(ID, ativo);
    }

    public Reserva(Cliente cliente, Vaga vaga, Veiculo veiculo, Placa placa, String cor, String observacao, Situacao situacao, Date horarioinicio, Date horariofinal, Date dataEntrada, Date dateSaida, Double valorHora, Double totalhoras, Double totalReserva, Integer ID, Ativo ativo) throws ValidatePropertyException {
        super(ID, ativo);
        setCliente(cliente);
        setVaga(vaga);
        setVeiculo(veiculo);
        setPlaca(placa);
        setCor(cor);
        setObservacao(observacao);
        setSituacao(situacao);
        setHorarioinicio(horarioinicio);
        setHorariofinal(horariofinal);
        setDataEntrada(dataEntrada);
        setDateSaida(dateSaida);
        setValorHora(valorHora);
        setTotalhoras(totalhoras);
        setTotalReserva(totalReserva);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) throws ValidatePropertyException {
        if(cliente == null)
        	throw new ValidatePropertyException("O cliente deve ser informado.");
    	this.cliente = cliente;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) throws ValidatePropertyException {
      if(vaga == null)
          	throw new ValidatePropertyException("A vaga deve ser informado.");
      this.vaga = vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) throws ValidatePropertyException {
    	if(veiculo == null)
          	throw new ValidatePropertyException("O veiculo deve ser informado.");
    	this.veiculo = veiculo;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) throws ValidatePropertyException {
    	if(placa == null)
          	throw new ValidatePropertyException("A placa deve ser informado.");
    	this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getHorarioinicio() {
        return horarioinicio;
    }

    public void setHorarioinicio(Date horarioinicio) {
        this.horarioinicio = horarioinicio;
    }

    public Date getHorariofinal() {
        return horariofinal;
    }

    public void setHorariofinal(Date horariofinal) {
        this.horariofinal = horariofinal;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDateSaida() {
        return dateSaida;
    }

    public void setDateSaida(Date dateSaida) {
        this.dateSaida = dateSaida;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Double getTotalhoras() {
        return totalhoras;
    }

    public void setTotalhoras(Double totalhoras) {
        this.totalhoras = totalhoras;
    }

    public Double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(Double totalReserva) {
        this.totalReserva = totalReserva;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.cliente);
        hash = 23 * hash + Objects.hashCode(this.vaga);
        hash = 23 * hash + Objects.hashCode(this.veiculo);
        hash = 23 * hash + Objects.hashCode(this.placa);
        hash = 23 * hash + Objects.hashCode(this.cor);
        hash = 23 * hash + Objects.hashCode(this.observacao);
        hash = 23 * hash + Objects.hashCode(this.situacao);
        hash = 23 * hash + Objects.hashCode(this.horarioinicio);
        hash = 23 * hash + Objects.hashCode(this.horariofinal);
        hash = 23 * hash + Objects.hashCode(this.dataEntrada);
        hash = 23 * hash + Objects.hashCode(this.dateSaida);
        hash = 23 * hash + Objects.hashCode(this.valorHora);
        hash = 23 * hash + Objects.hashCode(this.totalhoras);
        hash = 23 * hash + Objects.hashCode(this.totalReserva);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.vaga, other.vaga)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (this.situacao != other.situacao) {
            return false;
        }
        if (!Objects.equals(this.horarioinicio, other.horarioinicio)) {
            return false;
        }
        if (!Objects.equals(this.horariofinal, other.horariofinal)) {
            return false;
        }
        if (!Objects.equals(this.dataEntrada, other.dataEntrada)) {
            return false;
        }
        if (!Objects.equals(this.dateSaida, other.dateSaida)) {
            return false;
        }
        if (!Objects.equals(this.valorHora, other.valorHora)) {
            return false;
        }
        if (!Objects.equals(this.totalhoras, other.totalhoras)) {
            return false;
        }
        if (!Objects.equals(this.totalReserva, other.totalReserva)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reserva{" + "cliente=" + cliente + ", vaga=" + vaga + ", veiculo=" + veiculo + ", placa=" + placa + ", cor=" + cor + ", observacao=" + observacao + ", situacao=" + situacao + ", horarioinicio=" + horarioinicio + ", horariofinal=" + horariofinal + ", dataEntrada=" + dataEntrada + ", dateSaida=" + dateSaida + ", valorHora=" + valorHora + ", totalhoras=" + totalhoras + ", totalReserva=" + totalReserva + '}';
    }
}
