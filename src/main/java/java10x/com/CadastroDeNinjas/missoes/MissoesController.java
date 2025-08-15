package java10x.com.CadastroDeNinjas.missoes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/cadastro-de-missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Missoes!";
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um nova missão", description = "Rota cria uma nova missão e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuario manda os dados da nova missão a ser criada no corpo da requisiçao") @RequestBody MissoesDTO missao){
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + novaMissao.getNome() + ". com ID: " + novaMissao.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões", description = "Rota lista todas as missões cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas encontrados com sucesso")
    })
    public ResponseEntity<List<MissoesDTO>> listarAsMissoes() {
        List<MissoesDTO> missoes = missoesService.listarAsMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista a missão por Id", description = "Rota lista uma missão pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable Long id) {
        MissoesDTO missao =  missoesService.listarMissaoPorId(id);
        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível encontrar a missão com ID: " + id);
        }
        return ResponseEntity.ok(missao);
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = " (PUT) Altera a missão por completo por Id", description = "Rota altera uma missão por completo pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarMissaoPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id,
            @Parameter(description = "Usuario manda os dados da missão a ser atualizada no corpo da requisiçao") @RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missao =  missoesService.alterarMissaoPorId(id, missaoAtualizada);
        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não foi encontrada ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(missao);
    }

    @PatchMapping("alterar/{id}")
    @Operation(summary = " (PATCH) Altera o ninja parcialmente por Id", description = "Rota altera um ninja parcialmente com os dados fornecidos pelo cliente pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarMissoesPorID(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id,
            @Parameter(description = "Usuario manda os dados da missão a ser atualizada no corpo da requisiçao") @RequestBody Map<String, Object> fields){
        MissoesDTO missao = missoesService.alterarMissaoPorId(id, fields);
        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não foi encontrado ou não foi possível atualizar.");
        }
        return ResponseEntity.ok(missao);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta missão por ID", description = "Deleta a missão pelo ID passado no caminho da requisição")
    public ResponseEntity<String> deletarMissaoPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisiçao") @PathVariable long id) {
        MissoesDTO missaoAEliminar =  missoesService.listarMissaoPorId(id);
        if (missaoAEliminar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não foi encontrada.");
        }
        missoesService.deletarMissaoPorId(id);
        return ResponseEntity.ok("Missão " + missaoAEliminar.getNome() + " com ID (" + id + ") foi deletada com sucesso.");
    }
}
