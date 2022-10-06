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

import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.service.BookService;
import com.ludo.tutorial.service.CategoryService;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public String listBooks(Model model) {
		model.addAttribute("book", new Book());
		addAttributes(model);

		return "listBook";
	}

	private void addAttributes(Model model) {
		model.addAttribute("categories", categoryService.listCategories());
		model.addAttribute("menu", "lista_libros");
		model.addAttribute("books", bookService.listBooks());
		model.addAttribute("how_many", bookService.numBooks());
		model.addAttribute("titulo", "Formulario libros");
		model.addAttribute("descripcion",
				"En esta sección, después de haber creado instancias de libros, los listamos.");
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {

		if (result.hasErrors()) {
			addAttributes(model);
			return "listBook";
		}
		bookService.save(book);
		return "redirect:/book/list";
	}

	@GetMapping("/delete")
	public String deleteBook(@RequestParam("id") long id) {
		bookService.deleteBook(id);
		return "redirect:/book/list";
	}

	@GetMapping("/edit")
	public String editBook(@RequestParam("id") long id, Model model) {
		Book book = bookService.getBook(id);
		model.addAttribute(book);
		addAttributes(model);
		return "listBook";
	}
}
