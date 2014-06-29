package ve.unefa.labControl.gui.common;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author desarrollador
 */

			
@SuppressWarnings("serial")
public class RadioPanel extends Panel {
    /**
     * @param id component id
     * @param model model for contact
     */
	private CheckBox radio;
	private String radioId;
	private Long radioIdLong;
	
	private RadioPanel(String id, final IModel model) {
		super(id, model);
		radio = new CheckBox("radio",new Model());
		add(radio);
    }
	
    public RadioPanel(String id, final IModel model,String idObject) {
		this(id, model);
		radioId=idObject;
    }
    
    public RadioPanel(String id, final IModel model,Long idObject) {
    	this(id, model);
        radioIdLong=idObject;
    }
    
    public boolean isChecked() {
    	return Boolean.parseBoolean(radio.getValue());
    }

	public String getRadioId() {
		if(radioId != null)
			return radioId;
		else
			return radioIdLong.toString();
	}

    
}