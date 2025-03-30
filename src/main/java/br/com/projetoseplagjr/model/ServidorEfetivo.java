package br.com.projetoseplagjr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servidor_efetivo")
@Getter
@Setter
public class ServidorEfetivo {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "se_matricula", length = 20, nullable = false)
    private String matricula;
}
