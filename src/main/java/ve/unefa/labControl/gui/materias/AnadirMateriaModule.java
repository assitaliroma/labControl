package ve.unefa.labControl.gui.materias;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.dao.DuplicateKeyException;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Materia;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.gui.common.Utils;
import ve.unefa.labControl.persistence.dao.CoordinacionDAO;
import ve.unefa.labControl.persistence.dao.MateriaDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class AnadirMateriaModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(AnadirMateriaModule.class);
	private AnadirMateriaForm form;

	public AnadirMateriaModule() {
		super();
		
		add(form = new AnadirMateriaForm("anadirMateriaForm"));
	}

	public void onBeforeRender() {
		AnadirMateriaForm form = new AnadirMateriaForm("anadirMateriaForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}
	
	private class AnadirMateriaForm extends Form {
		private TextField codigo, name;
		private DropDownChoice coordinator;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;
		private List<String> coordinaciones;

		public AnadirMateriaForm(String id) {
			super(id);
			try {
				coordinaciones = ((CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO")).lookupCoordinacionForDisplay();
			} catch (Exception e1) {
				e1.printStackTrace();
				coordinaciones = new ArrayList<String>();
			}
			
			add(codigo = new TextField("anadirMateriaFormId",new Model()));
			add(name = new TextField("anadirMateriaFormName",new Model()));
			add(coordinator = new DropDownChoice("anadirMateriaFormCoord",new Model(), coordinaciones));
			add(submit = new AjaxFallbackButton("anadirMateriaFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						// Armo el objeto a enviar al DAO
						Materia mat = new Materia();
						mat.setId(codigo.getValue());
						mat.setNombre(name.getValue());
						mat.setCoordinacion(Utils.getLastPart(
							coordinaciones.get(Integer.parseInt(coordinator.getValue())),
							Constants.TOKEN_SEPARATOR));
						
						// Procesar los datos hacia el DAO
						MateriaDAO contenidoDAO;
						contenidoDAO = (MateriaDAO) ObjectFactory.getInstancia().getObjeto("materiasDAO");
						try {
							contenidoDAO.insertMateria(mat);
						} catch (DuplicateKeyException e) {
							error("El Id especificado para la materia ya esta en uso.");
							log.error("Se intento anadir la materia con un Id existente",e);
							return;
						} catch (Exception e) {
							log.error("Error al intentar anadir la materia",e);
							error("Ocurrio un error intentando añadir la materia.");
							return;
						}
						info("Los datos se han añadido exitosamente.");
					}
				}
			});
			add(feedback = (FeedbackPanel) new FeedbackPanel("anadirMateriaFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			// Ningun campo debe ser vacio
			if(codigo.getValue().equals("") || name.getValue().equals("")
				|| coordinator.getValue().equals("-1"))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// Hacer otras validaciones pertinentes
			
			return null;
		}
	}
}

