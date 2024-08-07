package douglas.com.service;

import douglas.com.dto.FuncionarioDTO;
import douglas.com.model.Funcionario;
import douglas.com.persistence.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FuncionarioService {

    @Inject
    FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario createFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setSenha(funcionarioDTO.getSenha());
        funcionario.setRoles(new HashSet<>()); // Inicializa os papéis

        funcionarioRepository.persist(funcionario);

        return funcionario;
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return Optional.ofNullable(funcionarioRepository.findById(id));
    }

    public List<Funcionario> listFuncionarios() {
        return funcionarioRepository.listAll();
    }

    @Transactional
    public boolean deleteFuncionario(Long id) {
        Optional<Funcionario> funcionario = getFuncionarioById(id);
        if (funcionario.isPresent()) {
            funcionarioRepository.delete(funcionario.get());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> funcionarioOptional = getFuncionarioById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setNome(funcionarioDTO.getNome());
            funcionario.setEmail(funcionarioDTO.getEmail());
            funcionario.setSenha(funcionarioDTO.getSenha());
            // Atualize os papéis conforme necessário
            return true;
        }
        return false;
    }
}
