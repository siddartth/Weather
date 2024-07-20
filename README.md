# WeatherApp
This App is a demo project which is written for a single screen yet customized for MVVM architecture
MainActivity is associated with a fragment -> WeatherHostFragment()
The app logic is in ViewModel -> WeatherViewModel() derived from BaseViewModel<Event>()
Data is loaded using retrofit from the api getWeather() returns a response of Weather Object
Weather is a data class which has a defined scructure according to the Json response from the api

**#UI Components:**

BackGround Gradient - Pre defined class Brush is used to create the base background effect

SearchBar = SearchBar is a global component which I created with a viewState which accepts different parameters such as:
onRootClick -> {}
submitQuery -> {}
isCursorVisible
ime keyboard action
hint
valueState
autoFocus
cursorPosition
showClearQueryIcon
showCancelButton
autoFocus

These predefined actions help us to write and reuse the same search accross the app if there are multiple screens used and can be updated at a single point if needed.

CardView = the Card is a compose section created with reusability in mind. It can also be expaned under a (LazyColum / LazyRow) if required in the future.

Shimmer = Shimmer is a pre defined function that is imported and customized using ShimmerUtils to create a custom component that can be used for the app.

rememberImagePainter = is a custom method extracted from the base class which helps the user to async loading the image in the UI using custom url that we get from api according to the weather. When the data is loaded an image is fetched from the following end point "https://openweathermap.org/img/wn/" with icon response.
Our custom component not only fetches url images but it also helps us render an imageview according to the requried formats.

**#Data Class**
WeatherApp/app/src/main/java/com/example/weatherapp/domain/data/Weather.kt
data class WeatherValue = is the main class which consists of the root json file. We are using Serializable / SerializedName GSON plugins to convert the json response to the respective values.

**#Api**

Two main end points used in this project:
Weather data -> "https://api.openweathermap.org/data/2.5/"
Icons(Image value) -> "https://openweathermap.org/img/wn/"

