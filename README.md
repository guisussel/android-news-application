--- Android Code Challenge ---

Build a smartphone app that should be able to show top headlines for a specific
source (BBC news)

Design
You don’t need to waste too much time in doing complex designs, but you should take into
account several screen dimensions and orientations.


Constraints
• The app must be written in Kotlin or Java, preferably Kotlin.
• The app must run on the latest released version of the target platform
• The app must be built using Android Studio 3.0+, without requiring the reviewer to modify any existing code.
• The app must support both portrait and landscape modes without crashing at any time.
• The app may use libraries for HTTP/REST or JSON, such as Retrofit2+, Moshi, Gson, etc...
• The app must include unit tests


Story 1: When the user launches the application, he should land in a screen where is possible to see top headlines for the specific
news source

Acceptance criteria:
  1. News provider name should be showed as a screen title
  2. Headlines are presented in a list format.
  3. Each cell should present the headline title
  4. Headlines must be sorted by date
  5. The user must be able to scroll through the list of headlines
  6. Each cell should present headline image, if available (download and cache it, don’t bundle it)


Story 2: When the user taps on a headline, he should be taken to a new screen

Acceptance criteria:
  1. Tapping on a headline presents a new screen.
  2. Image, title, description and content should be displayed, if available
  
  
Bonus Story 3: When user opens the application, it should ask for a fingerprint identification, if available

Acceptance criteria:
  1. If the device has a fingerprint scanner and it’s configured in the device, user should be required to use it when he opens the application
  2. If the device doesn’t have fingerprint scanner or it’s not configured, then it should open normally


Bonus Story 4: A new flavor should be created to present news for another source

Acceptance criteria:
1. User should land in a different news source if running another target
2. Headlines should be presented according to the target that was selected
