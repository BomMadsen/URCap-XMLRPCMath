package com.ur.urcap.sample.XMLRPCmath.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ur.urcap.api.contribution.DaemonService;
import com.ur.urcap.api.contribution.InstallationNodeService;

/**
 * Hello world activator for the OSGi bundle URCAPS contribution
 *
 */
public class Activator implements BundleActivator {
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		//System.out.println("Activator says Hello World!");
		
		XMLRPCmathDaemonService xmlrpcmathDaemonService = new XMLRPCmathDaemonService();
		XMLRPCmathInstallationNodeService xmlrpcmathInstallationNodeService = new XMLRPCmathInstallationNodeService(xmlrpcmathDaemonService);
		
		bundleContext.registerService(InstallationNodeService.class, xmlrpcmathInstallationNodeService, null);
		bundleContext.registerService(DaemonService.class, xmlrpcmathDaemonService, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		//System.out.println("Activator says Goodbye World!");
	}
}

