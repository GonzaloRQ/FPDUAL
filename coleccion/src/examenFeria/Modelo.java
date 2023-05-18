package examenFeria;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.*;

public class Modelo {
	
	private ListaPrecios listaPrecios;
	private ListaSocios listaSocios;
	private HashMap<Socio, LinkedList<Consumicion>> listaCuentas;

	public Modelo()
	{
		this.listaPrecios = new ListaPrecios();
		this.listaSocios = new ListaSocios();
		this.listaCuentas = new HashMap<Socio,LinkedList<Consumicion>>();
	}
	


	public boolean anadeArticulo(String nombre,  String tamaño, float precio) 
	{
		return listaPrecios.put(new Articulo(nombre,tamaño), precio) == null;
	}

	public boolean borraArticulo(String nombre, String tamaño) 
	{
		return listaPrecios.remove(new Articulo(nombre, tamaño)) == null;
	}

	public  ListaSocios getListaSocios() 
	{
		return this.listaSocios;
	}

	public ListaPrecios getListaPrecios() 
	{
		return this.listaPrecios;
	}

	public boolean hayCuentaPendienteConEseArticulo(String art, String tamaño) 
	{
		boolean hay = false;
		for(Socio s : listaCuentas.keySet())
		{
			for(Consumicion c : listaCuentas.get(s))
			{
				if(c.getArticulo().equals(new Articulo(art,tamaño)))
				{
					hay = true;
				}
			}
		}
		return hay;

	}

	public void anadeConsumicion(Socio socio, Articulo artículo, int cantidad) 
	{
//		LinkedList<Consumicion> cons = new LinkedList<>();
//		Consumicion c1 = new Consumicion(artículo,cantidad);
//		cons.add(c1);
//		listaCuentas.put(socio, cons);
		if(!this.listaCuentas.containsKey(socio)) {
			this.listaCuentas.put(socio, new LinkedList<Consumicion>());
		}
		this.listaCuentas.get(socio).add(new Consumicion(artículo, cantidad));	
	}

	public LinkedList<Consumicion> getListaConsumiciones(Socio socio) 
	{
		return listaCuentas.get(socio);
	}

	public String calculaTotalCuenta(Socio socio) 
	{
		String lista = "";
		float total = 0;
		if(socio != null)
		{
			for(Consumicion c : listaCuentas.get(socio))
			{
				total += c.getCantidad() * listaPrecios.get(c.getArticulo());
				lista += total;
			}
		}
		return lista;
	}

}

