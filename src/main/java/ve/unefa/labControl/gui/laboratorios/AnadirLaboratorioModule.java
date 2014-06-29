package ve.unefa.labControl.gui.laboratorios;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.dao.DuplicateKeyException;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.persistence.dao.LaboratorioDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class AnadirLaboratorioModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(AnadirLaboratorioModule.class);
	private AnadirLaboratorioForm form;

	public AnadirLaboratorioModule() {
		super();
		
		add(form = new AnadirLaboratorioForm("anadirLabForm"));
	}

	public void onBeforeRender() {
		AnadirLaboratorioForm form = new AnadirLaboratorioForm("anadirLabForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}
	
	private class AnadirLaboratorioForm extends Form {
		private TextField name, capacity;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;

		public AnadirLaboratorioForm(String id) {
			super(id);
			add(name = new TextField("anadirLabFormName",new Model()));
			add(capacity = new TextField("anadirLabFormCap",new Model()));
			add(submit = new AjaxFallbackButton("anadirLabFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						// Armo el objeto a enviar al DAO
						Laboratorio lab = new Laboratorio();
						lab.setNombre(name.getValue());
						lab.setCapacidad(Integer.parseInt(capacity.getValue()));
						
						// Procesar los datos hacia el DAO
						LaboratorioDAO contenidoDAO;
						contenidoDAO = (LaboratorioDAO) ObjectFactory.getInstancia().getObjeto("laboratoriosDAO");
						try {
							contenidoDAO.insertLaboratorio(lab);
						} catch (DuplicateKeyException e) {
							error("El Id especificado para el laboratorio ya esta en uso.");
							log.error("Se intento anadir el laboratorio con un Id existente",e);
							return;
						} catch (Exception e) {
							log.error("Error al intentar anadir el laboratorio",e);
							error("Ocurrio un error intentando añadir el laboratorio.");
							return;
						}
						info("Los datos se han añadido exitosamente.");
					}
				}
			});
			add(feedback = (FeedbackPanel) new FeedbackPanel("anadirLabFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			// Ningun campo debe ser vacio
			if(name.getValue().equals("") || capacity.getValue().equals(""))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// La capacidad debe ser un entero mayor o igual a 0
			try {
				int cap = Integer.parseInt(capacity.getValue());
				if(cap < 0)
					return Constants.MENSAJE_CAPACIDAD_INVALIDA;
			} catch(NumberFormatException e) {
				return Constants.MENSAJE_CAPACIDAD_NO_ENTERO;
			}
			
			// Hacer otras validaciones pertinentes
			
			return null;
		}
	}
}

