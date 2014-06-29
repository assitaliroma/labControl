package ve.unefa.labControl.persistence.detachable;
import org.apache.wicket.model.LoadableDetachableModel;

import ve.unefa.labControl.domain.Coordinacion;

public class DetachableCoordinacionModel extends LoadableDetachableModel {

	private String id;
	private Coordinacion c;
	
	public DetachableCoordinacionModel(Coordinacion c) {
		this(c.getId());
		this.c = c;
	}
	public DetachableCoordinacionModel(String id) {
		this.id = id;
	}
	
	@Override
	protected Object load() {
		return c;
//		List<Coordinacion> list =  null;
//		GeneralDataProvider d=new GeneralDataProvider();
//		try {
//			list=d.getCoordinacionList(null);
//		} catch (Exception e) {
//
//		}
//		for (Coordinacion coordinacion : list) {
//			if (id.equals(coordinacion.getId()))
//				return coordinacion;
//		}
//		return null;
	}

}