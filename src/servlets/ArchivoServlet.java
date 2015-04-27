package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Carpeta;
import backend.WordProcessor;

@WebServlet(name = "ArchivoServlet", urlPatterns = {"/verArchivo/*"})
public class ArchivoServlet extends HttpServlet{

	private WordProcessor proc;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		proc = WordProcessor.getInstance();

		String archivo = request.getParameter("q");

		PrintWriter pw = response.getWriter();
		String contextPath = getServletContext().getRealPath(File.separator);
		response.setCharacterEncoding("UTF-8");
		File file;
		if(!archivo.endsWith(".ocr"))
		{
			file = new File(contextPath+"archivos.html");
		}
		else
		{
			file = new File(contextPath+"archivosDecode.html");
		}
		FileInputStream fis = new FileInputStream(file);

		StringBuilder sb = new StringBuilder();
		Carpeta carp = proc.darCarpetaRaiz();
		generarCarpetas(sb, carp);
		String relleno = sb.toString();

		String relleno2 = proc.darArchivo(archivo).getTexto();

		try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
		{
			for(String linea = br.readLine();linea!=null;linea=br.readLine())
			{
				if(linea.contains("%s"))
				{
					pw.println(relleno);
				}
				else if(linea.contains("%p"))
				{
					pw.println("<textarea disabled name=\"text\" placeholder=\"Message\">"+relleno2+"</textarea>");
				}
				else
				{
					pw.println(linea);
				}
			}
		}


	}

	public void generarCarpetas(StringBuilder wip, Carpeta carp)
	{
		if(!carp.getNombre().equals("default"))
		{
			wip.append("<li>\n");
			wip.append("<input type=\"checkbox\" id=\"item-0\" /><label for=\"item-0\">"+carp.getNombre()+"</label>/n");
			wip.append("<ul>\n");
		}

		for(String s:carp.getCarpetasHijas())
		{
			Carpeta c= proc.darCarpeta(s);
			generarCarpetas(wip,c);
		}

		for(String s: carp.getArchivos())
		{
			wip.append("<li><a href=\"verArchivo?q=\""+ s +"\">"+s+"</a></li>\n" );
		}

		if(!carp.getNombre().equals("default"))
		{
			wip.append("</ul>\n");
			wip.append("</li>\n");
		}
	}

}
