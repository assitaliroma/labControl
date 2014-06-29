package ve.unefa.labControl.gui.horarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ve.unefa.labControl.domain.Horario;
import ve.unefa.labControl.domain.Laboratorio;
import ve.unefa.labControl.gui.common.AjaxSupportedPanel;
import ve.unefa.labControl.gui.common.Constants;
import ve.unefa.labControl.persistence.provider.ModuleDataProvider;

/**
 * @author biomorgoth
 */
@SuppressWarnings("serial")
public class HorarioTable extends AjaxSupportedPanel {
	private static Logger log = 
		org.apache.log4j.Logger.getLogger(HorarioTable.class);
	private List<IColumn> columnList;
	private ModuleDataProvider dataProvider;
	private Label weekLabel;
	private Label labLabel;
	private int registerByPage=10;
	
	public HorarioTable(String id) {
		super(id);
		
		dataProvider=new ModuleDataProvider(5);
		columnList = new ArrayList<IColumn>();
		columnList.add(new HorarioColumn(new Model(Constants.HORARIO_HORARIO),0));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_LUNES),1));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_MARTES),2));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_MIERCOLES),3));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_JUEVES),4));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_VIERNES),5));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_SABADO),6));
        columnList.add(new HorarioColumn(new Model(Constants.HORARIO_DOMINGO),7));
        
        weekLabel = new Label("horarioTableWeek");
        labLabel = new Label("horarioTableLab");
        add(weekLabel.setRenderBodyOnly(true));
        add(labLabel.setRenderBodyOnly(true));
        
	    AjaxFallbackDefaultDataTable table = new AjaxFallbackDefaultDataTable("horarioAjaxTable",columnList,dataProvider,registerByPage);
	    add(table);
	}
	
	public void onDetach() {
		dataProvider.clearHorariosData();
		
		super.onDetach();
	}
	
	public ModuleDataProvider getDataProvider() {
		return dataProvider;
	}

	public void fillTable(Laboratorio laboratorio, String date) {
		getDataProvider().loadHorariosData();
		labLabel.setDefaultModel(new Model(laboratorio.getNombre()));
		weekLabel.setDefaultModel(new Model(date));
	}

	private class HorarioColumn extends AbstractColumn {
		private int col;

		public HorarioColumn(IModel displayModel, int colNumber) {
			super(displayModel);
			col = colNumber;
		}

		@SuppressWarnings("unchecked")
		public void populateItem(Item cellItem, String componentId, IModel model) {
			List<Object> list = (List<Object>) model.getObject();
			Object obj = list.get(col);
			if(col == 0)
				cellItem.add(new Label(componentId, (String)obj));
			else if(obj instanceof Horario)
				cellItem.add(new HorarioCell(componentId, (Horario)obj));
			else
				cellItem.add(new HorarioCell(componentId, (Date)obj));
		}
		
	}

}
