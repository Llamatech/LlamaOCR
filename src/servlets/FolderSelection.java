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

import com.llama.tech.utils.list.LlamaIterator;

import backend.Carpeta;
import backend.WordProcessor;

/**
 * Servlet implementation class FolderSelection
 */
@WebServlet(name = "Foo", urlPatterns = {"/carpetas/*"})
public class FolderSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WordProcessor proc;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter pw = response.getWriter();
		String contextPath = getServletContext().getRealPath(File.separator);
		response.setCharacterEncoding("UTF-8");
		File file;
		if(request.getParameter("q").equals("nueva"))
			file= new File(contextPath+"eliminarCarpeta.html");
		else
			file= new File(contextPath+"nuevaCarpeta.html");
		FileInputStream fis = new FileInputStream(file);


		try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
		{
			for(String linea = br.readLine();linea!=null;linea=br.readLine())
			{
				if(linea.contains("%s"))
				{
					pw.println(listaCarpetas());
				}
				else
				{
					pw.println(linea);
				}
			}
		}
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

}
