package FileSync;

import com.google.api.services.drive.Drive;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client {
    private static final String DEFAULT_CLIENT_ID = "defaultClient";
    private static final String DEFAULT_DIRECTORY_PATH = "./src/main/java/FileSync";
    private static final String DRIVE_FOLDER_NAME = "FileSync_Backup";

    public void sync(String clientId, String directoryPath) throws IOException {
        try {
            Drive driveService = DriveServiceHelper.getDriveService();
            BackupManager backupManager = new BackupManager(driveService);

            String folderId = backupManager.findOrCreateFolder(DRIVE_FOLDER_NAME);

            Set<FileInfo> localFiles = collectFiles(directoryPath);
            backupManager.uploadFiles(folderId, localFiles);

            // Calculate total data size
            long totalDataSize = localFiles.stream().mapToLong(FileInfo::getLastModified).sum();

            // Log backup to Google Calendar
            CalendarHelper.logBackupEvent(localFiles.size(), totalDataSize);

            System.out.println("Backup completed successfully for client: " + clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<FileInfo> collectFiles(String directoryPath) {
        Set<FileInfo> fileSet = new HashSet<>();
        java.io.File directory = new java.io.File(directoryPath);
        for (java.io.File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                fileSet.add(new FileInfo(file.getAbsolutePath(), file.lastModified()));
            }
        }
        return fileSet;
    }

    public static void main(String[] args) {
        String clientId = args.length > 0 ? args[0] : DEFAULT_CLIENT_ID;
        String directoryPath = args.length > 1 ? args[1] : DEFAULT_DIRECTORY_PATH;

        Client client = new Client();
        try {
            client.sync(clientId, directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
