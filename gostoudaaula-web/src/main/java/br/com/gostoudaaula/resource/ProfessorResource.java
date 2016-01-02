package br.com.gostoudaaula.resource;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.service.ProfessorService;
import br.com.gostoudaaula.utils.ResourceUtils;

@Path("professor")
@Resource
public class ProfessorResource {

	private ProfessorService service;

	@Inject
	public ProfessorResource(ProfessorService service) {
		this.service = service;
	}
	
	@GET
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaProfessor() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Professor.class, ProfessorMixIn.class);
		String json = mapper.writeValueAsString(service.getLista());
		return Response.ok().entity(json).build();
	}
	
	@POST
	@Produces(ResourceUtils.JSONUTF8)
	public Response salvaProfessor(Professor professor){
		service.salva(professor);
		return Response.ok().build();
	}
	
	
	
}
