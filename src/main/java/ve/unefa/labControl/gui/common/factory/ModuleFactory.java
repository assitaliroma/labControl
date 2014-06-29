package ve.unefa.labControl.gui.common.factory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class ModuleFactory implements Serializable {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(ModuleFactory.class);
	private Map<Class<? extends Panel>, Panel> clazzToPanelMap;
	
	public ModuleFactory() {
		clazzToPanelMap = new HashMap<Class<? extends Panel>, Panel>();
	}
	
	public Panel getPanelFor(Class<? extends Panel> clazz) {
		Panel p;
		if(!clazzToPanelMap.containsKey(clazz)) {
			try {
				p = clazz.newInstance();
				clazzToPanelMap.put(clazz, p);
			} catch (Exception e) {
				log.error("Ha ocurrido un error al cargar el modulo",e);
				p = null;
			}
		} else
			p = clazzToPanelMap.get(clazz);
		
		return p;
	}
}
