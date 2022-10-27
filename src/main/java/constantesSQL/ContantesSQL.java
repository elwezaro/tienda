package constantesSQL;

public class ContantesSQL {
	public final static String OBTENER_TOTAL_MINIATURAS= "select count(id) from miniatura where nombre like :nombre";
	public final static String SQL_BORRAR_LIBRO="DELETE from miniatura where id = :id";
	public final static String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE = 
			"select id, nombre from categoria order by id asc";
	public final static String SQL_OBTENER_MINIATURAS_PARA_LISTADO=
			"select m.id, m.marca, m.faccion, m.formato, m.material, m.nombre, m.precio, m.tipoUnidad, c.nombre as nombre_categoria \r\n"
			+ "from miniatura as m, categoria as c \r\n"
			+ "where m.categoria_id = c.id and m.alta = 1 \r\n"
			+ "order by m.id desc";
	public final static String OBTENER_MINIATURA_POR_ID = "select * from miniatura where id= :id";
	public static final String OBTENER_PRODUCTOS_CARRITO="SELECT m.id as miniatura_id, m.nombre,m.precio,pc.cantidad from miniatura AS m,productoscarrito AS pc WHERE pc.miniatura_id = m.id AND pc.carrito_id = :carrito_id ORDER by pc.id ASC";
	public static final String SQL_BORRAR_PRODUCTO_CARRITO = "DELETE from productoscarrito where carrito_id = :id_carrito and miniatura_id = :miniatura_id";
	public static final String SQL_BORRAR_TODOS_PRODUCTOS_CARRITO = "DELETE from productoscarrito where carrito_id = :id_carrito";
	public static final String SQL_OBTENER_PEDIDOS_USUARIO = "select * from pedido where usuario_id= :idusuario";
}
