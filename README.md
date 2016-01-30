# Twitch Bot Template
Template for a twitch.tv bot
#Usage
To have a base for new bots
#Features
- Login to your account by changing String username to your username and then direct the BufferedReader fr to a text file that contians your password. Then call ```login()``` from Client.
- Send a message to the twitch IRC server.
- Connect to a server by using the 
  ```
  joinChannel("Channel_Name");
  ```
  method in Client.
- Leave a channel.
- Send a chat to either a single channel or to all connected channels
- Receives messages from the Twitch IRC server
- Automatically deals with the pong message in the ProcessMessage class
- Storage class to store data from ProcessMessage
- Creates different Storages for different connected servers
