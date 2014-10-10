import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path ("/file")
public class FileResource {

  
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  public Response uploadFile(@FormDataParam("uploadfile") InputStream uploadedInputStream,
                             @FormDataParam("uploadfile") FormDataContentDisposition fileDetail) {
    try (
      BufferedReader reader = new BufferedReader(new InputStreamReader(uploadedInputStream));
    ) {
      int numLines = 0;
      String line = null;
      while( (line = reader.readLine()) != null ) {
        numLines++;
        System.out.println(line);
      }
      return Response.ok(Integer.toString(numLines), "text/plain").build();
    } catch (final Exception e) {
      throw new WebApplicationException(e);
    }
  }
}
