/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diovani
 */
public class VeiculoDAO extends GenericDAO<Veiculo>{

    public VeiculoDAO(Persistencia persistencia) {
		super(persistencia);
	}

	@Override
    public boolean save(Veiculo entidade) throws IOException, ValidatePropertyException {
        return persistenceAbstractFactory.getVeiculo().save(entidade);
    }

    @Override
    public boolean merge(Veiculo entidade) throws IOException, ValidatePropertyException {
        return persistenceAbstractFactory.getVeiculo().merge(entidade);
        }

    @Override
    public boolean remove(Veiculo entidade) throws IOException, ValidatePropertyException {
        return persistenceAbstractFactory.getVeiculo().remove(entidade);
        
    }

    @Override
    public Veiculo findByKey(String ID) throws IOException, ValidatePropertyException {
        return persistenceAbstractFactory.getVeiculo().findByKey(ID);
    }

    @Override
    public List<Veiculo> findAll() throws IOException, ValidatePropertyException {
    	return persistenceAbstractFactory.getVeiculo().findAll();
    }   
}
