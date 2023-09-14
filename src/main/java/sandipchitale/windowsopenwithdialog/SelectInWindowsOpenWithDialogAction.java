package sandipchitale.windowsopenwithdialog;

import com.intellij.execution.Platform;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.jetbrains.annotations.NotNull;

public class SelectInWindowsOpenWithDialogAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        WindowsOpenWithDialog.openWithDialog(e.getDataContext().getData(CommonDataKeys.VIRTUAL_FILE));
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(Platform.current().equals(Platform.WINDOWS));
    }
}
