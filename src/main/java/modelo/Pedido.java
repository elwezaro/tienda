package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ProductoPedido> productosPedido = 
		new ArrayList<ProductoPedido>();
	
	//se piden en paso 1:
	private String nombreCompleto;
	
	private String direccion;
	
	private String provincia;
	
	private String codigoPostal;
	
	private String pais;
	//fin paso 1
	
	//se piden en paso 2:
	private String titularTarjeta;

	private String numeroTarjeta;
	
	private String tipoTarjeta;
	
	private String cvp;
	//fin paso dos
	
	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;
	
	private String estado;
	
	@Id
	@GeneratedValue
	private int id;

	
	

	public List<ProductoPedido> getProductosPedido() {
		return productosPedido;
	}

	public void setProductosPedido(List<ProductoPedido> productosPedido) {
		this.productosPedido = productosPedido;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	
	public String getCvp() {
		return cvp;
	}

	public void setCvp(String cvp) {
		this.cvp = cvp;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
