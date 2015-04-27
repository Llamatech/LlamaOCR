package servlets;

//Import required java libraries
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import backend.Carpeta;
import backend.WordProcessor;

import com.llama.tech.utils.list.LlamaArrayList;

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
			String forward = "menu?q=sistema";
			response.sendRedirect(response.encodeRedirectURL(forward));
			
//			if(proc.first_req())
//			{
//				PrintWriter pw = response.getWriter();
//				contextPath = getServletContext().getRealPath(File.separator);
//				response.setCharacterEncoding("UTF-8");
//				File file= new File(contextPath+"archivos.html");
//				FileInputStream fis = new FileInputStream(file);
//
//				StringBuilder sb = new StringBuilder();
//				Carpeta carp = proc.darCarpetaRaiz();
//				generarCarpetas(sb, carp);
//				String relleno = sb.toString();
//	
//				try(BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF8")))
//				{
//					for(String linea = br.readLine();linea!=null;linea=br.readLine())
//					{
//						if(linea.contains("%s"))
//						{
//							pw.println(relleno);
//						}
//						else if(linea.contains("%p"))
//						{
//							pw.println("<textarea disabled name=\"text\" placeholder=\"Message\"></textarea>");
//						}
//						else
//						{
//							pw.println(linea);
//						}
//					}
//				}
//			}
			
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
		File f = new File(contextPath+"files");
		f.mkdir();
		factory.setRepository(f);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.
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
				
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}
	
	
	public void generarCarpetas(StringBuilder wip, Carpeta carp)
	{
		if(!carp.getNombre().equals("default"))
		{
			wip.append("<li>\n");
			wip.append("<input type=\"checkbox\" id=\"item-0\" /><label for=\"item-0\">"+carp.getNombre()+"</label>\n");
			wip.append("<ul>\n");
		}

		for(String s:carp.getCarpetasHijas())
		{
			Carpeta c= proc.darCarpeta(s);
			generarCarpetas(wip,c);
		}

		for(String s: carp.getArchivos())
		{
			wip.append("<li><a href=\"ArchivoServlet?q=\""+ s +"\">"+s+"</a></li>\n" );
		}

		if(!carp.getNombre().equals("default"))
		{
			wip.append("</ul>\n");
			wip.append("</li>\n");
		}
	}
}
