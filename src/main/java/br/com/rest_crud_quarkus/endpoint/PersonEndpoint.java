package br.com.rest_crud_quarkus.endpoint;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rest_crud_quarkus.dao.person.PersonDAO;
import br.com.rest_crud_quarkus.dao.person.PersonDAOException;
import br.com.rest_crud_quarkus.dto.person.PersonDTO;
import br.com.rest_crud_quarkus.models.Person;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonEndpoint {
	
	@Inject
	protected PersonDAO personDAO; 

    @GET
    @Path("listAll")
    public Response listAll() {
    	return Response.ok(personDAO.listAll()).build();
    }
    
    @POST
    @Path("save")
    public Response save(PersonDTO personDTO) {
    	try {
			personDAO.save(personDTO);
			return Response.ok().build();
		} catch (PersonDAOException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }
    
    @DELETE
    @Path("remove")
    public Response remove(Long idPerson) {
    	personDAO.remove(Person.class, idPerson);
    	return Response.ok().build();
    }
}