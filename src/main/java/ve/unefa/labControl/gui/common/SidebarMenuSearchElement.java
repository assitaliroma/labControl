package ve.unefa.labControl.gui.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public abstract class SidebarMenuSearchElement extends Panel {

	private SearchForm form;
	private Class<? extends Panel> moduleClazz;

	public SidebarMenuSearchElement(String id, String title, Class<? extends Panel> module) {
		super(id);

		this.moduleClazz = module;
		
		add(new Label("searchTitle", title));
		
		form = new SearchForm("searchForm");
		add(form);
	}
	
	public Panel getModule() {
		return ((PageSkeleton)getPage()).getModuleFactory().getPanelFor(moduleClazz);
	}

	public void onBeforeRender() {
		SearchForm form = new SearchForm("searchForm");
		this.form.replaceWith(form);
		this.form = form;
		super.onBeforeRender();
	}

	protected abstract void onFormSubmit(AjaxRequestTarget target, SearchForm form);

	public class SearchForm extends Form {

		private TextField textField;
		private AjaxFallbackButton submit;

		public TextField getTextField() {
			return textField;
		}

		public void setTextField(TextField textField) {
			this.textField = textField;
		}
		
		public String getSearchString() {
			return textField.getValue();
		}

		public SearchForm(String id) {
			super(id);
			
			textField = new TextField("searchString",new Model());
			add(textField.setRequired(true));
			
			submit = new AjaxFallbackButton("searchSubmit", this) {
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					onFormSubmit(target,(SearchForm) form);
					PageSkeleton.getPageSkeleton(target)
						.addOrReplace(getModule());
					target.addComponent(getModule());
				}
			};
			add(submit);
		}
	}
}

