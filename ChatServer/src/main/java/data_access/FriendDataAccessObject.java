package data_access;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import comm.entity.Friend;
import comm.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
@Slf4j
public class FriendDataAccessObject {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Friend> allFriends = new HashMap<>();


    public FriendDataAccessObject(String csvPath) throws IOException {
        ClassLoader classLoader = FriendDataAccessObject.class.getClassLoader();
        String resourcePath = "d:\\";
        csvFile = new File(resourcePath + csvPath);
        headers.put("name", 0);
        headers.put("friend", 1);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("name,friend");
                String row;
                Gson gson=new Gson();
                while ((row = reader.readLine()) != null) {
                    try {
                        String[] col = row.split("#");
                        String username = String.valueOf(col[headers.get("name")]);
                        String f=String.valueOf(col[headers.get("friend")]);
                        Map<String, List<String>> friendobj = gson.fromJson(f, new TypeToken<Map<String, List<String>>>(){}.getType());
                        Map<String, List<String>> friendList = new HashMap<>();
                        for (Map.Entry<String, List<String>> entry : friendobj.entrySet()) {
                            String group = entry.getKey();
                            friendList.put(group, entry.getValue());
                        }
                        Friend friend = new Friend(username, friendList);
                        allFriends.put(username, friend);
                    }catch(Exception ex)
                    {
                        log.info("current row:"+row);
                        log.error("import friend error",ex);
                    }
                }
            }
        }
    }


    public void save(Friend friend) {
        allFriends.put(friend.getName(), friend);
        this.save();
    }


    public Friend get(String username) {
        return allFriends.get(username);
    }
    public  void addFriend(String userName,User user)
    {
         addFriend(userName,"My Friends",user);
    }
    public  void addFriend(String userName,String group,User user)
    {
        Friend friend=this.get(userName);
        boolean isnewrow=false;
        if (friend==null)
        {
            Map<String,List<String>> friendsname=new HashMap<>();
            List<String> names=new ArrayList<>();
            names.add(user.getName());
            friendsname.put(group,names);
            friend=new Friend(userName,friendsname);
            List<User> userList=new ArrayList<>();
            userList.add(user);
            friend.getFriendsGroup().put(userName,userList);
            allFriends.put(userName,friend);
        }
        else
        {
            if (friend.getFriendsNameGroup().containsKey(group))
            {
                List<String> friendNames=friend.getFriendsNameGroup().get(group);
                if (!friendNames.contains(user.getName()))
                {
                    friendNames.add(user.getName());
                    friend.getFriendsNameGroup().put(group,friendNames);
                    List<User> friendList=friend.getFriendsGroup().get(group);
                    friendList.add(user);
                    friend.getFriendsGroup().put(group,friendList);
                }
            }
            else
            {
                List<String>  friendNames=new ArrayList<>();
                friendNames.add(user.getName());
                friend.getFriendsNameGroup().put(group,friendNames);
                List<User> friendList=new ArrayList<>();
                friendList.add(user);
                friend.getFriendsGroup().put(group,friendList);
            }

        }
        this.save();

    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            Gson gson=new Gson();
            for (Friend friend : allFriends.values()) {
                String line = String.format("%s#%s",
                        friend.getName(), gson.toJson(friend.getFriendsNameGroup()));
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
        return allFriends.containsKey(identifier);
    }

    public void changeUserName(String oldUserName,String newUserName)
    {
        for(Map.Entry<String, Friend> entry:allFriends.entrySet()){

           String userName=entry.getKey();
            Friend friend=entry.getValue();
            Map<String, List<String>> friendsNameGroup=friend.getFriendsNameGroup();
            for(Map.Entry<String, List<String>> entry1:friendsNameGroup.entrySet()){
                String groupName=entry1.getKey();
                List<String> friends=entry1.getValue();
                for(int i=0;i<friends.size();i++)
                {
                    if (friends.get(i).equals(oldUserName))
                        friends.set(i,newUserName);
                }
            }
            if (userName.equals(oldUserName))
            {
                allFriends.remove(oldUserName);
                allFriends.put(newUserName,friend);
            }

        }
        this.save();

    }
    public String clearFriends() {
        int count = allFriends.size();
        if (count == 0) {
            return "";
        }
        String message = "";
        Iterator<Map.Entry<String, Friend>> iterator = allFriends.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Friend> entry = iterator.next();
            message = message + entry.getKey() + "\n";
            iterator.remove();
        }
        this.save();

        return message;
    }
}
