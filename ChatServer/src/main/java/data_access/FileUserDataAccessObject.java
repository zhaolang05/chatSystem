package data_access;


import comm.entity.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();


    public FileUserDataAccessObject(String csvPath) throws IOException {
        ClassLoader classLoader = FileUserDataAccessObject.class.getClassLoader();
        String resourcePath = "f:\\";
        csvFile = new File(resourcePath + csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("profile", 2);
        headers.put("creation_time", 3);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time,profile");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String profile = String.valueOf(col[headers.get("profile")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = new User(username, password,profile, ldt);
                    accounts.put(username, user);
                }
            }
        }
    }


    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }


    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getName(), user.getPassword(),user.getProfile(),user.getCreationTime());
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
     *
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */

    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }


    public String clearUsers() {
        int count = accounts.size();
        if (count == 0) {
            return "";
        }
        String message = "";
        Iterator<Map.Entry<String, User>> iterator = accounts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, User> entry = iterator.next();
            message = message + entry.getKey() + "\n";
            iterator.remove();
        }
        this.save();

        return message;
    }
}
