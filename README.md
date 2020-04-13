Group Project - README Template
===

# Productivity Well

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This Project is an app that is based around helping the user build good habits. While also giving people all around the world an opportunity to have access to clean drinking water.

This app lets the user set a certain amount of time in which they wish to work on a specific task without any distractions. While the timer is running, the user's calls and texts will be deferred by the app. Also, while the timer is running, the user will not be allowed to use certain apps. If the user opens any apps that are designated distractions, the user will have to give up a pre-designated amount of money. Before the user starts the timer for their task, the user will have to set aside a certain amount of money that they are willing to give up if they do not complete the task. All the money collected from the user will be donated to a nonprofit orginization based around providing clean water people who do not have access to it.

This app also allows users to connect with other users and show off how productive they have been.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:** This app would be developed only for mobile since it is designed to help users be productive by staying away from distractions on the phone. 
- **Story:** This app allows users to set a timer to get specific task/s done and it will send money from user's linked paypal account to a non-profit organization if the user quits the app before the timer is finished.
- **Market:** This app targets people who are looking to maximize their time effeiciency by removing distractions.
- **Habit:** This app will help user build a good habit staying on task and avoiding distractions. The user will open the app whenever they need to focus.
- **Scope:** The scope of This app will be having a functioning timer, as well as a way to handle users' money, as well as a clean UI, as well as an app that responds to incoming calls and texts. Features that are out of scope but would be nice to add if time permits a monthly or yearly donation subscription, a way for users to see and compete with friends. in-app currency, and clean animations for the building of a well.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* [x] ~~User must be able to register a new account~~
* [x] ~~user must be able to sign in~~
* [x] ~~Stats UI must have a pie and line chart~~
* [x] ~~Main UI must be completed.~~
* Settings page UI must be completed.
* user can set the amount that should be deducted if target not met.(if they close the app before done)
* user can set a timer
* minimum 50 cents for money taken

**Optional Nice-to-have Stories**
* User can compare their progress with other app users
* Do not disturb mode: cancel texts and phone calls
* Result analysis (stats: hours, and money spent)
* Choose the non-profit organization
* Time recommendation: 20 minutes with 10 minute breaks
* User can pause the app.
* User can select what non profit they want their money to go if they fail  to complete the task.

### 2. Screen Archetypes

* Login Page
   * user must be able to sign in
   * user must be able to automaticlly be logged in
* Register
   * User must be able to register a new account
* Timer Page
   *  user can set a timer
   *  minimum 50 cents for money taken
   *  user can set the amount that should be deducted if target not met.(if they close the app before done)
* Settings
   * user can link the app to paypal or bank
* Statistics
   * User can compare their progress with other app users

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Timer Page
* Settings
* Statistics

**Flow Navigation** (Screen to Screen)

* Login Page -> Account creation if no login is available
* Timer Page -> Start timer and choose the amount of money that would be sent if the app is closed/run in background etc.
* Settings Page -> Toggle Settings
 
## Wireframes
<img src="https://github.com/Technically-International/Productivity-Well/blob/master/Images/wireframe.jpg" width="300" height="300">

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
User
| Property       | Type.        | Description  |
| :------------- | :----------: | :----------- |
|  objectID      | String       | unique id for the user    |
|  UserName      | String       | what the user uses to login    |
|  Password      | String       | the user's login password |
|  Money         | Int          | this represents how much money the user has on their account |
|  ProfilePic    | File         | image for the user |
|  Email         | String.      | to help verify the user and send them emails |

### Networking

- Home Screen
  - (Read/GET) Query the user balance
    ```java
         ParseUser.findInBackground(userMoney, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                    if (e != null) {
                    Log.e(TAG, "Issue with login",e);
                    return;
                }
            }
        });
     ```
  - (Read/GET) Query the username
     ```java
     
        public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
       protected void queryUser() {
        ParseQuery query = ParseQuery.getQuery(User.class);
        query.include(User.KEY_USER);
        query.findInBackground(new FindCallback User() {
            @Override
            public void done(User user, ParseException e) {
                if (e != null){
                    Log.e(TAG,"issues with getting username", e);
                    return;
                }
            }
        });

        ParseUser.findInInBackground(username, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                    if (e != null) {
                    Log.e(TAG, "UserName not displaying",e);
                    return;
                }
            }
        });
      ```
  - (Update/PUT) Update user balance
     ```java
      private void updateBalance(Int userMoney, ParseUser currentUser) {
        Post post = new Post();
        post.setUserMoney(userMoney);
        post.setUser(currentUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(),"Error while saving!", Toast.LENGTH_SHORT).show();
                }

                Log.i (TAG,"Post save was successful");
                etDesctiption.setText("");
            }
        });
     
        put (KEY_DESCRIPTION, description);
     ```
- Login Screen
     ```java
             public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

        ParseUser.findInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                    if (e != null) {
                    Log.e(TAG, "UserName not displaying",e);
                    return;
                }
            }

     ```
   - (Read/GET) Query the user name
   - (Read/GET) Query the user password
- SignUp Screen 
    ```java
     private void signup user(String username, String password){
            // Create the ParseUser
            ParseUser user = new ParseUser();
            // Set core properties
            user.setUsername(username);
            user.setPassword(password);
            // Invoke signUpInBackground
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with login",e);
                        return;

                    }
                    goMainActivity();
                    Toast.makeText(LoginActivity.this, "success signing up!",Toast.LENGTH_SHORT).show();
                }
            });
        };
     ```
   - (Create/POST) Create a new username
   - (Create/POST) Create a new password for user
   
   ### User Runthroughs:
   - Sign Up and Login Functionality
   
   
<img src="https://imgur.com/IteW2Gy.gif" width=250><br>

-Main and Stats UI


<img src="https://imgur.com/7PXzJZ7.gif" width=250><br>
