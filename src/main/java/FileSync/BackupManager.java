package FileSync;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class BackupManager {
    private final Drive driveService;

    public BackupManager(Drive driveService) {
        this.driveService = driveService;
    }

    public String findOrCreateFolder(String folderName) throws IOException {
        FileList result = driveService.files().list()
                .setQ("mimeType='application/vnd.google-apps.folder' and name='" + folderName + "'")
                .setSpaces("drive")
                .execute();
        List<File> folders = result.getFiles();

        if (folders.isEmpty()) {
            File folderMetadata = new File();
            folderMetadata.setName(folderName);
            folderMetadata.setMimeType("application/vnd.google-apps.folder");
            File folder = driveService.files().create(folderMetadata).execute();
            return folder.getId();
        }

        return folders.get(0).getId();
    }

    public void uploadFiles(String folderId, Set<FileInfo> files) throws IOException {
        for (FileInfo file : files) {
            java.io.File localFile = new java.io.File(file.getPath());
            File fileMetadata = new File();
            fileMetadata.setName(localFile.getName());
            fileMetadata.setParents(List.of(folderId));

            FileContent mediaContent = new FileContent("application/octet-stream", localFile);
            driveService.files().create(fileMetadata, mediaContent).execute();
        }
    }
}
