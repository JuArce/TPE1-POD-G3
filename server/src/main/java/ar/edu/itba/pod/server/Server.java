package ar.edu.itba.pod.server;

import ar.edu.itba.pod.server.services.AdminServiceImpl;
import ar.edu.itba.pod.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        logger.info("tpe1-g3-parent Server Starting ...");
        var service = new AdminServiceImpl();
        var remote = UnicastRemoteObject.exportObject(service,0);

        final Registry registry = LocateRegistry.getRegistry();
        registry.rebind("AdminService", remote); // bind, rebind, unbind
    }
}
