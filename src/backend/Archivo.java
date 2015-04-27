package backend;

import com.llama.tech.misc.XMLFormat;

public class Archivo extends XMLFormat implements Comparable<Archivo>
{
	private String nombre;
	
	private String texto;
	
	private boolean esRecuperado;
	
	//TODO: hacer que conozca a su carpeta para poder guarar el recuperado en su lugar
	
	public Archivo(String pnombre, String ptexto, boolean esRecuper)
	{
		if(esRecuper)
			nombre = pnombre+".txt";
		else
			nombre = pnombre+".ocr";
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

	@Override
	public int compareTo(Archivo o) {
		return nombre.compareTo(o.getNombre());
	}

	@Override
	public void readXML() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toXML() 
	{
		String content = "<file name = \""+nombre+"\" processed = \""+esRecuperado+"\"/>\n";
		content += "<text>\n";
		content += texto+"\n";
		content += "</text>\n";
		return content;
	}
	
	
}
