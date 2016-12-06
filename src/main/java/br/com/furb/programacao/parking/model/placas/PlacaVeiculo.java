package br.com.furb.programacao.parking.model.placas;

import java.util.ArrayList;
import java.util.List;

import br.com.furb.programacao.parking.exceptions.BadFormatException;
import br.com.furb.programacao.parking.exceptions.EmptyException;
import br.com.furb.programacao.parking.model.Configuracao;

public class PlacaVeiculo implements Placa {

	protected List<String> placas;

	public PlacaVeiculo() {
		super();
		placas = new ArrayList<String>();
	}

	public PlacaVeiculo(List<String> placas) {
		super();
		this.placas = placas;
	}

	@Override
	public int numeroPlacas() {
		return placas.size();
	}

	@Override
	public String getPlaca(int index) {
		return placas.get(index);
	}

	@Override
	public void add(String placa) throws EmptyException,BadFormatException {
		if(placa == null || placa.isEmpty())
			throw new EmptyException("O Campo placa deve ser diferente de vazio");
		String format = formatoPlaca();
		boolean isValidade = placa.matches(format);
		if(!isValidade)
			throw new BadFormatException("O formato para a placa não está correto. Padrão Aceito AAA-9999");	
		placas.add(placa);
	}
	
	protected String formatoPlaca(){
		return Configuracao.getInstance().getFormatPlacaRegiao();
	}
}
