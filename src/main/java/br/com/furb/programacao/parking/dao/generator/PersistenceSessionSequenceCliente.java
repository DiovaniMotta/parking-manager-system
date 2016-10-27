package br.com.furb.programacao.parking.dao.generator;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.SessionAbstractFactory;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.files.SessionFile;
import br.com.furb.programacao.parking.model.Cliente;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenceSessionSequenceCliente implements PersistenceSession<Cliente> {
	
	private SessionFile sessionFile;

	public PersistenceSessionSequenceCliente() throws IOException, ValidatePropertyException {
		sessionFile = SessionAbstractFactory.create(Persistencia.TEXTO).getSession();
		sessionFile.createRootDirectory("sequence");
		sessionFile.createFile("cliente.js");
	}

	@Override
	public boolean save(Cliente entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean merge(Cliente entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Cliente entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente findByKey(String ID) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAll() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	protected String parse(List<Cliente> clientes){
		Gson gson = new Gson();
		return gson.toJson(clientes);
	}
}
