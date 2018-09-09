package in.cognitia.cognitia18;

/**
 * Created by devansh on 9/9/18.
 */

public class EventCategory {

    private String name;
    private long imageResId;
    private long colorResId;

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResId(long imageResId) {
        this.imageResId = imageResId;
    }

    public void setColorResId(long colorResId) {
        this.colorResId = colorResId;
    }

    public String getName() {
        return name;
    }

    public long getImageResId() {
        return imageResId;
    }

    public long getColorResId() {
        return colorResId;
    }
}
