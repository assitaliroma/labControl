package ve.unefa.labControl.gui.profesores;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ve.unefa.labControl.domain.Profesor;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.RadioPanel;
import ve.unefa.labControl.persistence.provider.GeneralDataProvider;
import ve.unefa.labControl.persistence.provider.ModuleDataProvider;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class ListarProfesoresModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(ListarProfesoresModule.class);
	private List<IColumn> columnList;
	private ModuleDataProvider dataProvider;
	private ListarProfesoresForm form;
	private int registerByPage=10;
	private  AjaxFallbackDefaultDataTable table=null;
	
	public ListarProfesoresModule() {
		super();
		
		add(form = new ListarProfesoresForm("profesoresListForm"));
	}
	
	public ModuleDataProvider getDataProvider() {
		return dataProvider;
	}
	
	private class ListarProfesoresForm extends Form {
		private RadioPanel[] radioList;
		private int radioIdx;
		private AjaxFallbackButton submit;
		public ListarProfesoresForm(String id) {
			super(id);
			radioList = new RadioPanel[registerByPage];
			radioIdx = 0;
			
			dataProvider=new ModuleDataProvider(2);
			dataProvider.loadCoordinacionesData();
			columnList = new ArrayList<IColumn>();
			columnList.add(new AbstractColumn(new Model("")) {
	        	public void populateItem(Item cellItem, String componentId,
	                    IModel model)
	            {
	        		RadioPanel r;
	        		Profesor pro=(Profesor)model.getObject();
	                cellItem.add(r = new RadioPanel(componentId, model,pro.getId()));
	                form.setRadioAt(r);
	            }
	        });
			columnList.add(new PropertyColumn(new Model("ID"), "id"));
	        columnList.add(new PropertyColumn(new Model("Nombre"), "nombre"));
	        columnList.add(new PropertyColumn(new Model("Periodo"), "periodo"));
	        columnList.add(new PropertyColumn(new Model("Telefono"), "telefono"));
	        columnList.add(new PropertyColumn(new Model("Turno"), "turno"));
	        columnList.add(new PropertyColumn(new Model("Coordinacion"), "coordinacion"));
	        
	        table = new AjaxFallbackDefaultDataTable("ProfesoresTable",columnList,dataProvider, registerByPage) {
	        	public void onBeforeRender() {
		    		radioIdx = 0;
		    		
		    		super.onBeforeRender();
		    	}
		    };
		    add(table);
			
			add(submit = new AjaxFallbackButton("profesoresListFormSubmit", this) {
			
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					for(int i=0; i<radioIdx ;i++) {
						if(radioList[i].isChecked()){
							try {
								
								GeneralDataProvider.deleteProfesor(radioList[i].getRadioId());
								radioIdx=0;
								dataProvider.loadProfesoresData();
								target.addComponent(table);
								log.info("Se elimino la Coordinacion con el ID: "+radioList[i].getRadioId());
							} catch (Exception e) {
								log.debug("Error eliminando la Coordinacion con el ID: "+radioList[i].getRadioId(),e);
							}
						}					
					}
				}
			});
		}
		
		public void setRadioAt(RadioPanel radio) {
			radioList[radioIdx++] = radio;
		}
		
	}
}

