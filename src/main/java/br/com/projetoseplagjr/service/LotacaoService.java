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
        Lotacao lotacao = lotacaoMapper.toEntity(lotacaoDTO);


        Pessoa pessoa = pessoaRepository.findById(lotacaoDTO.getId());
        Unidade unidade = unidadeRepository.findById(lotacaoDTO.getId());

        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);

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
        return lotacaoMapper.toResponseDTO(lotacao);
    }

    @Transactional
    public LotacaoResponseDTO atualizar(Long id, LotacaoDTO lotacaoDTO) {
        Lotacao lotacao = lotacaoRepository.findById(id);

        if (lotacaoDTO.getPessoaId() != null) {
            Pessoa pessoa = pessoaRepository.findById(lotacaoDTO.getPessoaId());
            lotacao.setPessoa(pessoa);
        }

        if (lotacaoDTO.getUnidadeId() != null) {
            Unidade unidade = unidadeRepository.findById(lotacaoDTO.getUnidadeId());
            lotacao.setUnidade(unidade);
        }

        lotacaoMapper.updateEntityFromDTO(lotacaoDTO, lotacao);
        lotacaoRepository.persist(lotacao);
        return lotacaoMapper.toResponseDTO(lotacao);
    }

    @Transactional
    public void excluir(Long id) {
        lotacaoRepository.deleteById(id);
    }
}