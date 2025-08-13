package java10x.com.CadastroDeNinjas.Ninjas;

import java10x.com.CadastroDeNinjas.missoes.MissoesDTO;
import java10x.com.CadastroDeNinjas.missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaDTO {
    private Long id;
    private String nome;
    private String email;
    private String img_url;
    private int idade;
    private String rank;
    private MissoesModel missoes;
}
