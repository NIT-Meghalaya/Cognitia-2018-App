package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by devansh on 2/10/18.
 */

public class TeamMembersArrayInitializer {

    private static ArrayList<CognitiaTeamMember> teamMembers = new ArrayList<>();

    public static void addTeamMembers() {

        teamMembers.add(new CognitiaTeamMember(R.string.laribok, R.string.team_app_development, R.string.post_coordinator, R.drawable.departmental));
        teamMembers.add(new CognitiaTeamMember(R.string.devansh, R.string.team_app_development, R.string.post_coordinator, R.drawable.robotics));
    }

    public static ArrayList<CognitiaTeamMember> getTeamMembers() {
        return teamMembers;
    }
}
