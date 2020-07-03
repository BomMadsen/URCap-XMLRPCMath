package com.ur.urcap.sample.XMLRPCmath.impl;

import com.ur.urcap.api.contribution.DaemonContribution;
import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.ui.annotation.Input;
import com.ur.urcap.api.ui.annotation.Label;
import com.ur.urcap.api.ui.component.InputCheckBox;
import com.ur.urcap.api.ui.component.InputEvent;
import com.ur.urcap.api.ui.component.LabelComponent;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class XMLRPCmathInstallationNodeContribution implements InstallationNodeContribution {
	
	private final XMLRPCmathDaemonService xmlrpcmathDaemonService;
	private DataModel model;
	private Timer uiTimer;
	
	private static final String cbDAEMON = "checkboxDaemon";
	
	public XMLRPCmathInstallationNodeContribution(XMLRPCmathDaemonService xmlrpcmathDaemonService, DataModel model) {
		this.xmlrpcmathDaemonService = xmlrpcmathDaemonService;
		this.model = model;
	}
	
	// Label
	@Label(id = "lblDaemonStatus")
	private LabelComponent daemonStatusLabel;
	
	
	// Checkbox controls
	private void setCB(boolean cb){
		model.set(cbDAEMON, cb);
	}
	
	private boolean getCB(){
		return model.get(cbDAEMON, false); 
	}
	
	@Input(id="cbDaemon")
	private InputCheckBox checkboxDaemon;
	
	@Input(id="cbDaemon")
	public void onCBToggle(InputEvent event){
		if(event.getEventType() == InputEvent.EventType.ON_CHANGE){
			setCB(checkboxDaemon.isSelected());
			if(checkboxDaemon.isSelected()){
				xmlrpcmathDaemonService.getDaemonContribution().start();;
			}
			else{
				xmlrpcmathDaemonService.getDaemonContribution().stop();
			}
		}
	}
	
	private void updateUI() {
		String text = "";
		DaemonContribution.State state = xmlrpcmathDaemonService.getDaemonContribution().getState();
		switch (state){
		case RUNNING:
			text = "Matt Damon runs";
			break;
		case STOPPED:
			text = "Matt Damon stopped";
			break;
		case ERROR:
			text = "Matt Damon failed";
		}
		daemonStatusLabel.setText(text);
	}
	
	@Override
	public void openView(){
		checkboxDaemon.setSelected(getCB());
		if(getCB() && (DaemonContribution.State.STOPPED == xmlrpcmathDaemonService.getDaemonContribution().getState())){
			xmlrpcmathDaemonService.getDaemonContribution().start();
		}
		else if (getCB() == false){
			xmlrpcmathDaemonService.getDaemonContribution().stop();
		}
		
		//UI updates from non-GUI threads must use EventQueue.invokeLater (or SwingUtilities.invokeLater)
		uiTimer = new Timer(true);
		uiTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						updateUI();
					}
				});
			}
		}, 0, 1000);
	}
	
	@Override
	public void closeView(){
		uiTimer.cancel();;
	}
	
	public boolean isDefined(){
		return true;
	}
	
	@Override
	public void generateScript(ScriptWriter writer){
		if(getCB()){
			writer.appendLine("# Connect to XMLRPC Math server");
			writer.appendLine("math = rpc_factory(\"xmlrpc\",\"http://127.0.0.1:33000\")");
		}
		
	}
}