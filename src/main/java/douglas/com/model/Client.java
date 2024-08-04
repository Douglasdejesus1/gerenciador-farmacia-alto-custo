package douglas.com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Client extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpf;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String email;

    public Client()  {
    }

    public Client(String cpf, String name, LocalDate dataNacimento, String email) {
        this.cpf=cpf;
        this.name = name;
        this.dataNascimento = dataNacimento;
        this.email = email;
    }

     public long getId() {
         return id;
     }

     public void setId(long id) {
         this.id = id;
     }

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
   