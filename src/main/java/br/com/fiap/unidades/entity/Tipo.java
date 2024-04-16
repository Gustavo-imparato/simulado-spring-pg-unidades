package br.com.fiap.unidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

@Entity
@Table( name = "TB_TIPO")
public enum Tipo {

    PF( 1L, "Pessoa Física", "PF" ), PJ( 2L, "Pessoa Jurídica", "PJ" );

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO")
    @SequenceGenerator( name = "SQ_TIPO", sequenceName = "SQ_TIPO", allocationSize = 1)
    @Column( name = "ID_TIPO")
    private Long id;
    private String nome;
    private String sigla;

    @Override
    public String toString() {
        return String.valueOf( sigla );
    }
}
