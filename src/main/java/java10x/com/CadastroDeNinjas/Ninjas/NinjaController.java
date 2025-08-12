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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.criarNinja(ninja);
    }

    // Ler um ninja (READ)
    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar um ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjaPorId(@PathVariable Long id) {
        return ninjaService.listarNinjaPorId(id);
    }
    // Atualizar um ninja por ID (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaDTO alterarNinjaPorID(@PathVariable long id, @RequestBody NinjaDTO ninjaAtualizado) {
        return ninjaService.alterarNinjaPorId(id, ninjaAtualizado);
    }

    // Deletar um ninja por ID (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorID(@PathVariable long id) {
        ninjaService.deletarNinjaPorId(id);
    }

    //Famoso CRUD Create, Read, ‘Update’, delete criado.

}
