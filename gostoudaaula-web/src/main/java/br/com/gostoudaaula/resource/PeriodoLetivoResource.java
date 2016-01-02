package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.service.PeriodoLetivoService;
import br.com.gostoudaaula.utils.ResourceUtils;

@Path("periodoLetivo")
public class PeriodoLetivoResource {

	private PeriodoLetivoService service;

	@Inject
	public PeriodoLetivoResource(PeriodoLetivoService service) {
		this.service = service;
	}
	
	@GET
	@Produces(ResourceUtils.JSONUTF8)
	public Response lista() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(service.getLista()); 
		return Response.ok().entity(json).build();
	}
}
