package com.ludo.tutorial.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.ludo.tutorial.dto.UserDto;
import com.ludo.tutorial.model.Book;
import com.ludo.tutorial.model.Role;
import com.ludo.tutorial.model.User;
import com.ludo.tutorial.service.BookService;
import com.ludo.tutorial.service.RoleService;
import com.ludo.tutorial.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/list")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		addAttributes(model);
		return "listUser";
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam("username") String username, Model model) {
		User user = userService.getUser(username);
		model.addAttribute(user);
		addAttributes(model);
		return "listUser";
	}

	private void addAttributes(Model model) {
		model.addAttribute("users", userService.ListWithBooks());
		model.addAttribute("how_many", userService.numUsers());
		model.addAttribute("titulo", "Formulario Usuarios");
		model.addAttribute("descripcion", "Formulario para añadir/modificar los usuarios");
		model.addAttribute("menu", "lista_usuarios");
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
		String equalPasswords = null;
		String emailExist = null;
		if (result.hasErrors()) {
			System.out.println("Número de errores: " + result.getErrorCount());
			System.out.println("EqualPasswords.user: " + result.getFieldErrors());
			System.out.println("Todas errores: " + result.getAllErrors().get(0).getDefaultMessage());
			for (int i = 0; i < result.getAllErrors().size(); i++) {
				if (result.getAllErrors().get(i).getDefaultMessage().equals("Las contraseñas no son iguales")) {
					equalPasswords = "Las contraseñas no son iguales";
					continue;
				}
				if (result.getAllErrors().get(i).getDefaultMessage().equals("{user.email.exist}")) {
					emailExist = "Este email ya existe";
				}
			}

			model.addAttribute("equalPasswords", equalPasswords);
			model.addAttribute("emailExist", emailExist);
			model.addAttribute(userDto);
			addAttributes(model);
			return "listUser";
		}
		User user = new User(userDto);

		userService.save(user);
		return "redirect:/user/list";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("username") String username) {
		userService.deleteUser(username);
		return "redirect:/user/list";
	}

	@GetMapping("/loan_books")
	public String loanBooksUser(@RequestParam("username") String username, Model model) {
		User user = userService.getUserWithBooks(username);
		model.addAttribute(user);
		model.addAttribute("booklist", bookService.listBooks());
		model.addAttribute("titulo", "Listado de libros prestados a " + user.getName());
		model.addAttribute("descripcion", "Formulario para añadir/modificar libros prestados por " + user.getName());

		return "loanBooks";
	}

	@PostMapping("/confirm_loan")
	public String confirmLoan(@ModelAttribute("user") User userSent, Model model) {
		User user = userService.getUserWithBooks(userSent.getUsername());
		List<Book> books = userSent.getBooks();
		user.setBooks(books);
		userService.loanBooks(user);
		return "redirect:/user/list";
	}

	@GetMapping("/role")
	public String userRole(@RequestParam("username") String username, Model model) {

		User user = userService.getUser(username);
		model.addAttribute("role", new Role());
		addAttributes(model, user);
		model.addAttribute(user);
		return "userRole";
	}

	@PostMapping("/add_role")
	public String addRole(@ModelAttribute("role") @Valid Role role, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("Pasando por la sección errores");
			User user = userService.getUser(role.getUser().getUsername());
			addAttributes(model, user);
			return "userRole";
		}
		roleService.save(role);
		return "redirect:/user/list";
	}

	private void addAttributes(Model model, User user) {
		ArrayList<String> rolelist = new ArrayList<String>(Arrays.asList("ROLE_USER", "ROLE_WRITER", "ROLE_ADMIN"));
		model.addAttribute(user);
		model.addAttribute("rolelist", rolelist);
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("titulo", "Gestionar permisos de " + user.getName());
		model.addAttribute("descripcion", "Tabla que muestra los detalles acerca de " + user.getName());
	}

}
