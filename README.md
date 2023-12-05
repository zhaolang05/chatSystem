# Project Hightlights and Summaries by Feature

## Project Setup
JDK >= 17

## API usage
- WebSocket

## API Endpoints

- http://127.0.0.1:8080/Data/getFriendList?user_name=zhao
- http://127.0.0.1:8080/Data/getUserList

## Running the application
Running ChatServerApplication and ChatClientApplication will run the server and the client application.

## Account Creation Notes
### Function
- Sign up & sign in
- Username check: whether the username exists and password check: whether it matches when entering the second time.
### Possible Additional Function in the future
- Multiple account creation
- Illegal username and password check: check for illegal symbal.

## Account Modification Notes
### Function
- Change username
- Change password
- Change personal profile

## User Interaction Notes
Friend limit :10 (increase or decrease by changing the FRIEND_LIMIT in SendFriendRequestHelper)
### Function
- Search friends
- Add friends
- Look through friend list

### Possible Additional Function in the future
- Send friend requests
- Agree or deny a friend requests

## Chat System Notes
### Function 
- Send & receive & save message
### Possible Additional Function in the future
- Search for chat history

### Possible Additional Function
- Look through friend's profile
### Chat Server API 
- Search friends
- Send friend requests
- Add & delete friends
- Accept or deny friend request
## Chat System Notes
### Function 
- Send & receive & save message[text]
### Possible Additional Function
- Search for chat history[by text][only local message]


