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

    private static HashMap<String, HashMap<String, CognitiaTeamMember>> teamMembersByCategories = new HashMap<>();

    private static HashMap<String , CognitiaTeamMember> teamMembers_departmental = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_designing = new HashMap<>();
    private static HashMap<String , CognitiaTeamMember> teamMembers_disciplinary = new HashMap<>();
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

    public static ArrayList<CognitiaTeamMember> getTeamMembers() {
        return teamMembersArrayList;
    }

    public static HashMap<String , CognitiaTeamMember> getMembersMap() {
        return teamMembers;
    }

    public static CognitiaTeamMember getMemberByName(String name) {
        return teamMembers.get(name);
    }

    private static void mapToArrayList() {
        //Getting collection of values from HashMap
        Collection<CognitiaTeamMember> values = teamMembers.values();
        //Creating an ArrayList of values
        teamMembersArrayList = new ArrayList<>(values);
    }

    private static void addTeamCategoriesToMap() {
        teamMembersByCategories.put(TeamGalleryActivity.DEPARTMENTAL, teamMembers_departmental);
        teamMembersByCategories.put(TeamGalleryActivity.DESIGNING, teamMembers_designing);
        teamMembersByCategories.put(TeamGalleryActivity.ECELL, teamMembers_ecell);
        teamMembersByCategories.put(TeamGalleryActivity.EVENT_MAMANGEMET, teamMembers_event_management);
        teamMembersByCategories.put(TeamGalleryActivity.FUN_EVENTS, teamMembers_fun_events);
        teamMembersByCategories.put(TeamGalleryActivity.GAMING, teamMembers_gaming);
        teamMembersByCategories.put(TeamGalleryActivity.HOSPITALITY, teamMembers_hospitality);
        teamMembersByCategories.put(TeamGalleryActivity.PUBLICITY, teamMembers_publicity);
        teamMembersByCategories.put(TeamGalleryActivity.QUIZ_DEBATE, teamMembers_quiz_debate);
        teamMembersByCategories.put(TeamGalleryActivity.SECRETARIES_MEMBERS, teamMembers_secretaries);
        teamMembersByCategories.put(TeamGalleryActivity.SHIMMER_ARPEGGIO, teamMembers_shimmer);
        teamMembersByCategories.put(TeamGalleryActivity.STAGE_MANAGEMENT, teamMembers_stage_management);
        teamMembersByCategories.put(TeamGalleryActivity.TECHNICAL, teamMembers_technical);
        teamMembersByCategories.put(TeamGalleryActivity.WEB_DEVELOPMENT, teamMembers_web_development);
        teamMembersByCategories.put(TeamGalleryActivity.APP_DEVELOPMENT, teamMembers_app_development);
    }

    private static void addMember(int nameId, int teamId, int postId, int imageId, int emailIdRes) {
        CognitiaTeamMember member = new CognitiaTeamMember(nameId, teamId, postId, imageId, emailIdRes);
        teamMembers.put(member.getName(), member);

        switch (teamId) {
            case team_civil_departmental:
            case team_cse_departmental:
            case team_ece_departmental:
            case team_ee_departmental:
            case team_me_departmental:
                teamMembers_departmental.put(member.getName(), member);
                break;
            case team_designing:
                teamMembers_designing.put(member.getName(), member);
                break;
            case team_disciplinary:
                teamMembers_disciplinary.put(member.getName(), member);
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
            case team_technical:
                teamMembers_technical.put(member.getName(), member);
                break;
            case team_web_development:
                teamMembers_web_development.put(member.getName(), member);
                break;
            case team_app_development:
                teamMembers_app_development.put(member.getName(), member);
                break;
        }
    }

    public static void addTeamMembers() {

        addTeamCategoriesToMap();

        addMember(karan, team_event_management, post_head, drawable.s_s_sri_karan, email_karan);
        addMember(gnaneshwar, team_event_management, post_coordinator, drawable.gnaneshwar, email_gnaneshwar);
        addMember(kethiri, team_event_management, post_coordinator, drawable.narshimha, email_kethri);
        addMember(shubham_kumar_singh, team_event_management, post_coordinator, drawable.shubham_kumar_singh, email_shubham_kumar_singh);
        addMember(rafad, team_event_management, post_co_coordinator, drawable.rafad, email_rafad);
        addMember(saurabh_singh, team_event_management, post_co_coordinator, drawable.sourabh, email_saurabh_singh);
        addMember(koustubh_kishore, team_event_management, post_co_coordinator, drawable.koustubh_kishore, email_koustubh_kishore);

        addMember(shivam_prasad, team_cse_departmental, post_head, drawable.shivam_prasad, email_shivam_prasad);
        addMember(ketan, team_cse_departmental, post_head, drawable.ketan_anand, email_laribok);
        addMember(ashutosh, team_cse_departmental, post_coordinator, drawable.ashutosh, email_ketan);
        addMember(alphin, team_cse_departmental, post_coordinator, drawable.alphin, email_alphin);
        addMember(allu, team_cse_departmental, post_co_coordinator, drawable.allu, email_allu);
        addMember(chandan, team_cse_departmental, post_co_coordinator, drawable.chandan, email_chandan);

        addMember(shivam_kumar, team_civil_departmental, post_head, drawable.shivam_kumar, email_shivam_kumar);
        addMember(sunil, team_civil_departmental, post_head, drawable.sunil, email_sunil);
        addMember(lalit, team_civil_departmental, post_coordinator, drawable.lalit, email_lalit);
        addMember(jakish, team_civil_departmental, post_coordinator, drawable.jakish, email_jakish);
        addMember(shashank, team_civil_departmental, post_co_coordinator, drawable.shashank, email_shashank);
        addMember(sudarsan, team_civil_departmental, post_co_coordinator, drawable.sudarsan, email_sudarsan);
        addMember(sweeta, team_civil_departmental, post_co_coordinator, drawable.sweeta, email_sweeta);

        addMember(parakhee, team_ece_departmental, post_head, drawable.parakhee, email_parakhee);
        addMember(rohit_kumar, team_ece_departmental, post_head, drawable.rohit_kumar, email_rohit_kumar);
        addMember(rajoo, team_ece_departmental, post_coordinator, drawable.rajoo, email_rajoo);
        addMember(rohit_raj, team_ece_departmental, post_co_coordinator, drawable.rohit_raj, email_rohit_raj);
        addMember(sourav_paul, team_ece_departmental, post_co_coordinator, drawable.sourav_paul, email_sourav_paul);
        addMember(wanchi, team_ece_departmental, post_co_coordinator, drawable.wanchi, email_wanchi);

        addMember(nitin, team_ee_departmental, post_head, drawable.nitin, email_nitin);
        addMember(pankaj, team_ee_departmental, post_coordinator, drawable.pankaj, email_pankaj);
        addMember(manish_kumar, team_ee_departmental, post_coordinator, drawable.manish, email_manish_kumar);
        addMember(teiboklang, team_ee_departmental, post_coordinator, drawable.teibok, email_teiboklang);
        addMember(naman, team_ee_departmental, post_co_coordinator, drawable.naman, email_naman);
        addMember(nirosha, team_ee_departmental, post_co_coordinator, drawable.nirosha, email_nirosha);
        addMember(rishabh, team_ee_departmental, post_co_coordinator, drawable.rishabh, email_rishabh);

        addMember(teiborlin, team_me_departmental, post_head, drawable.teiborlin, email_teiborlin);
        addMember(kishan_das, team_me_departmental, post_coordinator, drawable.kishan_das, email_kishan_das);
        addMember(baiateilang, team_me_departmental, post_coordinator, drawable.baiateilang, email_baiateilang);
        addMember(kapil, team_me_departmental, post_co_coordinator, drawable.kapil, email_kapil);
        addMember(shivam_sharma, team_me_departmental, post_co_coordinator, drawable.shivam_sharma, email_shivam_sharma);
        addMember(vivek, team_me_departmental, post_co_coordinator, drawable.vivek, email_vivek);
        addMember(sushant, team_me_departmental, post_co_coordinator, drawable.sushant, email_sushant);

        addMember(manikanta, team_race_against_time, post_head, drawable.manikanta, email_manikanta);
        addMember(amit_kumar_lal, team_race_against_time, post_coordinator, drawable.amit, email_amit_kumar_lal);
        addMember(kamarapu, team_race_against_time, post_coordinator, drawable.shiva_rat, email_kamarapu);
        addMember(divyesh, team_race_against_time, post_co_coordinator, drawable.divyesh, email_divyesh);
        addMember(sidharth, team_race_against_time, post_co_coordinator, drawable.sidharth, email_sidharth);

        addMember(ajoy, team_goal_against_time, post_head, drawable.ajoy, email_ajoy);
        addMember(mukesh, team_goal_against_time, post_coordinator, drawable.mukesh, email_mukesh);
        addMember(priya, team_goal_against_time, post_coordinator, drawable.priya, email_priya);
        addMember(sakshay, team_goal_against_time, post_co_coordinator, drawable.sakshay, email_sakshay);
        addMember(heplang, team_goal_against_time, post_co_coordinator, drawable.heplang, email_heplang);

        addMember(m_raj_kumar, team_robodiction, post_head, drawable.raj_kumar, email_m_raj_kumar);
        addMember(nitish, team_robodiction, post_head, drawable.nitish, email_nitish);
        addMember(viswa_teja, team_robodiction, post_coordinator, drawable.viswa, email_viswa_teja);
        addMember(harish, team_robodiction, post_coordinator, drawable.harish, email_harish);
        addMember(sai_lohit, team_robodiction, post_co_coordinator, drawable.lohit, email_sai_lohit);
        addMember(mummidi, team_robodiction, post_co_coordinator, drawable.mummidi_bharadwaj, email_mummidi);
        addMember(emmanuel, team_robodiction, post_co_coordinator, drawable.emmanuel, email_emmanuel);

        addMember(satyaveer, team_prison_breakout, post_head, drawable.satyaveer, email_satyaveer);
        addMember(anant_kumar, team_prison_breakout, post_head, drawable.john, email_anant_kumar);
        addMember(manish, team_prison_breakout, post_coordinator, drawable.manish_, email_manish);
        addMember(abhushan, team_prison_breakout, post_coordinator, drawable.abhushan, email_abhushan);
        addMember(uchit, team_prison_breakout, post_coordinator, drawable.uchit, email_uchit);
        addMember(wanchi, team_prison_breakout, post_co_coordinator, drawable.wanchi, email_wanchi);

        addMember(anurag, team_line_follower_robot, post_head, drawable.anurag, email_anurag);
        addMember(aniruddha, team_line_follower_robot, post_coordinator, drawable.aniruddha, email_aniruddha);
        addMember(mohan, team_line_follower_robot, post_coordinator, drawable.mohan, email_mohan);
        addMember(ujjwal, team_line_follower_robot, post_co_coordinator, drawable.ujjwal, email_ujjwal);        
        addMember(kishan_lal, team_line_follower_robot, post_co_coordinator, drawable.kishan_lal, email_kishan_lal);

        addMember(skhemboklang, team_robo_rumble, post_head, drawable.skhemboklang, email_skhemboklang);
        addMember(dennybert, team_robo_rumble, post_head, drawable.dennybert, email_dennybert);
        addMember(abhay, team_robo_rumble, post_coordinator, drawable.abhay, email_abhay);
        addMember(kishan_chaurasia, team_robo_rumble, post_coordinator, drawable.kishan_chaurasia, email_kishan_chaurasia);
        addMember(shubham_chang, team_robo_rumble, post_co_coordinator, drawable.shubham_chang, email_shubham_chang);
        addMember(saurav_dayal, team_robo_rumble, post_co_coordinator, drawable.saurav_dayal, email_saurav_dayal);

        addMember(ranjan_kumar, team_robo_bridge, post_head, drawable.ranjan_kumar, email_ranjan_kumar);
        addMember(raman, team_robo_bridge, post_head, drawable.raman_gupta, email_raman);
        addMember(deepak_kumar_sah, team_robo_bridge, post_coordinator, drawable.deepak_kumar_sah, email_deepak_kumar_sah);
        addMember(devashish, team_robo_bridge, post_coordinator, drawable.devashish_dubey, email_devashish);
        addMember(sanjay_kumar_kol, team_robo_bridge, post_coordinator, drawable.sanjay_kumar_kol, email_sanjay_kumar_kol);
        addMember(astha, team_robo_bridge, post_co_coordinator, drawable.astha_awasthi, email_astha);
        addMember(mithun, team_robo_bridge, post_co_coordinator, drawable.mithun_kumar, email_mithun);

        addMember(gyanishwar, team_gaming, post_head, drawable.gyaniswar, email_gyanishwar);
        addMember(jeny_welkin, team_gaming, post_head, drawable.jeny, email_jeny_welkin);
        addMember(balmhashwa, team_gaming, post_coordinator, drawable.balamhashwa, email_balmhashwa);
        addMember(hamewot, team_gaming, post_coordinator, drawable.hamewot, email_hamewot);
        addMember(jeush, team_gaming, post_co_coordinator, drawable.jeush, email_jeush);

        addMember(jayanth_reddy, team_arppegio_and_shimmer, post_head, drawable.jayanth, email_jayanth_reddy);
        addMember(annamaiah, team_arppegio_and_shimmer, post_head, drawable.annamaiah, email_annamaiah);
        addMember(debapratim, team_arppegio_and_shimmer, post_head, drawable.debapratim, email_debapratim);
        addMember(jugami, team_arppegio_and_shimmer, post_coordinator, drawable.jugami, email_jugami);
        addMember(khushi, team_arppegio_and_shimmer, post_coordinator, drawable.khushi_mishra, email_khushi);
        addMember(charan_reddy, team_arppegio_and_shimmer, post_coordinator, drawable.charan_reddy, email_charan_reddy);
        addMember(ananya_giri, team_arppegio_and_shimmer, post_coordinator, drawable.ananya_giri, email_ananya_giri);
        addMember(imdad, team_arppegio_and_shimmer, post_co_coordinator, drawable.imdadul, email_imdad);
        addMember(hemant_sabbavarapu, team_arppegio_and_shimmer, post_co_coordinator, drawable.hemanth_kumar, email_hemant_sabbavarapu);
        addMember(anamika, team_arppegio_and_shimmer, post_co_coordinator, drawable.anamika, email_anamika);

        addMember(pynsuklang, team_publicity, post_head, drawable.pynsuk, email_pynsuklang);
        addMember(evanstarfield, team_publicity, post_head, drawable.evanstar, email_evanstarfield);
        addMember(randolph, team_publicity, post_coordinator, drawable.randolph, email_randolph);
        addMember(rafaela, team_publicity, post_coordinator, drawable.rafaela, email_rafaela);
        addMember(rasalinda, team_publicity, post_coordinator, drawable.rasalinda, email_rasalinda);
        addMember(ellyn, team_publicity, post_coordinator, drawable.ellyn, email_ellyn);
        addMember(hanna, team_publicity, post_co_coordinator, drawable.hanna, email_hanna);
        addMember(barishisha, team_publicity, post_co_coordinator, drawable.barishisha, email_barishisha);
        addMember(leonard, team_publicity, post_co_coordinator, drawable.leonard, email_leonard);
        addMember(lakyntiew, team_publicity, post_co_coordinator, drawable.lakyntiew, email_lakyntiew);
        addMember(eugynea, team_publicity, post_co_coordinator, drawable.eugynea, email_eugynea);
        addMember(josekielnezer, team_publicity, post_co_coordinator, drawable.josekielnezer, email_josekielnezer);

        addMember(ananya_bhattacharya, team_designing, post_head, drawable.ananya, email_ananya_bhattacharya);
        addMember(shiva_teja, team_designing, post_head, drawable.shiva, email_shiva_teja);
        addMember(sonalika, team_designing, post_head, drawable.sonalika, email_sonalika);
        addMember(satya_abhinay, team_designing, post_coordinator, drawable.bollampalli_satya_abhinay, email_satya_abhinay);
        addMember(tirupathi_rao, team_designing, post_coordinator, drawable.kummari_tirupathi_rao, email_tirupathi_rao);
        addMember(kishore_chary, team_designing, post_coordinator, drawable.v_sai_kishore_chary, email_kishore_chary);
        addMember(chinmoy, team_designing, post_co_coordinator, drawable.chinmoy_deka, email_chinmoy);
        addMember(sagar_das, team_designing, post_co_coordinator, drawable.sagar_das, email_sagar_das);
        addMember(sanapala_rajesh, team_designing, post_co_coordinator, drawable.sanapala_rajesh, email_sanapala_rajesh);
        addMember(shubangi, team_designing, post_co_coordinator, drawable.shubangi_barua, email_shubangi);

        addMember(jibesh, team_web_development, post_head, drawable.jibesh, email_jibesh);
        addMember(wiwat, team_web_development,post_coordinator,drawable.wiwatdaka, email_wiwat);
        addMember(tanuj, team_web_development, post_coordinator, drawable.tanuj, email_tanuj);
        addMember(mahfooz, team_web_development, post_coordinator, drawable.mahfooz, email_mahfooz);
        addMember(devian, team_web_development, post_co_coordinator, drawable.devian, email_devian);
        addMember(prince, team_web_development, post_co_coordinator, drawable.prince, email_prince);

        addMember(devansh, team_app_development, post_coordinator, drawable.devansh, email_devansh);
        addMember(laribok, team_app_development, post_coordinator, drawable.laribok, email_laribok);
        addMember(amrit, team_app_development, post_coordinator, drawable.amrit, email_amrit);
        addMember(akash, team_app_development, post_co_coordinator, drawable.akash, email_akash);
        addMember(mrityunjay, team_app_development, post_co_coordinator, drawable.mritunjay, email_mrityunjay);

        addMember(chiron, team_hospitality, post_head, drawable.chiron, email_chiron);
        addMember(jubabmilekini, team_hospitality, post_head, drawable.jubab, email_jubabmilekini);
        addMember(elvarie, team_hospitality, post_coordinator, drawable.elva, email_elvarie);
        addMember(dianglinshisha, team_hospitality, post_coordinator, drawable.diang, email_dianglinshisha);
        addMember(donna, team_hospitality, post_coordinator,drawable.donna, email_donna);
        addMember(michelle, team_hospitality, post_co_coordinator, drawable.michelle, email_michelle);
        addMember(winnie, team_hospitality, post_co_coordinator, drawable.winnie, email_winnie);
        addMember(tapsri, team_hospitality, post_co_coordinator, drawable.tapsri, email_tapsri);
        addMember(priyanchi, team_hospitality, post_co_coordinator, drawable.priyanchi, email_priyanchi);
        addMember(ilasukri, team_hospitality, post_co_coordinator, drawable.ilasukri, email_ilasukri);

        addMember(nehemiah, team_photo_walk, post_head, drawable.nehemiah, email_nehemiah);
        addMember(kynmawlang, team_photo_walk, post_head, drawable.kynmawlang, email_kynmawlang);

        addMember(damanbha, team_disciplinary, post_head, drawable.damanbha, email_damanbha);

        addMember(deeksha, team_stage_management_and_printing, post_head, drawable.deeksha, email_deeksha);
        addMember(nathaneal, team_stage_management_and_printing, post_head, drawable.nathaneal, email_nathaneal);
        addMember(surabhi, team_stage_management_and_printing, post_coordinator, drawable.surabhi, email_surabhi);
        addMember(koustubh_deshpande, team_stage_management_and_printing, post_coordinator, drawable.koustubh_deshpande, email_koustubh_deshpande);
        addMember(jaswant, team_stage_management_and_printing, post_co_coordinator, drawable.jaswant, email_jaswant);
        addMember(pardip, team_stage_management_and_printing, post_co_coordinator, drawable.pardip, email_pardip);
        addMember(serene, team_stage_management_and_printing, post_co_coordinator, drawable.serene, email_serene);
        addMember(arwatki,team_stage_management_and_printing,post_co_coordinator,drawable.arwatki, email_arwatki);

        addMember(francis, team_general_quiz_debate, post_head, drawable.francis, email_francis);
        addMember(karasaphi, team_general_quiz_debate, post_coordinator, drawable.karasaphi, email_karasaphi);
        addMember(johnyborn, team_general_quiz_debate, post_coordinator, drawable.johnyborn, email_johnyborn);
        addMember(pynbhalang, team_general_quiz_debate, post_coordinator, drawable.pynbhalang, email_pynbhalang);
        addMember(pynkitbor, team_general_quiz_debate, post_co_coordinator, drawable.pynkitbor, email_pynkitbor);

        addMember(diswel, team_fun_events, post_head, drawable.diswel, email_diswel);
        addMember(arminias, team_fun_events, post_head, drawable.arminias, email_arminias);
        addMember(shilling, team_fun_events, post_head, drawable.shilling, email_shilling);

        addMember(shemphang, secretary_and_members, general_secretary, drawable.shemphang, email_shemphang);
        addMember(leon, secretary_and_members, general_secretary, drawable.leon, email_leon);
        addMember(khrawboklang, secretary_and_members, general_secretary_member, drawable.khrawboklang, email_khrawboklang);

        mapToArrayList();
    }
}
