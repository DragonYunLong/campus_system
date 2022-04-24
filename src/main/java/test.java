import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URL;

public class test {
    public static void main(String[] args) {
        String province = getProvince("114.21", "31.21");
        System.out.println(province);
    }

    public static String getProvince(String log, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        JSONArray jsonArray = JSON.parseArray(jsonObject.getString("addrList"));
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        String arr[] = jsonObject1.get("admName").toString().split(",");
        System.out.println(arr[0]);
        return arr[0];
    }
}
