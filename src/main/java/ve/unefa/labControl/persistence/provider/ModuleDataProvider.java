package ve.unefa.labControl.persistence.provider;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.model.IModel;

import ve.unefa.labControl.domain.Coordinacion;
import ve.unefa.labControl.domain.Horario;
import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.domain.Materia;
import ve.unefa.labControl.domain.Profesor;
import ve.unefa.labControl.persistence.detachable.DetachableCoordinacionModel;
import ve.unefa.labControl.persistence.detachable.DetachableHorarioModel;
import ve.unefa.labControl.persistence.detachable.DetachableLaboratorioModel;
import ve.unefa.labControl.persistence.detachable.DetachableMateriaModel;
import ve.unefa.labControl.persistence.detachable.DetachableProfesorModel;

@SuppressWarnings("serial")
public class ModuleDataProvider implements ISortableDataProvider{
	private List<Coordinacion> coordinacionesList;
	private List<Materia> materiasList;
	private List<Profesor> profesoresList;
	private List<Laboratorio> laboratoriosList;
	private List<List<Object>> horariosList;
	private int menu=0;
	
	public List<Coordinacion> getCoordinacionesList() {
		return coordinacionesList;
	}

	public void setCoordinacionesList(List<Coordinacion> coordinacionesList) {
		this.coordinacionesList = coordinacionesList;
	}

	public ModuleDataProvider(int menu) {
		super();
		this.menu=menu;
		horariosList =  new ArrayList<List<Object>>();
		laboratoriosList = new ArrayList<Laboratorio>();
		profesoresList =  new ArrayList<Profesor>();
		coordinacionesList = new ArrayList<Coordinacion>();
		materiasList =  new ArrayList<Materia>();
		
	}
	
	public Iterator iterator(int first, int count) {
		Iterator result=null;
		switch(menu){
		case 1:
			result= coordinacionesList.subList(first, first+count).iterator();
			break;
		case 2:
			result= profesoresList.subList(first, first+count).iterator();
			break;
		case 3:
			result= materiasList.subList(first, first+count).iterator();
			break;
		case 4:
			result= laboratoriosList.subList(first, first+count).iterator();
			break;
		case 5:
			result= horariosList.subList(first, first+count).iterator();
			break;
		}
		return result;
	
	}

	public IModel model(Object object) {
		IModel result=null;
		switch(menu){
		case 1:
			result= new DetachableCoordinacionModel((Coordinacion) object);
			break;
		case 2:
			result= new DetachableProfesorModel((Profesor) object);
			break;
		case 3:
			result= new DetachableMateriaModel((Materia) object);
			break;
		case 4:
			result= new DetachableLaboratorioModel((Laboratorio) object);
			break;
		case 5:
			result= new DetachableHorarioModel((List<Object>) object);
			break;
	
		}
		return result;
	}

	public int size() {
		int result=0;
		switch(menu){
		case 1:
			if (coordinacionesList == null) {
				coordinacionesList = new ArrayList <Coordinacion>();
			}
			
			result= coordinacionesList.size();
			break;
		case 2:
			if (profesoresList == null) {
				profesoresList = new ArrayList <Profesor>();
			}
			
			result= profesoresList.size();
			break;
		case 3:
			if (materiasList == null) {
				materiasList = new ArrayList <Materia>();
			}
			
			result= materiasList.size();
			break;
		case 4:
			if (laboratoriosList == null) {
				laboratoriosList = new ArrayList <Laboratorio>();
			}
			
			result= laboratoriosList.size();
			break;
		case 5:
			if (horariosList == null) {
				horariosList = new ArrayList <List<Object>>();
			}
			
			result= horariosList.size();
			break;
	
		}
		
		return result;
	}



	public ISortState getSortState() {
		return null;
	}

	public void setSortState(ISortState state) {
	}
	
	public void loadCoordinacionesData(){
		loadCoordinacionesData(null);
	}
	
	public void loadCoordinacionesData(String filterString){
		try{
			this.coordinacionesList = GeneralDataProvider.getCoordinacionList(filterString);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadMateriasData(){
		loadMateriasData(null);
	}
	
	public void loadMateriasData(String filterString){
		try{
			this.materiasList = GeneralDataProvider.getMateriaList(filterString);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadProfesoresData(){
		loadProfesoresData(null);
	}
	
	public void loadProfesoresData(String filterString){
		try{
			this.profesoresList = GeneralDataProvider.getProfesorList(filterString);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadLaboratoriosData(){
		loadLaboratoriosData(null);
	}
	
	public void loadLaboratoriosData(String filterString){
		try{
			 this.laboratoriosList = GeneralDataProvider.getLaboratorioList(filterString);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadHorariosData(){
		loadHorariosData(null);
	}
	
	public void loadHorariosData(String filterString){
//		try{
//			 this.laboratoriosList = GeneralDataProvider.getLaboratorioList(filterString);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		horariosList = new ArrayList<List<Object>>();
		List<Object> list = new ArrayList<Object>();
		try {
			list.add("7:00 - 7:45");
			list.add(new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("27-10-2010 07:00"));
		} catch (ParseException e) {}
		list.add(new Date());
		Materia m = new Materia();
		m.setId("6001");
		m.setNombre("Programación 1");
		Horario h = new Horario();
		h.setMateria(m);
		list.add(h);
		list.add(new Date());
		list.add(new Date());
		list.add(new Date());
		list.add(new Date());
		horariosList.add(list);
		
		List<Object> list1 = new ArrayList<Object>();
		list1.add("7:45 - 8:30");
		list1.add(new Date());
		list1.add(new Date());
		Materia m1 = new Materia();
		m1.setId("6001");
		m1.setNombre("Programación 1");
		Horario h1 = new Horario();
		h1.setMateria(m1);
		list1.add(h1);
		list1.add(new Date());
		list1.add(new Date());
		list1.add(new Date());
		list1.add(new Date());
		horariosList.add(list1);
		
		List<Object> list2 = new ArrayList<Object>();
		list2.add("8:30 - 9:15");
		list2.add(new Date());
		list2.add(new Date());
		Materia m2 = new Materia();
		m2.setId("6031");
		m2.setNombre("Programación 3");
		Horario h2 = new Horario();
		h2.setMateria(m2);
		list2.add(h2);
		list2.add(new Date());
		list2.add(new Date());
		Materia m3 = new Materia();
		m3.setId("3031");
		m3.setNombre("Electrónica 3");
		Horario h3 = new Horario();
		h3.setMateria(m3);
		list2.add(h3);
		list2.add(new Date());
		horariosList.add(list2);
	}
	
	public void clearHorariosData(){
		if(horariosList == null)
			horariosList = new ArrayList<List<Object>>();
		else
			horariosList.clear();
	}

	public void detach() {
	}

}
