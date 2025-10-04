# 📱 Dollar Blue

Dollar Blue is a calculator app that simplifies currency conversion between Dollar Blue and Bolivianos.    
Built with Kotlin Multiplatform and Compose Multiplatform.    
Available in [Google Play Store](https://play.google.com/store/apps/details?id=com.varqulabs.dolarblueapp).

<img width="600" alt="Untitled design (4)" src="https://github.com/user-attachments/assets/c585136c-c736-4217-ab36-c6d9a66c2a17" />

<p>
  <img src="https://github.com/user-attachments/assets/407e6ffc-eb76-4c2c-a3ba-84bc38431b19" width="240"/>
  <img src="https://github.com/user-attachments/assets/e9032659-d75d-4bc7-af02-5e44f927c6a6" width="240"/>
</p>

## 🗂️ Project structure

<img width="500" alt="Diagram" src="https://github.com/user-attachments/assets/5ff99353-7a32-4540-8f24-5891de0f816d" />

## 📐 Architecture

<img width="400" alt="android_clean_architecture" src="https://github.com/user-attachments/assets/a2da8368-4a8c-4130-9dbc-50c26a7d7081" />

## 🛠 Technologies Used

- **Kotlin** as the primary programming language.
- **Jetpack Compose & Compose Multiplatform** for building UIs.
- **Kotlin Multiplatform** for shared code and platform-specific implementations.
- **Room** as the local database.
- **DataStore Preferences** for lightweight data storage.
- **Koin** for dependency injection.
- **Ktor** for network communication.
- **JUnit** for unit testing.
- **Turbine** for Flow testing.
- **Compose Test** for UI testing.
- **Glance** for Android widgets.
- **Google AdMob** for ads integration.

### Build setup
- Clone the repository `https://github.com/juanjosevarqu/DollarBlue-KMP.git`
- Add your Google AdMob API Keys (ADMOB_ID, REWARDED_ID)
- To run Tests, in your terminal:
  ```bash
  ./gradlew :composeApp:connectedAndroidTest
  ./gradlew :feature:calculator:connectedAndroidTest
  ```


