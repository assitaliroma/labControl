package ve.unefa.labControl.domain;

import java.io.Serializable;

public class Coordinacion implements Serializable{
	
	private String carrera;
	private String turno;
	private String telefono;
	private String coordinador;
	private String id;
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
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
	public String getCoordinador() {
		return coordinador;
	}
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString() {
		String str = "ID: "+getId()+
			"\nCarrera: "+getCarrera()+
			"\nTelefono: "+getTelefono()+
			"\nCoordinador: "+getCoordinador()+
			"\nTurno: "+getTurno();
		return str;
	}

}
