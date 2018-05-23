package workshop;


import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import javafx.scene.control.Label;
import jv.number.PuDouble;
import jv.object.PsConfig;
import jv.object.PsDialog;
import jv.object.PsUpdateIf;
import jvx.project.PjWorkshop_IP;

public class Task1_IP extends PjWorkshop_IP implements ActionListener {

	protected Button genus_button;
	protected Button volume_button;
	protected Button component_button;


	protected Label genus_label;
	protected Label volume_label;
	protected Label component_label;
	//protected PuDouble m_xOff;
	
	Task1 m_ws;
	
	public  Task1_IP() {
	super();
		if(getClass() == Task1_IP.class){
			init();
			}
	}
		
	
	
	public void init() {
		super.init();
		setTitle("Task1");
	}
	
	/*public String getNotice() {
		return "This text should explain what the workshop is about and how to use it.";
	}
	*/
	
	public void setParent(PsUpdateIf parent) {
		super.setParent(parent);
		m_ws = (Task1)parent;
	
		addSubTitle("Task1: Mesh and Surface Analysis");
		
		genus_button = new Button("Calculate Genus");
		genus_button.addActionListener(this);
		volume_button = new Button("Calculate Volumn");
		volume_button.addActionListener(this);
		component_button = new Button("Calculate Connected Components");
		component_button.addActionListener(this);
		
		genus_label = new Label();
		volume_label = new Label();
		component_label = new Label();
		
		Panel panel1 = new Panel(new GridLayout(3,2));
		panel1.add(genus_button);
		panel1.add(genus_label);
		panel1.add(volume_button);
		panel1.add(volume_label);
		panel1.add(component_button);
		panel1.add(component_label);
		add(panel1);
		
	/*	
		Panel panel1 = new Panel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(m_bMakeRandomElementColors);
		panel1.add(m_bMakeRandomVertexColors);
		add(panel1);
		
		m_xOff = new PuDouble("X Offset");
		m_xOff.setDefBounds(-10,10,0.1,1);
		m_xOff.addUpdateListener(this);
		m_xOff.init();
		add(m_xOff.getInfoPanel());
		*/
		validate();
	}
	
	/*
	public boolean update(Object event) {
		if (event == m_xOff) {
			m_ws.setXOff(m_xOff.getValue());
			m_ws.m_geom.update(m_ws.m_geom);
			return true;
		} else
			return super.update(event);
	}*/
	
	/**
	 * Handle action events fired by buttons etc.
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == genus_button) {
			genus_label.setText("...");
			genus_label.setText(m_ws.cal_genus()+"");
			m_ws.m_geom.update(m_ws.m_geom);
			return;
		}
		else if (source == volume_button) {
			volume_label.setText("...");
			volume_label.setText(m_ws.cal_volume()+"");
			m_ws.m_geom.update(m_ws.m_geom);
			return;
		}
		else if (source == component_button) {
			component_label.setText("...");
			component_label.setText(m_ws.cal_components()+"");
			m_ws.m_geom.update(m_ws.m_geom);
			return;
		}
	}
	/**
	 * Get information which bottom buttons a dialog should create
	 * when showing this info panel.
	 */
	protected int getDialogButtons()		{
		return PsDialog.BUTTON_OK;
	}
}
