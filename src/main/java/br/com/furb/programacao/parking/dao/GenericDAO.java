package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.PersistenceAbstractFactory;
import br.com.furb.programacao.parking.model.Entidade;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public abstract class GenericDAO <T extends Entidade> implements Persistence<T> {

	protected PersistenceAbstractFactory persistenceAbstractFactory;

	public GenericDAO(Persistencia persistencia) {
			persistenceAbstractFactory = PersistenceAbstractFactory.getPersistence(persistencia);
	}
	
	
}
