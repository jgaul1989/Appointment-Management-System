Title: c195Project
Purpose: The purpose of this application is to create a GUI that enables users to easily add and modify customer and appointment information
in the connected database.
Author: Jonathan Gaul
Contact: jgaul4@wgu.edu
Version: 1.0a
Date: 2/25/2023
IDE: IntelliJ Community Edition 2021.1.3 x64
JDK: Java SE 17.0.1
JavaFX Version: JavaFX-SDK-17.0.1
MySQL Driver: 8.0.25
How to use: Launch the application and login with any valid user credentials in the client-schedule database.
User credentials can be found in the user table of the database. Currently, the username and password combination of user / user
or admin / admin will successfully login. Login information is stored in the file login_activity.txt.
The login menu currently displays the language in English or French depending on the user's locale.
After logging in to the application if you want to work with customer objects then select the customer table and click the add, delete or modify.
If you wish to work with appointment objects, then select any of the appointment tabs and click the add, delete or modify button.
Customer and appointment information that is added, deleted or modified will also update the
client schedule database. To view additional reports click on the view reports button.
Additional Report: The additional report chosen for this project allows the user to select a customer ID and an appointment type. The report then finds
the amount of appointments of the selected type that the customer has scheduled. To access the report, click on the view reports button.