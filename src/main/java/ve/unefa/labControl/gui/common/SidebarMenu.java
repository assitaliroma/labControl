package ve.unefa.labControl.gui.common;

import java.util.HashMap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ve.unefa.labControl.gui.coordinaciones.AnadirCoordinacionModule;
import ve.unefa.labControl.gui.coordinaciones.ListarCoordinacionesModule;
import ve.unefa.labControl.gui.horarios.AnadirHorarioModule;
import ve.unefa.labControl.gui.horarios.ListarHorariosModule;
import ve.unefa.labControl.gui.laboratorios.AnadirLaboratorioModule;
import ve.unefa.labControl.gui.laboratorios.ListarLaboratoriosModule;
import ve.unefa.labControl.gui.materias.AnadirMateriaModule;
import ve.unefa.labControl.gui.materias.ListarMateriasModule;
import ve.unefa.labControl.gui.profesores.AnadirProfesorModule;
import ve.unefa.labControl.gui.profesores.ListarProfesoresModule;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class SidebarMenu extends Panel {
	private static final String SIDEBAR_ELEMENT_CONTENT = "sidebarElementContent";
	/**
	 * Los keys de este HashMap deben coincidir con los Captions de las opciones
	 * del menu principal (MainMenuLink) 
	 */
	private HashMap<String,Panel[]> sidebarOptions;
	private RepeatingView repeater;

	/**
	 * Este constructor añade automaticamente el panel a la pagina
	 * @param id
	 * @param defaultMenu
	 * @param page
	 */
	public SidebarMenu(String id, String defaultMenu, PageSkeleton page) {
		super(id);
		page.add(this);
		
		fillSidebarOptions();
		
		repeater = new RepeatingView("sidebarElement");
		add(repeater);
		
		changeMenuTo(defaultMenu);
		setOutputMarkupId(true);
	}

	private void fillSidebarOptions() {
		if(sidebarOptions == null)
			sidebarOptions = new HashMap<String,Panel[]>();
		
		/* OPCIONES DE Coordinaciones */
		sidebarOptions.put(Constants.COORDINACIONES, new Panel[] {
			new SidebarMenuSearchElement(SIDEBAR_ELEMENT_CONTENT,"Buscar Coordinación",ListarCoordinacionesModule.class) {
				@Override
				protected void onFormSubmit(AjaxRequestTarget target, SearchForm form) {
					((ListarCoordinacionesModule)getModule()).getDataProvider().loadCoordinacionesData(form.getSearchString());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Añadir Coordinación",AnadirCoordinacionModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Anadir Coordinacion! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Listar Coordinaciones",ListarCoordinacionesModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
					((ListarCoordinacionesModule)getModule()).getDataProvider().loadCoordinacionesData();
				}
			}
		});
		
		/* OPCIONES DE Profesores */
		sidebarOptions.put(Constants.PROFESORES, new Panel[] {
			new SidebarMenuSearchElement(SIDEBAR_ELEMENT_CONTENT,"Buscar Profesor",ListarProfesoresModule.class) {
				@Override
				protected void onFormSubmit(AjaxRequestTarget target, SearchForm form) {
//					System.out.println("Usuario busco: "+form.getTextField().getValue());
					((ListarProfesoresModule)getModule()).getDataProvider().loadProfesoresData(form.getSearchString());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Añadir Profesor",AnadirProfesorModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 1! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Listar Profesores",ListarProfesoresModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 2! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
					((ListarProfesoresModule)getModule()).getDataProvider().loadProfesoresData();
				}
			}
		});
		
		/* OPCIONES DE Materias */
		sidebarOptions.put(Constants.MATERIAS, new Panel[] {
			new SidebarMenuSearchElement(SIDEBAR_ELEMENT_CONTENT,"Buscar Materia",ListarMateriasModule.class) {
				@Override
				protected void onFormSubmit(AjaxRequestTarget target, SearchForm form) {
//					System.out.println("Usuario busco: "+form.getTextField().getValue());
					((ListarMateriasModule)getModule()).getDataProvider().loadMateriasData(form.getSearchString());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Añadir Materia",AnadirMateriaModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 1! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Listar Materias",ListarMateriasModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 2! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
					((ListarMateriasModule)getModule()).getDataProvider().loadMateriasData();
				}
			}
		});
		
		/* OPCIONES DE Laboratorios */
		sidebarOptions.put(Constants.LABORATORIOS, new Panel[] {
			new SidebarMenuSearchElement(SIDEBAR_ELEMENT_CONTENT,"Buscar Laboratorio",ListarLaboratoriosModule.class) {
				@Override
				protected void onFormSubmit(AjaxRequestTarget target, SearchForm form) {
//					System.out.println("Usuario busco: "+form.getTextField().getValue());
					((ListarLaboratoriosModule)getModule()).getDataProvider().loadLaboratoriosData(form.getSearchString());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Añadir Laboratorio",AnadirLaboratorioModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 1! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Listar Laboratorios",ListarLaboratoriosModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 2! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
					((ListarLaboratoriosModule)getModule()).getDataProvider().loadLaboratoriosData();
				}
			}
		});
		
		/* OPCIONES DE Horarios */
		sidebarOptions.put(Constants.HORARIOS, new Panel[] {
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Añadir Horario",AnadirHorarioModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//						System.out.println("Clikee Opcion 3! y en el menu principal esta marcado: "+
//							PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
			new SidebarMenuLinkElement(SIDEBAR_ELEMENT_CONTENT,"Listar Horarios",ListarHorariosModule.class) {
				@Override
				protected void onElementClick(AjaxRequestTarget target) {
//					System.out.println("Clikee Opcion 3! y en el menu principal esta marcado: "+
//						PageSkeleton.getPageSkeleton(target).getActualMenuOption().getCaption());
				}
			},
//			new SidebarMenuSearchElement(SIDEBAR_ELEMENT_CONTENT,"Listar Horarios",new ListarHorariosModule.class) {
//				@Override
//				protected void onFormSubmit(AjaxRequestTarget target, SearchForm form) {
//					System.out.println("Usuario busco: "+form.getTextField().getValue());
//				}
//			}
		});
	}

	public void changeMenuTo(String caption) {
		if(repeater.hasBeenRendered())
			repeater.removeAll();
		
		// Buscar el set de opciones para la opcion del menu principal
		Panel[] actualOptions = sidebarOptions.get(caption);
		
		for(int i=0;i<actualOptions.length;i++) {
			WebMarkupContainer element = new WebMarkupContainer(repeater.newChildId());
			repeater.add(element);
			
			element.add(actualOptions[i]);
		}
	}

}

