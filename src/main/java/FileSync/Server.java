package FileSync;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Server {
    private static final int PORT = 12345;
    private final Map<String, Set<FileInfo>> clientFileRecords = new HashMap<>();

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started and waiting for connections...");

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String clientId = in.readLine();
                String clientFilesJson = in.readLine();

                Set<FileInfo> clientFiles = new Gson().fromJson(clientFilesJson, new TypeToken<Set<FileInfo>>() {}.getType());

                Set<FileInfo> serverFiles = clientFileRecords.getOrDefault(clientId, new HashSet<>());
                Set<FileInfo> newFiles = new HashSet<>(clientFiles);
                newFiles.removeAll(serverFiles);

                clientFileRecords.put(clientId, clientFiles);

                String newFilesJson = new Gson().toJson(newFiles);
                out.println(newFilesJson);
                System.out.println("Processed client: " + clientId);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
