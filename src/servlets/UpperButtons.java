package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.llama.tech.utils.list.LlamaIterator;

import backend.Carpeta;
import backend.WordProcessor;

@WebServlet(name = "UpperButtons", urlPatterns = {"/menu/*"})

public class UpperButtons extends HttpServlet{

	private WordProcessor proc;
	private PrintWriter pw;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException 
	{
		String contextPath = getServletContext().getRealPath(File.separator);
		proc = WordProcessor.getInstance();
		response.setCharacterEncoding("UTF-8");
		pw = response.getWriter();
		if(request.getParameter("q").equals("sistema"))
		{
			//			String contextPath = "archivos.html";
			//			response.sendRedirect(response.encodeRedirectURL(contextPath));

			StringBuilder sb = new StringBuilder();
			Carpeta carp = proc.darCarpetaRaiz();
			generarCarpetas(sb, carp);
			String relleno = sb.toString();

			File file = new File(contextPath+"archivos.html");
			FileInputStream fis = new FileInputStream(file);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
			{
				for(String linea = br.readLine();linea!=null;linea=br.readLine())
				{
					if(linea.contains("%s"))
					{
						pw.println(relleno);
					}
					else
					{
						pw.println(linea);
					}
				}
			}
		}
		else if(request.getParameter("q").equals("cargar"))
		{			
			System.out.println(contextPath);
			//String contextPath = "subirArchivo.html";
			File f = new File(contextPath+"subirArchivo.html");
			StringBuilder contentB = new StringBuilder();
			FileInputStream fis = new FileInputStream(f);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
			{
				String line;
				while((line = br.readLine()) != null)
				{
					contentB.append(line);
				}
			}

			String content = contentB.toString();
			String replacement = listaCarpetas();
			content = String.format(content, replacement);
			response.getWriter().write(content);
			//response.sendRedirect(response.encodeRedirectURL(contextPath));
		}
		
		pw.close();

	}

	public String listaCarpetas()
	{
		LlamaIterator<Carpeta> carpetas = proc.getCarpetas();
		StringBuilder sb= new StringBuilder();
		while(carpetas.hasNext())
		{
			String c = carpetas.next().getNombre();
			if(!c.contains("default"))
			{
				sb.append("<option>"+c +"</option>");
			}
		}
		
		return sb.toString();
	}

	public void generarCarpetas(StringBuilder wip, Carpeta carp)
	{
		if(!carp.getNombre().equals("default"))
		{
			wip.append("<li>/n");
			wip.append("<input type=\"checkbox\" id=\"item-0\" /><label for=\"item-0\">"+carp.getNombre()+"</label>/n");
			wip.append("<ul>/n");
		}

		for(String s:carp.getCarpetasHijas())
		{
			Carpeta c= proc.darCarpeta(s);
			generarCarpetas(wip,c);
		}

		for(String s: carp.getArchivos())
		{
			wip.append("<li><a href=\"ArchivoServlet?q="+ s +"\">"+s+"</a></li>/n" );
		}

		if(!carp.getNombre().equals("default"))
		{
			wip.append("</ul>/n");
			wip.append("</li>/n");
		}
	}

}
