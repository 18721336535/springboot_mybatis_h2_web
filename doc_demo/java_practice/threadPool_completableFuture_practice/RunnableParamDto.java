
import java.io.File;
import java.util.List;

public class RunnableParamDto {
    private String userId;
    private List<List<File>> fileGroups;
    private String releasePath;
    private String errorPath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<List<File>> getFileGroups() {
        return fileGroups;
    }

    public void setFileGroups(List<List<File>> fileGroups) {
        this.fileGroups = fileGroups;
    }

    public String getReleasePath() {
        return releasePath;
    }

    public void setReleasePath(String releasePath) {
        this.releasePath = releasePath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }
}
