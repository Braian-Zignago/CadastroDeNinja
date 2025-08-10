package java10x.com.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro-de-ninjas")
public class NinjaController {

    private final NinjaService ninjaService;
    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Endpoint de boas-vindas
    @GetMapping
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }

    // Endpoints para manipulação de ninjas

    // Criar um ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado com sucesso!";
    }

    // Ler um ninja (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar um ninja por ID (READ)
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasID() {
        return "Mostrar todos os ninjas cadastrados com ID!";
    }

    // Atualizar um ninja por ID (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorID() {
        return "Ninja alterado por ID com sucesso!";
    }

    // Deletar um ninja por ID (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorID() {
        return "Ninja deletado por ID com sucesso!";
    }

    //Famoso CRUD Create, Read, Update, Delete criado.

}
