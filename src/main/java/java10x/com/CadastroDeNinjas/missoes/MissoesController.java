package java10x.com.CadastroDeNinjas.missoes;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cadastro-de-missoes")
public class MissoesController {

    private MissoesService missoesService;
    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Missoes!";
    }

    // Endpoints para manipulação de missoes

    // Criar uma missao (CREATE)
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){
        return missoesService.criarMissao(missao);
    }

    // Listar missoes (READ)
    @GetMapping("/listar")
    public List<MissoesModel> listarAsMissoes() {
        return missoesService.listarAsMissoes();
    }

    // Listar uma missao por ID (READ)
    // Exemplo: http://localhost:8080/cadastro-de-missoes/listar/{id}
    // Onde {id} é o ID da missão que você deseja buscar.
    @GetMapping("/listar/{id}")
    public MissoesModel listarMissaoPorId(@PathVariable Long id) {
        return missoesService.listarMissaoPorId(id);
    }

    // Atualizar uma missao (UPDATE)
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissaoPorID(@PathVariable long id, @RequestBody MissoesModel missaoAtualizada) {
        return missoesService.alterarMissaoPorId(id, missaoAtualizada);
    }

    // Deletar uma missao (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable long id) {
        missoesService.deletarMissaoPorId(id);
    }
    //Famoso CRUD Create, Read, Update, Delete criado.
}
