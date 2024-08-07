package douglas.com.resource;

import douglas.com.dto.AtendimentoDTO;
import douglas.com.service.AtendimentoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;


@QuarkusTest
class AtendimentoResourceTest {
/*    @InjectMock
    private AtendimentoService atendimentoService; // Mock do serviço

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
       // MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }*/

    @Test
    void testCriarAtendimentoEndpoint() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{"
                        + "\"clienteId\": 2,"
                        + "\"dataHora\": \"2024-08-04T15:30:00\","
                        + "\"tipoAtendimento\": \"RETIRADA_MEDICAMENTO\""
                        + "}")
                .when()
                .post("/atendimentos")
                .then()
                .statusCode(201)
                .body("clienteId", equalTo(2));
    }

    @Test
    void testCriarAtendimentoComDadosInvalidos() {
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        // Defina os campos do DTO com valores inválidos conforme necessário
        atendimentoDTO.setClienteId(null);

        given()
                .contentType(JSON)
                .body(atendimentoDTO)
                .when()
                .post("/atendimentos")
                .then()
                .statusCode(400);
    }
/*    @Test
    void testDeletarAtendimento_Sucesso() {
        Long id = 1L;

        AtendimentoService atendimentoService = mock(AtendimentoService.class);

        // Configurar o comportamento esperado do mock
        when(atendimentoService.deletarAtendimento(anyLong())).thenReturn(true);
        RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .delete("/atendimentos/{id}")
                .then()
                .statusCode(204); // Espera sucesso na exclusão
    }*/


    @Test
    void testDeletarAtendimento_NaoEncontrado() {
        Long id = 999L;

        // Envia a requisição DELETE
        Response response = given()
                .pathParam("id", id)
                .when()
                .delete("/atendimentos/{id}");

        // Verifica se a resposta tem o status 404 Not Found
        response.then()
                .statusCode(404);
    }
}