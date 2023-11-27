package data_access;

import entity.CommonUser;
import entity.User;
import entity.UserFactory;
import use_case.changeUsername.ChangeUsernameDataAccessInterface;
import use_case.forgot.ForgotUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signUp.SignUpUserDataAccessInterface;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignUpUserDataAccessInterface, LoginUserDataAccessInterface,
        ForgotUserDataAccessInterface,
        ChangeUsernameDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, CommonUser> accounts = new HashMap<>();



    public FileUserDataAccessObject(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    CommonUser user = new CommonUser(username, password);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public CommonUser get(String username) {
        return accounts.get(username);
    }

    @Override
    public void save(CommonUser user) {
        accounts.put(user.getUserName(),user);
        save();

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (CommonUser user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getUserName(), user.getAccountPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }



}
