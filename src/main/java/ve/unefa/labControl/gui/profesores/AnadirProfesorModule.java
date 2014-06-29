package ve.unefa.labControl.gui.profesores;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.dao.DuplicateKeyException;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Profesor;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.gui.common.Utils;
import ve.unefa.labControl.persistence.dao.CoordinacionDAO;
import ve.unefa.labControl.persistence.dao.PeriodoDAO;
import ve.unefa.labControl.persistence.dao.ProfesorDAO;
import ve.unefa.labControl.persistence.dao.TurnoDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class AnadirProfesorModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(AnadirProfesorModule.class);
	private AnadirProfesorForm form;

	public AnadirProfesorModule() {
		super();
		
		add(form = new AnadirProfesorForm("anadirProfesorForm"));
	}

	public void onBeforeRender() {
		AnadirProfesorForm form = new AnadirProfesorForm("anadirProfesorForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}
	
	private class AnadirProfesorForm extends Form {
		private TextField name, telephone;
		private DropDownChoice coordinacion;
		private RadioChoice termino, shift;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;
		private List<String> coordinaciones;
		private List<String> turnos;
		private List<String> periodos;

		public AnadirProfesorForm(String id) {
			super(id);
			try {
				coordinaciones = ((CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO")).lookupCoordinacionForDisplay();
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener las coordinaciones",e1);
				coordinaciones = new ArrayList<String>();
			}
			
			try {
				turnos = ((TurnoDAO) ObjectFactory.getInstancia().getObjeto("turnosDAO")).lookupTurnoKeys();
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener los turnos",e1);
				turnos = new ArrayList<String>();
			}
			
			try {
				periodos = ((PeriodoDAO) ObjectFactory.getInstancia().getObjeto("periodosDAO")).lookupPeriodoKeys();
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener los periodos",e1);
				periodos = new ArrayList<String>();
			}
			
			add(name = new TextField("anadirProfesorFormName",new Model()));
			add(telephone = new TextField("anadirProfesorFormTel",new Model()));
			add(coordinacion = new DropDownChoice("anadirProfesorFormCoord",new Model(),coordinaciones));
			add(shift = new RadioChoice("anadirProfesorFormShift", new Model(), turnos));
			add(termino = new RadioChoice("anadirProfesorFormTerm", new Model(), periodos));
			add(submit = new AjaxFallbackButton("anadirProfesorFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						// Armo el objeto a enviar al DAO
						Profesor prof = new Profesor();
						prof.setNombre(name.getValue());
						prof.setTelefono(telephone.getValue());
						prof.setCoordinacion(Utils.getLastPart(
							coordinaciones.get(Integer.parseInt(coordinacion.getValue())),
							Constants.TOKEN_SEPARATOR));
						prof.setTurno(turnos.get(Integer.parseInt(shift.getValue())));
						prof.setPeriodo(periodos.get(Integer.parseInt(termino.getValue())));
						
						// Procesar los datos hacia el DAO
						ProfesorDAO contenidoDAO;
						contenidoDAO = (ProfesorDAO) ObjectFactory.getInstancia().getObjeto("profesoresDAO");
						try {
							contenidoDAO.insertProfesor(prof);
						} catch (DuplicateKeyException e) {
							error("El Id especificado para el profesor ya esta en uso.");
							log.error("Se intento anadir al profesor con un Id existente",e);
							return;
						} catch (Exception e) {
							log.error("Error al intentar anadir al profesor",e);
							error("Ocurrio un error intentando añadir al profesor.");
							return;
						}
						info("Los datos se han añadido exitosamente.");
					}
				}
			});
			add(feedback = (FeedbackPanel) new FeedbackPanel("anadirProfesorFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			// Ningun campo debe ser vacio
			if(name.getValue().equals("") || telephone.getValue().equals("")
				|| coordinacion.getValue().equals("-1") || shift.getValue().equals("-1")
				|| termino.getValue().equals("-1"))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// El telefono debe ser valido
			if(!Utils.validatePhone(telephone.getValue()))
				return Constants.MENSAJE_TELEFONO_INVALIDO;
			
			// Hacer otras validaciones pertinentes
			
			return null;
		}
	}
}

