package com.jbksoft.protobufferrpc;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.jbksoft.protobufferrpc.protobufferrpcData.GreetingProtos;
import com.jbksoft.protobufferrpc.protobufferrpcData.MyGreetingService.Greeting;

/**
 * Created by pooja on 10/8/15.
 */
public class MyGreetingServiceImpl extends Greeting {

    @Override
    public void sayHello(RpcController controller, GreetingProtos.HelloRequest request, RpcCallback<GreetingProtos.HelloReply> done) {
        GreetingProtos.HelloReply.Builder build= GreetingProtos.HelloReply.newBuilder();
        if(request.getName().equalsIgnoreCase("namenode")){
            build.setMessage("This is message for namenode only--->");

        }else{
            build.setMessage("Please see person sending message!!!!!!!!!!!!!!");
        }
        done.run(build.build());
    }
}
