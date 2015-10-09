package com.jbksoft.protobufferrpc;


import java.util.concurrent.Executors;
import com.googlecode.protobuf.socketrpc.RpcServer;
import com.googlecode.protobuf.socketrpc.ServerRpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;

/**
 * Created by pooja on 10/8/15.
 */
public class ServerCode  {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ServerRpcConnectionFactory rpcConnectionFactory = SocketRpcConnectionFactories.createServerRpcConnectionFactory(4446);
        RpcServer server = new RpcServer(rpcConnectionFactory, Executors.newFixedThreadPool(5), true);
        server.registerService(new MyGreetingServiceImpl());
        server.run();
    }

}
