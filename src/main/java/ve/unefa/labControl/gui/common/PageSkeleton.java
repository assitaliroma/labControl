package ve.unefa.labControl.gui.common;

import org.apache.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ve.unefa.labControl.gui.common.factory.ModuleFactory;
import ve.unefa.labControl.gui.coordinaciones.CoordinacionesModule;
import ve.unefa.labControl.gui.horarios.HorariosModule;
import ve.unefa.labControl.gui.laboratorios.LaboratoriosModule;
import ve.unefa.labControl.gui.materias.MateriasModule;
import ve.unefa.labControl.gui.profesores.ProfesoresModule;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class PageSkeleton extends WebPage {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(PageSkeleton.class);
	private static final String MENU_LINK = "menuLink";
	
	private MainMenuLink[] menuOptions = {
		new MainMenuLink(MENU_LINK,Constants.COORDINACIONES, CoordinacionesModule.class) {
			@Override
			protected void onMenuElementClick(AjaxRequestTarget target) {}
		},
		new MainMenuLink(MENU_LINK,Constants.PROFESORES, ProfesoresModule.class) {
			@Override
			protected void onMenuElementClick(AjaxRequestTarget target) {}
		},
		new MainMenuLink(MENU_LINK,Constants.MATERIAS, MateriasModule.class) {
			@Override
			protected void onMenuElementClick(AjaxRequestTarget target) {}
		},
		new MainMenuLink(MENU_LINK,Constants.LABORATORIOS, LaboratoriosModule.class) {
			@Override
			protected void onMenuElementClick(AjaxRequestTarget target) {}
		},
		new MainMenuLink(MENU_LINK,Constants.HORARIOS, HorariosModule.class) {
			@Override
			protected void onMenuElementClick(AjaxRequestTarget target) {}
		}
	};
	private RepeatingView menuRepeater;
	private MainMenuLink actualMenuOption;
	private SidebarMenu sidebar;
	private ModuleFactory moduleFactory;

	public PageSkeleton(PageParameters params) {
		prepareMainMenu();
		
		sidebar = new SidebarMenu("sidebar",getActualMenuOption().getCaption(),this);
		
		// Inicia la aplicacion con el modulo de la opcion de menu actual
		add(getActualMenuOption().getHomeModule());
	}

	/**
	 * Inicializa el menu principal con las secciones definidas segun menuOptionsLabels
	 */
	private void prepareMainMenu() {
		menuRepeater = new RepeatingView("menuRepeater");
		add(menuRepeater);
		
		for(int i=0;i<menuOptions.length;i++) {
			WebMarkupContainer menuItem = new WebMarkupContainer(menuRepeater.newChildId());
			menuRepeater.add(menuItem);
			
			menuItem.add(menuOptions[i]);
		}
		actualMenuOption = menuOptions[0];
		actualMenuOption.add(new SimpleAttributeModifier("class", "current_page_item"));
	}
	
	/**
	 * Dado un AjaxRequestTarget, esta funcion devuelve el PageSkeleton
	 * asociado a esa peticion web Ajax
	 * @param target La peticion a extraer el PageSkeleton
	 * @return Si la pagina obtenida es instancia de PageSkeleton es retornada,
	 * en caso contrario se retorna null
	 */
	public static PageSkeleton getPageSkeleton(AjaxRequestTarget target) {
		Page p = target.getPage();
		if(p instanceof PageSkeleton)
			return (PageSkeleton) p;
		else
			return null;
	}
	
	public MainMenuLink getActualMenuOption() {
		return actualMenuOption;
	}
	
	public ModuleFactory getModuleFactory() {
		return moduleFactory==null ? moduleFactory = new ModuleFactory() : moduleFactory;
	}

	public abstract class MainMenuLink extends AjaxFallbackLink {
		
		/**
		 * El texto que llevara como titulo el hipervinculo
		 */
		private String caption;
		/**
		 * La clase a instanciar del modulo
		 */
		private Class<? extends Panel> homeModuleClazz;

		/**
		 * Una vez seteados estos parametros, no se pueden cambiar
		 * @param id
		 * @param caption
		 * @param homeModule
		 */
		public MainMenuLink(String id, String caption, Class<? extends Panel> homeModule) {
			super(id);
			this.caption = caption;
			this.homeModuleClazz = homeModule;
			add(new Label("menuLinkCaption",caption).setRenderBodyOnly(true));
		}
		
		public String getCaption() {
			return caption;
		}

		public Panel getHomeModule() {
			return getModuleFactory().getPanelFor(homeModuleClazz);
		}

		@Override
		public void onClick(AjaxRequestTarget target) {
			if(target != null) {
				try {
					// Primero ejecuto la acción por si hay errores en esa seccion
					onMenuElementClick(target);
					// Cambio el modulo tanto en server (page) como en el client (target)
					PageSkeleton.getPageSkeleton(target)
						.addOrReplace(getHomeModule());
					target.addComponent(getHomeModule());
					// Cambio el menu lateral tanto en el server (page) como en el client (target)
					sidebar.changeMenuTo(getCaption());
					target.addComponent(sidebar);
					// Eliminar el estilo de seleccionado a la opcion anterior, se lo coloca al que le hicieron
					// click y finalmente hago la opcion actual este link
					target.addComponent(actualMenuOption.add(new SimpleAttributeModifier("class", "")));
					target.addComponent(this.add(new SimpleAttributeModifier("class", "current_page_item")));
					actualMenuOption = this;
				} catch (Exception e) {
					log.error("Ha ocurrido un error al cargar el modulo",e);
				}
			}
		}
		
		/**
		 * Este metodo se deja por preferencia para el caso en que se necesite agregar alguna
		 * funcionalidad particular a las opciones del menu principal
		 * @param target Objeto Ajax de salida
		 */
		protected abstract void onMenuElementClick(AjaxRequestTarget target);
	}
}

