/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diovani
 */
public class VagaDAO extends GenericDAO<Vaga>{

    public VagaDAO(Persistencia persistencia) {
		super(persistencia);
	}

	@Override
    public boolean save(Vaga entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getVaga().save(entidade);
	}

    @Override
    public boolean merge(Vaga entidade) throws IOException, ValidatePropertyException {
    	return persistenceAbstractFactory.getVaga().merge(entidade);
    }

    @Override
    public boolean remove(Vaga entidade) throws IOException, ValidatePropertyException{
    	return persistenceAbstractFactory.getVaga().remove(entidade);
        }

    @Override
    public Vaga findByKey(String ID) throws IOException, ValidatePropertyException{
    	return persistenceAbstractFactory.getVaga().findByKey(ID);
    }

    @Override
    public List<Vaga> findAll() throws IOException, ValidatePropertyException{
    	return persistenceAbstractFactory.getVaga().findAll();
    }
}
