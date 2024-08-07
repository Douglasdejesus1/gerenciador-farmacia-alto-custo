package douglas.com.resource;

import douglas.com.dto.AtendimentoDTO;
import douglas.com.model.Atendimento;
import douglas.com.model.Cliente;
import douglas.com.service.AtendimentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/atendimentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtendimentoResource {

    @Inject
    AtendimentoService atendimentoService;


    @POST
    public Response criarAtendimento(AtendimentoDTO atendimentoDTO) {
        try {
            Atendimento atendimento = atendimentoService.convertToEntity(atendimentoDTO);
            atendimentoService.createPesistAtendimento(atendimento);
            AtendimentoDTO responseDTO = atendimentoService.convertToDTO(atendimento);

            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarAtendimentoPorId(@PathParam("id") Long id) {
        Atendimento atendimento = atendimentoService.buscarAtendimentoPorId(id);
        if (atendimento == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(atendimento).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAtendimento() {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentos();
        return Response.ok(atendimentos).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarAtendimento(@PathParam("id") Long id, AtendimentoDTO novoAtendimento) {
        try {
            Atendimento atendimentoAtualizada = atendimentoService.atualizarAtendimento(id, novoAtendimento);
            return Response.ok(atendimentoAtualizada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAtendimento(@PathParam("id") Long id) {
        boolean deletado = atendimentoService.deletarAtendimento(id);
        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}