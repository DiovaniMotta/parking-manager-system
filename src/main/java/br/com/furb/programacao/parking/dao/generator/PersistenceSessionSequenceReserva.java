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
import br.com.furb.programacao.parking.model.Reserva;
import br.com.furb.programacao.parking.model.Veiculo;
import br.com.furb.programacao.parking.model.enumerator.Persistencia;

public class PersistenceSessionSequenceReserva implements PersistenceSession<Reserva> {

	private SessionFile sessionFile;
	
	public PersistenceSessionSequenceReserva() throws IOException, ValidatePropertyException {
		sessionFile = SessionAbstractFactory.create(Persistencia.TEXTO).getSession();
		sessionFile.createRootDirectory("sequence");
		sessionFile.createFile("reserva.js");
	}

	@Override
	public boolean save(Reserva entidade) {
		try {
			List<Reserva> reservas = findAll();
			int indexOf = reservas.indexOf(entidade);
			if(indexOf > -1)
				reservas.add(indexOf, entidade);
			else
				reservas.add(entidade);
			String json = parse(reservas);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean merge(Reserva entidade) {
		return save(entidade);
	}

	@Override
	public boolean remove(Reserva entidade) {
		try {
			List<Reserva> reservas = findAll();
			reservas.remove(entidade);
			String json = parse(reservas);
			sessionFile.writeAll(json);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public Reserva findByKey(String ID) throws IOException {
		List<Reserva> reservas = findAll();
		Optional<Reserva> reserva = reservas.stream().filter(v-> v.getID().equals(ID)).findFirst();
		return reserva.get();
	}


	@Override
	public List<Reserva> findAll() throws IOException {
		StringBuilder json = sessionFile.readAll();
		Gson gson = new Gson();
		Reserva[] reservas = gson.fromJson(json.toString(), Veiculo[].class);
	    if(reservas == null)
	    	return new ArrayList<Reserva>();
	    List<Reserva> v = new ArrayList<>();
	    v.addAll(Arrays.asList(reservas));
	    return v;
	}

	protected String parse(List<Reserva> reservas){
		Gson gson = new Gson();
		return gson.toJson(reservas);
	}
}
