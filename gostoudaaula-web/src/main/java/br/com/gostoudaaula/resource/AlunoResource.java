package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.gostoudaaula.json.mixin.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;
import br.com.gostoudaaula.utils.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("aluno")
public class AlunoResource {

	private AlunoService service;

	@Inject
	public AlunoResource(AlunoService service) {
		this.service = service;
	}

	@GET
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaAlunos() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Aluno.class, AlunoMixIn.class);
		String valor = mapper.writeValueAsString(service.getLista());
		return Response.ok().entity(valor).build();
	}

	@POST
	@Produces(ResourceUtils.JSONUTF8)
	public Response salvaAluno(Aluno aluno) {
		System.out.println(aluno);
		service.salva(aluno);
		return Response.ok().build();
	}

}
