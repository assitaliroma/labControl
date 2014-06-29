package ve.unefa.labControl.persistence.detachable;
import org.apache.wicket.model.LoadableDetachableModel;

import ve.unefa.labControl.domain.Laboratorio;

public class DetachableLaboratorioModel extends LoadableDetachableModel {

	private Long id;
	private Laboratorio l;
	
	public DetachableLaboratorioModel(Laboratorio l) {
		this(l.getId());
		this.l = l;
	}
	public DetachableLaboratorioModel(Long id) {
		this.id = id;
	}
	
	@Override
	protected Object load() {
		return l;
//		List<Laboratorio> list =  null;
//		GeneralDataProvider d=new GeneralDataProvider();
//		try {
//			list=d.getLaboratorioList();
//		} catch (Exception e) {
//
//		}
//		for (Laboratorio laboratorio : list) {
//			if (id.equals(laboratorio.getId()))
//				return laboratorio;
//		}
//		return null;
	}

}