package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.WordProcessor;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/home"})
public class MainServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private WordProcessor proc;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String contextPath = getServletContext().getRealPath(File.separator);
		proc = WordProcessor.getInstance();
	    if(!proc.initialized())
	    {
	    	String forward = "index.html";
			response.sendRedirect(response.encodeRedirectURL(forward));
	    }
	    else
	    {
	    	String forward = "mensajeBienvenida.html";
			response.sendRedirect(response.encodeRedirectURL(forward));
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		throw new ServletException("POST method used with "
				+ getClass().getName() + ": GET method required.");
	}

}
