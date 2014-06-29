package ve.unefa.labControl.gui.common;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Una subclase del Panel estandar de Wicket pero con opciones
 * habilitadas para su manejo en aplicaciones Ajax
 * (si existen mas instrucciones utiles -y comunes- para paneles Ajax
 * por favor colocar aqui)
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public abstract class AjaxSupportedPanel extends Panel {

	public AjaxSupportedPanel() {
		super(Constants.MODULE);
		setOutputMarkupId(true);
	}
	
	public AjaxSupportedPanel(String id) {
		super(id);
		setOutputMarkupId(true);
	}

}

