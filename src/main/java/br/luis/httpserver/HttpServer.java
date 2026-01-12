package br.luis.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.luis.httpserver.config.Configuration;
import br.luis.httpserver.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Starting Server...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("The Port is: " + configuration.getPort());
        System.out.println("The webRoot is: " + configuration.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(configuration.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java Server</title></head><body><h1>This page is a test</h1></body></html>";

            final String CRLF = "\r\n";

            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-length: " + html.getBytes().length + CRLF +
                    CRLF + html + CRLF + CRLF;

            outputStream.write(response.getBytes());

            serverSocket.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
