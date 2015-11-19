package br.com.gostoudaaula.ws;

import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ObjectMapperFactory extends AbstractContainerRequestValueFactory<ObjectMapper> {

    private static final XmlMapper xmlMapper;
    private static final ObjectMapper jsonMapper;

    static {
        jsonMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    @Override
    public ObjectMapper provide() {
        ContainerRequest req = getContainerRequest();
        String accept = req.getHeaderString("accept");

        return getMapper(accept);
    }

    public ObjectMapper getMapper(String type) {
        if (type.toLowerCase().contains("application/json"))
            return jsonMapper;
        else if (type.toLowerCase().contains("application/xml"))
            return xmlMapper;
        else
            throw new UnsupportedOperationException("Suporte para formatos atual: JSON e XML");
    }
}