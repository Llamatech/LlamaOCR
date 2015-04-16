package backend;

public class Archivo 
{
	private String nombre;
	
	private String texto;
	
	private boolean esRecuperado;
	
	public Archivo(String pnombre, String ptexto, boolean esRecuper)
	{
		nombre = pnombre;
		texto = ptexto;
		esRecuperado = esRecuper;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isEsRecuperado() {
		return esRecuperado;
	}

	public void setEsRecuperado(boolean esRecuperado) {
		this.esRecuperado = esRecuperado;
	}
	
	
}
