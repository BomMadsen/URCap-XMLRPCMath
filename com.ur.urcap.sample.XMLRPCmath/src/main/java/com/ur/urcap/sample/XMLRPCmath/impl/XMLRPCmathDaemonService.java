package com.ur.urcap.sample.XMLRPCmath.impl;

import com.ur.urcap.api.contribution.DaemonContribution;
import com.ur.urcap.api.contribution.DaemonService;

import java.net.MalformedURLException;
import java.net.URL;

public class XMLRPCmathDaemonService implements DaemonService {
	private DaemonContribution daemonContribution;
	
	public XMLRPCmathDaemonService(){
		;
	}
	
	@Override
	public void init(DaemonContribution daemonContribution){
		this.daemonContribution = daemonContribution;
		try{
			daemonContribution.installResource(new URL("file:com/ur/urcap/sample/XMLRPCmath/impl/daemon/"));
		} catch (MalformedURLException e) {}
	}
	
	@Override
	public URL getExecutable(){
		try{
			return new URL("file:com/ur/urcap/sample/XMLRPCmath/impl/daemon/mathserver.py");
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	public DaemonContribution getDaemonContribution(){
		return daemonContribution;
	}
}