package java10x.com.CadastroDeNinjas.missoes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String dificuldade;

    // Relacionamento com a tabela de ninjas: Missao pode ter vários ninjas nela.
    @OneToMany(mappedBy = "missoes") // mappedBy indica que a relação é gerenciada pela classe NinjaModel
    private List<String> ninjas;
}
