package java10x.com.CadastroDeNinjas.missoes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cadastro-de-missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Missoes!";
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao){
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + novaMissao.getNome() + ". com ID: " + novaMissao.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarAsMissoes() {
        List<MissoesDTO> missoes = missoesService.listarAsMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) {
        MissoesDTO missao =  missoesService.listarMissaoPorId(id);
        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível encontrar a missão com ID: " + id);
        }
        return ResponseEntity.ok(missao);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissaoPorID(@PathVariable long id, @RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missao =  missoesService.alterarMissaoPorId(id, missaoAtualizada);
        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não foi encontrada ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(missao);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable long id) {
        MissoesDTO missaoAEliminar =  missoesService.listarMissaoPorId(id);
        if (missaoAEliminar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não foi encontrada.");
        }
        missoesService.deletarMissaoPorId(id);
        return ResponseEntity.ok("Missão " + missaoAEliminar.getNome() + " com ID (" + id + ") foi deletada com sucesso.");
    }
}
