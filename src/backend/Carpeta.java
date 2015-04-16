package backend;

import com.llama.tech.utils.list.Lista;

public class Carpeta implements Comparable<Carpeta>
{
	private Lista<String> carpetasHijas;
	
	private String nombre;
	
	private boolean expandida;
	
	private String carpetaPadre;
	
	private Lista<Archivo> archivos;
	
	public void eliminarCarpetaHija(Carpeta c)
	{
		
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

	@Override
	public int compareTo(Carpeta o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
