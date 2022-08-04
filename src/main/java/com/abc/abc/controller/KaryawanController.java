package com.abc.abc.controller;

import java.util.List;

import com.abc.abc.service.KaryawanServiceStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.abc.model.Karyawan;

@Controller
@RequestMapping("/v1/view/karyawan")
public class KaryawanController {

	@Autowired
	public KaryawanServiceStatic karyawanServiceStatic;

	private final int ROW_PER_PAGE = 5;

	// index page
	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("title", "Titel Saya");
		return "index";
	}

	@GetMapping(value = "/list")
	public String getKaryawan(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Karyawan> karyawans = karyawanServiceStatic.dataKaryawan(pageNumber, ROW_PER_PAGE);

		long count = karyawans == null ? 1 : karyawans.size();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("karyawans", karyawans);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "karyawan-list";
	}

	@GetMapping(value = { "/add" })
	public String showAddKaryawan(Model model) {
		Karyawan karyawan = new Karyawan();
		model.addAttribute("add", true);
		model.addAttribute("karyawan", karyawan);

		return "karyawan-edit";
	}

	@PostMapping(value = "/add")
	public String addKaryawan(Model model, @ModelAttribute("Karyawan") Karyawan karyawan) {
		try {
			System.out.println("nilai Karyawan karyawan=" + karyawan.getNama());
			Karyawan newKaryawan = karyawanServiceStatic.save(karyawan);
			return "redirect:/v1/view/karyawan/" + String.valueOf(newKaryawan.getId());
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("add", true);
			return "karyawan-edit";
		}
	}

	@GetMapping(value = { "/{karyawanId}/edit" })
	public String showEditKaryawan(Model model, @PathVariable long karyawanId) {
		Karyawan karyawan = null;
		karyawan = karyawanServiceStatic.findById(karyawanId);
		model.addAttribute("add", false);
		model.addAttribute("karyawan", karyawan);
		return "karyawan-edit";
	}

	@PostMapping(value = { "/{karyawanId}/edit" })
	public String updateKaryawan(Model model, @PathVariable long karyawanId,
			@ModelAttribute("karyawan") Karyawan karyawan) {
		try {
			karyawan.setId(karyawanId);
			karyawanServiceStatic.update(karyawan);
			return "redirect:/v1/view/karyawan/" + String.valueOf(karyawan.getId());
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("add", false);
			return "karyawan-edit";
		}
	}

	@GetMapping(value = "/{karyawanId}")
	public String getKaryawanById(Model model, @PathVariable long karyawanId) {
		Karyawan karyawan = null;
		karyawan = karyawanServiceStatic.findById(karyawanId);
		model.addAttribute("karyawan", karyawan);
		return "karyawan";
	}

	@GetMapping(value = { "/{karyawanId}/delete" })
	public String showDeleteKaryawanById(Model model, @PathVariable long karyawanId) {
		Karyawan karyawan = null;
		karyawan = karyawanServiceStatic.findById(karyawanId);
		model.addAttribute("allowDelete", true);
		model.addAttribute("karyawan", karyawan);
		return "karyawan";
	}

	@PostMapping(value = { "/{karyawanId}/delete" })
	public String deleteKaryawanById(Model model, @PathVariable long karyawanId) {
		karyawanServiceStatic.deleted(karyawanId);
		return "redirect:/v1/view/karyawan/list";
	}

}
