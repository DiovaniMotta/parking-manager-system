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
import br.com.furb.programacao.parking.model.Vaga;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenceSessionBinaryVaga implements PersistenceSession<Vaga> {

	private SessionFile sessionFile;

	
	public PersistenceSessionBinaryVaga() throws IOException, ValidatePropertyException {
		sessionFile = SessionAbstractFactory.create(Persistencia.TEXTO).getSession();
		sessionFile.createRootDirectory("binary");
		sessionFile.createFile("vagas.bin");
	}

	@Override
	public boolean save(Vaga entidade) {
		try {
			List<Vaga> vagas = findAll();
			int indexOf = vagas.indexOf(entidade);
			if(indexOf > -1)
				vagas.add(indexOf, entidade);
			else
				vagas.add(entidade);
			String json = parse(vagas);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean merge(Vaga entidade) {
		return save(entidade);
	}

	@Override
	public boolean remove(Vaga entidade) {
		try {
			List<Vaga> vagas = findAll();
			vagas.remove(entidade);
			String json = parse(vagas);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public Vaga findByKey(String ID) throws IOException {
		List<Vaga> vagas = findAll();
		Optional<Vaga>vaga = vagas.stream().filter(v-> v.getID().equals(ID)).findFirst();
		return vaga.get();
	}

	@Override
	public List<Vaga> findAll() throws IOException {
		StringBuilder json = sessionFile.readAll();
		Gson gson = new Gson();
		Vaga[] vagas = gson.fromJson(json.toString(), Vaga[].class);
	    if(vagas == null)
	    	return new ArrayList<Vaga>();
	    List<Vaga> v = new ArrayList<>();
	    v.addAll(Arrays.asList(vagas));
	    return v;
	}

	protected String parse(List<Vaga> vagas){
		Gson gson = new Gson();
		return gson.toJson(vagas);
	}
}
