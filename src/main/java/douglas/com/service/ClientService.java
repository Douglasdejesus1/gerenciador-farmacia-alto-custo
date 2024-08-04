package douglas.com.service;

import douglas.com.dto.ClientDTO;
import douglas.com.model.Client;
import douglas.com.persistence.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientService {
    @Inject
    ClientRepository clientRepository;
    @Transactional
    public void createPesistClient(Client client) {
        clientRepository.persist(client);
    }

    @Transactional
    public Client getClientById(Long id) {
        return clientRepository.findClientById(id);
    }

    @Transactional
    public boolean deleteClientById(Long id) {
        return clientRepository.deleteClientById(id);
    }
    @Transactional
    public boolean updateClient(Long id, ClientDTO clientDTO) {
        // Encontra o cliente pelo ID
        Client client = clientRepository.findById(id);
        if (client == null) {
            return false; // Cliente não encontrado
        }

        // Atualiza os campos do cliente
        client.setCpf(clientDTO.getCpf());
        client.setName(clientDTO.getName());
        client.setDataNascimento(clientDTO.getDataNascimento());
        client.setEmail(clientDTO.getEmail());

        clientRepository.persist(client); // Salva as alterações
        return true;
    }


    public Client convertToEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        if (clientDTO.getCpf() == null) {
            throw new IllegalArgumentException("CPF cannot be null");

        }
        return new Client(
                clientDTO.getCpf(),
                clientDTO.getName(),
                clientDTO.getDataNascimento(),
                clientDTO.getEmail()
        );
    }
}
