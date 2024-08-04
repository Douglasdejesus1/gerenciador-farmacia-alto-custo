package douglas.com.resource;

import douglas.com.dto.ClientDTO;

import douglas.com.model.Client;
import douglas.com.service.ClientService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clients")
public class ClientResource {


    @Inject
    ClientService clientService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createClient(ClientDTO clientDTO) {
        Client client = clientService.convertToEntity(clientDTO);
        clientService.createPesistClient(client);
        return Response.ok("Client created successfully").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientById(@PathParam("id") Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se não encontrar o cliente
        }
        return Response.ok(client).build(); // Retorna o cliente se encontrado
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClientById(@PathParam("id") Long id) {
        boolean deleted = clientService.deleteClientById(id);
        if (deleted) {
            return Response.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se o cliente não for encontrado
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClient(@PathParam("id") Long id, ClientDTO clientDTO) {
        boolean updated = clientService.updateClient(id, clientDTO);
        if (updated) {
            return Response.ok("Client updated successfully").build(); // Retorna 200 OK se a atualização for bem-sucedida
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se o cliente não for encontrado
        }
    }
}