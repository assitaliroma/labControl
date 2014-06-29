package ve.unefa.labControl.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Horario implements Serializable{
	private Long id;
	private Profesor profesor;
	private Materia materia;
	private Laboratorio lab;
	private String description;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public Laboratorio getLab() {
		return lab;
	}
	public void setLab(Laboratorio lab) {
		this.lab = lab;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
