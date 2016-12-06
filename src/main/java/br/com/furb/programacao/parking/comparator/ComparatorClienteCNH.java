package br.com.furb.programacao.parking.comparator;

import java.util.Comparator;

import br.com.furb.programacao.parking.model.Cliente;

public class ComparatorClienteCNH implements Comparator<Cliente> {

	@Override
	public int compare(Cliente c1, Cliente c2) {
		return c1.getCnh().compareTo(c2.getCnh());
	}

	
}
