public class PictureInfo {

    private String description;

    private String relativePath;

    public PictureInfo(String description, String relativePath) {

        this.description = description;
        this.relativePath = relativePath;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getRelativePath() {

        return relativePath;
    }

    public void setRelativePath(String relativePath) {

        this.relativePath = relativePath;
    }
    
}
