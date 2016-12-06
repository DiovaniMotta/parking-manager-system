package br.com.furb.programacao.parking.comparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import br.com.furb.programacao.facede.ClienteFacede.Order;
import br.com.furb.programacao.parking.model.Cliente;

public class ComparatorUtis {

	private static Map<Order,Comparator<Cliente>> clientes = new HashMap<>();
	
	static {
		clientes.put(Order.CELULAR, new ComparatorClienteCelular()); 
		clientes.put(Order.CNH, new ComparatorClienteNome());
		clientes.put(Order.ENDERECO, new ComparatorClienteEndereco());
		clientes.put(Order.NOME, new ComparatorClienteNome());
		clientes.put(Order.TELEFONE, new ComparatorClienteTelefone());
	}
	
	public static Comparator<Cliente> getCliente(Order order){
		return clientes.get(order);
	}
}
