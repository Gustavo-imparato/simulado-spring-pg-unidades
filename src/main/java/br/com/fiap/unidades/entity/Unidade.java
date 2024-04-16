package br.com.fiap.unidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_UNIDADE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_SIGLA", columnNames = "SIGLA_UNIDADE"),
        @UniqueConstraint( name = "UK_MACRO", columnNames = "MACRO_UNIDADE")
})
public class Unidade {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator( name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE", allocationSize = 1)
    @Column( name = "ID_UNIDADE")
    private Long id;

    @Column( name = "NM_UNIDADE")
    private String nome;

    @Column( name = "SIGLA_UNIDADE")
    private String sigla;

    @Column( name = "DESCRICAO_UNIDADE")
    private String descricao;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "MACRO_UNIDADE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(
                    name = "FK_MACRO_UNIDADE"
            )
    )
    private Unidade macro;

}
