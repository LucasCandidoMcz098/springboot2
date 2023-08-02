package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.SerieModel;
import academy.devdojo.springboot2.requests.SeriePostRequestBody;
import academy.devdojo.springboot2.requests.SeriePutRequestBody;
import academy.devdojo.springboot2.service.SerieService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("serie")
@Log4j2
public class SerieController {

    private DateUtil dateUtil;
    private SerieService serieService;
    @Autowired
    public SerieController(DateUtil dateUtil, SerieService serieService) {
        this.dateUtil = dateUtil;
        this.serieService = serieService;
    }

    @GetMapping
    public ResponseEntity<List<SerieModel>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(serieService.listAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<SerieModel> findById(@PathVariable long id){
        return ResponseEntity.ok(serieService.findByIdOrThrowBadRequestException(id));
    }
    @PostMapping
    public ResponseEntity<SerieModel> save(@RequestBody SeriePostRequestBody seriePostRequestBody){
        return new ResponseEntity<>(serieService.save(seriePostRequestBody), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        serieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody SeriePutRequestBody seriePutRequestBody){
        serieService.replace(seriePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
