package com.ludo.tutorial.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category extends DateColumns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	@Size(max = 100, min = 3, message = "{category.name.invalid}")
	@NotEmpty(message = "{category.value.required}")
	@NotNull(message = "{category.value.notnull}")
	private String name;

	// De one to many se autoriza el uso de ALL
	// Pero se debería de evitar a todo coste:
	// No se debe utilizar como comodín ni ponerse en casos de desconocimiento de la
	// funcionalidad de la relación.
	// https://www.nestoralmeida.com/cascade-en-jpa-hibernate/#4-cascadetypeall
	// MERGE es para actualizar datos. Lo hará respectando la relación
	// con los hijos de la clase
	// PERSIST es para crear datos. Lo hará respectando la relación
	// con los hijos de la clase
	// SAVE es automáticamente disponible pero no respeta la relación
	// establecida
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.DETACH }, mappedBy = "category")
	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		return books;
	}

	// Los demás son: REMOVE --> borra la fila indicada y sus relaciones
	// asociadas
	// DETACH --> Borra quitando la relación que existe entre dos modelos
	// REFRESH --> refresca los datos recargando los contenidos
	// de la BDD y descartando el objeto gestionado.
	// Muy especial y muy poco utilizado
	// Útil para garantizar que se esté manejando la versión
	// más actualizada de una entidad en caso de haber sido
	// modificado por otro usuario

	public Category(long id, Date createdAt, Date updatedAt,
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {
		super(createdAt, updatedAt);
		this.id = id;
		this.name = name;
	}

	public Category(Date createdAt, Date updatedAt,
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {
		super(createdAt, updatedAt);
		this.name = name;
	}

	public Category(
			@Size(max = 100, min = 3, message = "{category.name.invalid}") @NotEmpty(message = "{category.value.required}") String name) {

		this.name = name;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
