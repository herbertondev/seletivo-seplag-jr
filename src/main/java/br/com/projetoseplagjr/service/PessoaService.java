package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.PessoaRequestDTO;
import br.com.projetoseplagjr.dto.PessoaResponseDTO;
import br.com.projetoseplagjr.mappers.PessoaMapper;
import br.com.projetoseplagjr.model.Endereco;
import br.com.projetoseplagjr.model.Lotacao;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.repository.EnderecoRepository;
import br.com.projetoseplagjr.repository.LotacaoRepository;
import br.com.projetoseplagjr.repository.PessoaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaMapper pessoaMapper;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    LotacaoRepository lotacaoRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    public List<PessoaResponseDTO> listarTodasPessoas(int page, int size) {
        List<Pessoa> pessoas = pessoaRepository.findAll().page(Page.of(page, size)).list();
        pessoas.forEach(pessoa -> {
            Hibernate.initialize(pessoa.getLotacoes());
            Hibernate.initialize(pessoa.getEnderecos());
            Hibernate.initialize(pessoa.getFotos());
        });
        return pessoas.stream()
                .map(pessoaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PessoaResponseDTO buscarPessoaPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        Hibernate.initialize(pessoa.getLotacoes());
        Hibernate.initialize(pessoa.getEnderecos());
        Hibernate.initialize(pessoa.getFotos());
        return pessoaMapper.toResponseDTO(pessoa);
    }

    @Transactional
    public PessoaResponseDTO salvarPessoa(PessoaRequestDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        if (pessoaDTO.getLotacaoIds() != null && !pessoaDTO.getLotacaoIds().isEmpty()) {
            List<Lotacao> lotacoes = new ArrayList<>();
            for (Long lotacaoId : pessoaDTO.getLotacaoIds()) {
                Lotacao lotacao = lotacaoRepository.findByIdOptional(lotacaoId)
                        .orElseThrow(() -> new NotFoundException("Lotação com ID " + lotacaoId + " não encontrada"));
                lotacao.setPessoa(pessoa);
                lotacoes.add(lotacao);
            }
            pessoa.setLotacoes(lotacoes);
        }
        if (pessoaDTO.getEnderecoIds() != null && !pessoaDTO.getEnderecoIds().isEmpty()) {
            List<Endereco> enderecos = new ArrayList<>();
            for (Long enderecoId : pessoaDTO.getEnderecoIds()) {
                Endereco endereco = enderecoRepository.findByIdOptional(enderecoId)
                        .orElseThrow(() -> new NotFoundException("Endereço com ID " + enderecoId + " não encontrado"));
                enderecos.add(endereco);
            }
            pessoa.setEnderecos(enderecos);
        }
        pessoaRepository.persist(pessoa);
        return pessoaMapper.toResponseDTO(pessoa);
    }

    @Transactional
    public PessoaResponseDTO atualizarPessoa(Long id, PessoaRequestDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        pessoaMapper.update(pessoa, pessoaDTO);
        if (pessoaDTO.getLotacaoIds() != null) {
            List<Lotacao> lotacoes = new ArrayList<>();
            for (Long lotacaoId : pessoaDTO.getLotacaoIds()) {
                Lotacao lotacao = lotacaoRepository.findByIdOptional(lotacaoId)
                        .orElseThrow(() -> new NotFoundException("Lotação com ID " + lotacaoId + " não encontrada"));
                lotacao.setPessoa(pessoa);
                lotacoes.add(lotacao);
            }
            pessoa.setLotacoes(lotacoes);
        }
        if (pessoaDTO.getEnderecoIds() != null) {
            List<Endereco> enderecos = new ArrayList<>();
            for (Long enderecoId : pessoaDTO.getEnderecoIds()) {
                Endereco endereco = enderecoRepository.findByIdOptional(enderecoId)
                        .orElseThrow(() -> new NotFoundException("Endereço com ID " + enderecoId + " não encontrado"));
                enderecos.add(endereco);
            }
            pessoa.setEnderecos(enderecos);
        }
        pessoaRepository.persist(pessoa);
        return pessoaMapper.toResponseDTO(pessoa);
    }

    public List<PessoaResponseDTO> buscarPessoasPorNome(String nome, int page, int size) {
        List<Pessoa> pessoas = pessoaRepository.buscarPessoasPorNome(nome, Page.of(page, size));
        pessoas.forEach(pessoa -> {
            Hibernate.initialize(pessoa.getLotacoes());
            Hibernate.initialize(pessoa.getEnderecos());
            Hibernate.initialize(pessoa.getFotos());
        });
        return pessoas.stream()
                .map(pessoaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}