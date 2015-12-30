package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.gostoudaaula.service.AulaService;
import br.com.gostoudaaula.utils.ResourceUtils;

@Path("aula")
public class AulaResource {

	private AulaService service;

	@Inject
	public AulaResource(AulaService service) {
		this.service = service;
	}

	@GET
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaAula() {
		return Response.ok().entity(service.getLista()).build();
	}

}
