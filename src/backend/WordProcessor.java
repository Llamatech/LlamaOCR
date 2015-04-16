package backend;

import com.llama.tech.utils.dict.Dictionary;
import com.llama.tech.utils.list.Lista;
import com.llama.tech.utils.tree.LlamaTrie;

public class WordProcessor 
{
	private static WordProcessor instance;
	
	private LlamaTrie<Archivo> trie;
	
	private Dictionary<String,Carpeta> carpetas;
	
	public static WordProcessor getInstance()
	{
		if(instance == null)
		{
			instance = new WordProcessor();
		}
		return instance;
	}
	
	public void setDictionary(Lista<String> list)
	{
		for(String s : list)
		{
			trie.agregar(s, null);
		}
		System.out.println(trie);
	}
	
	public String decodeDocument(String doc)
	{
		return null;
	}
	
	public Lista<Archivo> getDocumentsByIndex(String prefix)
	{
		return null; 
	}
	
	public void eliminarCarpeta(String nombre)
	{
		
	}
	
	public void añadirCarpeta(String nombre, String padre)
	{
		
	}
	
	public Lista<Archivo> darArchivosPorPalabra(String palabra)
	{
		return null;
	}
	
	public Lista<Archivo> darArchivosPorPrefijo(String prefijo)
	{
		return null;
	}
	
	

}
