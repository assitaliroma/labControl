package ve.unefa.labControl.persistence.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Coordinacion;
import ve.unefa.labControl.domain.Horario;
import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.domain.Materia;
import ve.unefa.labControl.domain.Profesor;
import ve.unefa.labControl.persistence.dao.CoordinacionDAO;
import ve.unefa.labControl.persistence.dao.LaboratorioDAO;
import ve.unefa.labControl.persistence.dao.MateriaDAO;
import ve.unefa.labControl.persistence.dao.ProfesorDAO;
/**
 * Clase que permite la interaccion entre el frontend y los DAOs
 * para invocar los diferentes metodos que operan la BD
 * @author desarrollador
 *
 */

@SuppressWarnings("serial")
public class GeneralDataProvider implements Serializable{

    public static List<Coordinacion> getCoordinacionList(String filterString){

        List<Coordinacion> coordinaciones=new ArrayList<Coordinacion>();
		   	
		CoordinacionDAO contenidoDAO;
		try {
			contenidoDAO = (CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO");
			coordinaciones=contenidoDAO.lookupCoordinacion(filterString);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return coordinaciones;
	}

	public static List<Materia> getMateriaList(String filterString){
        List<Materia> materias=new ArrayList<Materia>();
		
		MateriaDAO contenidoDAO;
		try {
			contenidoDAO = (MateriaDAO) ObjectFactory.getInstancia().getObjeto("materiasDAO");
			materias=contenidoDAO.lookupMateria(filterString);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return materias;
	}
	
	public static List<Profesor> getProfesorList(String filterString){

        List<Profesor> profesores=new ArrayList<Profesor>();
        
        ProfesorDAO contenidoDAO;
		try {
			contenidoDAO = (ProfesorDAO) ObjectFactory.getInstancia().getObjeto("profesoresDAO");
			profesores=contenidoDAO.lookupProfesor(filterString);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return profesores;
	}
	public static List<Laboratorio> getLaboratorioList(String filterString){

        List<Laboratorio> laboratorios=new ArrayList<Laboratorio>();

        LaboratorioDAO contenidoDAO;
		try {
			contenidoDAO = (LaboratorioDAO) ObjectFactory.getInstancia().getObjeto("laboratoriosDAO");
			laboratorios=contenidoDAO.lookupLaboratorio(filterString);
		} catch (Exception e) {
			e.printStackTrace();
		}
        	
		return laboratorios;
	}
	
	public static void deleteCoordinacion(String id){
		CoordinacionDAO coordinacionDAO;
		try{
		coordinacionDAO=(CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO");
		coordinacionDAO.deleteCoordinacion(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void deleteLaboratorio(String id){
		LaboratorioDAO laboratorioDAO;
		try{
			laboratorioDAO=(LaboratorioDAO) ObjectFactory.getInstancia().getObjeto("laboratoriosDAO");
			laboratorioDAO.deleteLaboratorio(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void deleteMateria(String id){
		MateriaDAO materiaDAO;
		try{
			materiaDAO=(MateriaDAO) ObjectFactory.getInstancia().getObjeto("materiasDAO");
			materiaDAO.deleteLaboratorio(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void deleteProfesor(String id){
		ProfesorDAO profesorDAO;
		try{
			profesorDAO=(ProfesorDAO) ObjectFactory.getInstancia().getObjeto("profesoresDAO");
			profesorDAO.deleteProfesor(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}