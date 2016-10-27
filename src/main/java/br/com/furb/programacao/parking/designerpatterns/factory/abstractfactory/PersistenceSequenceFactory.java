package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import java.io.IOException;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceCliente;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceConfiguracao;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceReserva;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceVaga;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionSequenceVeiculo;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.Configuracao;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.Veiculo;

public class PersistenceSequenceFactory extends PersistenceAbstractFactory {

	@Override
	public PersistenceSession<Cliente> getCliente() throws IOException, ValidatePropertyException {
		return new PersistenceSessionSequenceCliente();
	}

	@Override
	public PersistenceSession<Configuracao> getConfiguracao() throws IOException, ValidatePropertyException {
		return new PersistenceSessionSequenceConfiguracao();
	}

	@Override
	public PersistenceSession<Reserva> getReserva() throws IOException, ValidatePropertyException {
		return new PersistenceSessionSequenceReserva();
	}

	@Override
	public PersistenceSession<Vaga> getVaga() throws IOException, ValidatePropertyException {
		return new PersistenceSessionSequenceVaga();
	}

	@Override
	public PersistenceSession<Veiculo> getVeiculo() throws IOException, ValidatePropertyException {
		return new PersistenceSessionSequenceVeiculo();
	}

}
