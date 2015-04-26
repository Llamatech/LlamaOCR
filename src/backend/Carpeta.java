package backend;

import com.llama.tech.utils.list.Lista;
import com.llama.tech.utils.list.LlamaArrayList;

public class Carpeta implements Comparable<Carpeta>
{
	private Lista<String> carpetasHijas;
	
	private String nombre;
	
	private boolean expandida;
	
	public Lista<String> getArchivos() {
		return archivos;
	}

	private String carpetaPadre;
	
	private Lista<String> archivos;
	
	public Carpeta(String pNombre, String pPadre)
	{
		nombre = pNombre;
		expandida = false;
		carpetaPadre = pPadre;
		archivos = new LlamaArrayList<String>(10);
		carpetasHijas = new LlamaArrayList<String>(10);
		
	}
	
	public void eliminarCarpetaHija(String c)
	{
		carpetasHijas.remove(c);
	}
	
	public void agregarHijo(String nombre)
	{
		carpetasHijas.addAlFinal(nombre);
	}

	public Lista<String> getCarpetasHijas() {
		return carpetasHijas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isExpandida() {
		return expandida;
	}

	public void setExpandida(boolean expandida) {
		this.expandida = expandida;
	}

	public String getCarpetaPadre() {
		return carpetaPadre;
	}

	public void setCarpetaPadre(String carpetaPadre) {
		this.carpetaPadre = carpetaPadre;
	}
	
	public void agregarArchivo(String archivo)
	{
		archivos.addAlFinal(archivo);
	}

	@Override
	public int compareTo(Carpeta o) {
		// TODO Auto-generated method stub
		return nombre.compareTo(o.getNombre());
	}
	
	
}
