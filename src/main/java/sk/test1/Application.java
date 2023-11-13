package sk.test1;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args){
        Application application = new Application();
        application.print("./products.json");
    }
}
