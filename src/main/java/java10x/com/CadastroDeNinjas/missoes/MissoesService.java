package java10x.com.CadastroDeNinjas.missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarAsMissoes() {
        return missoesRepository.findAll();
    }

    /**
     * Metodo para listar uma missão por ID.
     *
     * @param id ID da missão a ser buscada.
     * @return Objeto MissoesModel correspondente ao ID, ou null se não encontrado.
     */
    public MissoesModel listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);
    }

    // Criar Missão (CREATE)
    public MissoesModel criarMissao(MissoesModel missao) {
        return missoesRepository.save(missao);
    }

    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    public MissoesModel alterarMissaoPorId(Long id, MissoesModel missaoAtualizada) {
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            missaoAtualizada.setId(id); // Garantir que o ID seja mantido
            return missoesRepository.save(missaoAtualizada);
        }
        return null; // Retorna null se a missão não existir
    }
}
