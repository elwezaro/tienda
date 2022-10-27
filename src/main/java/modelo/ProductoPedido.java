package modelo;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProductoPedido {

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Miniatura miniatura;
	
	private int cantidad;
	
	@Id
	@GeneratedValue
	private int id;
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
