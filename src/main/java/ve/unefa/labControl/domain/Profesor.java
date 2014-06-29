package ve.unefa.labControl.domain;

import java.io.Serializable;

public class Profesor implements Serializable{
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getCoordinacion() {
		return coordinacion;
	}
	public void setCoordinacion(String coordinacion) {
		this.coordinacion = coordinacion;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	private String nombre;
	private String periodo;
	private String coordinacion;
	private String turno;
	private String telefono;

}
