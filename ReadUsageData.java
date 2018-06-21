package com.pidata.read;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class ReadUsageData
 */
public class ReadUsageData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadUsageData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
//		String q = "&begin=2018-05-28 00:00:00.0&end=2018-06-04 00:00:00.0&detailedStatus=true";
		URI baseUri = null;
		URI uri = null;
		try {
			baseUri = new URI("http://piserver:port/mdt/messageoverviewqueryservlet");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String encodedCredentials = "YXVzaW5naHZpOlNhcmVnYW1hMUA=";
        
        String[] paramValues = {"component", "_AE_NAME", "view", "SR_ENTRY_OVERVIEW_XPI","begin", "2018-06-07 00:00:00.0","end","2018-06-08 00:00:00.0"
        		,"detailedStatus","false"};
        
        try {
			uri = applyParameters(baseUri, paramValues);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		HttpURLConnection servletConnection = (HttpURLConnection) uri.toURL().openConnection();
		servletConnection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
		servletConnection.setConnectTimeout(300000);
		servletConnection.setRequestMethod("GET");
        servletConnection.setDoOutput(true);
        
        servletConnection.connect();
        
//        InputStream response1 = servletConnection.getInputStream();
        
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(servletConnection.getInputStream());
			
			Node messageStatisticsQueryResults = doc.getFirstChild();
			NodeList nl = messageStatisticsQueryResults.getChildNodes();
			
			String data = null;
			for (int i=0; i < nl.getLength(); i++) {
				Node an2 = nl.item(i);
				

				
					data = data + an2.getNodeValue() +  an2.getPrefix() + an2.getTextContent() +  an2.getBaseURI() 
					+  an2.getLocalName() +  an2.getNamespaceURI() ;
				
				
				
				
				}
				
				
				
			
			
			response.getWriter().write( data );
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        
//        String outputData = readFullyAsString(servletConnection.getInputStream(), "UTF-8");
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public String readFullyAsString(InputStream inputStream, String encoding) throws IOException {
        return readFully(inputStream).toString(encoding);
    }
	
	
	URI applyParameters(URI baseUri, String[] urlParameters) throws URISyntaxException, UnsupportedEncodingException{
		StringBuilder query = new StringBuilder();
		boolean first = true;
		
		   for (int i = 0; i < urlParameters.length; i += 2) {
			     if (first) {
			        first = false;
			     } else {
			        query.append("&");
			     }

			     query.append(urlParameters[i]).append("=").append(urlParameters[i + 1]);

			     
		   }
		   
		   
		
				return new URI(baseUri.getScheme(), baseUri.getAuthority(), baseUri.getPath(), query.toString(), null);
		      
		
		}


    private ByteArrayOutputStream readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos;
    }	

}
