package br.com.furb.programacao.parking.dao.generator;

import java.io.IOException;
import java.util.List;

import br.com.furb.programacao.parking.model.Entidade;

public interface PersistenceSession<T extends Entidade> {

    public boolean save(T entidade);
    
    public boolean merge(T entidade);
    
    public boolean remove(T entidade);
    
    public T findByKey(String ID) throws IOException;
    
    public List<T> findAll()throws IOException;
}
