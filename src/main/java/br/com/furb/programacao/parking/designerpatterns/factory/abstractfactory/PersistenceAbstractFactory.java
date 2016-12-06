package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import java.io.IOException;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.Configuracao;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public abstract class PersistenceAbstractFactory {

	public static PersistenceAbstractFactory getPersistence(Persistencia persistencia){
		switch(persistencia){
		case BINARIO:
			return new PersistenceBinaryFactory();
		case TEXTO:
			return new PersistenceSequenceFactory();
		}
		return null;
	}

	public abstract PersistenceSession<Cliente> getCliente() throws IOException, ValidatePropertyException;

	public abstract PersistenceSession<Configuracao> getConfiguracao() throws IOException, ValidatePropertyException;

	public abstract PersistenceSession<Reserva> getReserva() throws IOException, ValidatePropertyException;

	public abstract PersistenceSession<Vaga> getVaga() throws IOException, ValidatePropertyException;

	public abstract PersistenceSession<Veiculo> getVeiculo() throws IOException, ValidatePropertyException;

}
