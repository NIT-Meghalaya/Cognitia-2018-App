package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;

import java.io.Serializable;

/**
 * Created by devansh on 2/10/18.
 */

//Making it serializable so that it can be passed between activities
public class CognitiaTeamMember implements Serializable {
     private String name;
     private String team;
     private String post;
     private int imageId;
     private String email;

     public static Context context;

     public CognitiaTeamMember(int nameRes, int teamRes, int postRes, int imageRes, int emailRes) {

         Resources res = context.getResources();

         setName(res.getString(nameRes));
         setTeam(res.getString(teamRes));
         setPost(res.getString(postRes));
         setImageId(imageRes);
         setEmail(res.getString(emailRes));

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

     public void setEmail(String email) {
         this.email = email;
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

     public String getEmail() {
         return email;
     }
}
