package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.SerieModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepository extends JpaRepository<SerieModel, Long> {

    List<SerieModel> findByName(String name);
}
