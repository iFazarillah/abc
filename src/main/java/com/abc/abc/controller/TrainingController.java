package com.abc.abc.controller;

import com.abc.abc.model.Training;
import com.abc.abc.repository.TrainingRepository;
import com.abc.abc.service.TrainingService;
import com.abc.abc.utils.SimpleStringUtils;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/training")
public class TrainingController {

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TemplateResponse templateResponse;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody Training objModel) {
        Map map = new HashMap();
        Map obj = (Map) trainingRepository.save(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Training objModel) {
        Map obj = trainingService.update(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = trainingService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByTema(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String tema) {
        Map map = new HashMap();
        Page<Training> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        if ( tema != null && !tema.isEmpty() ) {
            list = trainingRepository.findByTema("%" + tema + "%", show_data);
        } else {
            list = trainingRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }


}
