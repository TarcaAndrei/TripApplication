package org.example.Utils;

import org.example.ITripServices;
import org.example.TripClientRPCWorker;
import org.example.protobuffprotocol.TripClientRPCWorkerProto;

import java.net.Socket;

public class RPCConcurrentServerProto extends AbstractConcurrentServer {

    private ITripServices tripServices;
    public RPCConcurrentServerProto(int port, ITripServices tripServices) {
        super(port);
        this.tripServices = tripServices;
        System.out.println("RPCConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        TripClientRPCWorkerProto workerProto = new TripClientRPCWorkerProto(tripServices, client);
//        TripClientRPCWorker worker = new TripClientRPCWorker(tripServices, client);
        Thread tw = new Thread(workerProto);
        return tw;
    }
}
