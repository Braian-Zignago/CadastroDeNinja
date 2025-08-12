package java10x.com.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    // Metodo para converter NinjaDTO para NinjaModel
    public NinjaModel map (NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setImg_url(ninjaDTO.getImg_url());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setRank(ninjaDTO.getRank());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());
        return ninjaModel;
    }

    // Método para converter NinjaModel para NinjaDTO
    public NinjaDTO map(NinjaModel ninjaModel) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setImg_url(ninjaModel.getImg_url());
        ninjaDTO.setIdade(ninjaModel.getIdade());
        ninjaDTO.setRank(ninjaModel.getRank());
        ninjaDTO.setMissoes(ninjaModel.getMissoes());
        return ninjaDTO;
    }
}
