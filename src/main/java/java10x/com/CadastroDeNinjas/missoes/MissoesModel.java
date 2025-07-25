package java10x.com.CadastroDeNinjas.missoes;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String dificuldade;

    // Relacionamento com a tabela de ninjas: Missao pode ter vários ninjas nela.
    @OneToMany(mappedBy = "missoes") // mappedBy indica que a relação é gerenciada pela classe NinjaModel

    private List<String> ninjas;

    public MissoesModel() {
    }

    public MissoesModel(long id, String nome, String dificuldade) {
        this.id = id;
        this.nome = nome;
        this.dificuldade = dificuldade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}
