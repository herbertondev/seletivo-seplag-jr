package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.FotoResponseDTO;
import br.com.projetoseplagjr.model.FotoPessoa;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.repository.FotoPessoaRepository;
import br.com.projetoseplagjr.repository.PessoaRepository;
import io.minio.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@ApplicationScoped
public class FotoService {

    private static final String BUCKET_NAME = "pessoas-fotos";

    @Inject
    MinioClient minioClient;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    FotoPessoaRepository fotoPessoaRepository;

    @Transactional
    public FotoPessoa uploadFotosPessoa(Long pessoaId, FileUpload fotos) throws Exception {
        if (pessoaRepository.findById(pessoaId) == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }

        String objectName = "fotos/" + pessoaId + ".jpg";
        Path filePath = fotos.uploadedFile();
        String fileHash = calculateHash(filePath);

        // Faz o upload para o MinIO (sobrescreve o arquivo existente)
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .filename(filePath.toString())
                        .contentType(fotos.contentType())
                        .build()
        );

        // Verifica se já existe uma foto para essa pessoa
        FotoPessoa fotoPessoa = fotoPessoaRepository.find("pessoaId", pessoaId).firstResult();
        if (fotoPessoa == null) {
            // Se não existe, cria um novo registro
            fotoPessoa = new FotoPessoa();
            fotoPessoa.setPessoaId(pessoaId);
        }

        // Atualiza os dados
        fotoPessoa.setData(LocalDate.now());
        fotoPessoa.setBucket(BUCKET_NAME);
        fotoPessoa.setHash(fileHash);
        fotoPessoaRepository.persist(fotoPessoa);

        return fotoPessoa;
    }

    public FotoResponseDTO buscarFotoPorPessoaId(Long pessoaId) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(pessoaId);
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }

        FotoPessoa foto = fotoPessoaRepository.find("pessoaId", pessoaId).firstResult();
        if (foto == null) {
            throw new IllegalArgumentException("Foto não encontrada para esta pessoa");
        }

        InputStream imageStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(foto.getBucket())
                        .object("fotos/" + pessoaId + ".jpg")
                        .build()
        );

        return new FotoResponseDTO(pessoa.getNome(), imageStream);
    }

    private String calculateHash(Path filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            byte[] byteArray = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}