package douglas.com.resource;

import douglas.com.dto.ClienteDTO;

import douglas.com.model.Cliente;
import douglas.com.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
public class ClienteResource {


    @Inject
    ClienteService clienteService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.convertToEntity(clienteDTO);
        clienteService.createPesistCliente(cliente);
        return Response.ok("Cliente created successfully").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientById(@PathParam("id") Long id) {
        Cliente cliente = clienteService.getClientById(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se não encontrar o cliente
        }
        return Response.ok(cliente).build(); // Retorna o cliente se encontrado
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return Response.ok(clientes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClientById(@PathParam("id") Long id) {
        boolean deleted = clienteService.deleteClientById(id);
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
    public Response updateClient(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        boolean updated = clienteService.atualizarCLiente(id, clienteDTO);
        if (updated) {
            return Response.ok("Cliente updated successfully").build(); // Retorna 200 OK se a atualização for bem-sucedida
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se o cliente não for encontrado
        }
    }
}