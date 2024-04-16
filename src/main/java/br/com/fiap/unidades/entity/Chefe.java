package br.com.fiap.unidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table( name = "TB_CHEFE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_UNIDADE", columnNames = "UNIDADE_CHEFE"),
        @UniqueConstraint(name = "UK_USUARIO", columnNames = "USUARIO_CHEFE")
})
public class Chefe {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SQ_CHEFE")
    @SequenceGenerator( name = "SQ_CHEFE", sequenceName = "SQ_CHEFE", allocationSize = 1)
    @Column( name = "ID_CHEFE")
    private Long id;

    private Boolean substituto;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "USUARIO_CHEFE",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(
                    name = "FK_CHEFE_USUARIO"
            )
    )
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "UNIDADE_CHEFE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(
                    name = "FK_CHEFE_UNIDADE"
            )
    )
    private Unidade unidade;

    @Column(name = "INICIO_CHEFE")
    private LocalDateTime inicio;

    @Column(name = "FIM_CHEFE")
    private LocalDateTime fim;

}
