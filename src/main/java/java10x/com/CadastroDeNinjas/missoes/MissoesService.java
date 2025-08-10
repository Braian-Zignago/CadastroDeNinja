package java10x.com.CadastroDeNinjas.missoes;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarAsMissoes() {
        return missoesRepository.findAll();
    }
}
