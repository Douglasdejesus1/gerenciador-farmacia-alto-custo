package douglas.com.service;

import douglas.com.dto.AtendimentoDTO;
import douglas.com.model.Atendimento;
import douglas.com.model.Cliente;
import douglas.com.persistence.ClienteRepository;
import douglas.com.persistence.AtendimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AtendimentoService {

    @Inject
    AtendimentoRepository atendimentoRepository;

    @Inject
    ClienteRepository clienteRepository;


    @Transactional
    public Atendimento criarAtendimento(AtendimentoDTO atendimentoDTO) throws IllegalArgumentException {
        // Usar o método de conversão
        Atendimento atendimento = convertToEntity(atendimentoDTO);
        atendimentoRepository.persist(atendimento);
        return atendimento;
    }

    public Atendimento buscarAtendimentoPorId(Long id) {

        return atendimentoRepository.findById(id);
    }

    @Transactional
    public List<Atendimento> listarAtendimentos() {
        return atendimentoRepository.listAll();
    }

    @Transactional
    public Atendimento atualizarAtendimento(Long id, AtendimentoDTO atendimentoDTO) {
        Atendimento atendimentoExistente = atendimentoRepository.findById(id);
        if (atendimentoExistente == null) {
            throw new IllegalArgumentException("Atendimento não encontrada");
        }

        Atendimento novaAtendimento = convertToEntity(atendimentoDTO);
        atendimentoExistente.setDataHora(novaAtendimento.getDataHora());
        atendimentoExistente.setTipoAtendimento(novaAtendimento.getTipoAtendimento());
        atendimentoExistente.setCliente(novaAtendimento.getCliente());

        return atendimentoExistente;
    }

    @Transactional
    public boolean deletarAtendimento(Long id) {
        Atendimento atendimento = atendimentoRepository.findById(id);
        if (atendimento == null) {
            return false;
        }
        atendimentoRepository.delete(atendimento);
        return true;
    }

    public Atendimento convertToEntity(AtendimentoDTO atendimentoDTO) {
        Cliente cliente = clienteRepository.findById(atendimentoDTO.getClienteId());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Atendimento atendimento = new Atendimento();
        atendimento.setCliente(cliente);
        atendimento.setDataHora(atendimentoDTO.getDataHora());
        atendimento.setTipoAtendimento(atendimentoDTO.getTipoAtendimento());

        return new Atendimento(
                cliente,
                atendimentoDTO.getDataHora(),
                atendimentoDTO.getTipoAtendimento()

        );
    }

    public AtendimentoDTO convertToDTO(Atendimento atendimento) {
        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setClienteId(atendimento.getCliente().getId());
        dto.setDataHora(atendimento.getDataHora());
        dto.setTipoAtendimento(atendimento.getTipoAtendimento());
        return dto;
    }
    @Transactional
    public void createPesistAtendimento(Atendimento atendimento) {
        atendimentoRepository.persist(atendimento);
    }
}