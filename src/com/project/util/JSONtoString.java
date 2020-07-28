package com.project.util;

import com.project.dao.GeoCitiesDao;
import com.project.dao.impl.GeoCitiesDaoImpl;
import com.project.pojo.TravelImage;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.ArrayList;
import java.util.List;

public class JSONtoString {
    public static void main(String[] args) {
        GeoCitiesDao geoCitiesDao= new GeoCitiesDaoImpl();
        String iso = "CN";
        JSONArray jsonArray = JSONArray.fromObject(geoCitiesDao.getCitiesByCountryISO(iso));

    }
    public static String toString(JSONArray objects){
        JSONObject json = JSONObject.fromObject(objects);
        JSONArray array=JSONArray.fromObject(objects);
        String strJson=json.toString();
        String strArray=array.toString();
        System.out.println(strArray);
        System.out.println(strJson);
        return null;
    }
}
