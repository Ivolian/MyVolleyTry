public class DownloadInfo {

    private String name;

    private String imgUrl;

    private String description;

    private double size;

    public DownloadInfo(String name, String imgUrl, String description, double size) {

        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.size = size;
    }

    public String getName() {

        return name;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public String getDescription() {

        return description;
    }

    public double getSize() {

        return size;
    }

}
