package com.abc.abc.controller;


import com.abc.abc.model.Rekening;
import com.abc.abc.repository.RekeningRepository;
import com.abc.abc.service.RekeningService;
import com.abc.abc.utils.SimpleStringUtils;
import com.abc.abc.utils.TemplateResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

}
