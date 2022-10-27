package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProductosCarrito {
	
	@ManyToOne
	@JoinColumn(name="carrito_id")
	private Carrito carrito;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Miniatura miniatura;
	
	private int cantidad;
	
	@Id
	@GeneratedValue
	private int id;

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Miniatura getMiniatura() {
		return miniatura;
	}

	public void setMiniatura(Miniatura miniatura) {
		this.miniatura = miniatura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	
}
