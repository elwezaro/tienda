package datos.servicios;

import java.util.List;

import java.util.Map;




public class ResumenPedido {

	private  List<Map<String, Object>> miniaturas;
	
	// paso 1
	private String nombreCompleto;
	
	private String direccion;
	
	private String provincia;
	
	private String codigoPostal;
	
	private String pais;
	
	
	//paso 2
	private String titularTarjeta;

	private String numeroTarjeta;
	
	private String tipoTarjeta;

	private String cvp;
	

	public List<Map<String, Object>> getMiniaturas() {
		return miniaturas;
	}

	public void setMiniaturas(List<Map<String, Object>> miniaturas) {
		this.miniaturas = miniaturas;
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

	public String getTitularTarjeta() {
		return titularTarjeta;
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

	
}
