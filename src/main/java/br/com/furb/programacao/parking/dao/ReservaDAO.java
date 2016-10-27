/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diovani
 */
public class ReservaDAO extends GenericDAO<Reserva> {

	public ReservaDAO(Persistencia persistencia) {
		super(persistencia);
	}

	@Override
	public boolean save(Reserva entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getReserva().save(entidade);
	}

	@Override
	public boolean merge(Reserva entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getReserva().merge(entidade);
	}

	@Override
	public boolean remove(Reserva entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getReserva().remove(entidade);
	}

	@Override
	public Reserva findByKey(String ID) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getReserva().findByKey(ID);
	}

	@Override
	public List<Reserva> findAll() throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getReserva().findAll();
	}
}
