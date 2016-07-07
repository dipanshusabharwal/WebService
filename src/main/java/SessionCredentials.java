package main.java;

import com.opentok.OpenTok;
import com.opentok.exception.OpenTokException;
import java.io.IOException;
import java.io.StringWriter;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

@Path("/SessionAndToken")

public class SessionCredentials {
	
	final int API_KEY = 45618232;
	final String API_SECRET = "09f6db96849092b8c0584c63c63e495ebd82961f";
	final String SESSION_ID = "1_MX40NTYxODIzMn5-MTQ2Nzg3MjYwMDU3NX41alR1eW9abHJuMEdJYlZJQUV5VStzemh-fg";
	String TOKEN = "";
	String jsonOutput = "";
	
	@GET
	@Produces("application/json")
    public Response returnSessionAndToken() throws OpenTokException, IOException {	
    	try{  		
        	OpenTok otObject = new OpenTok(API_KEY, API_SECRET);
            TOKEN = otObject.generateToken(SESSION_ID);        
    		JSONObject jObject = new JSONObject();    		
    		jObject.put("Token", TOKEN);
    		jObject.put("SessionID", SESSION_ID);
    	    StringWriter out = new StringWriter();
    	    jObject.writeJSONString(out); 	      
    	    jsonOutput = out.toString();  
    	    System.out.println(jsonOutput);
    	    return Response.status(200).entity(jsonOutput).build();
    	}    	
    	catch(OpenTokException e){
			e.printStackTrace();
			return Response.status(404).entity("Could not generate token").build();    		
    	}
    }
}

