package douglas.com;

import douglas.com.dto.AtendimentoDTO;
import douglas.com.model.TipoAtendimento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HoraTEst {
    public static void main(String[] args) {
      //  @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String dateTimeString = "2024-01-01T00:00:01"; // Assuming January 1st, 2024, at midnight
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        AtendimentoDTO dto = new AtendimentoDTO(1L, dateTime,  TipoAtendimento.RETIRADA_MEDICAMENTO);
        System.out.println(dto.getDataHora());
        System.out.println(LocalDateTime.now());
    }
}
