package data_access;


import comm.entity.User;

import java.io.*;

import java.util.*;


public class FileUserDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();



    public FileUserDataAccessObject() throws IOException {
        this(null);
    }

    public FileUserDataAccessObject(String csvPath) throws IOException {
        ClassLoader classLoader = FileUserDataAccessObject.class.getClassLoader();
        String resourcePath = "d:\\";
        csvFile = new File(resourcePath + csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("profile", 2);
        headers.put("creation_time", 3);
        headers.put("personalized_sign", 4);
        headers.put("avatar", 5);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,profile,creation_time,personalized_sign,avatar");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split("#");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String profile = String.valueOf(col[headers.get("profile")]);
                    String personalizedSign = String.valueOf(col[headers.get("personalized_sign")]);
                    String avatar = String.valueOf(col[headers.get("avatar")]);
                    User user = new User(username, password, profile, creationTimeText, personalizedSign, avatar);
                    accounts.put(username, user);
                }
            }
        }
    }


    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    public List<User> getUsers()
    {
        List<User> userList=new ArrayList<>();
        Iterator<Map.Entry<String, User>> iterator = accounts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, User> entry = iterator.next();
            userList.add(entry.getValue());

        }
        return userList;
    }
    public void updateOnline(User user) {
        accounts.put(user.getName(), user);
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
                String line = String.format("%s#%s#%s#%s#%s#%s",
                        user.getName(), user.getPassword(), user.getProfile(), user.getCreationTime(), user.getPersonalizedSign(), user.getAvatar());
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
