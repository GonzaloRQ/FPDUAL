package examenFeria;

public class Articulo implements Comparable<Articulo>
{
	private String denominacion;
	private String tamano;
	
	public Articulo(String denominacion, String tamano) 
	{
		super();
		this.denominacion = denominacion;
		this.tamano = tamano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
		result = prime * result + ((tamano == null) ? 0 : tamano.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		if (tamano == null) {
			if (other.tamano != null)
				return false;
		} else if (!tamano.equals(other.tamano))
			return false;
		return true;
	}

public int compareTo(Articulo otro)
{
	int res;
	
	res = this.denominacion.compareTo(otro.denominacion);
	if(res == 0)
		res = this.tamano.compareTo(otro.tamano);
	return res;
}

public String toString()
{
	return this.denominacion+" "+this.tamano;
}

public String getDenominacion() {
	return denominacion;
}

public void setDenominacion(String denominacion) {
	this.denominacion = denominacion;
}

public String getTamano() {
	return tamano;
}

public void setTamano(String tamano) {
	this.tamano = tamano;
}

}
