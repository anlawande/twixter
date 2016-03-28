### Twixter
A twitter like API that's hipster, for some reason

#### API
See below for examples

** Prefix all calls with /Twixter **

* ```/person/{personId}/followers``` - Get people following personId  
* ```/person/{personId}/following``` - Get people personId is following  
* ```/person/{personId}/following/add/{followingId}``` - Make personId follow followingId  
* ```/person/{personId}/following/remove/{followingId}``` - Stop personId following followingId  
* ```/person/{personId}/tweets/self``` - Get tweets posted by personId  
* ```/person/{personId}/tweets/all``` - Get tweets posted by personId and their followers  

I made the decision to have the add and remove following calls as **GET** instead of **POST** and **DELETE** respectively because of convenience of being able to easily modify and fire requests from the browser.
