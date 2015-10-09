package com.jbksoft.protobufferrpc;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import com.googlecode.protobuf.socketrpc.RpcChannels;
import com.googlecode.protobuf.socketrpc.RpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;
import com.googlecode.protobuf.socketrpc.SocketRpcController;
import com.jbksoft.protobufferrpc.protobufferrpcData.GreetingProtos;
import com.jbksoft.protobufferrpc.protobufferrpcData.MyGreetingService;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pooja on 10/8/15.
 */
public class ProtoClient {
    public static void main(String[] args)
    {
        // Create a thread pool
        ExecutorService threadPool = Executors.newFixedThreadPool(1);


// Create channel
        String host =args[0];                 //IP Address of machine "172.17.42.1";
        int port = 4446;
        RpcConnectionFactory connectionFactory = SocketRpcConnectionFactories.createRpcConnectionFactory(host, port);
        RpcChannel channel = RpcChannels.newRpcChannel(connectionFactory, threadPool);


       // Call service
        MyGreetingService.Greeting.Stub myService = MyGreetingService.Greeting.newStub(channel);
        RpcController controller = new SocketRpcController();

        GreetingProtos.HelloRequest.Builder cr = GreetingProtos.HelloRequest.newBuilder();
        cr.setName("Hello");
        myService.sayHello(controller, cr.build(),
                new RpcCallback<GreetingProtos.HelloReply>()
                {
                    public void run(GreetingProtos.HelloReply myResponse)
                    {
                        System.out.println("Received Response: " + myResponse);
                    }
                });
// Check success
        if (controller.failed())
        {
            System.err.println(String.format("Rpc failed %s ", controller.errorText()));
        }
    }

}
