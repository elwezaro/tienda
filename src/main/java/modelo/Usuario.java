package modelo;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario {
	
	@OneToOne
	private Carrito carrito;
	
	private String nombre;
	private String email;
	private String pass;
	@Id
	@GeneratedValue
	private int id;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String email, String pass) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}

	
	
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", pass=" + pass + "]";
	}
	
}
