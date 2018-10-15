package in.cognitia.cognitia18.Util;

import android.content.Context;
import android.content.res.Resources;

import java.util.HashMap;

import static in.cognitia.cognitia18.R.*;

import static in.cognitia.cognitia18.R.string.*;


/**
 * Created by devansh on 7/10/18.
 */

public class EventsImagesAssociator {

    public static Context context;

    public static String GOAL_AGAINST_TIME;
    public static String LINE_FOLLOWER_ROBOT;
    public static String PRISON_BREAKOUT;
    public static String ROBO_RUMBLE;
    public static String ROBODICTION;
    public static String ROBO_BRIDGE;
    public static String RACE_AGAINST_TIME;

    private static HashMap<String, Integer> imageMap = new HashMap<>();

    public static HashMap<String, Integer> getImageMap() {
        return imageMap;
    }

    public static int getEventImageId(String eventName) {
        if (imageMap.get(eventName) != null)
            return imageMap.get(eventName);
        return 0;
    }

    private static void initializeEventNames() {

        Resources res = context.getResources();

        GOAL_AGAINST_TIME = res.getString(team_goal_against_time);
        LINE_FOLLOWER_ROBOT = res.getString(team_line_follower_robot);
        PRISON_BREAKOUT = res.getString(team_prison_breakout);
        ROBO_RUMBLE = res.getString(team_robo_rumble);
        ROBODICTION = res.getString(team_robodiction);
        ROBO_BRIDGE = res.getString(team_robo_bridge);
        RACE_AGAINST_TIME = res.getString(team_race_against_time);
    }

    public static void initializeImageMap() {

        initializeEventNames();

        imageMap.put(LINE_FOLLOWER_ROBOT, drawable.line_follower_robot);
        imageMap.put(GOAL_AGAINST_TIME, drawable.goal_against_time);
        imageMap.put(PRISON_BREAKOUT, drawable.prison_breakout);
        imageMap.put(ROBO_RUMBLE, drawable.robo_rumble);
        imageMap.put(ROBODICTION, drawable.robodiction);
        imageMap.put(ROBO_BRIDGE, drawable.robobridge);
        imageMap.put(RACE_AGAINST_TIME, drawable.race_against_time);

        imageMap.put("Palace of Cards", drawable.palace_of_cards);
        imageMap.put("Android Bridge Constructor Simulator", drawable.android_bridge_simulator);
        imageMap.put("Autocad Drafting", drawable.autocad_drafting);
        imageMap.put("Bascule Bridge", drawable.bascule_bridge);
        imageMap.put("Cantilever Design", drawable.cantilever_design);

        imageMap.put("Beat the Q", drawable.beat_the_q);
        imageMap.put("Bicode", drawable.bicode);
        imageMap.put("Crack it", drawable.crack_it);
        imageMap.put("Megabyte", drawable.megabyte);

        imageMap.put("Electromania", drawable.electromania);
        imageMap.put("Fast and Furious", drawable.fast_and_furious);
        imageMap.put("Inventolution", drawable.inventolution);

        imageMap.put("Not So Fast", drawable.not_so_fast);
        imageMap.put("The Ultimate Swot", drawable.the_ultimate_swot);
        imageMap.put("Tricky Circuits", drawable.tricky_circuits);

        imageMap.put("Assembler", drawable.assembler);
        imageMap.put("Inertia", drawable.inertia);
        imageMap.put("Load Upholder", drawable.load_upholder);

        imageMap.put("Counter Strike: Global Offensive", drawable.csgo);
        imageMap.put("DOTA 2", drawable.dota);
        imageMap.put("FIFA 18", drawable.fifa);
        imageMap.put("Need For Speed: Most Wanted", drawable.nfsmw);
    }




}
