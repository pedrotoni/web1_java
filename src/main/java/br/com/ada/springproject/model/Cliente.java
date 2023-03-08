package br.com.ada.springproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cliente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String cpf;

}