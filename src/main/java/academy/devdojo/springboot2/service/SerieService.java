package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.SerieModel;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.repository.SerieRepository;
import academy.devdojo.springboot2.requests.SeriePostRequestBody;
import academy.devdojo.springboot2.requests.SeriePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SerieService {

     private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<SerieModel> listAll() {
        return serieRepository.findAll();
    }

    public List<SerieModel> findByName(String name) {
        return serieRepository.findByName(name);
    }

    public SerieModel findByIdOrThrowBadRequestException(long id) {
        return serieRepository.findById(id).orElseThrow(() -> new BadRequestException("Serie not found"));
    }

    public SerieModel save(SeriePostRequestBody seriePostRequestBody) {
        return serieRepository.save(SerieModel.builder().name(seriePostRequestBody.getName()).build());
    }

    public void delete(long id) {
        serieRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(SeriePutRequestBody seriePutRequestBody) {
        SerieModel savedSerie = findByIdOrThrowBadRequestException(seriePutRequestBody.getId());
        SerieModel serieModel = SerieModel.builder().id(savedSerie.getId()).name(seriePutRequestBody.getName()).build();
        serieRepository.save(serieModel);
    }
}
