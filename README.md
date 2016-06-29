# Parsing JSON using okhttp library
It is project for parsing json using okhttp library
This project includes how to connect to server using okhttp library and get json from the server, then how to parse json from server and display in the listview in android.
This project also deals with using Glide library for fetching images from the server


Creates an Android app which:
1. Ingests a json feed from https://dl.dropboxusercontent.com/u/746330/facts.json.
2. Displays the content in a ListView 
 
    • The title in the ActionBar is updated from the json data.
    • Each row  is dynamically sized to display its content, no clipping, no extraneous white-space etc. 
3. Loads the images lazily.
4. Allows the data/view to be refreshed, via either: 
• A refresh button
• Pull down to refresh
