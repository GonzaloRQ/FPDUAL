import java.util.*;

public class Modelo
{
	private HashMap<Comercial, TreeSet<Empresa>> asignaciones;
	private Vector<Visita> visitas;

	public Modelo()
	{
		this.asignaciones = new HashMap<Comercial,TreeSet<Empresa>>();
		this.visitas = new Vector<Visita>();
	}
	
	public void añadeComercial(Comercial c)
	{
		asignaciones.put(c, new TreeSet<Empresa>());
		// a�ade al mapa de asignaciones una nueva pareja con el comercial pasado como 
		// par�metro y un conjunto nuevo vac�o de empresas asignadas.
	}

	public void asignaEmpresaAComercial(Comercial c, Empresa e)
	{
		if(!asignaciones.containsKey(c))
		{
			añadeComercial(c);
		}
		asignaciones.get(c).add(e);
		// asigna la empresa pasada como par�metro al conjunto de empresas que tiene asignadas
		// el comercial pasado como par�metro.
	}

	public void añadeVisita(Visita visita)
	{
		this.visitas.add(visita);
	}

	public boolean hayErrores()
	{
		boolean error = false;
		// Devuelve true si hay alguna visita hecha por un comercial a una empresa que NO estaba
		// asignada a ese comercial. Devuelve false en otro caso.
		for(Visita v : this.visitas)
		{
			if(!asignaciones.get(v.getComercial()).contains(v.getEmpresa()))
			{
				error = true;
			}
		}
		return error;
	}

	public float totalComprasPorComercial(Comercial c)
	{
		float calculoCompras = 0;
		for(Empresa e : this.asignaciones.get(c))
		{
			calculoCompras += e.getCompras();
		}
		return calculoCompras;
	}

	public float totalVentasPorEmpresa(Empresa e)
	{
		// Devuelve el total de ventas hechas por todos los comerciales asignados a 
		// la empresa pasada como par�metro.
		float calculoCompras = 0;
		for(Comercial c : this.asignaciones.keySet())
		{
			if(asignaciones.get(c).contains(e))
			{
				calculoCompras += c.getVentas();
			}
		}
		return calculoCompras;
		
	}


	public TreeSet<Comercial> comercialesQueVisitaronEnElMes(int mes)
	{
		TreeSet<Comercial> comercialVisitaMes = new TreeSet<>();
		for(Visita v : visitas)
		{
			if(v.getMes() == (mes))
			{
				comercialVisitaMes.add(v.getComercial());
			}
		}
		// Devuelve un TreeSet con todos los comerciales que hicieron alguna visita 
		// en el mes pasado como par�metro.
		return comercialVisitaMes;
		
	}

	public TreeSet<String> nombresDeEmpresasNoVisitadas()
	{
		//Opción 1
//		public TreeSet<String> nombresDeEmpresasNoVisitadas()
//	    {
//	        // Devuelve un TreeSet con todas las empresas que no han recibido ninguna visita.
//	        TreeSet<String> arbol = new TreeSet<String>();
//
//	        for(TreeSet<Empresa> t:this.asignaciones.values())
//	        {
//	            for(Empresa e:t)
//	            {
//	                boolean visitada = false;
//	                for(Visita v:this.visitas)
//	                    if(v.getEmpresa().equals(e))
//	                        visitada = true;
//	                if(!visitada)
//	                    arbol.add(e.getNombre());
//	            }
//	        }
//
//	        return arbol;
//	    }
		// Devuelve un TreeSet con todas las empresas que no han recibido ninguna visita.
		//Opción 2
		TreeSet<String> nombreEmpresaNoVisitada = new TreeSet<>();
        TreeSet<Empresa> empresasVisitadas = new TreeSet<>();

        for(Visita v : visitas) {
            empresasVisitadas.add(v.getEmpresa());
        }

        for(TreeSet<Empresa> e : asignaciones.values()) {
            for(Empresa eTreeSet : e) {
                if(!empresasVisitadas.contains(eTreeSet)) {
                    nombreEmpresaNoVisitada.add(eTreeSet.getNombre());
                }
            }
        }
        return nombreEmpresaNoVisitada;
	}


	public TreeMap<Comercial, LinkedList<Visita>> mapaVisitasPorComercial()
	{
		TreeMap<Comercial, LinkedList<Visita>> mapaVisitasPorComercial = new TreeMap<>();

        for (Comercial c : asignaciones.keySet()) {

            LinkedList<Visita> lista = new LinkedList<Visita>();

            for (Visita v : visitas) {
                if (v.getComercial().equals(c)) {
                    lista.add(v);
                }
            }
            mapaVisitasPorComercial.put(c, lista);
        }

        return mapaVisitasPorComercial;
        // Devuelve un mapa con todos los comerciales como claves, y el valor asociado a
        // cada comercial es una LinkedList de las visitas que ha hecho ese comercial.
	}

}
