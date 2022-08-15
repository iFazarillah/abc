package com.abc.abc.controller;


import com.abc.abc.model.Karyawan;
import com.abc.abc.model.Rekening;
import com.abc.abc.repository.RekeningRepository;
import com.abc.abc.service.RekeningService;
import com.abc.abc.utils.SimpleStringUtils;
import com.abc.abc.utils.TemplateResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/rekening")
public class RekeningController {

    @Autowired
    RekeningService rekeningService;

    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    public TemplateResponse templateResponse;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @PostMapping("/add/{idkaryawan}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                    @Valid @RequestBody Rekening objModel) {
        Map obj = rekeningService.insert(objModel, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Rekening objModel) {
        Map obj = rekeningService.update(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                      @RequestBody Rekening objModel) {
        Map map = rekeningService.update(objModel, idkaryawan);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = rekeningService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

//    @GetMapping("/list/{idkaryawan}")
//    public ResponseEntity<Map> select(@PathVariable(value = "idkaryawan") Long idkaryawan) {
//        Map rekening = rekeningRepository.getByIdKaryawan(idkaryawan);
//        return new ResponseEntity<Map>(templateResponse.templateSukses(rekening), HttpStatus.OK);
//
//    }

    @GetMapping("/list")
    public ResponseEntity<Map> list(
            @RequestParam() Integer page,
            @RequestParam() Integer size) {
        Map map = new HashMap();
        Page<Rekening> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        list = rekeningRepository.findAll(show_data);

        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Map> selectById(@PathVariable(value = "id") Long id) {
        Map karyawan = rekeningService.findById(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses(karyawan), HttpStatus.OK);

    }


}
