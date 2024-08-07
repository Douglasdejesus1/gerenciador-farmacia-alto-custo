package douglas.com.dto;

import douglas.com.model.TipoAtendimento;
import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDateTime;

public class AtendimentoDTO {

    private Long clienteId;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;
    private TipoAtendimento tipoAtendimento;

    // Construtor padrão
    public AtendimentoDTO() {
    }

    // Construtor com parâmetros
    public AtendimentoDTO(Long clienteId, LocalDateTime dataHora, TipoAtendimento tipoAtendimento) {
        this.clienteId = clienteId;
        this.dataHora = dataHora;
        this.tipoAtendimento = tipoAtendimento;
    }

    // Getters e Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
}
