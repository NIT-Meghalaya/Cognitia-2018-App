package in.cognitia.cognitia18;

/**
 * Created by devansh on 9/9/18.
 */

public class CognitiaEvent extends EventCategory{
    private String shortDescription;

    public CognitiaEvent(String name, String shortDescription, int imageResId) {
        super(name, imageResId, 0);
        setShortDescription(shortDescription);
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
