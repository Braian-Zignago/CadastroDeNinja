package java10x.com.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }


    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criaçao do ninja")
    })
    public ResponseEntity<String> criarNinja(
            @Parameter(description = "Usuario manda os dados do novo ninja a ser criado no corpo da requisiçao") @RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + ". com ID: " + novoNinja.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota lista todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas encontrados com sucesso")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/por-id={id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    })
    public ResponseEntity<?> listarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível encontrar o ninja com ID: " + id);
        }
        return ResponseEntity.ok(ninja);
    }

    @GetMapping("/listar/por-nome={nome}")
    public ResponseEntity<?> listarNinjaPorNome(@PathVariable String nome) {
        NinjaDTO ninja = ninjaService.listarNinjaPorNome(nome);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível encontrar o ninja com nome: " + nome);
        }
        return ResponseEntity.ok(ninja);
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = " (PUT) Altera o ninja por completo por Id", description = "Rota altera um ninja por completo pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id,
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja =  ninjaService.alterarNinjaPorId(id, ninjaAtualizado);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(ninja);
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = " (PATCH) Altera o ninja parcialmente por Id", description = "Rota altera um ninja parcialmente com os dados fornecidos pelo cliente pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisição") @RequestBody Map<String, Object> fields) {
        NinjaDTO ninja = null;
        ninja = ninjaService.alterarNinjaPorId(id, fields);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(ninja);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta ninja por ID", description = "Deleta o ninja pelo ID passado no caminho da requisição")
    public ResponseEntity<String> deletarNinjaPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id) {
        NinjaDTO ninjaAELiminar = ninjaService.listarNinjaPorId(id);
        if (ninjaAELiminar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID (" + id + ") não foi encontrado.");
        }
        ninjaService.deletarNinjaPorId(id);
        return ResponseEntity.ok("Ninja " + ninjaAELiminar.getNome() + " com ID (" + id + ") foi deletado com sucesso.");
    }
}
