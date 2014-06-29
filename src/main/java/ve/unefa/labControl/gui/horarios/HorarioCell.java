package ve.unefa.labControl.gui.horarios;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import ve.unefa.labControl.domain.Horario;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.gui.common.PageSkeleton;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class HorarioCell extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(HorarioCell.class);
	private Horario horario;

	public HorarioCell(String id, Horario h) {
		super(id);
		setRenderBodyOnly(true);
		
		horario = h;
		
		AjaxFallbackLink l;
		
		add(l = new AjaxFallbackLink("cellContent"){

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				log.debug("Clickee! "+horario.getMateria());
			}
		});
		l.add(new Label("cellText", h.getMateria().getId()).setRenderBodyOnly(true));
		l.add(new SimpleAttributeModifier("title", h.getMateria().getNombre()));
	}
	
	public HorarioCell(String id, Date d) {
		super(id);
		setRenderBodyOnly(true);
		horario = new Horario();
		horario.setFechaInicio(d);
		
		AjaxFallbackLink l;
		
		add(l = new AjaxFallbackLink("cellContent"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				log.debug("Clickee! "+horario.getFechaInicio().toString());
				// Cambio el modulo tanto en server (page) como en el client (target)
				AnadirHorarioModule panelFor = (AnadirHorarioModule) PageSkeleton.getPageSkeleton(target)
						.getModuleFactory().getPanelFor(AnadirHorarioModule.class);
				
				panelFor.loadDataForAdd(horario.getFechaInicio());
				
				PageSkeleton.getPageSkeleton(target)
					.addOrReplace(panelFor);
				target.addComponent(panelFor);
			}
		});
		l.add(new Label("cellText", Constants.HORARIO_LIBRE).setRenderBodyOnly(true));
		l.add(new SimpleAttributeModifier("title", Constants.HORARIO_ALT_RESERVAR));
	}

}

