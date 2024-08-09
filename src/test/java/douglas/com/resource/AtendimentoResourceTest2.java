package douglas.com.resource;

import douglas.com.dto.AtendimentoDTO;
import douglas.com.model.Atendimento;
import douglas.com.model.Cliente;
import douglas.com.model.TipoAtendimento;
import douglas.com.service.AtendimentoService;
import douglas.com.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

@QuarkusTest
@Transactional
class AtendimentoResourceTest2 {
    @Inject
    AtendimentoService atendimentoService;
    @Inject
    ClienteService clienteService;

    private Long clienteId;
    private Long atendimentoId;

    @BeforeEach
    @Transactional
    public void setup() {


            // Criar um cliente para usar nos testes
        Cliente cliente = new Cliente();
        cliente.setName("Cliente Teste");
        clienteService.createPesistCliente(cliente); // Método fictício para criar cliente
        clienteId = cliente.getId();

        // Criar um atendimento para testar os métodos GET, PUT e DELETE
        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setClienteId(clienteId);
        dto.setDataHora(LocalDateTime.now());
        dto.setTipoAtendimento(TipoAtendimento.RETIRADA_MEDICAMENTO);

        Atendimento atendimento = atendimentoService.convertToEntity(dto);
        atendimentoService.createPesistAtendimento(atendimento);
        atendimentoId = atendimento.getId();
    }

    @Test
    public void testCriarAtendimento_Sucesso() {
        String jsonBody = "{"
                + "\"clienteId\": 1,"
                + "\"dataHora\": \"2024-08-04T15:30:00\","
                + "\"tipoAtendimento\": \"RETIRADA_MEDICAMENTO\""
                + "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/atendimentos")
                .then()
                .statusCode(201)
                .body("clienteId", equalTo(1))
                .body("tipoAtendimento", equalTo("RETIRADA_MEDICAMENTO"));
    }

    @Test
    public void testCriarAtendimento_Falha() {
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        // Defina os campos do DTO com valores inválidos conforme necessário
        atendimentoDTO.setClienteId(null);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(atendimentoDTO)
                .when()
                .post("/atendimentos")
                .then()
                .statusCode(400); // Bad Request esperado
    }

    @Test
    public void testBuscarAtendimentoPorId_Sucesso() {
        RestAssured.given()
                .pathParam("id", 1L)
                .when()
                .get("/atendimentos/{id}")
                .then()
                .statusCode(200)
                .body("tipoAtendimento", equalTo("RENOVACAO_PROCESSO"));
    }

    @Test
    public void testBuscarAtendimentoPorId_Falha() {
        RestAssured.given()
                .pathParam("id", 999) // ID que não existe
                .when()
                .get("/atendimentos/{id}")
                .then()
                .statusCode(404); // Not Found esperado
    }

    @Test
    public void testAtualizarAtendimento_Sucesso() {
        String jsonBody = "{"
                + "\"clienteId\": 2,"
                + "\"dataHora\": \"2024-08-04T15:30:00\","
                + "\"tipoAtendimento\": \"RETIRADA_MEDICAMENTO\""
                + "}";
        // Atualizar tipoAtendimento

        RestAssured.given()
                .pathParam("id", atendimentoId)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/atendimentos/{id}")
                .then()
                .statusCode(200)
                //.body("dataHora", equalTo(dto.getDataHora().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .body("tipoAtendimento", equalTo("RETIRADA_MEDICAMENTO"));
    }

    @Test
    public void testAtualizarAtendimento_Falha() {
        String jsonBody = "{"
                + "\"clienteId\": 2,"
                + "\"dataHora\": \"2024-08-04T15:30:00\","
                + "\"tipoAtendimento\": \"RETIRADA_MEDICAMENTO\""
                + "}";

        RestAssured.given()
                .pathParam("id", 999) // ID que não existe
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/atendimentos/{id}")
                .then()
                .statusCode(404); // Not Found esperado
    }

    @Test
    public void testDeletarAtendimento_Sucesso() {
        RestAssured.given()
                .pathParam("id", atendimentoId)
                .when()
                .delete("/atendimentos/{id}")
                .then()
                .statusCode(204); // No Content esperado
    }

    @Test
    public void testDeletarAtendimento_Falha() {
        RestAssured.given()
                .pathParam("id", 999) // ID que não existe
                .when()
                .delete("/atendimentos/{id}")
                .then()
                .statusCode(404); // Not Found esperado
    }
}