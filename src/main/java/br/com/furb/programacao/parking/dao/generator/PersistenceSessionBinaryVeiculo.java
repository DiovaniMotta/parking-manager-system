package br.com.furb.programacao.parking.dao.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import br.com.furb.programacao.parking.designerpatterns.factory.abstractfactory.SessionAbstractFactory;
import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.files.SessionFile;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenceSessionBinaryVeiculo implements PersistenceSession<Veiculo> {

	private SessionFile sessionFile;
	
	public PersistenceSessionBinaryVeiculo() throws IOException, ValidatePropertyException {
		sessionFile = SessionAbstractFactory.create(Persistencia.BINARIO).getSession();
		sessionFile.createRootDirectory("binary");
		sessionFile.createFile("veiculo.bin");
	}


	@Override
	public boolean save(Veiculo entidade) {
		try {
			List<Veiculo> veiculos = findAll();
			int indexOf = veiculos.indexOf(entidade);
			if(indexOf > -1)
				veiculos.add(indexOf, entidade);
			else
				veiculos.add(entidade);
			String json = parse(veiculos);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean merge(Veiculo entidade) {
		return save(entidade);
	}

	@Override
	public boolean remove(Veiculo entidade) {
		try {
			List<Veiculo> veiculos = findAll();
			veiculos.remove(entidade);
			String json = parse(veiculos);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public Veiculo findByKey(String ID) throws IOException {
		List<Veiculo> veiculos = findAll();
		Optional<Veiculo> veiculo = veiculos.stream().filter(v-> v.getID().equals(ID)).findFirst();
		return veiculo.get();
	}


	@Override
	public List<Veiculo> findAll() throws IOException {
		StringBuilder json = sessionFile.readAll();
		Gson gson = new Gson();
		Veiculo[] veiculos = gson.fromJson(json.toString(), Veiculo[].class);
	    if(veiculos == null)
	    	return new ArrayList<Veiculo>();
	    List<Veiculo> v = new ArrayList<>();
	    v.addAll(Arrays.asList(veiculos));
	    return v;
	}
	

	protected String parse(List<Veiculo> vagas){
		Gson gson = new Gson();
		return gson.toJson(vagas);
	}
}
