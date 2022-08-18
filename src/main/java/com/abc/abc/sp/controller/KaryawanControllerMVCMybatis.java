package com.abc.abc.sp.controller;


import com.abc.abc.model.Karyawan;
import com.abc.abc.repository.KaryawanRepository;
import com.abc.abc.service.KaryawanService;
import com.abc.abc.sp.model.KaryawanMybatis;
import com.abc.abc.sp.repository.KaryawanRepoMybatis;
import com.abc.abc.sp.service.KaryawanServiceMybatis;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/view/sp/karyawan")
public class KaryawanControllerMVCMybatis {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public KaryawanService karyawanService;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public KaryawanServiceMybatis karyawanServiceMybatis;

    private final int ROW_PER_PAGE = 5;

    //    Index Page
    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
        model.addAttribute("title", "Database PT ABC");
        return "/sp/index";
    }


    @GetMapping(value = "/list")
    public String getKaryawan(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        Map data = karyawanService.getAll(ROW_PER_PAGE, pageNumber);
//        List<KaryawanMybatis> obj = karyawanServiceMybatis.selectList("%%");
        Object data1 = data.get("data");

        Page<Karyawan> data2 = (Page<Karyawan>) data1;

        List<Karyawan> karyawans = data2.getContent();
        //konversi ke barang response
//        List<Karyawan> karyawans = templateResponse.convertToKaryawan(obj);
        long count = karyawanRepository.count();

        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("karyawans", karyawans);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "/sp/karyawan-list";
    }

    @GetMapping(value = { "/add" })
    public String showAddKaryawan(Model model) {
        Karyawan karyawan = new Karyawan();
        model.addAttribute("add", true);
        model.addAttribute("karyawan", karyawan);

        return "/sp/karyawan-edit";
    }

    @PostMapping(value = "/add")
    public String addKaryawan(Model model,
                              @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            System.out.println("nilai karyawan =" + karyawan.getNama());
//            Map data = serviceBarang.insert(barang);
            Map data = karyawanServiceMybatis.savekaryawanwitheror(karyawan.getNama(), karyawan.getDob(), karyawan.getJk(),
                    karyawan.getAlamat(), karyawan.getStatus(), 0);

            Karyawan newKaryawan = (Karyawan) data.get("data");
            return "redirect:/v1/view/sp/karyawan/" + String.valueOf(newKaryawan.getId());
        } catch ( Exception ex ) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("barang", barang);
            model.addAttribute("add", true);
            return "/sp/karyawan-edit";
        }
    }

    @GetMapping(value = { "/{karyawanId}/edit" })
    public String showEditKaryawan(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            KaryawanMybatis obj = karyawanServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(karyawanId)));
            karyawan = templateResponse.convertToKaryawan(obj);
        } catch ( ResourceNotFoundException ex ) {
            model.addAttribute("errorMessage", "Karyawan not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("karyawan", karyawan);
        return "/sp/karyawan-edit";
    }

    @PostMapping(value = { "/{karyawanId}/edit" })
    public String updateKaryawan(Model model,
                                 @PathVariable long karyawanId,
                                 @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            karyawan.setId(karyawanId);
            karyawanService.update(karyawan);
            return "redirect:/v1/view/sp/karyawan/" + String.valueOf(karyawan.getId());
        } catch ( Exception ex ) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/sp/karyawan-edit";
        }
    }

    @GetMapping(value = "/{karyawanId}")
    public String getKaryawanById(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            KaryawanMybatis obj = karyawanServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(karyawanId)));
            karyawan = templateResponse.convertToKaryawan(obj);
        } catch ( ResourceNotFoundException ex ) {
            model.addAttribute("errorMessage", "Karyawan Not Found not found");
        }
        model.addAttribute("karyawan", karyawan);
        return "/sp/karyawan";
    }

    //delete
    @GetMapping(value = { "/{karyawanId}/delete" })
    public String showKaryawanById(
            Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            KaryawanMybatis obj = karyawanServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(karyawanId)));
            karyawan = templateResponse.convertToKaryawan(obj);
        } catch ( ResourceNotFoundException ex ) {
            model.addAttribute("errorMessage", "Karyawan not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("karyawan", karyawan);
        return "/sp/karyawan";
    }

    @PostMapping(value = { "/{karyawanId}/delete" })
    public String deleteKaryawanById(
            Model model, @PathVariable long karyawanId) {
        try {
//            serviceBarang.delete(barangId);
//            method delete belum bikin
            return "redirect:/v1/view/sp/karyawan/list";
        } catch ( ResourceNotFoundException ex ) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/sp/karyawan";
        }
    }
}
