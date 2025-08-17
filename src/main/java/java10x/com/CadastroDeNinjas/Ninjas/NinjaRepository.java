package java10x.com.CadastroDeNinjas.Ninjas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {

    NinjaModel findByNome(String nome);

}
