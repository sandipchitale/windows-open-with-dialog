package sandipchitale.windowsopenwithdialog;

import com.intellij.execution.Platform;
import com.intellij.ide.SelectInContext;
import com.intellij.ide.SelectInTarget;

public class SelectInWindowsOpenWithDialog implements SelectInTarget {

    @Override
    public boolean canSelect(SelectInContext context) {
        return Platform.current().equals(Platform.WINDOWS);
    }

    @Override
    public void selectIn(SelectInContext context, boolean requestFocus) {
        WindowsOpenWithDialog.openWithDialog(context.getVirtualFile());
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
