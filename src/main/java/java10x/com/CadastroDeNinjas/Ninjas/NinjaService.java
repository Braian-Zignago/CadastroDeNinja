package java10x.com.CadastroDeNinjas.Ninjas;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class NinjaService {
    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Metodo para listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .toList();
    }

    // Metodo para listar um ninja por ID
    public NinjaDTO listarNinjaPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO listarNinjaPorNome(String nome){
        NinjaModel ninjaPorNome = ninjaRepository.findByNome(nome);
        return ninjaMapper.map(ninjaPorNome);
    }

    //Metodo para criar um ninja
    @Transactional
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public void deletarNinjaPorId(long id) {
        ninjaRepository.deleteById(id);
    }

    public NinjaDTO alterarNinjaPorId(Long id, NinjaDTO ninjaDTO) {
        NinjaModel ninjaExistente = ninjaRepository.findById(id).orElse(null);
        if (ninjaExistente == null){

            return null; // Retorna null se o ninja não existir
        }
        NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
        ninjaAtualizado.setId(id); // Garantir que o ID seja mantido
        NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
        return ninjaMapper.map(ninjaSalvo);
    }

    public NinjaDTO alterarNinjaPorId(Long id, Map<String, Object> fields) {
        NinjaModel ninjaExistente = ninjaRepository.findById(id).orElse(null);
        if (ninjaExistente == null) {
            return null; // Retorna null se o ninja não existir
        }
        NinjaDTO ninjaExistenteDto = ninjaMapper.map(ninjaExistente);
        merge(fields, ninjaExistenteDto);
        ninjaExistenteDto = ninjaMapper.map(ninjaRepository.save(ninjaMapper.map(ninjaExistenteDto)));
        return ninjaExistenteDto;
    }

    private void merge(Map<String, Object> fields, NinjaDTO ninjaExistente) {
        ObjectMapper objectMapper = new ObjectMapper();
        NinjaDTO ninjaConvertido = objectMapper.convertValue(fields, NinjaDTO.class);
        fields.forEach((atributo, valor) -> {
            Field field = ReflectionUtils.findField(NinjaDTO.class, atributo);
            Objects.requireNonNull(field).setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, ninjaConvertido);
            ReflectionUtils.setField(field, ninjaExistente, novoValor);
        });
    }
}
