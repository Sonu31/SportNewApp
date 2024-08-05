To evaluate your ability to integrate APIs, manage local database storage, and implement search functionality in an Android application.

Task Description:
Create an Android application using Kotlin as the programming language and add the required dependencies. Create a listview that fetches data from the API and displays it in a Recyclerview. The application should store data locally using a local database (e.g., Room DB or SQLite) to ensure offline access. Additionally, implement a search functionality to filter the displayed data.

Requirements:
**API Integration:**

       Fetch data from a given REST API endpoint.
       Display the fetched data in a list format.
       Use URLSession or a third-party library like Retrofit for network requests.
       Parse the JSON response and map it to model objects.
      
 **Local Database Storage:**

         Save the fetched data locally using Room DB or SQLite.
         Implement logic to check internet connectivity:
          If the device is online, fetch data from the API and update the local database.
          If the device is offline, load data from the local database.
**Data Deletion:**


          Allow users to delete items from the list.
           Ensure that deletions are reflected both in the UI and in the local database.

**Search Functionality:**

         Add a UISearchBar or UISearchController to the view controller.
         Implement the necessary delegate methods to filter the data based on the search query.
         Update the list view to display the filtered results.

**Evaluation Criteria:**

       Code Quality: Clean, readable, and well-structured code.
       API Integration: Correct implementation of network requests and JSON parsing.
       Local Storage: Proper setup and usage of Room DB or SQLite.
       Offline Functionality: Ability to handle offline scenarios and load data from the local database.
       Search Functionality: Effective and responsive search implementation.
       UI/UX: User-friendly interface and smooth navigation.