package servlets;

//Import required java libraries
<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

=======
import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
<<<<<<< HEAD
=======
import org.apache.commons.io.output.*;
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52

import backend.WordProcessor;

import com.llama.tech.utils.list.LlamaArrayList;

<<<<<<< HEAD
@WebServlet(name = "FileUpload", urlPatterns = {"/enviar/*"})

public class FileUpload extends HttpServlet 
{
	public static final String RUTA_ARCHIVO="mensajeBienvenida.html";

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	private static boolean dictLoad = false;
	private static long timeRef; 
	private WordProcessor proc;
	

	public void init() 
	{
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
		timeRef = System.currentTimeMillis();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException 
	{
		if(request.getParameter("t").equals("uploadDict"))
		{
			proc = WordProcessor.getInstance();
			boolean init = proc.init_dict();
			if(!init)
			{
				dictionaryUpload(request, response);
			}
			else
			{
				String forward = "mensajeBienvenida.html";
				response.sendRedirect(response.encodeRedirectURL(forward));
			}
		}
		else if(request.getParameter("t").equals("uploadFile"))
		{
			textUpload(request, response);
		}
		
	}
	
	private void textUpload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		String contextPath = getServletContext().getRealPath(File.separator);
		proc = WordProcessor.getInstance();
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (!isMultipart) 
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		File f = new File("/home/andfoy");
		f.mkdir();
		factory.setRepository(f);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		
		List<FileItem> fileItems;
		String text = null;
		try
		{
			fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator<FileItem> i = fileItems.iterator();
			boolean[] status = {false, false};
			int j = 0;
			while(i.hasNext())
			{
				FileItem fi = i.next();
				if(fi.isFormField())
				{
			        if(fi.getFieldName().equals("text"))
			        {
			        	String content = fi.getString("UTF8");
						if(content.length() > 0)
						{
							text = content;
						}
			        }
			        else if(fi.getFieldName().equals("fname"))
			        {
			        	String content = fi.getString("UTF8");
						if(content.length() > 0)
						{
							proc.setFileName(content);
						}
			        }
			        else
			        {
			        	String content = fi.getString("UTF8");
						if(content.length() > 0)
						{
							proc.setFolder(content);
						}
			        }
				}
				else
				{
					String fileName = fi.getName();
					if(fileName.matches("^\\w+([.]?\\w*)*$"))
					{
						proc.setFile(fi);
						status[1] = true;
					}
				}
				j++;
			}
			
			String fname = proc.getFileName();
			String folder = proc.getFolder();
			if(text != null)
			{
				proc.agregarArchivo(text, folder, fname);
			}
			else
			{
				if(fname != null && folder != null)
				{
					text = "";
					FileItem fi = proc.getFile();
					//FileInputStream fis = new FileInputStream(fi);
					try (BufferedReader br = new BufferedReader(
							new InputStreamReader(fi.getInputStream(), "UTF8"))) 
					{
						String line;
						while ((line = br.readLine()) != null) 
						{
						    text += line;
						}
					}
					
					proc.agregarArchivo(text, folder, fname);
					proc.resetFile();
					
				}
			}
			
			//proc.agregarArchivo(text, folder, f_name);
			String forward = "mensajeBienvenida.html";
			response.sendRedirect(response.encodeRedirectURL(forward));
			
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}

		
	}

	public void dictionaryUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException 
	{
		String contextPath = getServletContext().getRealPath(File.separator);
		proc = WordProcessor.getInstance();
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
=======
@WebServlet(name = "Foo", urlPatterns = {"/enviar/*"})

public class FileUpload extends HttpServlet 
{

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	private WordProcessor proc;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
		if (!isMultipart) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
<<<<<<< HEAD
		File f = new File(contextPath+"files");
		f.mkdir();
		factory.setRepository(f);
=======
		factory.setRepository(new File("/home/andfoy/"));
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
<<<<<<< HEAD
			List<FileItem> fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator<FileItem> i = fileItems.iterator();
			boolean[] status = {false, false};
			
			while(i.hasNext())
			{
				FileItem fi = i.next();
				if(fi.isFormField())
				{
					String content = fi.getString();
					if(content.length() > 0)
					{
						status[0] = true;
					}
				}
				else
				{
					String fileName = fi.getName();
					if(fileName.matches("^\\w+([.]?\\w*)*$"))
					{
						status[1] = true;
					}
				}
			}
			
			i = fileItems.iterator();
			LlamaArrayList<String> list = new LlamaArrayList<String>(10);
			
			if(status[1]) 
			{
				while (i.hasNext()) 
				{
					FileItem fi = i.next();
					if (!fi.isFormField()) 
					{
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						// Write the file
//						if (fileName.lastIndexOf("\\") >= 0) 
//						{
//							file = new File(filePath
//									+ fileName.substring(fileName
//											.lastIndexOf("\\")));
//						} 
//						else 
//						{
//							file = new File(filePath
//									+ fileName.substring(fileName
//											.lastIndexOf("\\") + 1));
//						}
						// fi.write(file);
						// out.println("Uploaded Filename: " + fileName +
						// "<br>");
						//FileInputStream fis = new FileInputStream();
						try (BufferedReader br = new BufferedReader(
								new InputStreamReader(fi.getInputStream(), "UTF8"))) 
						{
							String line;
							while ((line = br.readLine()) != null) 
							{
								// out.println(line);
								list.addAlFinal(line);
							}
						}
					}
				}
			}
			else if(status[0])
			{
				while (i.hasNext()) 
				{
					FileItem fi = i.next();
					if(fi.isFormField()) 
					{
						String content = fi.getString("UTF-8");
						String[] parts = content.split("\r\n");
						for(String s: parts)
						{
							if(!s.equals(""))
							{
								list.addAlFinal(s);
							}
						}
					}
				}
			}

			proc = WordProcessor.getInstance();
			proc.setDictionary(list);

			String forward = "mensajeBienvenida.html";
			response.sendRedirect(response.encodeRedirectURL(forward));
			
			
		} 
		catch (Exception ex) 
		{
			throw new ServletException(ex.getMessage());
		}
				
=======
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");
			out.println("</head>");
			out.println("<body>");
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					out.println("Uploaded Filename: " + fileName + "<br>");
                    LlamaArrayList<String> list = new LlamaArrayList<String>(10);
					FileInputStream fis = new FileInputStream(file);
					try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
					{
						String line;
						while((line = br.readLine()) != null)
						{
							out.println(line);
							list.addAlFinal(line);
						}
					}
					
					proc = WordProcessor.getInstance();
					proc.setDictionary(list);
					
					
				}
			}
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			System.out.println(ex);
		}
>>>>>>> 7fe12a07a8aa0b489c9ec58fc069fecf996dda52
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
}
