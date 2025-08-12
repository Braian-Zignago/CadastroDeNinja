package java10x.com.CadastroDeNinjas.missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarAsMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .toList();
    }

    /**
     * Metodo para listar uma missão por ‘ID’.
     *
     * @param id ‘ID’ da missão a ser buscada.
     * @return Objeto MissoesModel correspondente ao ‘ID’, ou null se não encontrado.
     */
    public MissoesDTO listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.stream()
                .map(missoesMapper::map)
                .findAny()
                .orElse(null);
    }

    // Criar Missão (CREATE)
    public MissoesDTO criarMissao(MissoesDTO missaoDTO) {
        MissoesModel missoes = missoesMapper.map(missaoDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    public MissoesDTO alterarMissaoPorId(Long id, MissoesDTO missaoDTO) {
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null; // Retorna null se a missão não existir
    }
}
