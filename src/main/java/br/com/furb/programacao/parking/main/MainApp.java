/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.main;

import br.com.furb.programacao.parking.exceptions.NotImplementationException;
import br.com.furb.programacao.parking.model.placas.Placa;
import br.com.furb.programacao.parking.model.placas.PlacaVeiculo;

/**
 *
 * @author Diovani
 */
public class MainApp {

	public static void main(String[] args) throws NotImplementationException {
		try {
			Placa placa = new PlacaVeiculo();
			placa.add("MH-9001-AA");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
