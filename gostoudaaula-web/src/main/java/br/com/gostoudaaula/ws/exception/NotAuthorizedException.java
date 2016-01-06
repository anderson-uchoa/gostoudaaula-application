package br.com.gostoudaaula.ws.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@SuppressWarnings("serial")
public class NotAuthorizedException extends WebApplicationException {

	public NotAuthorizedException(String message) {
		super(Response.status(Status.UNAUTHORIZED).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

}
