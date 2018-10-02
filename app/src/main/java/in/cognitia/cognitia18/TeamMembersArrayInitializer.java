package in.cognitia.cognitia18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static in.cognitia.cognitia18.R.string.*;
import static in.cognitia.cognitia18.R.*;

/**
 * Created by devansh on 2/10/18.
 */

public class TeamMembersArrayInitializer {

    //HashMap is needed to get the object corresponding to a particular name
    private static HashMap<Integer, CognitiaTeamMember> teamMembers = new HashMap<>();
    //An array of teamMembers is needed to show the images in random order
    private static ArrayList<CognitiaTeamMember> teamMembersArrayList;

    public static void addTeamMembers() {

        addMember(laribok, team_app_development, post_coordinator, drawable.laribok);
        addMember(devansh, team_app_development, post_coordinator, drawable.robotics);

        mapToArrayList();
    }

    public static ArrayList<CognitiaTeamMember> getTeamMembers() {
        return teamMembersArrayList;
    }

    private static void addMember(int nameId, int teamId, int postId, int imageId) {
        teamMembers.put(nameId, new CognitiaTeamMember(nameId, teamId, postId, imageId));
    }

    private static void mapToArrayList() {
        //Getting collection of values from HashMap
        Collection<CognitiaTeamMember> values = teamMembers.values();
        //Creating an ArrayList of values
        teamMembersArrayList = new ArrayList<>(values);
    }
}
