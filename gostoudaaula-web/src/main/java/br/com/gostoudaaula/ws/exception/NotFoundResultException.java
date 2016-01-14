package br.com.gostoudaaula.ws.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@SuppressWarnings("serial")
public class NotFoundResultException extends WebApplicationException {

	public NotFoundResultException(String message) {
		super(Response.status(Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
}
