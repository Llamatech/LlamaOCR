package backend;

import com.llama.tech.utils.list.Lista;
<<<<<<< HEAD
import com.llama.tech.utils.list.LlamaArrayList;
=======
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52

public class Carpeta implements Comparable<Carpeta>
{
	private Lista<String> carpetasHijas;
	
	private String nombre;
	
	private boolean expandida;
	
<<<<<<< HEAD
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
	
=======
	private String carpetaPadre;
	
	private Lista<Archivo> archivos;
	
	public void eliminarCarpetaHija(Carpeta c)
	{
		
	}
	
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
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
<<<<<<< HEAD
	
	public void agregarArchivo(String archivo)
	{
		archivos.addAlFinal(archivo);
	}
=======
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52

	@Override
	public int compareTo(Carpeta o) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return nombre.compareTo(o.getNombre());
=======
		return 0;
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	}
	
	
}
