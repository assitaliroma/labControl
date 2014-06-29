package ve.unefa.labControl.gui.horarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.dao.DuplicateKeyException;

import ve.unefa.labControl.bean.factory.ObjectFactory;
import ve.unefa.labControl.domain.Horario;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.gui.common.Utils;
import ve.unefa.labControl.persistence.dao.CoordinacionDAO;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class AnadirHorarioModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(AnadirHorarioModule.class);
	private AnadirHorarioForm form;

	public AnadirHorarioModule() {
		super();
		
		add(form = new AnadirHorarioForm("anadirHorarioForm"));
	}

	protected void onRemove() {
		AnadirHorarioForm form = new AnadirHorarioForm("anadirHorarioForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onRemove();
	}
	
	public void loadDataForAdd(Date startDate) {
		form.getStartDate().setDefaultModelObject(Utils.getStringDateFor(startDate));
		log.debug(Utils.getStringHourFor(startDate));
		List<String> hourList = form.getHourList();
		log.debug("idx: "+hourList.indexOf(Utils.getStringHourFor(startDate)));
		form.getStartHour().setDefaultModelObject(""+Utils.getStringHourFor(startDate));
	}
	
	private class AnadirHorarioForm extends Form {
		private DropDownChoice coordinacion;
		private DropDownChoice profesor;
		private DropDownChoice materia;
		private DropDownChoice laboratorio;
		private DropDownChoice tipoReservacion;
		private TextField descripcion;
		private TextField startDate;
		private TextField endDate;
		private DropDownChoice startHour;
		private DropDownChoice endHour;
		private AjaxFallbackButton submit;
		private FeedbackPanel feedback;
		private List<String> coordinaciones;
		private List<String> tiposReservacion;
		private List<String> periodos;
		private DatePicker startDatePicker;
		private DatePicker endDatePicker;
		private WebMarkupContainer startDateContainer;
		private WebMarkupContainer endDateContainer;
//		private RadioChoice termino, shift;
		private static final String DEFAULT_START_DATE = "15/09/2010";
		private static final String DEFAULT_END_DATE = "20/12/2010";
		private List<String> hourList;

		public AnadirHorarioForm(String id) {
			super(id);
			try {
				coordinaciones = ((CoordinacionDAO) ObjectFactory.getInstancia().getObjeto("coordinacionesDAO")).lookupCoordinacionForDisplay();
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener las coordinaciones",e1);
				coordinaciones = new ArrayList<String>();
			}
			
			try {
//				tiposReservacion = ((TurnoDAO) ObjectFactory.getInstancia().getObjeto("turnosDAO")).lookupTurnoKeys();
				tiposReservacion = Arrays.asList(new String[] {"Semestral", "Individual", "Contínua"});
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener los tipos de reservacion",e1);
				tiposReservacion = new ArrayList<String>();
			}
			
			try {
//				periodos = ((PeriodoDAO) ObjectFactory.getInstancia().getObjeto("periodosDAO")).lookupPeriodoKeys();
			} catch (Exception e1) {
				log.warn("Ocurrio un error al obtener los periodos",e1);
				periodos = new ArrayList<String>();
			}
			
			hourList = new ArrayList<String>();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 7);
			cal.set(Calendar.MINUTE, 0);
			for(int i=0;i<10;i++) {
				int h = cal.get(Calendar.HOUR_OF_DAY);
				int m = cal.get(Calendar.MINUTE);
				hourList.add((h<10?"0"+h:h)+":"+(m<10?"0"+m:m));
				cal.add(Calendar.MINUTE, 45);
			}
			
			add(coordinacion = new DropDownChoice("anadirHorarioFormCoord",new Model(),coordinaciones));
			add(profesor = new DropDownChoice("anadirHorarioFormProf",new Model(),coordinaciones));
			add(materia = new DropDownChoice("anadirHorarioFormMat",new Model(),coordinaciones));
			add(laboratorio = new DropDownChoice("anadirHorarioFormLab",new Model(),coordinaciones));
			add(tipoReservacion = new DropDownChoice("anadirHorarioFormType",new Model(),tiposReservacion));
			tipoReservacion.add(new AjaxFormComponentUpdatingBehavior("onchange") {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					int choice = Integer.parseInt(tipoReservacion.getValue());
					log.debug("Cambie tipoReservacion a "+choice);
					switch(choice) {
					case 1:
						// Se selecciono Individual
						target.addComponent(startDateContainer.add(new SimpleAttributeModifier("class","")));
						target.addComponent(startDate.setEnabled(true));
						target.addComponent(endDateContainer.add(new SimpleAttributeModifier("class","hideYUICalendar")));
						target.addComponent(endDate.setDefaultModelObject("").setEnabled(false));
						break;
					case 2:
						// Se selecciono Contínua
						target.addComponent(startDateContainer.add(new SimpleAttributeModifier("class","")));
						target.addComponent(startDate.setEnabled(true));
						target.addComponent(endDateContainer.add(new SimpleAttributeModifier("class","")));
						target.addComponent(endDate.setEnabled(true));
						break;
					default:
						// Se selecciono Semestral
						target.addComponent(startDateContainer.add(new SimpleAttributeModifier("class","hideYUICalendar")));
						target.addComponent(startDate.setDefaultModelObject(DEFAULT_START_DATE).setEnabled(false));
						target.addComponent(endDateContainer.add(new SimpleAttributeModifier("class","hideYUICalendar")));
						target.addComponent(endDate.setDefaultModelObject(DEFAULT_END_DATE).setEnabled(false));
						break;
					}
				}
			});
			startDateContainer = new WebMarkupContainer("anadirHorarioFormStartDateContainer");
			add(startDateContainer.setOutputMarkupId(true));
			startDateContainer.add(startDate = new TextField("anadirHorarioFormStartDate",new Model()));
			startDatePicker = new DatePicker();
			startDate.add(startDatePicker);
			startDate.setEnabled(false).setOutputMarkupId(true);
			
			endDateContainer = new WebMarkupContainer("anadirHorarioFormEndDateContainer");
			add(endDateContainer.setOutputMarkupId(true));
			
			endDateContainer.add(endDate = new TextField("anadirHorarioFormEndDate",new Model()));
			endDatePicker = new DatePicker();
			endDate.add(endDatePicker);
			endDate.setEnabled(false).setOutputMarkupId(true);
			
			add(startHour = new DropDownChoice("anadirHorarioFormStartHour",new Model(),hourList));
			add(endHour = new DropDownChoice("anadirHorarioFormEndHour",new Model(),hourList));
			
			add(descripcion = new TextField("anadirHorarioFormDesc",new Model()));
//			add(shift = new RadioChoice("anadirHorarioFormShift", new Model(), turnos));
			add(submit = new AjaxFallbackButton("anadirHorarioFormSubmit",this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					target.addComponent(feedback);
					String result = validateForm();
					if(result != null) {
						log.debug("Ocurrieron errores durante la validacion: "+result);
						error(result);
					} else {
						// Armo el objeto a enviar al DAO
						Horario prof = new Horario();
//						prof.setNombre(name.getValue());
//						prof.setTelefono(telephone.getValue());
//						prof.setCoordinacion(Utils.getLastPart(
//							coordinaciones.get(Integer.parseInt(coordinacion.getValue())),
//							Constants.TOKEN_SEPARATOR));
//						prof.setTurno(turnos.get(Integer.parseInt(shift.getValue())));
//						prof.setPeriodo(periodos.get(Integer.parseInt(termino.getValue())));
//						
//						// Procesar los datos hacia el DAO
//						ProfesorDAO contenidoDAO;
//						contenidoDAO = (ProfesorDAO) ObjectFactory.getInstancia().getObjeto("profesoresDAO");
						try {
//							contenidoDAO.insertProfesor(prof);
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
			add(feedback = (FeedbackPanel) new FeedbackPanel("anadirHorarioFormError").setOutputMarkupId(true));
		}
		
		private String validateForm() {
			log.debug("startHour.getDefaultModelObjectAsString "+startHour.getDefaultModelObjectAsString());
			log.debug("startHour.getModelValue "+startHour.getModelValue());
			log.debug("startHour.getModelObject "+startHour.getModelObject());
			
			// Ningun campo debe ser vacio
			if(coordinacion.getValue().equals("-1") || profesor.getValue().equals("-1")
				|| materia.getValue().equals("-1") || laboratorio.getValue().equals("-1")
				|| tipoReservacion.getValue().equals("-1")
				|| startHour.getValue().equals("-1") || endHour.getValue().equals("-1")
				|| tipoReservacion.getValue().equals("-1"))
				return Constants.MENSAJE_LLENAR_CAMPOS;
			
			// Si el tipo de reservacion es individual, entonces la fecha final es la misma que la inicial
			if(tipoReservacion.getValue().equals("1"))
				endDate.setDefaultModelObject(startDate.getDefaultModelObject());
			
			// Las fechas deben ser validas
			Date start = Utils.validateDate(startDate.getValue());
			Date end = Utils.validateDate(endDate.getValue());
			if(start == null || end == null)
				return Constants.MENSAJE_FECHA_FORMATO_INVALIDO;
			
			// La hora final no debe ser menor a la hora inicial
			if(Integer.parseInt(endHour.getValue()) <= Integer.parseInt(startHour.getValue()))
				return Constants.MENSAJE_HORA_INVALIDA;
			
//			String[] eHour = hourList.get(Integer.parseInt(endHour.getValue())).split(":");
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(end);
//			endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(eHour[0]));
//			endCal.set(Calendar.MINUTE, Integer.parseInt(eHour[1]));
			endCal.set(Calendar.HOUR_OF_DAY, 0);
			endCal.set(Calendar.MINUTE, 0);
			endCal.set(Calendar.SECOND, 0);
			endCal.set(Calendar.MILLISECOND, 0);
			
//			String[] sHour = hourList.get(Integer.parseInt(startHour.getValue())).split(":");
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(start);
//			startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sHour[0]));
//			startCal.set(Calendar.MINUTE, Integer.parseInt(sHour[1]));
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 0);
			startCal.set(Calendar.MILLISECOND, 0);
			
			if(endCal.compareTo(startCal) < 0)
				return Constants.MENSAJE_FECHA_INVALIDA;
			
			// Hacer otras validaciones pertinentes

			return null;
		}

		public TextField getStartDate() {
			return startDate;
		}

		public void setStartDate(TextField startDate) {
			this.startDate = startDate;
		}

		public DropDownChoice getStartHour() {
			return startHour;
		}

		public void setStartHour(DropDownChoice startHour) {
			this.startHour = startHour;
		}

		public List<String> getHourList() {
			return hourList;
		}

		public void setHourList(List<String> hourList) {
			this.hourList = hourList;
		}
	}
}

