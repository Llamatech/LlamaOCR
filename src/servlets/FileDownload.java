package servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.WordProcessor;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet(name = "FileDownload", urlPatterns = {"/download/*"})
public class FileDownload extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private WordProcessor proc;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		proc = WordProcessor.getInstance();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		
		String text = proc.exportXML();
		byte[] byteArray = text.getBytes("UTF-8");
		response.setContentLength(byteArray.length);
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "filesystem.xml");
        response.setHeader(headerKey, headerValue);
        
        OutputStream outStream = response.getOutputStream();
        outStream.write(byteArray);
        outStream.close();
        
        //String forward = "menu?q=sistema";
		//response.sendRedirect(response.encodeRedirectURL(forward));
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
