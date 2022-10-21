package com.ludo.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping({ "/", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "Inserción de estilos tipo Bootstrap");
		String msj = "Versión 0.0.1 Hola mundo.<br/>";
		msj += "Versión 0.0.2 Configuración de estilos con Bootstrap.<br/>";
		msj += "Versión 0.0.3 Creación del modelo Book.<br/>";
		msj += "Versión 0.0.4 Creación de la carpeta resources.<br/>";
		msj += "Versión 0.0.5 Creación de las capas de negocio y de acceso a datos.<br/>";
		msj += "Versión 0.0.6 Impresión en tabla de los libros incluidos en la base de datos.<br/>";
		msj += "Versión 0.0.7 Configuración del formulario para incluir libros en la base de datos.<br/>";
		msj += "Versión 0.0.8 Validación del formulario.<br/>";
		msj += "Versión 0.0.9 Modificación del formulario para poder borrar libros.<br/>";
		msj += "Versión 0.1.0 Modificación del formulario para poder actualizar libros.<br/>";
		msj += "Versión 0.1.1 Modificación del modelo Book: Le extendemos al modelo DateColumns "
				+ "y añadimos dos columnas de fecha a la tabla. Indicarán cuando se ha creado un registro y cuando se ha actualizado.<br/>";
		msj += "Versión 0.1.2 Creación del modelo BookDetails y de la relación con Book. Configuración de la validación.<br/>";
		msj += "Versión 0.1.3 Creación del modelo Category y del formulario.<br/>";
		msj += "Versión 0.1.4 Modificación del formulario Book para asignar categorías a los libros.<br/>";
		msj += "Versión 0.1.5 Modificación de la estructura de interfaces.<br/>";
		msj += "Versión 0.1.6 Configuración completa del modelo User.<br/>";
		msj += "Versión 0.1.7 Revisión global: Activación de la opción active y modificación en el formulario User.<br/>";
		/*
		 * Se han llevado a cabo ciertas modificaciones al nivel de la NAV, de la
		 * variable menu de cada controlador. Además en el formulario User, he bloqueado
		 * la opción para modificar el username una vez creada y he añadido la columna
		 * updated_at
		 */

		msj += "Versión 0.1.8 Extender la descripción de usuarios añadiendo la relación con el préstamo de Book.<br/>";
		msj += "Versión 0.1.9 Listar los libros prestados a un usuario en modo Lazy.<br/>";
		msj += "Versión 0.2.0 Listar los libros prestados a UNOS usuarios en modo Lazy.<br/>";
		msj += "Versión 0.2.1 Validación de la contraseña. Los dos campos deben de ser iguales.<br/>";
		msj += "Versión 0.2.2 Validación de dos campos del formulario. Deben de ser iguales.<br/>";
		msj += "Versión 0.2.3 Validación del campo email. No debe de figurar en la BDD.<br/>";
		msj += "Versión 0.2.4 Creación del modelo Role.<br/>";
		msj += "Versión 0.2.5 Atribuir roles a los usuarios.<br/>";
		msj += "Versión 0.2.6 Añadir el inicio de sesión.<br/>";
		msj += "Versión 0.2.7 Afinar las opciones disponibles con el inicio de sesión.<br/>";
		msj += "Versión 0.2.8 Revisión global. Resolviendo un problema de seguridad.<br/>";
		msj += "Versión 0.2.9 Revisión global. Resolviendo un problema con el email del usuario actual.<br/>";
		mv.addObject("descripcion",
				"En esta sección, aprenderemos cómo crear un CRUD usando Spring MVC e Hibernate ORM framework "
						+ "sin ningún fichero de configuración XML que no sea el POM de Maven.");
		mv.addObject("descripcion_larga", msj);
		mv.addObject("menu", "index");
		mv.setViewName("index");

		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "Página de login");
		mv.addObject("descripcion", "Presentación de las funciones login usando Java Framework Security");

		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/forbidden")
	public ModelAndView forbidden() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "Contenidos restringidos");
		mv.addObject("descripcion", "Ups! Por lo que parece, no puedes acceder a estos contenidos");
		mv.addObject("lorem", lorem());

		mv.setViewName("forbidden");
		return mv;
	}

	private String lorem() {
		String cadena = "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut\r\n"
				+ "						posuere orci tortor, at ornare est mollis et. Quisque vel rhoncus\r\n"
				+ "						tortor, et vehicula tortor. Maecenas et bibendum arcu. Etiam id\r\n"
				+ "						erat eu felis viverra hendrerit sit amet quis felis. Etiam auctor\r\n"
				+ "						est ut est aliquam feugiat et faucibus leo. Phasellus et magna\r\n"
				+ "						justo. Suspendisse non vehicula leo. Pellentesque sollicitudin\r\n"
				+ "						augue ac sem sollicitudin blandit sit amet in metus.</p>\r\n"
				+ "					<p>Fusce a est at dolor ultrices tempor. Vivamus iaculis\r\n"
				+ "						laoreet turpis, vitae sagittis ante cursus sit amet. Donec non\r\n"
				+ "						elit rhoncus, pretium dui eget, sodales ipsum. Aenean sed placerat\r\n"
				+ "						velit. Nunc ultrices semper lacus vitae pellentesque. Fusce eget\r\n"
				+ "						nulla gravida, hendrerit ipsum ac, vehicula felis. Vivamus in\r\n"
				+ "						placerat nisi. Aenean dignissim enim vel neque euismod posuere.\r\n"
				+ "						Curabitur hendrerit nunc in magna volutpat, et dignissim ex\r\n"
				+ "						sollicitudin.</p>\r\n"
				+ "					<p>Suspendisse sit amet augue pharetra, ultricies odio\r\n"
				+ "						condimentum, rutrum velit. Vivamus id lorem dapibus, sagittis\r\n"
				+ "						velit vitae, mattis elit. Sed id varius leo. Cras convallis ligula\r\n"
				+ "						dictum, pellentesque quam eget, mattis lectus. Nulla maximus\r\n"
				+ "						pretium mi, eget mollis quam tincidunt et. Maecenas sollicitudin\r\n"
				+ "						dolor in ligula efficitur, scelerisque fringilla neque sodales.\r\n"
				+ "						Sed aliquet metus eget lectus iaculis, ut viverra urna pretium. Ut\r\n"
				+ "						sed imperdiet ex.</p>";
		return cadena;
	}

}
