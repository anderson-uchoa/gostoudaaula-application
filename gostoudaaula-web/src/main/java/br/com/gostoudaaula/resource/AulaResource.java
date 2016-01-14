package br.com.gostoudaaula.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.service.AulaService;
import br.com.gostoudaaula.utils.ResourceUtils;
import br.com.gostoudaaula.ws.exception.NotFoundResultException;

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
		mapper.addMixIn(Aula.class, AulaMixIn.AssociationWithProfessorMixIn.class)
				.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
				.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class);

		String json = mapper.writeValueAsString(service.getLista());
		return Response.ok().entity(json).build();
	}

	@GET
	@Path("/{prontuario}")
	@Produces(ResourceUtils.JSONUTF8)
	public Response listaAulaDeAluno(@PathParam("prontuario") Integer prontuario) throws JsonProcessingException  {

		List<Aula> aulas = service.getListaDeAulaParaAvaliar(prontuario);

		if (aulas.isEmpty())
			throw new NotFoundResultException("não há aulas para serem avaliadas por esse aluno");

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class)
				.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
				.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class);

		String json = mapper.writeValueAsString(aulas);
		
		return Response.ok().entity(json).build();
	}

	@POST
	@Produces(ResourceUtils.JSONUTF8)
	public Response salvaAula(Aula aula) {
		service.salva(aula);
		return Response.ok().build();
	}

}
