package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
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
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Aluno.class, AlunoMixIn.class);
		return Response.ok().entity(service.getLista()).build();
	}

	@POST
	public Response salvaAluno(Aluno aluno) {
		System.out.println(aluno);
		return Response.ok().build();
	}

}
