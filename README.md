# Android Crash Detector Library [![](https://jitpack.io/v/subhambikashbehera/Crash_Detector_Android.svg)](https://jitpack.io/#subhambikashbehera/Crash_Detector_Android)

In the dynamic world of mobile app development, crashes are an unfortunate reality. They can occur due to a variety of reasons, from unexpected user interactions to network issues. Detecting and addressing these crashes quickly is crucial for maintaining a positive user experience.

## Installation
To get started with the crash detector, follow these steps:

1. Add the following dependency to your project's `build.gradle` file:
dependencies{
  ......
	implementation 'com.github.subhambikashbehera:Crash_Detector_Android:1.1.0'
  ......
}
Add the following in the settings.gradle
maven { url 'https://jitpack.io' }
2. In the Activity just add the following methods to get started and you are ready to find the all the crashes realted to that activity
  handleUncaughtException()
  e.g.

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        handleUncaughtException()
    }
}

## License
To use this libray nothing to do with this only you need my permission .you can seek permission by asking permission request on subhambikashbehera@gmail.com

## Contact
For questions or support, please contact us at subhambikashbehera@gmail.com.
