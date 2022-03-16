package com.adilmx.spring_mvc_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adilmx.spring_mvc_app.entities.Contrat;
import com.adilmx.spring_mvc_app.repository.ContratRepo;

@Controller
public class ContratRest {

	@Autowired
	ContratRepo contratRepo;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/contrats")
	public String contrats(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord) {
		// List<Contrat> contrats = contratRepo.findAll();
		// Page<Contrat> pageContrats = contratRepo.findAll(PageRequest.of(page, size));
		Page<Contrat> pageContrats = contratRepo.findByTitleContains(keyWord, PageRequest.of(page, size));
		model.addAttribute("listContrats", pageContrats);
		model.addAttribute("keyWord", keyWord);
		return "contrats";
	}

	@GetMapping("/addContrat")
	public String addContrat(Model model) {
		model.addAttribute("contrat", new Contrat());
		return "addContrat";
	}

	@PostMapping("/saveContrat")
	public String save(@ModelAttribute Contrat contrat) {
		System.out.println(contrat);
		if (contrat != null) {
			// Contrat contratSaved = contratRepo.save(contrat);
		}
		return "addContrat";
	}

}
