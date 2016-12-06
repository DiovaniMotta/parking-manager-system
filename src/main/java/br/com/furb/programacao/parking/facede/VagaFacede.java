package br.com.furb.programacao.parking.facede;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.PersistenceAbstractFactory;
import br.com.furb.programacao.parking.exceptions.DetachedException;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;
import br.com.furb.programacao.parking.model.enumerator.Situacao;

public class VagaFacede {

	
	private PersistenceSession<Vaga> persistence;

	public VagaFacede() throws IOException, ValidatePropertyException {
		super();
		persistence = PersistenceAbstractFactory.getPersistence(Persistencia.BINARIO).getVaga();
	}
	
	public boolean verificarExistencia(Vaga vaga) throws IOException, ValidatePropertyException{
		Vaga v = persistence.findByKey(String.valueOf(vaga.getID()));
		return v == null ? false : true; 
	}
	
	public boolean salvar(Vaga vaga) throws IOException, ValidatePropertyException{
		boolean existencia = verificarExistencia(vaga);
		if(existencia)
			return atualizar(vaga);
		return persistence.save(vaga);
	}
	
	public boolean atualizar(Vaga vaga) throws IOException, ValidatePropertyException{
		return persistence.merge(vaga);
	}
	
	public boolean remover(Vaga vaga) throws IOException, ValidatePropertyException, DetachedException{
		boolean existencia = verificarExistencia(vaga);
		if(existencia)
			return persistence.remove(vaga);
		throw new DetachedException("Não é possível remover um registro que ainda não está salvo.");
	}
	
	public Vaga buscarPeloID(String ID) throws IOException, ValidatePropertyException{
		return persistence.findByKey(ID);
	}
	
	public List<Vaga> buscarTodos() throws IOException{
		return persistence.findAll();
	}
	
	public boolean ocupada(Vaga vaga) throws IOException, ValidatePropertyException{
		PersistenceSession<Reserva> persistence = PersistenceAbstractFactory.getPersistence(Persistencia.TEXTO).getReserva();
		List<Reserva> reservas = persistence.findAll();
		return !reservas.stream().filter(r -> r.getVaga().equals(vaga) && r.getSituacao() == Situacao.ABERTA).collect(Collectors.toList()).isEmpty();
	}
}
