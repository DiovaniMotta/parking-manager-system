/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diovani
 */
public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO(Persistencia persistencia) {
		super(persistencia);
	}

	@Override
	public boolean save(Cliente entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getCliente().save(entidade);
	}

	@Override
	public boolean merge(Cliente entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getCliente().merge(entidade);
	}

	@Override
	public boolean remove(Cliente entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getCliente().remove(entidade);
	}

	@Override
	public Cliente findByKey(String ID) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getCliente().findByKey(ID);
	}

	@Override
	public List<Cliente> findAll() throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getCliente().findAll();
	}
}
