package com.example.WoTStat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    public List<Stat> JsonToStat (String jsonstring) {

        List<Stat> stats = new ArrayList<Stat>();
        try {

            JSONObject jsonObject = new JSONObject(jsonstring);
            JSONArray resultsObject = jsonObject.getJSONArray("data");
            for (int i =0, j = resultsObject.length();i<j;i++){
                JSONObject resultObject = resultsObject.getJSONObject(i);
                Stat stat = new Stat();
                stat.setName(resultObject.getString("nickname"));
                stat.setGamesPlayed(resultObject.getString("account_id"));
                stats.add(stat);

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return stats;   }

}

