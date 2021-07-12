package com.habr;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

public class JavaClient {

    public static void main(String[] args) throws TTransportException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
        TProtocol protocol = new TBinaryProtocol(transport);

    }

}
