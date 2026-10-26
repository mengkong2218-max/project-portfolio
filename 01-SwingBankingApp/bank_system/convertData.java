package bank_system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class convertData {
    private static final String FILE_NAME = "bank_system/Data/bankData.json";

    public static void saveAccounts(ArrayList<Account> accounts) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static ArrayList<Account> loadAccounts() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type accountListType = new TypeToken<ArrayList<Account>>() {
            }.getType();
            ArrayList<Account> loadedAccounts = gson.fromJson(reader, accountListType);

            if (loadedAccounts != null) {
                return loadedAccounts;
            }
        } catch (IOException e) {
            System.out.println("File Not Found.");
        }
        return new ArrayList<>();
    }
}
