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
import br.com.furb.programacao.parking.model.Configuracao;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenceSessionBinaryConfiguracao implements PersistenceSession<Configuracao>{

	private SessionFile sessionFile;
	
	public PersistenceSessionBinaryConfiguracao() throws IOException, ValidatePropertyException {
		sessionFile = SessionAbstractFactory.create(Persistencia.BINARIO).getSession();
		sessionFile.createRootDirectory("binary");
		sessionFile.createFile("configuracao.bin");
	}
	

	@Override
	public boolean save(Configuracao entidade) {
		try {
			List<Configuracao> configuracoes = findAll();
			int indexOf = configuracoes.indexOf(entidade);
			if(indexOf > -1)
				configuracoes.add(indexOf, entidade);
			else
				configuracoes.add(entidade);
			String json = parse(configuracoes);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean merge(Configuracao entidade) {
		return save(entidade);
	}

	@Override
	public boolean remove(Configuracao entidade) {
		try {
			List<Configuracao> configuracoes = findAll();
			configuracoes.remove(entidade);
			String json = parse(configuracoes);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public Configuracao findByKey(String ID) throws IOException {
		List<Configuracao> configuracoes = findAll();
		Optional<Configuracao> configuracao = configuracoes.stream().filter(v-> v.getID().equals(ID)).findFirst();
		return configuracao.get();
	}


	@Override
	public List<Configuracao> findAll() throws IOException {
		StringBuilder json = sessionFile.readAll();
		Gson gson = new Gson();
		Configuracao[] configuracoes = gson.fromJson(json.toString(), Configuracao[].class);
	    if(configuracoes == null)
	    	return new ArrayList<Configuracao>();
	    List<Configuracao> v = new ArrayList<>();
	    v.addAll(Arrays.asList(configuracoes));
	    return v;
	}


	protected String parse(List<Configuracao> configuracoes){
		Gson gson = new Gson();
		return gson.toJson(configuracoes);
	}

}
