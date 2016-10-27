package br.com.furb.programacao.parking.dao;

import java.io.IOException;
import java.util.List;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Configuracao;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class ConfiguracaoDAO extends GenericDAO<Configuracao> {

	public ConfiguracaoDAO(Persistencia persistencia) {
		super(persistencia);
	}

	@Override
	public boolean save(Configuracao entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getConfiguracao().save(entidade);
	}

	@Override
	public boolean merge(Configuracao entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getConfiguracao().merge(entidade);
	}

	@Override
	public boolean remove(Configuracao entidade) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getConfiguracao().remove(entidade);
	}

	@Override
	public Configuracao findByKey(String ID) throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getConfiguracao().findByKey(ID);
	}

	@Override
	public List<Configuracao> findAll() throws IOException, ValidatePropertyException {
		return persistenceAbstractFactory.getConfiguracao().findAll();
	}

}
