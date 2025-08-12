package java10x.com.CadastroDeNinjas.missoes;

import java10x.com.CadastroDeNinjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesDTO {
    private long id;
    private String nome;
    private String dificuldade;
}
