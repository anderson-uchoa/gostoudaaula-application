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
import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
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
	public Response listaAula() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class).addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class)
				.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
				.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class);
		String json = mapper.writeValueAsString(service.getLista());
		return Response.ok().entity(json).build();
	}

	@POST
	@Produces(ResourceUtils.JSONUTF8)
	public Response salvaAula(Aula aula) {
		service.salva(aula);
		return Response.ok().build();
	}

}
