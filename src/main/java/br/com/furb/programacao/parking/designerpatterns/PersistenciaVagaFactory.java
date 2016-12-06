package br.com.furb.programacao.parking.designerpatterns;

import java.io.IOException;

import br.com.furb.programacao.parking.dao.Persistence;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryVaga;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceVaga;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Entidade;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenciaVagaFactory extends PersistenceFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Entidade> Persistence<T> open(Persistencia persistencia) throws IOException, ValidatePropertyException {
		if(persistencia == Persistencia.BINARIO)
			return (Persistence<T>) new PersistenceSessionBinaryVaga();
		return (Persistence<T>) new PersistenceSessionSequenceVaga();

	}

}
