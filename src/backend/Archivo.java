package backend;

<<<<<<< HEAD
public class Archivo implements Comparable<Archivo>
=======
public class Archivo 
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
{
	private String nombre;
	
	private String texto;
	
	private boolean esRecuperado;
	
<<<<<<< HEAD
	//TODO: hacer que conozca a su carpeta para poder guarar el recuperado en su lugar
	
	public Archivo(String pnombre, String ptexto, boolean esRecuper)
	{
		if(esRecuper)
			nombre = pnombre+".txt";
		else
			nombre = pnombre+".ocr";
=======
	public Archivo(String pnombre, String ptexto, boolean esRecuper)
	{
		nombre = pnombre;
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
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
<<<<<<< HEAD

	@Override
	public int compareTo(Archivo o) {
		return nombre.compareTo(o.getNombre());
	}
=======
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	
	
}
