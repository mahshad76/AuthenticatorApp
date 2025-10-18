# AppleNewsApp
Dive into the world of Apple with this Android mobile app. AppleNewsApp lets you seamlessly sign up and log in to access a curated feed of trending Apple articles using [NewsApi](https://newsapi.org/). Users can instantly like and save any article, building a completely personalized reading list that persists securely even after the app is closed.
<video src="https://github.com/user-attachments/assets/47212a18-bc13-4420-a1b4-52d0d36234cd" controls></video>
# ✨ Features
The AppleNewsApp is designed to provide a personalized, persistent, and focused news consumption experience for Apple enthusiasts.

## 🔐 User Authentication & Access

* Secure Sign-Up & Login: Provides a streamlined process for new users to register and existing users to log in securely.

* Persistent Session: Maintains user login status, ensuring users are taken directly to the main content upon returning to the app.

## 📰 Content Discovery & Filtering

* Curated News Feed (Home Tab): Displays a constantly updated list of the latest articles relevant to Apple news upon successful login.

* Article Search: A dedicated search bar allows users to filter the article list by keyword, enabling them to quickly find news on specific topics or products.

## ❤️ Personalization & Persistence

* Article Liking: Users can "like" articles directly from the main Home feed.

* Favorites (Saved Tab): A separate tab dedicated to viewing only the articles the user has liked.

* Cross-Session Persistence: All liked articles are saved securely, ensuring the user's favorite list is retained and available even after closing and restarting the application.
  
# 🏗️ Architecture: Model-View-Presenter (MVP)
This application is built using the Model-View-Presenter (MVP) architectural pattern to ensure a clear separation of concerns, which improves testability and maintainability across the codebase. The MVP structure divides the application into three key components:
## 1. View (Passive Layer)
The View layer is handled by Activities and Fragments (such as the Home, Favorites, and Auth screens).
- Responsibility: Displays data to the user and captures user interactions (e.g., button clicks, search input).
- Key Principle: The View is "dumb" and contains minimal logic; it simply forwards user actions to the Presenter and implements methods the Presenter calls to update the UI (e.g., showLoading(), displayArticleList(data)).
## 2. Presenter (Logic Layer)
The Presenter acts as the middle-man between the View and the Model. It is the heart of the business logic.
- Responsibility: Processes user input from the View, retrieves data from the Model, applies business logic (e.g., filtering search results, handling authentication flow), and tells the View exactly what to display.
- Key Principle: The Presenter is framework-independent, meaning it contains no references to Android classes, making it highly unit-testable.
## 3. Model (Data Layer)
The Model is responsible for managing the application's data sources.
- Responsibility: Handles all data operations, including remote API calls (fetching Apple news), local database transactions (persisting liked articles), and user authentication status management.
- Key Principle: The Model is completely unaware of the View and the Presenter, ensuring the data logic can be easily swapped or updated without affecting the UI.

# 🛠️ Technologies & Libraries

The AppleNewsApp is developed using the **Kotlin** language and is structured around the Model-View-Presenter (MVP) architecture. Key components and third-party libraries leveraged in this project include:

1.  **Core Development Stack**
    * **Kotlin:** The primary, modern programming language used for its conciseness, safety, and interoperability with Android's framework.
    * **XML Layouts:** Utilized for declaring flexible and responsive user interface views across all screens.

2.  **Dependency Injection**
    * **Dagger 2:** A powerful, compile-time dependency injection framework that manages the creation and provision of essential objects (Presenters, Models, Services), which is foundational for enabling the MVP architecture and facilitating unit testing.

3.  **Reactive Programming**
    * **RxJava 2, RxAndroid, & RxBinding:** A comprehensive suite for managing asynchronous operations and data streams. It is used to handle concurrent tasks like network requests and database access, as well as binding UI events (like search input) reactively.

4.  **Networking & API Communication**
    * **Retrofit, OkHttp, & Gson:** This combination forms the robust network layer. **Retrofit** is the type-safe HTTP client for defining API endpoints; **OkHttp** handles the efficient, underlying network protocol; and **Gson** automatically deserializes JSON API responses into native Kotlin data objects.

5.  **Data Persistence**
    * **Room:** The official Android persistence library that provides a reliable abstraction over SQLite. It is used in the Model layer to locally cache news data and persistently store the user's liked articles.

6.  **Image Handling**
    * **Glide:** A dedicated, high-performance image management library responsible for quickly fetching, caching, and displaying high-quality article thumbnails while minimizing memory usage and preventing common loading issues.
