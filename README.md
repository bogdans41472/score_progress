# Notes:
* All `networking` has been extracted to core module - intention is for this module to provide all the necessary data back to HostApp
* `core` module is using Content Provider to set itself up - although it currently doesn't require the use of the retrieve context, it's good to be there for the future.

* Unit tests can be found under core/test
* UI tests can be found under app/androidTest

Further improvements:
* UI could be nicer
* No progress dialog during the API call to the server
* Retrofit setup should be extracted to outside of the Dao for it to be re-used

