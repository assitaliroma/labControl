package ve.unefa.labControl.persistence.detachable;
import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

@SuppressWarnings("serial")
public class DetachableHorarioModel extends LoadableDetachableModel {

	private List<Object> l;
	
	public DetachableHorarioModel(List<Object> l) {
		this.l = l;
	}
	
	@Override
	protected Object load() {
		return l;
//		List<Horario> list =  null;
//		GeneralDataProvider d=new GeneralDataProvider();
//		try {
//			list=d.getHorarioList();
//		} catch (Exception e) {
//
//		}
//		for (Horario horario : list) {
//			if (id.equals(horario.getId()))
//				return horario;
//		}
//		return null;
	}

}