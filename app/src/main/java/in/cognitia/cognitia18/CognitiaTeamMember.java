package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by devansh on 2/10/18.
 */

public class CognitiaTeamMember {
     private String name;
     private String team;
     private String post;
     private int imageId;

     public static Context context;

     public CognitiaTeamMember(int nameRes, int teamRes, int postRes, int imageRes) {

         Resources res = context.getResources();

         setName(res.getString(nameRes));
         setTeam(res.getString(teamRes));
         setPost(res.getString(postRes));
         setImageId(imageRes);
     }

     public void setName(String name) {
         this.name = name;
     }

     public void setTeam(String team) {
         this.team = team;
     }

     public void setPost(String post) {
         this.post = post;
     }

     public void setImageId(int imageId) {
         this.imageId = imageId;
     }

     public String getName() {
         return name;
     }

     public String getTeam() {
         return team;
     }

     public String getPost() {
         return post;
     }

     public int getImageId() {
         return imageId;
     }
}
