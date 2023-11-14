package sk.test1;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {

    public void print(String file){
        JSONObject jsonObject = getJsonObject(file);
        if(jsonObject != null){
            printJsonRecursive(jsonObject, 0);
        }
    }

    private JSONObject getJsonObject(String file){
        try {
            FileReader fileReader = new FileReader(file);
            return (JSONObject) new JSONParser().parse(fileReader);
        }catch(FileNotFoundException ex){
            System.out.println("File not found.");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }catch(ParseException ex){
            System.out.println("Failed to parse the json file.");
        }
        return null;
    }

    // Recursively iterate through JsonObject
    private void printJsonRecursive(JSONObject json, int level){
        for(Object key: json.keySet()){
            printDots(level);
            System.out.println(key.toString());
            Object o = json.get(key);

            // Continue recursion if there is another layer
            if(o instanceof JSONObject){
                printJsonRecursive((JSONObject) o, level+1);
            }
        }
    }

    private void printDots(int num){
        for (int i = 0; i < num; i++){
            System.out.print(". . ");
        }
    }

    public void findMax(String path) {
        JSONObject jsonObject = getJsonObject(path);
        if(jsonObject != null){
            MaxData maxData = findMaxRecursive(jsonObject, new ArrayList<>());
            System.out.println(maxData.toString());
        }
    }

    public MaxData findMaxRecursive(JSONObject jsonObject, List<String> path){
        int max = Integer.MIN_VALUE;
        MaxData maxData = null;

        for(Object key: jsonObject.keySet()){
            Object o = jsonObject.get(key);
            path.add((String) key);

            // Continue recursion if there is another level
            if(o instanceof JSONObject){
                // get max of the next layer
                MaxData nextLayer = findMaxRecursive((JSONObject) o, new ArrayList<>(path));
                if(nextLayer.val > max){
                    max = nextLayer.val;
                    maxData = nextLayer;
                }

                // Last level of json
            }else{
                if(((Long) o).intValue() > max){
                    maxData = new MaxData(new ArrayList<>(path), ((Long) o).intValue());
                }
            }
            path.remove(path.size()-1);
        }
        return maxData;
    }

    public static void main(String[] args){
        Application application = new Application();
        if(Objects.equals(args[0], "print")){
            application.print("./products.json");
        }else if(Objects.equals(args[0], "findMax")){
            application.findMax("./products.json");
        }
    }
}
