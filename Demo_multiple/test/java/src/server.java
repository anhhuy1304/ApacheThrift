
package test;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class server {
  public static MultiplicationHandler handler;
  public static MultiplicationService.Processor processor;

  public static void main(String[] args) {

    handler = new MultiplicationHandler();
    processor = new MultiplicationService(handler);

    Runnable multi = new Runnable() {

      @Override
      public void run() {
        multi(processor);
      }
    };
    new Thread(multi).start();
  }

  public static void multi(MultiplicationService.Processor processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}