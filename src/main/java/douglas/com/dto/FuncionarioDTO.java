package douglas.com.dto;

import java.util.Set;

/**
 * Data Transfer Object (DTO) para a entidade Funcionario.
 */
public class FuncionarioDTO {

    private String nome;
    private String email;
    private String senha;
    private Set<Long> roles; // IDs dos papéis associados

    public FuncionarioDTO() {
    }

    public FuncionarioDTO( String nome, String email, String senha, Set<Long> roles) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }
}
