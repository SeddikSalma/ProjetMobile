# Mind Grove

Our app offers an intuitive interface that lets you easily write, publish, and manage your blog from your phone, so you can focus on what you do best without any judgeme

## Backend
* Link: https://backendmobilegl4.onrender.com/

## Jetpack compose Components 

<p align="center">
  <img src="https://github.com/SeddikSalma/ProjetMobile/assets/90422613/cb642854-07cd-4858-ba61-e82aa81a2959"><br/>
</p>

* Common :
    * UserAvatar: is the component used for displaying the user profile picture 
    * YCenteredAvatar: is the same as the userAvatar but centered regarding the Y axis

* Post :
    *  PostComponent : is the component used to display the combination of the PostBody and PostHeader
    * PostBody : is the component used to display the content of the post 
    * PostHeader : is the component used to display the name of the username that wrote the post

## Navigation



## Retrofit

*  Interceptors:
    * AccessTokenInterceptor:
        * Adds `Authorization` header to request
    * RefreshTokenInterceptor:
        * If request fails with error codes 401/403, this interceptor will try to refresh the access token using a refresh token.
        * If success, the failed request will be replayed & the result will be returned.
        * If fail, the user will be disconnected.


## User Interface

* SpashScreen

<p align="center">
  <img width=300 height=500 src="https://github.com/SeddikSalma/ProjetMobile/assets/90422613/8bc3ec29-3218-4a97-9ae2-5db9f6fb1fe7"><br/>
</p>


* LoginActivity 

<p align="center">
  <img width=300 height=500 src="https://github.com/SeddikSalma/ProjetMobile/assets/90422613/72db4b6c-cd29-48a3-9b82-c055f71c0720"><br/>
</p>


* SignupActivity

<p align="center">
  <img width=300 height=500 src="https://github.com/SeddikSalma/ProjetMobile/assets/90422613/0b4e030d-43d8-4b7a-8f57-1ead176d8c41"><br/>
</p>

