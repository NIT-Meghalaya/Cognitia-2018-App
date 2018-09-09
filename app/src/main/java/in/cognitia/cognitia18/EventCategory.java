package in.cognitia.cognitia18;

/**
 * Created by devansh on 9/9/18.
 */

public class EventCategory {

    private String name;
    private int imageResId;
    private int colorResId;

    public EventCategory(String name, int imageResId, int colorResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.colorResId = colorResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setColorResId(int colorResId) {
        this.colorResId = colorResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getColorResId() {
        return colorResId;
    }
}
