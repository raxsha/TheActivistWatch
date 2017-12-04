<p align="center">
  <h1>ActivistWatch</h1>
</p>

ActivistWatch is an application that connects activists with organizations to help promote activism. ActivistWatch brings together organizers who are hosting events and activists who are looking for causes to be a part of.

--- 
#### Current Version: 1.01

##### New Features
1. Allows "swiping" on all events the dashboard
2. View your organizations and events
3. Load event images on pages and dashboard
4. **Fix** Login and registration errors that resulted in bad calls to the database
5. **Fix** Client-side decoding of getEvents() database response

--- 

## Installation ##

ActivistWatch is coded in Android Java and is only compatible on Android devices. 

#### Requirements (requires one of the following) ####

* An Android Device with Android KitKat (v4.4, API level 19) or sooner.
* OR Android Studio with an Android Emulator (v4.4, API level 19 or sooner) installed.

#### Installation Instructions:

* Ideally, ActivistWatch will be hosted on the Google Play store. To download, simply search "ActivistWatch" and install the official ActivistWatch application. No further setup is required.

### Until ActivistWatch is on the play store:
* If not installed, please install Android Studio (https://developer.android.com/studio/index.html)
* Open Android Studio and select "Import Project from Existing Sources"
* Select "Version Control", and type "https://github.com/rakshamuth/TheActivistWatch.git" in the box. Select "Next" until you get to "Finish"
* Once completed, hit the "Run" button at the top. If you have an Android Device connected via USB, you will be prompted to run on your device, otherwise, it will ask you to create a Virtual Android Device
   * If you do not have an Android Device, select "Create New Android Device," select "API 21" and "Install"
   * After this has installed, select the Android Device created, and hit "Start" (once the Android Device has loaded, you may need to hit the run button again)
   
Voila! The application will run on the simulator!


--- 

## Use ##

The application will guide you through setting up an account and using the interface. ActivistWatch is modeled after Tinder. Events will show up in your dashboard. "Swiping" yes or no on the events will add them to your events. In the future, we would like to add ways for these Events to push notifications and a matching algorithm for new Events based on previously swiped Events.

--- 

## Developers ##

For future developers, here are the specifications that define how the application works:

* src/main/java/com/example/patrickcaruso/activistwatch/ contains the modified src files
* ActivistWatch follows an MVC format
  * Models are located in their respective directories (Event, Organization, User, etc.)
  * Controllers are located in -Activity.java files in the designated directory
  * Views are located in src/main/res/layout/ and contain the screens visible in the application
  
* Database calls are static methods made within the Database/Database.java file
* Database calls supported (refer to javadocs for specifics):
  * register(...) - Attempts to register a new user in the database
  * createEvent(...) - creates an Event in the database
  * createOrganization(...) - creates an Organization in the database
  * editUser(...) - Updates Database entry of a User with a new User
  * editOrganization(...) - Updates Database entry of an Organization with a new Organization
  * editEvent(...) - Updates Database entry of an Event with a new Event
  * lookupEvent(...) - Returns database entry for a specific Event
  * lookupUser(...) - Returns database entry for a specific User
  * lookupOrganization(...) - Returns database entry for a specific Organization
