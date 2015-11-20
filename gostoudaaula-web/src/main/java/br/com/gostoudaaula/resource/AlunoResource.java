package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.gostoudaaula.service.AlunoService;
import br.com.gostoudaaula.utils.ResourceUtils;

@Path("aluno")
public class AlunoResource {

	private AlunoService service;

	@Inject
	public AlunoResource(AlunoService service) {
		this.service = service;
	}

	@GET
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaAlunos() {
		return Response.ok().entity(service.getLista()).build();
	}

}
