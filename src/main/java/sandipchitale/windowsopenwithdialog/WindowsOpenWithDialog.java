package sandipchitale.windowsopenwithdialog;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.nio.file.Path;

public class WindowsOpenWithDialog {
    static void openWithDialog(VirtualFile virtualFile) {
        if (virtualFile != null && virtualFile.isInLocalFileSystem()) {
            try {
                Process process = new ProcessBuilder()
                        .command(Path.of(System.getenv("WINDIR"), "system32", "rundll32.exe").toString(),
                                "shell32,OpenAs_RunDLL",
                                virtualFile.getPath().replace("/", "\\")
                        ).inheritIO().start();
                new Thread(() -> {
                    try {
                        process.waitFor();
                    } catch (InterruptedException ignore) {
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
