package ve.unefa.labControl.gui.horarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.persistence.dao.LaboratorioDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class ListarHorariosModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(ListarHorariosModule.class);
	private ListarHorariosForm form;
	private HorarioTable table;

	public ListarHorariosModule() {
		super();
		
		add(form = new ListarHorariosForm("horarioLookupForm"));
//		add(table = new Label("horarioTable",""));
		add(table = new HorarioTable("horarioTable"));
		table.setOutputMarkupId(true);
	}

	public void onBeforeRender() {
		ListarHorariosForm form = new ListarHorariosForm("horarioLookupForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}
	
	private class ListarHorariosForm extends Form {
		private DropDownChoice lab, week;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;
		private List<String> laboratories;
		private List<String> weekList;
		private List<Laboratorio> laboratoriesObject;

		public ListarHorariosForm(String id) {
			super(id);
			
			try {
				laboratoriesObject = ((LaboratorioDAO) ObjectFactory.getInstancia().getObjeto("laboratoriosDAO"))
					.lookupLaboratorio(null);
				laboratories = new ArrayList<String>();
				for(Laboratorio l : laboratoriesObject)
					laboratories.add(l.getNombre());
			} catch (Exception e) {
				log.warn("Ocurrio un error al consultar la informacion de los laboratorios", e);
				laboratories = new ArrayList<String>();
			}
			
			weekList = Arrays.asList(new String[] {"Semana 1", "Semana 2", "Semana 3"});
			
			add(lab = new DropDownChoice("horarioLookupFormLab", new Model(), laboratories));
			add(week = new DropDownChoice("horarioLookupFormWeek", new Model(), weekList));
			add(submit = new AjaxFallbackButton("horarioLookupFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						try {
							table.fillTable(laboratoriesObject.get(Integer.parseInt(lab.getValue())),
								weekList.get(Integer.parseInt(week.getValue())));
						} catch(Exception e) {
							log.error("Hubo un error al buscar la información.", e);
							error("Hubo un error al buscar la información.");
							return;
						}
						target.addComponent(table);
						info("Todo fino");
					}
				}
			});
			add(feedback = (FeedbackPanel) new FeedbackPanel("horarioLookupFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			// Ningun campo debe ser vacio
			if(lab.getValue().equals("-1")
				|| week.getValue().equals("-1"))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// Hacer otras validaciones pertinentes
			
			return null;
		}
		
	}

}

