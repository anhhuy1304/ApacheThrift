
package test;

import java.net.Socket;
import com.sun.java_cup.internal.runtime.Scanner;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class client {
    public static void main(String[] args) {
        TTransport transport;
        transport = new TSocket("localhost", 7000);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        MultiplicationService.client client = new MultiplicationService.client(protocol);
        perform(client);
        transport.close();
    }

    private static void perform(MultiplicationService.Client client) throws TException {

        int a, b;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        int product = client.multiply(a, b);
        System.out.println(a + "*" + b + "=" + product);
    }
}