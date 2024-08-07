package douglas.com.resource;

import douglas.com.dto.FuncionarioDTO;
import douglas.com.model.Funcionario;
import douglas.com.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/funcionarios")
public class FuncionarioResource {

    @Inject
    FuncionarioService funcionarioService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioService.createFuncionario(funcionarioDTO);
        return Response.ok(funcionario).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFuncionarioById(@PathParam("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listFuncionarios();
        return Response.ok(funcionarios).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFuncionario(@PathParam("id") Long id) {
        boolean deleted = funcionarioService.deleteFuncionario(id);
        return deleted ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFuncionario(@PathParam("id") Long id, FuncionarioDTO funcionarioDTO) {
        boolean updated = funcionarioService.updateFuncionario(id, funcionarioDTO);
        return updated ? Response.ok("Funcionario updated successfully").build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
