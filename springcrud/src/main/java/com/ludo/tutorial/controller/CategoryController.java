package com.ludo.tutorial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.tutorial.model.Category;
import com.ludo.tutorial.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("category", new Category());
		addAttributes(model);
		return "listCategory";
	}

	private void addAttributes(Model model) {
		model.addAttribute("categories", categoryService.listCategories());
		model.addAttribute("menu", "lista_categorias");
		model.addAttribute("how_many", categoryService.numCategories());
		model.addAttribute("titulo", "Formulario categorías");
		model.addAttribute("descripcion",
				"En esta sección, después de haber creado instancias de categorías, las listamos.");
	}

	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("category") @Valid Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			addAttributes(model);
			return "listCategory";
		}
		categoryService.save(category);

		return "redirect:/category/list";
	}

	@GetMapping("/delete")
	public String deleteCategory(@RequestParam("id") long id) {
		categoryService.deleteCategory(id);
		return "redirect:/category/list";
	}

	@GetMapping("/edit")
	public String editCategory(@RequestParam("id") long id, Model model) {
		Category category = categoryService.getCategory(id);
		model.addAttribute(category);
		addAttributes(model);
		return "listCategory";
	}
}
