package modelo;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrito {

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Usuario usuario;

	@OneToMany(mappedBy = "carrito",cascade = CascadeType.ALL)
	private List<ProductosCarrito> productosCarritos =
	new ArrayList<ProductosCarrito>();

	@Id
	@GeneratedValue
	private int id;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<ProductosCarrito> getProductosCarritos() {
		return productosCarritos;
	}

	public void setProductosCarritos(List<ProductosCarrito> productosCarritos) {
		this.productosCarritos = productosCarritos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
