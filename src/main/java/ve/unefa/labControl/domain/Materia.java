package ve.unefa.labControl.domain;

import java.io.Serializable;

public class Materia implements Serializable {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoordinacion() {
		return coordinacion;
	}
	public void setCoordinacion(String coordinacion) {
		this.coordinacion = coordinacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String coordinacion;
	public String nombre;
	

}
