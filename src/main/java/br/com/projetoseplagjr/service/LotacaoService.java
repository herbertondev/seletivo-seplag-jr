package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.LotacaoDTO;
import br.com.projetoseplagjr.dto.LotacaoResponseDTO;
import br.com.projetoseplagjr.mappers.LotacaoMapper;
import br.com.projetoseplagjr.model.Lotacao;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.model.Unidade;
import br.com.projetoseplagjr.repository.LotacaoRepository;
import br.com.projetoseplagjr.repository.PessoaRepository;
import br.com.projetoseplagjr.repository.UnidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LotacaoService {

    @Inject
    LotacaoRepository lotacaoRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    UnidadeRepository unidadeRepository;

    @Inject
    LotacaoMapper lotacaoMapper;

    @Transactional
    public LotacaoResponseDTO criar(LotacaoDTO lotacaoDTO) {
        // Validações
        if (lotacaoDTO.getPessoaId() == null) {
            throw new IllegalArgumentException("O ID da pessoa é obrigatório");
        }
        if (lotacaoDTO.getUnidadeId() == null) {
            throw new IllegalArgumentException("O ID da unidade é obrigatório");
        }

        // Busca Pessoa e Unidade pelos IDs corretos
        Pessoa pessoa = pessoaRepository.findByIdOptional(lotacaoDTO.getPessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa com ID " + lotacaoDTO.getPessoaId() + " não encontrada"));
        Unidade unidade = unidadeRepository.findByIdOptional(lotacaoDTO.getUnidadeId())
                .orElseThrow(() -> new NotFoundException("Unidade com ID " + lotacaoDTO.getUnidadeId() + " não encontrada"));

        // Converte DTO para entidade
        Lotacao lotacao = lotacaoMapper.toEntity(lotacaoDTO);

        // Associa Pessoa e Unidade explicitamente
        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);

        // Verificação para garantir que unidade não é null
        if (lotacao.getUnidade() == null || lotacao.getUnidade().getId() == null) {
            throw new IllegalStateException("A unidade não foi associada corretamente à lotação");
        }

        // Persiste a lotação
        lotacaoRepository.persist(lotacao);
        return lotacaoMapper.toResponseDTO(lotacao);
    }

    public List<LotacaoResponseDTO> listarTodos() {
        return lotacaoRepository.findAll().stream()
                .map(lotacaoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public LotacaoResponseDTO buscarPorId(Long id) {
        Lotacao lotacao = lotacaoRepository.findById(id);
        if (lotacao == null) {
            throw new NotFoundException("Lotação com ID " + id + " não encontrada");
        }
        return lotacaoMapper.toResponseDTO(lotacao);
    }

    @Transactional
    public LotacaoResponseDTO atualizar(Long id, LotacaoDTO lotacaoDTO) {
        Lotacao lotacao = lotacaoRepository.findById(id);
        if (lotacao == null) {
            throw new NotFoundException("Lotação com ID " + id + " não encontrada");
        }

        if (lotacaoDTO.getPessoaId() != null) {
            Pessoa pessoa = pessoaRepository.findByIdOptional(lotacaoDTO.getPessoaId())
                    .orElseThrow(() -> new NotFoundException("Pessoa com ID " + lotacaoDTO.getPessoaId() + " não encontrada"));
            lotacao.setPessoa(pessoa);
        }

        if (lotacaoDTO.getUnidadeId() != null) {
            Unidade unidade = unidadeRepository.findByIdOptional(lotacaoDTO.getUnidadeId())
                    .orElseThrow(() -> new NotFoundException("Unidade com ID " + lotacaoDTO.getUnidadeId() + " não encontrada"));
            lotacao.setUnidade(unidade);
        }

        lotacaoMapper.updateEntityFromDTO(lotacaoDTO, lotacao);
        lotacaoRepository.persist(lotacao);
        return lotacaoMapper.toResponseDTO(lotacao);
    }

//    @Transactional
//    public void excluir(Long id) {
//        if (!lotacaoRepository.deleteById(id)) {
//            throw new NotFoundException("Lotação com ID " + id + " não encontrada");
//        }
//    }
}