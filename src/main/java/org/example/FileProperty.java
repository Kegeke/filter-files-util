package org.example;

public class FileProperty {
    private String prefixName = "";
    private String pathResult = "";
    private boolean appendMode = false;
    private boolean fullStats = false;
    private boolean shortStats = false;

    public FileProperty() {
    }

    public String getPathResult() {
        return pathResult;
    }

    public void setPathResult(String pathResult) {
        this.pathResult = pathResult + "/";
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public void setFullStats(boolean fullStats) {
        this.fullStats = fullStats;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public void setShortStats(boolean shortStats) {
        this.shortStats = shortStats;
    }
}
