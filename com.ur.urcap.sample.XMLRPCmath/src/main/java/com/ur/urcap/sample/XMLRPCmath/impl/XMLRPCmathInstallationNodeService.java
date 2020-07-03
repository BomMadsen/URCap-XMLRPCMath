package com.ur.urcap.sample.XMLRPCmath.impl;

import java.io.InputStream;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.InstallationNodeService;
import com.ur.urcap.api.domain.URCapAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class XMLRPCmathInstallationNodeService implements InstallationNodeService {
	
	private XMLRPCmathDaemonService xmlrpcmathDaemonService;
	
	public XMLRPCmathInstallationNodeService(XMLRPCmathDaemonService xmlrpcmathDaemonService){
		this.xmlrpcmathDaemonService = xmlrpcmathDaemonService;
	}
	
	@Override
	public InstallationNodeContribution createInstallationNode(URCapAPI api, DataModel model) {
		return new XMLRPCmathInstallationNodeContribution(xmlrpcmathDaemonService, model);
	}
	
	@Override
	public String getTitle(){
		return "XMLRPC Math";
	}
	
	@Override
	public InputStream getHTML(){
		InputStream is = this.getClass().getResourceAsStream("/com/ur/urcap/sample/XMLRPCmath/impl/installation.html");
		return is;
	}
}