package com.adilmx.spring_mvc_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.adilmx.spring_mvc_app.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adilmx.spring_mvc_app.entities.Contrat;
import com.adilmx.spring_mvc_app.repository.ContratRepo;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver;

@Controller
public class ContratController {

	private ContratService contratService;

	public ContratController(ContratService contratService) {
		this.contratService = contratService;
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/contrats")
	public String contrats(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord) {
		// List<Contrat> contrats = contratService.findAll();
		// Page<Contrat> pageContrats = contratService.findAll(PageRequest.of(page, size));
		Page<Contrat> pageContrats = contratService.findByTitleContains(keyWord, page, size);
		model.addAttribute("listContrats", pageContrats);
		model.addAttribute("keyWord", keyWord);
		return "contrats";
	}

	@GetMapping("/addContrat")
	public String addContrat(Model model) {
		model.addAttribute("contrat", new Contrat());
		return "addContrat";
	}
	
	@GetMapping("/editContrat")
	public String editContrat(Model model, @RequestParam(name = "id") Long id) {
		Optional<Contrat> contrat = contratService.findById(id);
		model.addAttribute("contrat", contrat.get());
		return "editContrat";
	}


	@PostMapping("/saveContrat")
	public String save(@Valid Contrat contrat, BindingResult bindingResult) {
		/*
		 * List<String> message = new ArrayList<>(); List<FieldError> errors =
		 * bindingResult.getFieldErrors(); errors.forEach((e) -> { message.add("--[ " +
		 * e.getField().toUpperCase() + " : " + e.getDefaultMessage() + "]--\n"); });
		 */
		if (bindingResult.hasErrors()) return "addContrat";
		Contrat contratSaved = contratService.save(contrat);
		return "redirect:/contrats";

	}

	@GetMapping("/deleteContrat")
	public String delete(Model model, @RequestParam(name = "id") Long id,
			@RequestParam(name = "keyWord", defaultValue = "") String keyWord) {
		contratService.deleteById(id);
		return "redirect:/contrats?keyWord=" + keyWord;
	}

	@GetMapping("/cloture")
	public String cloture(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "id") Long id, @RequestParam(name = "keyWord", defaultValue = "") String keyWord) {
		Optional<Contrat> contrat = contratService.findById(id);
		contrat.get().setCloture(!contrat.get().isCloture());
		contratService.save(contrat.get());
		return "redirect:/contrats?page=" + page + "&keyWord=" + keyWord;
	}
}
