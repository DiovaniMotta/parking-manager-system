/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.furb.programacao.parking.dao;

import br.com.furb.programacao.parking.model.Entidade;
import java.util.List;

/**
 *
 * @author Diovani
 * @param <T> o tipo de dados que será persistido. Obrigatóriamente esse dado deverá herdar da classe Entidade. 
 */
public interface Persistence <T extends Entidade> {
    
   
    public boolean save(T entidade);
    
    public boolean merge(T entidade);
    
    public boolean remove(T entidade);
    
    public T findByKey(String ID);
    
    public List<T> findAll();
    
}
