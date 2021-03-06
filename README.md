# Project 2 - *NeatYorkTimes Search*

**NeatYorkTimes Search** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **35** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [x] User can click on "settings" which allows selection of **advanced search options** to filter results
* [x] User can configure advanced search filters such as:
  * [x] Begin Date (using a date picker)
  * [x] News desk values (Arts, Fashion & Style, Sports)
  * [x] Sort order (oldest or newest)
* [x] Subsequent searches have any filters applied to the search results
* [x] User can tap on any image in results to see the full text of article **full-screen**
* [x] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.

The following **optional** features are implemented:

* [x] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [x] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [x] User can **share an article link** to their friends or email it to themselves
* [x] Replaced Filter Settings Activity with a lightweight modal overlay
* [x] Improved the user interface and experiment with image assets and/or styling and coloring

The following **bonus** features are implemented:

* [x] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [ ] For different news articles that only have text or only have images, use [Heterogenous Layouts](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) with RecyclerView
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [x] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ ] Leverages the [data binding support module](http://guides.codepath.com/android/Applying-Data-Binding-for-Views) to bind data into layout templates.
* [ ] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [ ] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Uses [retrolambda expressions](http://guides.codepath.com/android/Lambda-Expressions) to cleanup event handling blocks.
* [x] Leverages the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [x] Leverages the [Retrofit networking library](http://guides.codepath.com/android/Consuming-APIs-with-Retrofit) to access the New York Times API.

The following **additional** features are implemented:

* [x] Search Filter Criteria are stored using the [Shared Preferences](http://guides.codepath.com/android/Persisting-Data-to-the-Device) which helps it save and restore across app sessions.
* [x] [Customize Toolbar] (http://guides.codepath.com/android/Using-the-App-ToolBar#styling-the-toolbar)
* [x] Customized most of the UI components
* [x] Implemented [custom progress bar](http://guides.codepath.com/android/Handling-ProgressBars) using the [Cat Loading View](https://github.com/Rogero0o/CatLoadingView)
* [x] Implemented dynamic Javascript injection in the webview to make the webpage body transparent.[Check discussion here](http://stackoverflow.com/questions/32601885/dynamically-change-html-element-in-android-webview)
* [x] Used [custom fonts](http://guides.codepath.com/android/Working-with-the-TextView#using-custom-fonts)
* [x] Customized the [spinner bar with custom fonts](http://stackoverflow.com/questions/5483495/how-to-set-font-custom-font-to-spinner-text-programmatically) 
* [x] [Custom layout decorators](https://github.com/devunwired/recyclerview-playground) for Recycler View.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/nirvanalab/NeatYorkTimesSearch/blob/master/NeatYorkTimes.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Open-source libraries used

- [Retrofit](http://square.github.io/retrofit/) - Retrofit turns your HTTP API into a Java interface.
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Gson](http://guides.codepath.com/android/Leveraging-the-Gson-Library) - Google's Gson library provides a powerful framework for converting between JSON strings and Java objects.
- [Custom layout decorators](https://github.com/devunwired/recyclerview-playground) for Recycler View.
- [Custom progress bar](http://guides.codepath.com/android/Handling-ProgressBars) using the [Cat Loading View](https://github.com/Rogero0o/CatLoadingView)

## License

    Copyright [2016] [Vidhur Voora]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
