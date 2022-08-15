package com.abc.abc.sp.controller;

import com.abc.abc.model.Karyawan;
import com.abc.abc.sp.model.KaryawanMybatis;
import com.abc.abc.sp.service.KaryawanServiceMybatis;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sp/karyawan")
public class KaryawanControllerMyBatis {

    @Autowired
    public KaryawanServiceMybatis karyawanServiceMybatis;


    @Autowired
    public TemplateResponse templateResponse;


    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody Karyawan objModel) {
        Map map = karyawanServiceMybatis.savekaryawanwitheror(
                objModel.getNama(),
                objModel.getDob(),
                objModel.getJk(),
                objModel.getAlamat(),
                objModel.getStatus(),
                Integer.parseInt(objModel.getId().toString()));
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel) {
        Map map = karyawanServiceMybatis.updateKryOnly(
                objModel.getNama(),
                objModel.getJk(),
                objModel.getDob(),
                objModel.getAlamat(),
                objModel.getStatus(),
                Integer.parseInt(objModel.getId().toString()));
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
//        Map map = barangService.delete(id);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }ink

    @GetMapping("/list")
    public ResponseEntity<Map> listByNama(
            @RequestParam(required = false) String nama) {
        List<KaryawanMybatis> list = karyawanServiceMybatis.selectList("%" + nama + "%");
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.convertToKaryawan(list)), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> selectById(@PathVariable(value = "id") Integer id) {
        KaryawanMybatis obj = karyawanServiceMybatis.selectBlog(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.convertToKaryawan(obj)), HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Integer did) {
        Map map = karyawanServiceMybatis.deleteProcedure(did);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
