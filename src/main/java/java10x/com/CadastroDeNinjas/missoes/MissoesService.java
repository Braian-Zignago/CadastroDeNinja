package java10x.com.CadastroDeNinjas.missoes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public MissoesDTO alterarMissaoPorId(Long id, Map<String, Object> fields) {
        MissoesModel missaoExistente = missoesRepository.findById(id).orElse(null);
        if (missaoExistente == null) {
            return null;
        }
        MissoesDTO missaoExistenteDto = missoesMapper.map(missaoExistente);
        merge(fields, missaoExistenteDto);
        missaoExistenteDto = missoesMapper.map(missoesRepository.save(missoesMapper.map(missaoExistenteDto)));
        return missaoExistenteDto;
    }

    private void merge(Map<String, Object> fields, MissoesDTO missaoExistente){
        ObjectMapper objectMapper = new ObjectMapper();
        MissoesDTO missaoConvertida = objectMapper.convertValue(fields, MissoesDTO.class);
        fields.forEach((atributo, valor) -> {
            Field field = ReflectionUtils.findField(MissoesDTO.class, atributo);
            Objects.requireNonNull(field).setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, missaoConvertida);
            ReflectionUtils.setField(field, missaoExistente, novoValor);
        });
    }
}
