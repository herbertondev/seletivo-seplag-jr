package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.EnderecoRequestDTO;
import br.com.projetoseplagjr.dto.EnderecoResponseDTO;
import br.com.projetoseplagjr.mappers.EnderecoMapper;
import br.com.projetoseplagjr.model.Cidade;
import br.com.projetoseplagjr.model.Endereco;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.repository.CidadeRepository;
import br.com.projetoseplagjr.repository.EnderecoRepository;
import br.com.projetoseplagjr.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    EnderecoMapper enderecoMapper;

    @Transactional
    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO enderecoRequest) {
        Cidade cidade = cidadeRepository.findById(enderecoRequest.getCidadeId());
        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada");
        }

        Endereco endereco = enderecoMapper.toEntity(enderecoRequest);
        endereco.setCidade(cidade);
        enderecoRepository.persist(endereco);
        return enderecoMapper.toResponse(endereco);
    }


    @Transactional
    public EnderecoResponseDTO createForPessoa(Long pessoaId, EnderecoRequestDTO enderecoRequest) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId);
        if (pessoa == null) {
            throw new NotFoundException("Pessoa não encontrada");
        }

        Cidade cidade = cidadeRepository.findById(enderecoRequest.getCidadeId());
        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada");
        }

        Endereco endereco = enderecoMapper.toEntity(enderecoRequest);
        endereco.setCidade(cidade);
        enderecoRepository.persist(endereco);

        // Associa o endereço à pessoa
        if (pessoa.getEnderecos() == null) {
            pessoa.setEnderecos(new ArrayList<>());
        }
        pessoa.getEnderecos().add(endereco);
        pessoaRepository.persist(pessoa);

        return enderecoMapper.toResponse(endereco);
    }

    public List<EnderecoResponseDTO> findAll() {
        return enderecoRepository.listAll()
                .stream()
                .map(enderecoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        return enderecoMapper.toResponse(endereco);
    }

    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoRepository.listAll()
                .stream()
                .map(enderecoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EnderecoResponseDTO enderecoPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        return enderecoMapper.toResponse(endereco);
    }

    @Transactional
    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoRequestDTO request) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        Cidade cidade = cidadeRepository.findById(request.getCidadeId());
        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada");
        }
        endereco.setTipoLogradouro(request.getTipoLogradouro());
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(cidade);
        enderecoRepository.persist(endereco);
        return enderecoMapper.toResponse(endereco);
    }

}