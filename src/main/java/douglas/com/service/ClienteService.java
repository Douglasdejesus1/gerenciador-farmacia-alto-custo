package douglas.com.service;

import douglas.com.dto.ClienteDTO;
import douglas.com.model.Cliente;
import douglas.com.persistence.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public void createPesistCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
    }

    @Transactional
    public Cliente getClientById(Long id) {

        return clienteRepository.findClientById(id);
    }

    @Transactional
    public boolean deleteClientById(Long id) {
        Cliente cliente = clienteRepository.findClientById(id);
        if (cliente == null) {
            return false;
        }
        clienteRepository.delete(cliente);
        return true;
    }

    @Transactional
    public boolean atualizarCLiente(Long id, ClienteDTO clienteDTO) {
        // Encontra o cliente pelo ID
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrada");
        }

        // Atualiza os campos do cliente
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setName(clienteDTO.getName());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEmail(clienteDTO.getEmail());

        clienteRepository.persist(cliente); // Salva as alterações
        return true;
    }

    @Transactional
    public List<Cliente> listarClientes() {
        return clienteRepository.listAll();
    }


    public Cliente convertToEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        if (clienteDTO.getCpf() == null) {
            throw new IllegalArgumentException("CPF cannot be null");

        }
        return new Cliente(
                clienteDTO.getCpf(),
                clienteDTO.getName(),
                clienteDTO.getDataNascimento(),
                clienteDTO.getEmail()
        );
    }
}
