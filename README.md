# Bootcamp Spring Challenge

## 🎙 About the challenge
The challenge consists on create a kind of social media that use follow features, post a new product and be able to create a promo when you want.

### Summary
1. [How to start the application](#startapp)
2. [Database structure](#database)
3. [Swagger](#docs)
4. [Collections](#collections)

## 🔨 How to start the application <a name="startapp"></a>
![app](https://user-images.githubusercontent.com/84407703/121186437-7962a400-c83d-11eb-8e4d-67c64fb066a2.gif)

#### Using Gradle build tool
- At MacOS and Linux
Run the command: `./gradlew bootRun`

- At Windows
Run the command: `gradlew bootRun`

#### 🐳 Using docker
Just run the command: `docker run -dp 8080:8080 vinidomeli/bootcamp-spring-challenge`

* Ps: The application will run automatically, you can check it running the command `docker ps`.

## 📦 Database architecture <a name="database"></a>
![spring-challenge](https://user-images.githubusercontent.com/84407703/121180890-c6dc1280-c837-11eb-8cb8-020b47e8cd96.png)

This architecture was develop with a single principle to create a solid relation between the entities that we are using.

## 📰 Documentation <a name="docs"></a>
All implemented endpoints are available on swagger-ui.

Running Local: `localhost:8080/swagger-ui.html`

URI: `/swagger-ui.html`

![Swagger](https://user-images.githubusercontent.com/84407703/121184877-e5dca380-c83b-11eb-9d78-c6e030a38c6b.gif)

## ⚙️ Collections <a name="collections"></a>
To make all things easier you can download the postman collection with all requests. They are available [here](https://github.com/vinidomeli/bootcamp-spring-challenge/blob/master/collections/Bootcamp%20Spring%20Challenge.postman_collection.json)

![image](https://user-images.githubusercontent.com/84407703/121182468-7f568600-c839-11eb-9678-bdaa512e13c1.png)

That's it! 🪐
