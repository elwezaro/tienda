package modelo;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Miniatura {
	
 @OneToOne
 private ProductosCarrito productosCarrito;
	
 @NotEmpty(message = "error el nombre no puede estar vacio")
 @Size(min = 1,max = 60,message = "nombre tiene que tener entr uno y 20 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String nombre;
 
 @NotEmpty(message = "error el marca no puede estar vacio")
 @Size(min = 1,max = 60,message = "marca tiene que tener entr uno y 60 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String marca;
 

 @NumberFormat(style = Style.NUMBER,pattern = "#,###.###")
 @Min(value = 1,message = "tiene que tener un precio minimo")
 private double precio;

 @NotEmpty(message = "error la  no puede estar vacio")
 @Size(min = 1,max = 60,message = "faccion tiene que tener entr uno y 60 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String faccion;
 
 @NotEmpty(message = "error el marca no puede estar vacio")
 @Size(min = 1,max = 60,message = "el tipo de unidad tiene que tener entr 1 y 60 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String tipoUnidad;
 
 @NotEmpty(message = "error el material no puede estar vacio")
 @Size(min = 1,max = 60,message = "material tiene que tener entr 1 y 60 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String material;
 
 @NotEmpty(message = "error el marca no puede estar vacio")
 @Size(min = 1,max = 60,message = "formato tiene que tener entr 1 y 60 caracteres")
 @Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$",message = "solo letras numeros y espacios")
 private String formato;
 
 @Transient
 private MultipartFile imagen;
 
 @ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = Categoria.class, optional = false, fetch = FetchType.EAGER)
 private Categoria categoria;

 private boolean alta;
 
 @Transient
 private long idCategoria;
 @Id
 @GeneratedValue
 private int id;

 public Miniatura() {

 }
public Miniatura(String nombre, String marca, double precio, String faccion, String tipoUnidad, String material,
		String formato,int id) {
	super();
	this.nombre = nombre;
	this.marca = marca;
	this.precio = precio;
	this.faccion = faccion;
	this.tipoUnidad = tipoUnidad;
	this.material = material;
	this.formato = formato;
	this.id=id;
}
public Miniatura(String nombre, String marca, double precio, String faccion, String tipoUnidad, String material,
		String formato) {
	super();
	this.nombre = nombre;
	this.marca = marca;
	this.precio = precio;
	this.faccion = faccion;
	this.tipoUnidad = tipoUnidad;
	this.material = material;
	this.formato = formato;

}


public ProductosCarrito getProductosCarrito() {
	return productosCarrito;
}
public void setProductosCarrito(ProductosCarrito productosCarrito) {
	this.productosCarrito = productosCarrito;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public double getPrecio() {
	return precio;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public String getFaccion() {
	return faccion;
}
public void setFaccion(String faccion) {
	this.faccion = faccion;
}
public String getTipoUnidad() {
	return tipoUnidad;
}
public void setTipoUnidad(String tipoUnidad) {
	this.tipoUnidad = tipoUnidad;
}
public String getMaterial() {
	return material;
}
public void setMaterial(String material) {
	this.material = material;
}
public String getFormato() {
	return formato;
}
public void setFormato(String formato) {
	this.formato = formato;
}

public MultipartFile getImagen() {
	return imagen;
}

public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}

public boolean isAlta() {
	return alta;
}
public void setAlta(boolean alta) {
	this.alta = alta;
}
public long getIdCategoria() {
	return idCategoria;
}
public void setIdCategoria(long idCategoria) {
	this.idCategoria = idCategoria;
}
public void setImagen(MultipartFile imagen) {
	this.imagen = imagen;
}
@Override
public String toString() {
	return "Miniatura [nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", faccion=" + faccion
			+ ", tipoUnidad=" + tipoUnidad + ", material=" + material + ", formato=" + formato + ", id=" + id + "]";
}
 
 
}
