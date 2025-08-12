package java10x.com.CadastroDeNinjas.Ninjas;

import jakarta.persistence.*;
import java10x.com.CadastroDeNinjas.missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String img_url;

    private int idade;
    // Relacionamento com a tabela de missões: So Podemos ter uma missão por ninja

    @Column(name = "rank")
    private String rank;
    @ManyToOne
    @JoinColumn(name = "missao_id") // Forein key ou chave estrangeira
    private MissoesModel missoes;
}
