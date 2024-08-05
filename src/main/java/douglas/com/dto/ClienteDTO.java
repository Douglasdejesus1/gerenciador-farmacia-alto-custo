package douglas.com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ClienteDTO {
    private String cpf;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String email;

    // Construtor padrão
    public ClienteDTO() {
    }

    // Construtor com parâmetros
    public ClienteDTO(String cpf, String name, LocalDate dataNascimento, String email) {
        this.cpf = cpf;
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}