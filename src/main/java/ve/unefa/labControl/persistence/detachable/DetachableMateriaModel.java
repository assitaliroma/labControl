package ve.unefa.labControl.persistence.detachable;
import org.apache.wicket.model.LoadableDetachableModel;

import ve.unefa.labControl.domain.Materia;

public class DetachableMateriaModel extends LoadableDetachableModel {

	private String id;
	private Materia m;
	
	public DetachableMateriaModel(Materia m) {
		this(m.getId());
		this.m = m;
	}
	public DetachableMateriaModel(String id) {
		this.id = id;
	}
	
	@Override
	protected Object load() {
		return m;
//		List<Materia> list =  null;
//		GeneralDataProvider d=new GeneralDataProvider();
//		try {
//			list=d.getMateriaList();
//		} catch (Exception e) {
//
//		}
//		for (Materia materia : list) {
//			if (id.equals(materia.getId()))
//				return materia;
//		}
//		return null;
	}

}