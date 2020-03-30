package com.leesin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * @description:
 * @author: Leesin.Dong
 * @date: Created in 2020/3/30 10:28
 * @version: ${VERSION}
 * @modified By:
 */
public class RpcNetTransport implements Serializable {

    String host;
    String port;

    public RpcNetTransport(String host,String port) {

    }

    public Object  send(RpcRequest rpcRequest) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(rpcRequest);
        objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object o = objectInputStream.readObject();
        if (objectInputStream != null) {
            objectInputStream.close();
        }
        if (objectOutputStream != null) {
            objectOutputStream.close();
        }
        return o;
    }
}
