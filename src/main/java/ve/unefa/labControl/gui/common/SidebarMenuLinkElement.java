package ve.unefa.labControl.gui.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public abstract class SidebarMenuLinkElement extends Panel {

	private AjaxFallbackLink elementLink;
	private Class<? extends Panel> moduleClazz;

	public SidebarMenuLinkElement(String id, String text, Class<? extends Panel> module) {
		super(id);
		
		this.moduleClazz = module;
		elementLink = new AjaxFallbackLink("sidebarLink") {
			
			public void onClick(AjaxRequestTarget arg0) {
				// NO MODIFICAR ESTA FUNCION!!!
				// Para mantener esta clase lo mas generica posible, se colocó
				// un método abstracto donde se implementa la acción de este link
				if(arg0 != null) {
					onElementClick(arg0);
					PageSkeleton.getPageSkeleton(arg0)
						.addOrReplace(getModule());
					arg0.addComponent(getModule());
				}
			}
		};
		elementLink.add(new Label("sidebarLinkCaption",text).setRenderBodyOnly(true));
		add(elementLink);
		
		setRenderBodyOnly(true);
	}
	
	public Panel getModule() {
		return ((PageSkeleton)getPage()).getModuleFactory().getPanelFor(moduleClazz);
	}

	/**
	 * Este metodo se deja por preferencia para el caso en que se necesite agregar alguna
	 * funcionalidad particular a las opciones del menu lateral
	 * @param target Objeto Ajax de salida
	 */
	protected abstract void onElementClick(AjaxRequestTarget target);

}

