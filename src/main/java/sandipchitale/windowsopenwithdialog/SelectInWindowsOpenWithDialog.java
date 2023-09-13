package sandipchitale.windowsopenwithdialog;

import com.intellij.execution.Platform;
import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.nio.file.Path;

public class SelectInWindowsOpenWithDialog implements SelectInTarget {

    @Override
    public boolean canSelect(SelectInContext context) {
        return Platform.current().equals(Platform.WINDOWS);
    }

    @Override
    public void selectIn(SelectInContext context, boolean requestFocus) {
        VirtualFile virtualFile = context.getVirtualFile();
        if (virtualFile.isInLocalFileSystem()) {
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

    @Override
    public float getWeight() {
        return 1000;
    }

    @Override
    public String toString() {
        return "Open with...";
    }
}
