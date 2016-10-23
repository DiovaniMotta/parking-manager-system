/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.exceptions.ValidatePropertyException;
import br.com.furb.programacao.parking.model.Entidade;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Diovani
 * @param <T>
 *            o tipo de dados que será persistido. Obrigatóriamente esse dado
 *            deverá herdar da classe Entidade.
 */
public interface Persistence<T extends Entidade> {

	public boolean save(T entidade) throws IOException, ValidatePropertyException;

	public boolean merge(T entidade) throws IOException, ValidatePropertyException;

	public boolean remove(T entidade) throws IOException, ValidatePropertyException;

	public T findByKey(String ID) throws IOException, ValidatePropertyException;

	public List<T> findAll() throws IOException, ValidatePropertyException;

}
