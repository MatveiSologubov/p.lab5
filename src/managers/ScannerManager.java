package src.managers;

import java.util.Scanner;

public class ScannerManager {
    Scanner mainScanner;
    Scanner fileScanner;
    boolean scriptMode = false;

    public ScannerManager(Scanner mainScanner) {
        this.mainScanner = mainScanner;
    }

    public void setScriptScanner(Scanner fileScanner) {
        this.fileScanner = fileScanner;
    }

    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    public Scanner getScanner() {
        if (scriptMode) {
            return fileScanner;
        }
        return mainScanner;
    }
}
