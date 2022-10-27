package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	
	private String nombre;
	private String descripcion;
	private boolean alta;
	//relacion de categoria con libro
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Miniatura> miniaturas = new ArrayList<Miniatura>();
	
	
	@Id
	@GeneratedValue
	private long id;

	public Categoria() {
	}
	
	public Categoria(String nombre, String descripcion, List<Miniatura> miniaturas, long id) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.miniaturas = miniaturas;
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Miniatura> getMiniaturas() {
		return miniaturas;
	}
	public void setMiniaturas(List<Miniatura> miniaturas) {
		this.miniaturas = miniaturas;
	}
	
	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	

}
