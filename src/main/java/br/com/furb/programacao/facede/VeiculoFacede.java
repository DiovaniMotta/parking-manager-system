package br.com.furb.programacao.facede;

import java.io.IOException;
import java.util.List;

import br.com.furb.programacao.parking.dao.generator.PersistenceSession;
import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.PersistenceAbstractFactory;
import br.com.furb.programacao.parking.exceptions.DetachedException;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class VeiculoFacede {

	private PersistenceSession<Veiculo> persistence;

	public VeiculoFacede() throws IOException, ValidatePropertyException {
		super();
		persistence = PersistenceAbstractFactory.getPersistence(Persistencia.TEXTO).getVeiculo();
	}
	
	public boolean verificarExistencia(Veiculo veiculo) throws IOException, ValidatePropertyException{
		Veiculo v = persistence.findByKey(String.valueOf(veiculo.getID()));
		return v == null ? false : true; 
	}
	
	public boolean salvar(Veiculo veiculo) throws IOException, ValidatePropertyException{
		boolean existencia = verificarExistencia(veiculo);
		if(existencia)
			return atualizar(veiculo);
		return persistence.save(veiculo);
	}
	
	public boolean atualizar(Veiculo veiculo) throws IOException, ValidatePropertyException{
		return persistence.merge(veiculo);
	}
	
	public boolean remover(Veiculo veiculo) throws IOException, ValidatePropertyException, DetachedException{
		boolean existencia = verificarExistencia(veiculo);
		if(existencia)
			return persistence.remove(veiculo);
		throw new DetachedException("Não é possível remover um registro que ainda não está salvo.");
	}
	
	public Veiculo buscarPeloID(String ID) throws IOException, ValidatePropertyException{
		return persistence.findByKey(ID);
	}
	
	public List<Veiculo> buscarTodos() throws IOException{
		return persistence.findAll();
	}
}
