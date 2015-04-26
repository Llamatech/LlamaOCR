package backend;

<<<<<<< HEAD
import java.io.File;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;

import com.llama.tech.misc.LlamaText.TextSegmentationException;
import com.llama.tech.utils.dict.Dictionary;
import com.llama.tech.utils.dict.LlamaDict;
import com.llama.tech.utils.dict.LlamaDict.UnhashableTypeException;
import com.llama.tech.utils.list.Lista;
import com.llama.tech.utils.list.LlamaArrayList;
import com.llama.tech.utils.list.LlamaIterator;
=======
import com.llama.tech.utils.dict.Dictionary;
import com.llama.tech.utils.list.Lista;
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
import com.llama.tech.utils.tree.LlamaTrie;

public class WordProcessor 
{
	private static WordProcessor instance;
<<<<<<< HEAD
	private LlamaTrie<Lista<Archivo>> trie;
	private Dictionary<String,Carpeta> carpetas;
	private Dictionary<String,Archivo> archivosAux;
	private Carpeta carpetaRaiz;
	//Misc!
	private boolean trie_init = false;
	private FileItem item = null;
	private String fname = null;
	private String folder = null;
	
	public WordProcessor()
	{
		trie = new LlamaTrie<Lista<Archivo>>();
		carpetaRaiz = new Carpeta("default", "");
		carpetas = new LlamaDict<String, Carpeta>(10);
		try {
			carpetas.addEntry("default", carpetaRaiz);
		} catch (UnhashableTypeException e) {
			e.printStackTrace();
		}
		archivosAux = new LlamaDict<String, Archivo>(10);
	}
	
	public boolean init_dict()
	{
		if(!trie_init)
		{
			trie_init = true;
			return false;
		}
		return true;
	}
	
	public Carpeta darCarpetaRaiz()
	{
		return carpetaRaiz;
	}
	
	public LlamaIterator<Carpeta> getCarpetas()
	{
		return carpetas.getValues();
	}

=======
	
	private LlamaTrie<Archivo> trie;
	
	private Dictionary<String,Carpeta> carpetas;
	
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	public static WordProcessor getInstance()
	{
		if(instance == null)
		{
			instance = new WordProcessor();
		}
		return instance;
	}
<<<<<<< HEAD

	

=======
	
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	public void setDictionary(Lista<String> list)
	{
		for(String s : list)
		{
			trie.agregar(s, null);
		}
		System.out.println(trie);
	}
<<<<<<< HEAD

	public void agregarArchivo(String texto, String carpeta, String nombre)
	{
		System.out.println(texto+", "+carpeta+", "+nombre);
		Archivo arch = new Archivo(nombre, texto, false);
		try {
			archivosAux.addEntry(nombre+".ocr", arch);
			Carpeta carp = carpetas.getValue(carpeta);
			carp.agregarArchivo(nombre+".ocr");
		} catch (UnhashableTypeException e) {
			e.printStackTrace();
		}

	}
	
	public Carpeta darCarpeta(String nombre)
	{
		return carpetas.getValue(nombre);
	}
	
	public Archivo darArchivo(String nombre)
	{
		return archivosAux.getValue(nombre);
	}

	public String decodeDocument(String doc, String name) throws TextSegmentationException
	{
		Lista<String> opciones = trie.text_segmentation(doc, true);
		StringBuilder sb = new StringBuilder();
		for(String l: opciones)
		{
			sb.append(l);
			//sb.append("/n");
			//sb.append("/n");
		}
		

		String texto = sb.toString();

		String[] palabras = texto.split(" ");
		Archivo arch = new Archivo(name, texto, true);
		Lista<Archivo> lista = new LlamaArrayList<Archivo>(1);
		lista.addAlFinal(arch);

		try
		{
			for(String s:palabras)
			{
				s = s.replace(".", "");
				s = s.replace(",", "");
				s = s.replace(";", "");
				
				trie.agregar(s, lista);
			}
		}
		catch(IndexOutOfBoundsException e)
		{

		}

		return texto;

	}

	public void eliminarCarpeta(String nombre)
	{
		Carpeta eliminar = carpetas.getValue(nombre);
		Carpeta padre = carpetas.getValue(eliminar.getCarpetaPadre());
		padre.eliminarCarpetaHija(nombre);
	}

	public void añadirCarpeta(String nombre, String padre)
	{
		Carpeta papa = carpetas.getValue(padre);
		if(padre!=null)
		{
			papa.agregarHijo(nombre);
			try {
				carpetas.addEntry(nombre, new Carpeta(nombre, padre));
			} catch (UnhashableTypeException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("no encontre carpeta");
		}
	}

	public Lista<Archivo> darArchivosPorPalabra(String palabra)
	{
		return trie.buscar(palabra); 
	}

	public Lista<Archivo> darArchivosPorPrefijo(String prefijo)
	{
//		return trie.buscarPrefijo(prefijo); 
		Iterator<String> it=trie.buscarPalabrasConPrefijo(prefijo);
		Lista<Archivo> lista = new LlamaArrayList<>(20);
		while(it.hasNext())
		{
			lista.addAll(trie.buscar(it.next()));
		}
		return lista;
		
	}

	public void setFile(FileItem fi) throws Exception 
	{
//		File f = new File("/home/andfoy/"+fi.getName());
//		fi.write(f);
//		item = f;
		item = fi;
		
	}
	
	public void setFileName(String f)
	{
		fname = f;
	}
	
	public void setFolder(String f)
	{
		folder = f;
	}
	
	public FileItem getFile()
	{
		return item;
	}
	
	public String getFileName()
	{
		return fname;
	}
	
	public String getFolder()
	{
		return folder;
	}

	public void resetFile() 
	{
		item = null;
		fname = null;
		folder = null;
		
=======
	
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
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	}
	
	

<<<<<<< HEAD



=======
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
}
