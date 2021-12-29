The ToDo App fetches the todo list from the API when the network is available and caches the data into a local database powered by Room .On further opening of the app, 
the cached data  will be displayed from the database. Each item in the recyclerview has a checkbox which would be checked if it is completed (Please note that no update 
functionality is provided on toggling the checkbox as it is not mentioned ) and title of the task. On clicking on a todo item, the app navigates to the Detail View of the 
todo item.

App also allows you to add new tasks and it will be stored in the local database only and hence every time the app is opened ,the data will be displayed from the local database.
If no network is available,a previously cached todo list will be shown to the user.

The code for the app follows MVVM Architecture integrating the jetpack libraries including Databinding. And the layouts files are designed using ConstraintLayout.

Assumptions - The app is designed in a way to display todo lists from all the users and the new task added will be from User Id 1. 
