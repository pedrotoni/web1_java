package br.com.ada.springproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, message = "Nome deve ter ao menos 3 caracteres.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    private String nome;

    @NotEmpty(message = "CPF não pode estar vazio.")
    @Column(unique = true)
    private String cpf;

}