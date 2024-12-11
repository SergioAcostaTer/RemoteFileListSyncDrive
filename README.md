# **FileSync**

FileSync is a Java-based application designed to back up and synchronize local files to Google Drive using the Google Drive API. The project features secure OAuth authentication and offers functionality for both file synchronization and server-client communication to manage file records.

---

## Example of Usage

![Screenshot 2024-12-11 145747](https://github.com/user-attachments/assets/b2ae0425-f027-4608-92f6-f2ea616a2656)
![Screenshot 2024-12-11 145756](https://github.com/user-attachments/assets/2aad2a87-b407-41e5-b13b-f652f172215c)
![Screenshot 2024-12-11 145808](https://github.com/user-attachments/assets/e9da40ac-176d-4956-a2cb-25f281e4686e)


## **Features**

- **Google Drive Integration**:
  - Automatically creates or uses existing folders on Google Drive.
  - Uploads files from a specified local directory to the Drive folder.
  - Uses the Google Drive API for seamless integration.

- **File Synchronization**:
  - Tracks file modifications using timestamps.
  - Ensures only new or updated files are uploaded to the server.

- **Server-Client Communication**:
  - Maintains a record of client files on the server.
  - Identifies and sends information about newly added files to the server.

- **Google Calendar Integration**:
  - Adds a marker event in your Google Calendar indicating that a backup has been completed.
  - Includes basic details in the event, such as the number of files copied and the total size of the data transferred.

---

## **Prerequisites**

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Google Cloud Console**:
  - A project with Google Drive API and Calendar API enabled.
  - A `credentials.json` file for OAuth authentication.
- **Libraries**:
  - [Google API Client](https://developers.google.com/api-client-library/java)
  - Gson for JSON parsing.
  - Guava for advanced collection utilities.

---

## **Setup**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/SergioAcostaTer/RemoteFileListSyncDrive.git
   cd FileSync
   ```

2. **Set Up Google Drive and Calendar Credentials**:
   - Place your `credentials.json` file in the `src/main/resources/` directory.

3. **Build the Project**:
   Compile the project using your preferred IDE (e.g., IntelliJ IDEA, Eclipse) or with `javac`.

4. **Run the Server**:
   Start the server to manage file records and communicate with clients.
   ```bash
   java FileSync.Server
   ```

5. **Run the Client**:
   Synchronize your local files with Google Drive:
   ```bash
   java FileSync.Client [client_id] [directory_path]
   ```
   - `client_id` (optional): Defaults to `"defaultClient"`.
   - `directory_path` (optional): Defaults to `"./src/main/java/FileSync"`.
---

## **Project Structure**

```
FileSync/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── FileSync/
│   │           ├── BackupManager.java
│   │           ├── CalendarHelper.java
│   │           ├── Client.java
│   │           ├── DriveServiceHelper.java
│   │           ├── FileInfo.java
│   │           ├── Server.java
│   │           └── CalendarServiceHelper.java
│   └── resources/
│       └── credentials.json
└── README.md
```

---

## **Usage Example**

### Synchronize Local Files to Google Drive:
```bash
java FileSync.Client myClientID ./my/local/directory
```

### Start the FileSync Server:
```bash
java FileSync.Server
```

### Log Backup Event to Google Calendar:
The marker will automatically appear in your primary Google Calendar once the backup is completed.

---

## **Future Enhancements**

- **GUI Support**: Add a graphical user interface for ease of use.
- **Advanced Conflict Resolution**: Handle file version conflicts between local and remote files.

---

## **License**

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## **Contributing**

Contributions are welcome! Feel free to fork the project and submit pull requests.
