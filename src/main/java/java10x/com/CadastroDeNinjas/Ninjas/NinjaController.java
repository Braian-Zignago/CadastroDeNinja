package java10x.com.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cadastro-de-ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }


    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + ". com ID: " + novoNinja.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível encontrar o ninja com ID: " + id);
        }
        return ResponseEntity.ok(ninja);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorID(@PathVariable long id, @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja =  ninjaService.alterarNinjaPorId(id, ninjaAtualizado);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(ninja);
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorID(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        NinjaDTO ninja = null;
        ninja = ninjaService.alterarNinjaPorId(id, fields);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(ninja);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable long id) {
        NinjaDTO ninjaAELiminar = ninjaService.listarNinjaPorId(id);
        if (ninjaAELiminar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado.");
        }
        ninjaService.deletarNinjaPorId(id);
        return ResponseEntity.ok("Ninja " + ninjaAELiminar.getNome() + " com ID (" + id + ") foi deletado com sucesso.");
    }
}
