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
    private static HashMap<String , CognitiaTeamMember> teamMembers = new HashMap<>();
    //An array of teamMembers is needed to show the images in random order
    private static ArrayList<CognitiaTeamMember> teamMembersArrayList;

    public static void addTeamMembers() {

        addMember(laribok, team_app_development, post_coordinator, drawable.laribok, email_laribok);
        addMember(devansh, team_app_development, post_coordinator, drawable.robotics, email_devansh);

        mapToArrayList();
    }

    public static ArrayList<CognitiaTeamMember> getTeamMembers() {
        return teamMembersArrayList;
    }

    public static HashMap<String , CognitiaTeamMember> getMembersMap() {
        return teamMembers;
    }

    public static CognitiaTeamMember getMemberByName(String name) {
        return teamMembers.get(name);
    }

    private static void addMember(int nameId, int teamId, int postId, int imageId, int emailIdRes) {
        CognitiaTeamMember member = new CognitiaTeamMember(nameId, teamId, postId, imageId, emailIdRes);
        teamMembers.put(member.getName(), member);
    }

    private static void mapToArrayList() {
        //Getting collection of values from HashMap
        Collection<CognitiaTeamMember> values = teamMembers.values();
        //Creating an ArrayList of values
        teamMembersArrayList = new ArrayList<>(values);
    }
}
