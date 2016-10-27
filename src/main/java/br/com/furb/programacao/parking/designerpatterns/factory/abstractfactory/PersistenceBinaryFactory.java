package br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory;

import java.io.IOException;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryCliente;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryConfiguracao;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryReserva;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryVaga;
import br.com.furb.programacao.parking.dao.generator.PersistenceSessionBinaryVeiculo;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.Configuracao;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.Veiculo;

public class PersistenceBinaryFactory  extends PersistenceAbstractFactory {

	@Override
	public PersistenceSession<Cliente> getCliente() throws IOException,ValidatePropertyException{
		return new PersistenceSessionBinaryCliente();
	}

	@Override
	public PersistenceSession<Configuracao> getConfiguracao() throws IOException,ValidatePropertyException{
		return new PersistenceSessionBinaryConfiguracao();
	}

	@Override
	public PersistenceSession<Reserva> getReserva() throws IOException,ValidatePropertyException{
		return new PersistenceSessionBinaryReserva();
	}

	@Override
	public PersistenceSession<Vaga> getVaga() throws IOException,ValidatePropertyException{
		return new PersistenceSessionBinaryVaga();
	}

	@Override
	public PersistenceSession<Veiculo> getVeiculo() throws IOException,ValidatePropertyException{
		return new PersistenceSessionBinaryVeiculo();
	}

		
}
