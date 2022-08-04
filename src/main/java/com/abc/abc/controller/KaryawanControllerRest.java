package com.abc.abc.controller;

import com.abc.abc.model.Karyawan;
import com.abc.abc.service.KaryawanService;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan/")
public class KaryawanControllerRest {

    @Autowired
    public KaryawanService karyawanService;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/Java")
    public ResponseEntity<Map> save(@Valid @RequestBody Karyawan objModel){
        Map obj = karyawanService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel) {
        Map map = karyawanService.update(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam() Integer page,
            @RequestParam() Integer size) {
        Map list = karyawanService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
    }



}
