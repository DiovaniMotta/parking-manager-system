package br.com.furb.programacao.parking.model.placas;

import br.com.furb.programacao.parking.exceptions.BadFormatException;
import br.com.furb.programacao.parking.exceptions.EmptyException;

public interface Placa {

	public int numeroPlacas();
	
	public String getPlaca(int index);
	
	public void add(String placa) throws EmptyException,BadFormatException;
}
