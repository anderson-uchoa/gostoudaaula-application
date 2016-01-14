package br.com.gostoudaaula.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;
import br.com.gostoudaaula.utils.ResourceUtils;
import br.com.gostoudaaula.ws.exception.NotAuthorizedException;

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
		mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
		// mapper.addMixIn(Aluno.class, AlunoMixIn.MainMixIn.class)
		// .addMixIn(Aula.class, AulaMixIn.AssociationWithProfessorMixIn.class)
		// .addMixIn(PeriodoLetivo.class,PeriodoLetivoMixIn.AssociationMixIn.class)
		// .addMixIn(Avaliacao.class, AvaliacaoMixIn.AssociationMixIn.class)
		// .addMixIn(Projeto.class, ProjetoMixIn.AssociationMixIn.class);
		String json = mapper.writeValueAsString(service.getLista());
		return Response.ok().entity(json).build();
	}

	@POST
	@Produces(ResourceUtils.JSONUTF8)
	public Response salvaAluno(Aluno aluno) {
		service.salva(aluno);
		return Response.ok().build();
	}

	@POST
	@Path("/auth")
	@Produces(ResourceUtils.JSONUTF8)
	public Response autentica(Aluno aluno) throws JsonProcessingException {
		if (service.autentica(aluno)) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
			String json = mapper.writeValueAsString(service.retorna(aluno));
			return Response.ok().entity(json).build();
		}
		throw new NotAuthorizedException("Erro na autenticação");
	}
	
	@GET
	@Path("/aulas")
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaDeAulas(Aluno aluno){
		return null;
	}

}
