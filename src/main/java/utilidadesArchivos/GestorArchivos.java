package utilidadesArchivos;

import java.io.File;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import modelo.Miniatura;
import modelo.Usuario;

public class GestorArchivos {
	public static void guardarFotoUsuario(Usuario u, String rutaReal,CommonsMultipartFile foto) {
		String nombreArchivo = u.getId()+".jpg";
		
		String rutaFotos = rutaReal+"/fotos";
		File fileCarpetaFotos = new File(rutaFotos);
		if(! fileCarpetaFotos.exists()) {
			fileCarpetaFotos.mkdir();
		}
		if(foto.getSize()>0) {
			try {
				foto.transferTo(new File(rutaFotos,nombreArchivo));
				System.out.println("foto del usuario disponible en:"+ rutaFotos);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public static void guardarPortdaMiniatura(Miniatura m, String rutaReal) {
		// si el libro a sido previamente guardao en base de datos por hibernate, hibarnate ya le asigno una id
		
		MultipartFile archivo = m.getImagen();
		String nombreArchivo = m.getId()+".jpg";
		
		// vamos a crear una carpeta de subidas si no existe en la ruta del proyecto
		
		String rutaSubidas=rutaReal+"/subidas"; 
		File fileRutaSubidas = new File(rutaSubidas);
		if(! fileRutaSubidas.exists()) {
			fileRutaSubidas.mkdir();
		}
		try {
			archivo.transferTo(new File(rutaSubidas,nombreArchivo));
		} catch (IllegalStateException | IOException e) {
			System.out.println("no pude subir el archivo a la ruta de subidas");
			e.printStackTrace();
		}
		
		//mover el archivo a dicha ruta poniendole el nombre indicado

	}
	
}
