package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gostoudaaula.service.AlunoService;

@Path("aluno")
public class AlunoResource {

	private static final String JSONUTF8 = MediaType.APPLICATION_JSON
			+ ";charset=utf-8";
	private AlunoService service;

	@Inject
	public AlunoResource(AlunoService service) {
		this.service = service;
	}

	@GET
	@Produces(JSONUTF8)
	public Response listaAlunos() {
		return Response.ok().entity(service.getLista()).build();
	}

}
