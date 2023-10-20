# Android Crash Detector Library [![](https://jitpack.io/v/subhambikashbehera/Crash_Detector_Android.svg)](https://jitpack.io/#subhambikashbehera/Crash_Detector_Android)

In the dynamic world of mobile app development, crashes are an unfortunate reality. They can occur due to a variety of reasons, from unexpected user interactions to network issues. Detecting and addressing these crashes quickly is crucial for maintaining a positive user experience.

![Screenshot_2023-10-20-09-16-01-25_15c245f759578505c4e798fbc7bf786e (1)](https://github.com/subhambikashbehera/Crash_Detector_Android/assets/69251887/1f33c7ee-7c8c-4c90-980c-fd30e93c99fc)
## Installation
To get started with the crash detector, follow these steps:

1. Add the following dependency to your project's `build.gradle` file:
 ```kotlin
dependencies{
  ......
	implementation 'com.github.subhambikashbehera:Crash_Detector_Android:1.1.1'
  ......
}
```  
2. Add the following in the `settings.gradle`
 ```kotlin
maven { url 'https://jitpack.io' }
```  

3. In the Activity just add the following methods to get started and you are ready to find the all the crashes related to that activity
  e.g.
 ```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        handleUncaughtException()
    }
}
```
4. We can also customize the error log details
 ```kotlin
	handleUncaughtException()
	/* it enable auto detection of release and debug build app ,
	 in the release build the error logs will not visible , in the debug release only the logs will be visible */
	or	
	handleUncaughtException(showLogs = true) // it will enable to visible the logs in both relaese build and debug build 
	or
	handleUncaughtException(showLogs = false) // it will not show any error logs in both release and debug build

	But in all above cases the restart app button always visible 
 
```
![Screenshot_2023-10-20-09-16-01-25_15c245f759578505c4e798fbc7bf786e (1)](https://github.com/subhambikashbehera/Crash_Detector_Android/assets/69251887/470846ea-f58b-4b07-ab91-5cb39912b5f0)
![Screenshot_2023-10-20-09-15-04-94_15c245f759578505c4e798fbc7bf786e (1)](https://github.com/subhambikashbehera/Crash_Detector_Android/assets/69251887/61ddeaf8-1764-43d2-bfed-759caa956be2)
![Screenshot_2023-10-20-09-15-22-86_15c245f759578505c4e798fbc7bf786e (1)](https://github.com/subhambikashbehera/Crash_Detector_Android/assets/69251887/10f22457-3cab-4558-b9b2-e8a4d1c0adaa)

## License
To use this libray nothing to do with this only you need my permission .you can seek permission by asking permission request on subhambikashbehera@gmail.com

## Contact
For questions or support, please contact us at subhambikashbehera@gmail.com.
