package in.cognitia.cognitia18;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static in.cognitia.cognitia18.R.string.*;
import static in.cognitia.cognitia18.R.*;

import static in.cognitia.cognitia18.CognitiaTeamMember.*;

/**
 * Created by devansh on 2/10/18.
 */

public class TeamMembersArrayInitializer {

    private static ArrayList<CognitiaTeamMember> allTeamMembers = new ArrayList<>();

    //HashMap is needed to get the object corresponding to a particular name

    private static HashMap<String, ArrayList<CognitiaTeamMember>> teamMembersByCategories = new HashMap<>();

    private static HashMap<String , CognitiaTeamMember> teamMembers_departmental = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_designing = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_ecell = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_event_management = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_fun_events = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_gaming = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_hospitality = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_photowalk = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_publicity = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_quiz_debate = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_secretaries = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_shimmer = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_stage_management = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_technical = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_web_development = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_app_development = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_sponsorship = new HashMap<>();

    private static HashMap<String , CognitiaTeamMember> teamMembers_line_follower_robot = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_goal_against_time = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_race_against_time = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_robo_rumble = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_robo_bridge = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_robo_diction = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_prison_breakout = new HashMap<>();

    private static HashMap<String, CognitiaTeamMember> teamMembers_ce_departmental = new HashMap<>();
    private static HashMap<String, CognitiaTeamMember> teamMembers_cse_departmental = new HashMap<>();
    private static HashMap<String, CognitiaTeamMember> teamMembers_eee_departmental = new HashMap<>();
    private static HashMap<String, CognitiaTeamMember> teamMembers_ece_departmental = new HashMap<>();
    private static HashMap<String, CognitiaTeamMember> teamMembers_me_departmental = new HashMap<>();

    private static HashMap<String , CognitiaTeamMember> sponsorsMap = new HashMap<>();

    //An array of teamMembers is needed to show the images in random order

    private static ArrayList<CognitiaTeamMember> arrayList_departmental = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_designing = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_ecell = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_event_management = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_fun_events = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_gaming = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_hospitality = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_photowalk = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_publicity = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_quiz_debate = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_secretaries = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_shimmer = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_stage_management = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_technical = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_web_development = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_app_development = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_sponsorship = new ArrayList<>();

    private static ArrayList<CognitiaTeamMember> arrayList_line_follower_robot = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_goal_against_time = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_race_against_time = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_robo_rumble = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_robo_bridge = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_robodiction = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_prison_breakout = new ArrayList<>();

    private static ArrayList<CognitiaTeamMember> arrayList_ce_departmental = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_cse_departmental = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_eee_departmental = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_ece_departmental = new ArrayList<>();
    private static ArrayList<CognitiaTeamMember> arrayList_me_departmental = new ArrayList<>();

    private static ArrayList<CognitiaTeamMember> arrayList_sponsors = new ArrayList<>();


    static ArrayList<CognitiaTeamMember> getTeamMembers(String teamName) {
        return teamMembersByCategories.get(teamName);
    }

    static CognitiaTeamMember[] getTeamMembersSortedArray(String teamName) {

        ArrayList<CognitiaTeamMember> membersList = getTeamMembers(teamName);
        Log.v("Team name", teamName);
        Log.v("Members list", membersList + "");

        CognitiaTeamMember[] members = new CognitiaTeamMember[membersList.size()];

        int beg = 0, end = membersList.size()-1;

        //Sort the array, with heads first followed by coordinators
        for (CognitiaTeamMember member: membersList) {
            if (member.getTeam().equals(teamName)) {
                if (member.getPost().equals(CognitiaTeamMember.POST_HEAD)) {
                    members[beg++] = member;
                } else if (member.getPost().equals(CognitiaTeamMember.POST_COCOORDINATOR)){
                    members[end--] = member;
                }
            }
        }

        for (CognitiaTeamMember member : membersList) {
            if (member.getTeam().equals(teamName)) {
                if (member.getPost().equals(CognitiaTeamMember.POST_COORDINATOR)) {
                    members[beg++] = member;
                }
            }
        }

        Log.v("Members array len", members.length + "");
        Log.v("member array", members[0].getName() + " " + members[1].getName() + " " + members[2].getName() + " "+ "");

        return members;
    }

    @NonNull
    private static ArrayList<CognitiaTeamMember> mapToArrayList(HashMap<String, CognitiaTeamMember> teamMembers) {
        //Getting collection of values from HashMap
        Collection<CognitiaTeamMember> values = teamMembers.values();
        //Creating an ArrayList of values
        ArrayList<CognitiaTeamMember> arrayList = new ArrayList<>(values);
        Log.v("Returned array size", arrayList.size() + "");
        return arrayList;
    }

    static ArrayList<CognitiaTeamMember> getMembersByName(String inputName) {

        ArrayList<CognitiaTeamMember> searchedMembers = new ArrayList<>();

        for (CognitiaTeamMember member : allTeamMembers) {
            if (member.getName().toLowerCase().equals(inputName.toLowerCase())) {
                searchedMembers.add(member);
            }
        }

        return searchedMembers;
    }

    private static void createTeamsArrayLists() {
        arrayList_departmental = mapToArrayList(teamMembers_departmental);
        arrayList_designing = mapToArrayList(teamMembers_designing);
        Log.v("Designing hash map", ""+ teamMembers_designing);
        Log.v("Designing array", "" + arrayList_designing.size());
        Log.v("Designing array size", arrayList_designing.size() + "");
        arrayList_ecell = mapToArrayList(teamMembers_ecell);
        arrayList_event_management = mapToArrayList(teamMembers_event_management);
        arrayList_fun_events = mapToArrayList(teamMembers_fun_events);
        arrayList_gaming = mapToArrayList(teamMembers_gaming);
        arrayList_hospitality = mapToArrayList(teamMembers_hospitality);
        arrayList_photowalk = mapToArrayList(teamMembers_photowalk);
        arrayList_publicity = mapToArrayList(teamMembers_publicity);
        arrayList_quiz_debate = mapToArrayList(teamMembers_quiz_debate);
        arrayList_secretaries = mapToArrayList(teamMembers_secretaries);
        arrayList_shimmer = mapToArrayList(teamMembers_shimmer);
        arrayList_stage_management = mapToArrayList(teamMembers_stage_management);
        arrayList_technical = mapToArrayList(teamMembers_technical);
        arrayList_web_development = mapToArrayList(teamMembers_web_development);
        arrayList_app_development = mapToArrayList(teamMembers_app_development);
        arrayList_sponsorship = mapToArrayList(teamMembers_sponsorship);

        arrayList_line_follower_robot = mapToArrayList(teamMembers_line_follower_robot);
        arrayList_goal_against_time = mapToArrayList(teamMembers_goal_against_time);
        arrayList_race_against_time = mapToArrayList(teamMembers_race_against_time);
        arrayList_robo_bridge = mapToArrayList(teamMembers_robo_bridge);
        arrayList_robodiction = mapToArrayList(teamMembers_robo_diction);
        arrayList_robo_rumble = mapToArrayList(teamMembers_robo_rumble);
        arrayList_prison_breakout = mapToArrayList(teamMembers_prison_breakout);

        arrayList_ce_departmental = mapToArrayList(teamMembers_ce_departmental);
        arrayList_cse_departmental = mapToArrayList(teamMembers_cse_departmental);
        arrayList_eee_departmental = mapToArrayList(teamMembers_eee_departmental);
        arrayList_ece_departmental = mapToArrayList(teamMembers_ece_departmental);
        arrayList_me_departmental = mapToArrayList(teamMembers_me_departmental);

        arrayList_sponsors = mapToArrayList(sponsorsMap);
    }

    private static void addTeamCategoriesArraysToMap() {
        teamMembersByCategories.put(CognitiaTeamMember.DEPARTMENTAL, arrayList_departmental);
        teamMembersByCategories.put(CognitiaTeamMember.DESIGNING, arrayList_designing);
        teamMembersByCategories.put(CognitiaTeamMember.ECELL, arrayList_ecell);
        teamMembersByCategories.put(CognitiaTeamMember.EVENT_MAMANGEMET, arrayList_event_management);
        teamMembersByCategories.put(CognitiaTeamMember.FUN_EVENTS, arrayList_fun_events);
        teamMembersByCategories.put(CognitiaTeamMember.GAMING, arrayList_gaming);
        teamMembersByCategories.put(CognitiaTeamMember.HOSPITALITY, arrayList_hospitality);
        teamMembersByCategories.put(CognitiaTeamMember.PHOTOWALK, arrayList_photowalk);
        teamMembersByCategories.put(CognitiaTeamMember.PUBLICITY, arrayList_publicity);
        teamMembersByCategories.put(CognitiaTeamMember.QUIZ_DEBATE, arrayList_quiz_debate);
        teamMembersByCategories.put(CognitiaTeamMember.SECRETARIES_MEMBERS, arrayList_secretaries);
        teamMembersByCategories.put(CognitiaTeamMember.SHIMMER_ARPEGGIO, arrayList_shimmer);
        teamMembersByCategories.put(CognitiaTeamMember.STAGE_MANAGEMENT, arrayList_stage_management);
        teamMembersByCategories.put(CognitiaTeamMember.TECHNICAL, arrayList_technical);
        teamMembersByCategories.put(CognitiaTeamMember.WEB_DEVELOPMENT, arrayList_web_development);
        teamMembersByCategories.put(CognitiaTeamMember.APP_DEVELOPMENT, arrayList_app_development);
        teamMembersByCategories.put(CognitiaTeamMember.SPONSORSHIP, arrayList_sponsorship);

        teamMembersByCategories.put(LINE_FOLLOWER_ROBOT, arrayList_line_follower_robot);
        teamMembersByCategories.put(RACE_AGAINST_TIME, arrayList_race_against_time);
        teamMembersByCategories.put(GOAL_AGAINST_TIME, arrayList_goal_against_time);
        teamMembersByCategories.put(ROBO_BRIDGE, arrayList_robo_bridge);
        teamMembersByCategories.put(ROBORUMBLE, arrayList_robo_rumble);
        teamMembersByCategories.put(ROBODICTION, arrayList_robodiction);
        teamMembersByCategories.put(PRISON_BREAKOUT, arrayList_prison_breakout);

        teamMembersByCategories.put(CE_DEPARTMENTAL, arrayList_ce_departmental);
        teamMembersByCategories.put(CSE_DEPARTMENTAL, arrayList_cse_departmental);
        teamMembersByCategories.put(EEE_DEPARTMENTAL, arrayList_eee_departmental);
        teamMembersByCategories.put(ECE_DEPARTMENTAL, arrayList_ece_departmental);
        teamMembersByCategories.put(ME_DEPARTMENTAL, arrayList_me_departmental);

        teamMembersByCategories.put(SPONSORS, arrayList_sponsors);
    }

    private static void addMember(int nameId, int teamId, int postId, int imageId, int emailIdRes) {
        CognitiaTeamMember member = new CognitiaTeamMember(nameId, teamId, postId, imageId, emailIdRes);

        allTeamMembers.add(member);

        switch (teamId) {
            case team_civil_departmental:
                teamMembers_ce_departmental.put(member.getName(), member);
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_cse_departmental:
                teamMembers_cse_departmental.put(member.getName(), member);
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_ece_departmental:
                teamMembers_ece_departmental.put(member.getName(), member);
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_ee_departmental:
                teamMembers_eee_departmental.put(member.getName(), member);
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_me_departmental:
                teamMembers_me_departmental.put(member.getName(), member);
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_designing:
                teamMembers_designing.put(member.getName(), member);
                break;
            case team_e_cell:
                teamMembers_ecell.put(member.getName(), member);
                break;
            case team_event_management:
                teamMembers_event_management.put(member.getName(), member);
                break;
            case team_fun_events:
                teamMembers_fun_events.put(member.getName(), member);
                break;
            case team_gaming:
                teamMembers_gaming.put(member.getName(), member);
                break;
            case team_hospitality:
                teamMembers_hospitality.put(member.getName(), member);
                break;
            case team_photo_walk:
                teamMembers_photowalk.put(member.getName(), member);
                break;
            case team_publicity:
                teamMembers_publicity.put(member.getName(), member);
                break;
            case team_general_quiz_debate:
                teamMembers_quiz_debate.put(member.getName(), member);
                break;
            case secretary_and_members:
                teamMembers_secretaries.put(member.getName(), member);
                break;
            case team_arppegio_and_shimmer:
                teamMembers_shimmer.put(member.getName(), member);
                break;
            case team_stage_management_and_printing:
                teamMembers_stage_management.put(member.getName(), member);
                break;
            case team_goal_against_time:
                teamMembers_goal_against_time.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_race_against_time:
                teamMembers_race_against_time.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_line_follower_robot:
                teamMembers_line_follower_robot.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_robo_bridge:
                teamMembers_robo_bridge.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_robodiction:
                teamMembers_robo_diction.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_robo_rumble:
                teamMembers_robo_rumble.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_prison_breakout:
                teamMembers_prison_breakout.put(member.getName(), member);
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_web_development:
                teamMembers_web_development.put(member.getName(), member);
                break;
            case team_app_development:
                teamMembers_app_development.put(member.getName(), member);
                break;
            case team_sponsorship:
                teamMembers_sponsorship.put(member.getName(), member);
                break;
            case sponsors:
                sponsorsMap.put(member.getName(), member);
                break;
        }
    }

    public static void addTeamMembers() {

        addMember(karan, team_event_management, post_head, drawable.s_s_sri_karan, email_karan);
        addMember(gnaneshwar, team_event_management, post_coordinator, drawable.gnaneshwar, email_gnaneshwar);
        addMember(kethiri, team_event_management, post_coordinator, drawable.narshimha, email_kethri);
        addMember(shubham_kumar_singh, team_event_management, post_coordinator, drawable.shubham_kumar_singh, email_shubham_kumar_singh);
        addMember(rafad, team_event_management, post_co_coordinator, drawable.rafad, email_rafad);

        addMember(shivam_prasad, team_cse_departmental, post_head, drawable.shivam_prasad, email_shivam_prasad);
        addMember(ketan, team_cse_departmental, post_head, drawable.ketan_anand, email_laribok);
        addMember(ashutosh, team_cse_departmental, post_coordinator, drawable.ashutosh, email_ketan);
        addMember(alphin, team_cse_departmental, post_coordinator, drawable.alphin, email_alphin);

        addMember(shivam_kumar, team_civil_departmental, post_head, drawable.shivam_kumar, email_shivam_kumar);
        addMember(sunil, team_civil_departmental, post_head, drawable.sunil, email_sunil);
        addMember(lalit, team_civil_departmental, post_coordinator, drawable.lalit, email_lalit);
        addMember(jakish, team_civil_departmental, post_coordinator, drawable.jakish, email_jakish);

        addMember(parakhee, team_ece_departmental, post_head, drawable.parakhee, email_parakhee);
        addMember(rohit_kumar, team_ece_departmental, post_head, drawable.rohit_kumar, email_rohit_kumar);
        addMember(rajoo, team_ece_departmental, post_coordinator, drawable.rajoo, email_rajoo);

        addMember(nitin, team_ee_departmental, post_head, drawable.nitin, email_nitin);
        addMember(pankaj, team_ee_departmental, post_coordinator, drawable.pankaj, email_pankaj);
        addMember(manish_kumar, team_ee_departmental, post_coordinator, drawable.manish, email_manish_kumar);
        addMember(teiboklang, team_ee_departmental, post_coordinator, drawable.teibok, email_teiboklang);

        addMember(teiborlin, team_me_departmental, post_head, drawable.teiborlin, email_teiborlin);
        addMember(kishan_das, team_me_departmental, post_coordinator, drawable.kishan_das, email_kishan_das);
        addMember(baiateilang, team_me_departmental, post_coordinator, drawable.baiateilang, email_baiateilang);
        addMember(kapil, team_me_departmental, post_coordinator, drawable.kapil, email_kapil);

        addMember(manikanta, team_race_against_time, post_head, drawable.manikanta, email_manikanta);
        addMember(amit_kumar_lal, team_race_against_time, post_coordinator, drawable.amit, email_amit_kumar_lal);
        addMember(kamarapu, team_race_against_time, post_coordinator, drawable.shiva_rat, email_kamarapu);

        addMember(ajoy, team_goal_against_time, post_head, drawable.ajoy, email_ajoy);
        addMember(mukesh, team_goal_against_time, post_coordinator, drawable.mukesh, email_mukesh);
        addMember(priya, team_goal_against_time, post_coordinator, drawable.priya, email_priya);

        addMember(m_raj_kumar, team_robodiction, post_head, drawable.raj_kumar, email_m_raj_kumar);
        addMember(nitish, team_robodiction, post_head, drawable.nitish, email_nitish);
        addMember(viswa_teja, team_robodiction, post_coordinator, drawable.viswa, email_viswa_teja);
        addMember(harish, team_robodiction, post_coordinator, drawable.harish, email_harish);

        addMember(satyaveer, team_prison_breakout, post_head, drawable.satyaveer, email_satyaveer);
        addMember(anant_kumar, team_prison_breakout, post_head, drawable.john, email_anant_kumar);
        addMember(manish, team_prison_breakout, post_coordinator, drawable.manish_, email_manish);
        addMember(abhushan, team_prison_breakout, post_coordinator, drawable.abhushan, email_abhushan);
        addMember(uchit, team_prison_breakout, post_coordinator, drawable.uchit, email_uchit);

        addMember(anurag, team_line_follower_robot, post_head, drawable.anurag, email_anurag);
        addMember(aniruddha, team_line_follower_robot, post_coordinator, drawable.aniruddha, email_aniruddha);
        addMember(mohan, team_line_follower_robot, post_coordinator, drawable.mohan, email_mohan);

        addMember(skhemboklang, team_robo_rumble, post_head, drawable.skhemboklang, email_skhemboklang);
        addMember(dennybert, team_robo_rumble, post_head, drawable.dennybert, email_dennybert);
        addMember(abhay, team_robo_rumble, post_coordinator, drawable.abhay, email_abhay);
        addMember(kishan_chaurasia, team_robo_rumble, post_coordinator, drawable.kishan_chaurasia, email_kishan_chaurasia);

        addMember(ranjan_kumar, team_robo_bridge, post_head, drawable.ranjan_kumar, email_ranjan_kumar);
        addMember(raman, team_robo_bridge, post_head, drawable.raman_gupta, email_raman);
        addMember(deepak_kumar_sah, team_robo_bridge, post_coordinator, drawable.deepak_kumar_sah, email_deepak_kumar_sah);
        addMember(devashish, team_robo_bridge, post_coordinator, drawable.devashish_dubey, email_devashish);
        addMember(sanjay_kumar_kol, team_robo_bridge, post_coordinator, drawable.sanjay_kumar_kol, email_sanjay_kumar_kol);

        addMember(gyanishwar, team_gaming, post_head, drawable.gyaniswar, email_gyanishwar);
        addMember(jeny_welkin, team_gaming, post_head, drawable.jeny, email_jeny_welkin);
        addMember(balmhashwa, team_gaming, post_coordinator, drawable.balamhashwa, email_balmhashwa);
        addMember(hamewot, team_gaming, post_coordinator, drawable.hamewot, email_hamewot);

        addMember(jayanth_reddy, team_arppegio_and_shimmer, post_head, drawable.jayanth, email_jayanth_reddy);
        addMember(annamaiah, team_arppegio_and_shimmer, post_head, drawable.annamaiah, email_annamaiah);
        addMember(debapratim, team_arppegio_and_shimmer, post_head, drawable.debapratim, email_debapratim);
        addMember(jugami, team_arppegio_and_shimmer, post_coordinator, drawable.jugami, email_jugami);
        addMember(khushi, team_arppegio_and_shimmer, post_coordinator, drawable.khushi_mishra, email_khushi);
        addMember(charan_reddy, team_arppegio_and_shimmer, post_coordinator, drawable.charan_reddy, email_charan_reddy);
        addMember(ananya_giri, team_arppegio_and_shimmer, post_coordinator, drawable.ananya_giri, email_ananya_giri);

        addMember(pynsuklang, team_publicity, post_head, drawable.pynsuk, email_pynsuklang);
        addMember(evanstarfield, team_publicity, post_head, drawable.evanstar, email_evanstarfield);
        addMember(randolph, team_publicity, post_coordinator, drawable.randolph, email_randolph);
        addMember(rafaela, team_publicity, post_coordinator, drawable.rafaela, email_rafaela);
        addMember(rasalinda, team_publicity, post_coordinator, drawable.rasalinda, email_rasalinda);
        addMember(ellyn, team_publicity, post_coordinator, drawable.ellyn, email_ellyn);

        addMember(ananya_bhattacharya, team_designing, post_head, drawable.ananya, email_ananya_bhattacharya);
        addMember(shiva_teja, team_designing, post_head, drawable.shiva, email_shiva_teja);
        addMember(sonalika, team_designing, post_head, drawable.sonalika, email_sonalika);
        addMember(satya_abhinay, team_designing, post_coordinator, drawable.bollampalli_satya_abhinay, email_satya_abhinay);
        addMember(tirupathi_rao, team_designing, post_coordinator, drawable.kummari_tirupathi_rao, email_tirupathi_rao);
        addMember(kishore_chary, team_designing, post_coordinator, drawable.v_sai_kishore_chary, email_kishore_chary);

        addMember(jibesh, team_web_development, post_head, drawable.jibesh, email_jibesh);
        addMember(wiwat, team_web_development,post_coordinator,drawable.wiwatdaka, email_wiwat);
        addMember(tanuj, team_web_development, post_coordinator, drawable.tanuj, email_tanuj);
        addMember(mahfooz, team_web_development, post_coordinator, drawable.mahfooz, email_mahfooz);

        addMember(jibesh, team_app_development, post_head, drawable.jibesh, email_jibesh);
        addMember(devansh, team_app_development, post_coordinator, drawable.devansh, email_devansh);
        addMember(laribok, team_app_development, post_coordinator, drawable.laribok, email_laribok);
        addMember(amrit, team_app_development, post_coordinator, drawable.amrit, email_amrit);

        addMember(chiron, team_hospitality, post_head, drawable.chiron, email_chiron);
        addMember(jubabmilekini, team_hospitality, post_head, drawable.jubab, email_jubabmilekini);
        addMember(elvarie, team_hospitality, post_coordinator, drawable.elva, email_elvarie);
        addMember(dianglinshisha, team_hospitality, post_coordinator, drawable.diang, email_dianglinshisha);
        addMember(donna, team_hospitality, post_coordinator,drawable.donna, email_donna);

        addMember(nehemiah, team_photo_walk, post_head, drawable.nehemiah, email_nehemiah);
        addMember(kynmawlang, team_photo_walk, post_head, drawable.kynmawlang, email_kynmawlang);

        addMember(damanbha, team_disciplinary, post_head, drawable.damanbha, email_damanbha);

        addMember(deeksha, team_stage_management_and_printing, post_head, drawable.deeksha, email_deeksha);
        addMember(nathaneal, team_stage_management_and_printing, post_head, drawable.nathaneal, email_nathaneal);
        addMember(surabhi, team_stage_management_and_printing, post_coordinator, drawable.surabhi, email_surabhi);
        addMember(koustubh_deshpande, team_stage_management_and_printing, post_coordinator, drawable.koustubh_deshpande, email_koustubh_deshpande);

        addMember(francis, team_general_quiz_debate, post_head, drawable.francis, email_francis);
        addMember(karasaphi, team_general_quiz_debate, post_coordinator, drawable.karasaphi, email_karasaphi);
        addMember(johnyborn, team_general_quiz_debate, post_coordinator, drawable.johnyborn, email_johnyborn);
        addMember(pynbhalang, team_general_quiz_debate, post_coordinator, drawable.pynbhalang, email_pynbhalang);

        addMember(diswel, team_fun_events, post_head, drawable.diswel, email_diswel);
        addMember(arminias, team_fun_events, post_head, drawable.arminias, email_arminias);
        addMember(shilling, team_fun_events, post_head, drawable.shilling, email_shilling);

        addMember(shemphang, secretary_and_members, general_secretary, drawable.shemphang, email_shemphang);
        addMember(leon, secretary_and_members, general_secretary, drawable.leon, email_leon);
        addMember(khrawboklang, secretary_and_members, general_secretary_member, drawable.khrawboklang, email_khrawboklang);

        addMember(sarthak, team_sponsorship, post_head, drawable.sarthak, email_sarthak);
        addMember(shakti, team_sponsorship, post_coordinator, drawable.shakti, email_shakti);
        addMember(priyesh, team_sponsorship, post_co_coordinator, drawable.priyesh, email_priyesh);
        addMember(chirag, team_sponsorship, post_co_coordinator, drawable.chirag, email_chirag);
        addMember(himanshu, team_sponsorship, post_co_coordinator, drawable.himanshu, email_himanshu);

        addMember(pratik, team_e_cell, post_head, drawable.pratik, email_pratik);
        addMember(gaurav, team_e_cell, post_coordinator, drawable.gaurav, email_gaurav);
        addMember(beauty, team_e_cell, post_coordinator, drawable.beauty, email_beauty);
        addMember(amandeep, team_e_cell, post_co_coordinator, drawable.amandeep, email_amandeep);
        addMember(ngamla, team_e_cell, post_co_coordinator, drawable.ngamla, email_ngamla);

        addMember(kfc, sponsors, empty_string, drawable.kfc, empty_string);
        addMember(dominos, sponsors, empty_string, drawable.dominos, empty_string);
        addMember(mcab, sponsors, empty_string, drawable.mcab, empty_string);

        /*
        co_coordinators
        */

        addMember(rafad, team_event_management, post_co_coordinator, drawable.rafad, email_rafad);
        addMember(saurabh_singh, team_event_management, post_co_coordinator, drawable.sourabh_singh, email_saurabh_singh);
        addMember(koustubh_kishore, team_event_management, post_co_coordinator, drawable.koustubh_kishore, email_koustubh_kishore);
        addMember(allu, team_cse_departmental, post_co_coordinator, drawable.allu, email_allu);
        addMember(chandan, team_cse_departmental, post_co_coordinator, drawable.chandan, email_chandan);
        addMember(shashank, team_civil_departmental, post_co_coordinator, drawable.shashank, email_shashank);
        addMember(sudarsan, team_civil_departmental, post_co_coordinator, drawable.sudarsan, email_sudarsan);
        addMember(sweeta, team_civil_departmental, post_co_coordinator, drawable.sweeta, email_sweeta);
        addMember(rohit_raj, team_ece_departmental, post_co_coordinator, drawable.rohit_raj, email_rohit_raj);
        addMember(sourav_paul, team_ece_departmental, post_co_coordinator, drawable.sourav_paul, email_sourav_paul);
        addMember(wanchi, team_ece_departmental, post_co_coordinator, drawable.wanchi, email_wanchi);
        addMember(naman, team_ee_departmental, post_co_coordinator, drawable.naman, email_naman);
        addMember(nirosha, team_ee_departmental, post_co_coordinator, drawable.nirosha, email_nirosha);
        addMember(rishabh, team_ee_departmental, post_co_coordinator, drawable.rishabh, email_rishabh);
        addMember(kapil, team_me_departmental, post_co_coordinator, drawable.kapil, email_kapil);
        addMember(shivam_sharma, team_me_departmental, post_co_coordinator, drawable.shivam_sharma, email_shivam_sharma);
        addMember(vivek, team_me_departmental, post_co_coordinator, drawable.vivek, email_vivek);
        addMember(sushant, team_me_departmental, post_co_coordinator, drawable.sushant, email_sushant);
        addMember(divyesh, team_race_against_time, post_co_coordinator, drawable.divyesh, email_divyesh);
        addMember(sidharth, team_race_against_time, post_co_coordinator, drawable.sidharth, email_sidharth);
        addMember(sakshay, team_goal_against_time, post_co_coordinator, drawable.sakshay, email_sakshay);
        addMember(heplang, team_goal_against_time, post_co_coordinator, drawable.heplang, email_heplang);
        addMember(sai_lohit, team_robodiction, post_co_coordinator, drawable.lohit, email_sai_lohit);
        addMember(mummidi, team_robodiction, post_co_coordinator, drawable.mummidi_bharadwaj, email_mummidi);
        addMember(emmanuel, team_robodiction, post_co_coordinator, drawable.emmanuel, email_emmanuel);
        addMember(ujjwal, team_line_follower_robot, post_co_coordinator, drawable.ujjwal, email_ujjwal);
        addMember(kishan_lal, team_line_follower_robot, post_co_coordinator, drawable.kishan_lal, email_kishan_lal);
        addMember(shubham_chang, team_robo_rumble, post_co_coordinator, drawable.shubham_chang, email_shubham_chang);
        addMember(saurav_dayal, team_robo_rumble, post_co_coordinator, drawable.saurav_dayal, email_saurav_dayal);
        addMember(astha, team_robo_bridge, post_co_coordinator, drawable.astha_awasthi, email_astha);
        addMember(mithun, team_robo_bridge, post_co_coordinator, drawable.mithun_kumar, email_mithun);
        addMember(jeush, team_gaming, post_co_coordinator, drawable.jeush, email_jeush);
        addMember(imdad, team_arppegio_and_shimmer, post_co_coordinator, drawable.imdadul, email_imdad);
        addMember(hemant_sabbavarapu, team_arppegio_and_shimmer, post_co_coordinator, drawable.hemanth_kumar, email_hemant_sabbavarapu);
        addMember(anamika, team_arppegio_and_shimmer, post_co_coordinator, drawable.anamika, email_anamika);
        addMember(hanna, team_publicity, post_co_coordinator, drawable.hanna, email_hanna);
        addMember(barishisha, team_publicity, post_co_coordinator, drawable.barishisha, email_barishisha);
        addMember(leonard, team_publicity, post_co_coordinator, drawable.leonard, email_leonard);
        addMember(lakyntiew, team_publicity, post_co_coordinator, drawable.lakyntiew, email_lakyntiew);
        addMember(eugynea, team_publicity, post_co_coordinator, drawable.eugynea, email_eugynea);
        addMember(josekielnezer, team_publicity, post_co_coordinator, drawable.josekielnezer, email_josekielnezer);
        addMember(chinmoy, team_designing, post_co_coordinator, drawable.chinmoy_deka, email_chinmoy);
        addMember(sagar_das, team_designing, post_co_coordinator, drawable.sagar_das, email_sagar_das);
        addMember(sanapala_rajesh, team_designing, post_co_coordinator, drawable.sanapala_rajesh, email_sanapala_rajesh);
        addMember(shubangi, team_designing, post_co_coordinator, drawable.shubangi_barua, email_shubangi);
        addMember(devian, team_web_development, post_co_coordinator, drawable.devian, email_devian);
        addMember(prince, team_web_development, post_co_coordinator, drawable.prince, email_prince);
        addMember(akash, team_app_development, post_co_coordinator, drawable.akash, email_akash);
        addMember(mrityunjay, team_app_development, post_co_coordinator, drawable.mritunjay, email_mrityunjay);
        addMember(michelle, team_hospitality, post_co_coordinator, drawable.michelle, email_michelle);
        addMember(winnie, team_hospitality, post_co_coordinator, drawable.winnie, email_winnie);
        addMember(tapsri, team_hospitality, post_co_coordinator, drawable.tapsri, email_tapsri);
        addMember(priyanchi, team_hospitality, post_co_coordinator, drawable.priyanchi, email_priyanchi);
        addMember(ilasukri, team_hospitality, post_co_coordinator, drawable.ilasukri, email_ilasukri);
        addMember(jaswant, team_stage_management_and_printing, post_co_coordinator, drawable.jaswant, email_jaswant);
        addMember(pardip, team_stage_management_and_printing, post_co_coordinator, drawable.pardip, email_pardip);
        addMember(serene, team_stage_management_and_printing, post_co_coordinator, drawable.serene, email_serene);
        addMember(arwatki,team_stage_management_and_printing,post_co_coordinator,drawable.arwatki, email_arwatki);
        addMember(pynkitbor, team_general_quiz_debate, post_co_coordinator, drawable.pynkitbor, email_pynkitbor);
        createTeamsArrayLists();
        addTeamCategoriesArraysToMap();

    }
}
