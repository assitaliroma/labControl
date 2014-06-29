package ve.unefa.labControl.persistence.detachable;
import org.apache.wicket.model.LoadableDetachableModel;

import ve.unefa.labControl.domain.Profesor;

public class DetachableProfesorModel extends LoadableDetachableModel {

	private Long id;
	private Profesor p;
	
	public DetachableProfesorModel(Profesor p) {
		this(p.getId());
		this.p = p;
	}
	public DetachableProfesorModel(Long id) {
		this.id = id;
	}
	
	@Override
	protected Object load() {
		return p;
//		List<Profesor> list =  null;
//		GeneralDataProvider d=new GeneralDataProvider();
//		try {
//			list=d.getProfesorList();
//		} catch (Exception e) {
//
//		}
//		for (Profesor profesor : list) {
//			if (id.equals(profesor.getId()))
//				return profesor;
//		}
//		return null;
	}

}