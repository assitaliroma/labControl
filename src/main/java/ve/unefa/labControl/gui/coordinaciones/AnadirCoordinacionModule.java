package ve.unefa.labControl.gui.coordinaciones;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.dao.DuplicateKeyException;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Coordinacion;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.gui.common.Utils;
import ve.unefa.labControl.persistence.dao.CoordinacionDAO;
import ve.unefa.labControl.persistence.dao.TurnoDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class AnadirCoordinacionModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(AnadirCoordinacionModule.class);
	private AnadirCoordinacionForm form;

	public AnadirCoordinacionModule() {
		super();
		
		add(form = new AnadirCoordinacionForm("anadirCoordForm"));
	}

	public void onBeforeRender() {
		AnadirCoordinacionForm form = new AnadirCoordinacionForm("anadirCoordForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}

	private class AnadirCoordinacionForm extends Form {
		private TextField _id, name, coordinator, telephone;
		private RadioChoice shift;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;
		private List<String> turnos;

		public AnadirCoordinacionForm(String id) {
			super(id);
			try {
				turnos = ((TurnoDAO) ObjectFactory.getInstancia().getObjeto("turnosDAO")).lookupTurnoKeys();
			} catch (Exception e1) {
				e1.printStackTrace();
				turnos = new ArrayList<String>();
			}
			
			add(_id = new TextField("anadirCoordFormId",new Model()));
			add(name = new TextField("anadirCoordFormName",new Model()));
			add(coordinator = new TextField("anadirCoordFormCoord",new Model()));
			add(telephone = new TextField("anadirCoordFormTel",new Model()));
			add(shift = new RadioChoice("anadirCoordFormShift", new Model(), turnos));
			add(submit = new AjaxFallbackButton("anadirCoordFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						// Armo el objeto a enviar al DAO
						Coordinacion coord = new Coordinacion();
						coord.setId(_id.getValue());
						coord.setCarrera(name.getValue());
						coord.setTelefono(telephone.getValue());
						coord.setCoordinador(coordinator.getValue());
						coord.setTurno(turnos.get(Integer.parseInt(shift.getValue())));
						
						// Procesar los datos hacia el DAO
						CoordinacionDAO contenidoDAO;
						contenidoDAO = (CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO");
						try {
							contenidoDAO.insertCoordinacion(coord);
						} catch (DuplicateKeyException e) {
							error("El Id especificado para la coordinacion ya esta en uso.");
							log.error("Se intento anadir la coordinacion con un Id existente",e);
							return;
						} catch (Exception e) {
							log.error("Error al intentar anadir la coordinacion",e);
							error("Ocurrio un error intentando añadir la coordinacion.");
							return;
						}
						info("Los datos se han añadido exitosamente.");
					}
				}
			});
			add(feedback = (FeedbackPanel) new FeedbackPanel("anadirCoordFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			// Ningun campo debe ser vacio
			if(_id.getValue().equals("") || name.getValue().equals("")
				|| coordinator.getValue().equals("") || telephone.getValue().equals("")
				|| shift.getValue().equals("-1"))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// El telefono debe ser valido
			if(!Utils.validatePhone(telephone.getValue()))
				return Constants.MENSAJE_TELEFONO_INVALIDO;
			
			// Hacer otras validaciones pertinentes
			
			return null;
		}
	}
}

