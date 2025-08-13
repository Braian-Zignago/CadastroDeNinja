package java10x.com.CadastroDeNinjas.missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java10x.com.CadastroDeNinjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String dificuldade;

    // Relacionamento com a tabela de ninjas: Missao pode ter vários ninjas nela.
    @OneToMany(mappedBy = "missoes") // mappedBy indica que a relação é gerenciada pela classe NinjaModel
    @JsonIgnore
    private List<NinjaModel> ninjas;
}
