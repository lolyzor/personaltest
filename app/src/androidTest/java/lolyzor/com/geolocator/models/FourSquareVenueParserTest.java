package lolyzor.com.geolocator.models;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import lolyzor.com.geolocator.models.core.FoursquareLocation;
import lolyzor.com.geolocator.models.core.MockClass;

public class FourSquareVenueParserTest extends TestCase {

    public void testGetParsedLocations() throws Exception {
        FourSquareVenueParser fourSquareVenueParser = new FourSquareVenueParser(MockClass.fourSquareMockResposne);
        List<FoursquareLocation> locations = fourSquareVenueParser.getParsedLocations();
        assertEquals(146,fourSquareVenueParser.getParsedLocations().get(0).getLocation().getDistance());
        assertEquals(30,fourSquareVenueParser.getParsedLocations().size());
    }

    private Object getObjectFromJson(Class c) throws IllegalAccessException, ClassNotFoundException {
        try {
            JSONObject jsonObject = new JSONObject("{\n" +
                    "    \"test\": 123,\n" +
                    "    \"tet2\": \"string\",\n" +
                    "    \"test3\": {\n" +
                    "        \"boolean1\": true\n" +
                    "    }\n" +
                    "}");

            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                System.out.println("next = " + next);
                if(jsonObject.get(next) instanceof Integer){
                    int num = jsonObject.getInt(next);
                    System.out.println("num = " + num);
                }
                if(jsonObject.get(next) instanceof String){
                    String num = jsonObject.getString(next);
                    System.out.println("num = " + num);
                }
                if(jsonObject.get(next) instanceof Boolean){
                    boolean num = jsonObject.getBoolean(next);
                    System.out.println("num = " + num);
                }
                if(jsonObject.get(next) instanceof JSONObject){
                    JSONObject jsonObjectNested = jsonObject.getJSONObject(next);
                    final Package[] packages = Package.getPackages();
                    Class claz = Class.forName("lolyzor.com.geolocator.models.FourSquareVenueParserTest$test3");
                    Method[] methods = claz.getMethods();
                    HashMap<String, Method> fieldHashMap = new HashMap<>();
                    for (int i = 0; i < methods.length; i++) {
                        Method method = methods[i];
                        fieldHashMap.put(method.getName(), method);
                    }
                    Iterator<String> keyes2 = jsonObjectNested.keys();
                    while (keyes2.hasNext()) {
                        String s = keyes2.next();
                        if(fieldHashMap.get("set"+s.substring(0,1).toUpperCase()+s.substring(1)) != null){
                            Method method = fieldHashMap.get("set"+s.substring(0,1).toUpperCase()+s.substring(1));
                            Object objectj = claz.newInstance();
                            Object booleanObject = jsonObjectNested.get(s);
                            method.invoke(objectj,booleanObject);
                            System.out.println("objectj = " + objectj);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class test3{
        private boolean boolean1;

        public test3() {
        }

        public boolean isBoolean1() {
            return boolean1;
        }

        public void setBoolean1(boolean boolean1) {
            this.boolean1 = boolean1;
        }
    }
}