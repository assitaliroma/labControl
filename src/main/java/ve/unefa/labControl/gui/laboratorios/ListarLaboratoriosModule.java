package ve.unefa.labControl.gui.laboratorios;

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

import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.RadioPanel;
import ve.unefa.labControl.gui.coordinaciones.ListarCoordinacionesModule;
import ve.unefa.labControl.persistence.provider.GeneralDataProvider;
import ve.unefa.labControl.persistence.provider.ModuleDataProvider;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class ListarLaboratoriosModule extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(ListarLaboratoriosModule.class);
	private List<IColumn> columnList;
	private ModuleDataProvider dataProvider;
	private ListarLaboratoriosForm form;
	private int registerByPage=10;
	private  AjaxFallbackDefaultDataTable table=null;
	
	public ListarLaboratoriosModule() {
		super();
		
		add(form = new ListarLaboratoriosForm("laboratoriosListForm"));
	}
	
	public ModuleDataProvider getDataProvider() {
		return dataProvider;
	}
	
	private class ListarLaboratoriosForm extends Form {
		private RadioPanel[] radioList;
		private int radioIdx;
		private AjaxFallbackButton submit;
		public ListarLaboratoriosForm(String id) {
			super(id);
			radioList = new RadioPanel[registerByPage];
			radioIdx = 0;
			
			dataProvider=new ModuleDataProvider(4);
			dataProvider.loadLaboratoriosData();
			columnList = new ArrayList<IColumn>();
			columnList.add(new AbstractColumn(new Model("")) {
	        	public void populateItem(Item cellItem, String componentId,
	                    IModel model)
	            {
	        		RadioPanel r;
	        		Laboratorio lab=(Laboratorio)model.getObject();
	                cellItem.add(r = new RadioPanel(componentId, model,lab.getId()));
	                form.setRadioAt(r);
	            }
	        });
	        columnList.add(new PropertyColumn(new Model("ID"), "id"));
	        columnList.add(new PropertyColumn(new Model("Nombre"), "nombre"));
	        columnList.add(new PropertyColumn(new Model("Capacidad"), "capacidad"));
	        
	        table = new AjaxFallbackDefaultDataTable("LaboratoriosTable",columnList,dataProvider, registerByPage) {
	        	public void onBeforeRender() {
		    		radioIdx = 0;
		    		
		    		super.onBeforeRender();
		    	}
		    };
		    add(table);
			
			add(submit = new AjaxFallbackButton("laboratoriosListFormSubmit", this) {
			
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form form) {
					for(int i=0; i<radioIdx ;i++) {
						if(radioList[i].isChecked()){
							try {
								
								GeneralDataProvider.deleteLaboratorio(radioList[i].getRadioId());
								radioIdx=0;
								dataProvider.loadLaboratoriosData();
								target.addComponent(table);
								log.info("Se elimino el Laboratorio con el ID: "+radioList[i].getRadioId());
							} catch (Exception e) {
								log.debug("Error eliminando el Laboratorio con el ID: "+radioList[i].getRadioId(),e);
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

