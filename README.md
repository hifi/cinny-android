# Cinny Android

Cinny Android is an **unofficial** Android wrapper for [Cinny](https://github.com/ajbura/cinny/) and is *not* affiliated with the application in any way.
The current version wraps the live production app at https://app.cinny.in which is suboptimal but makes testing easier.

Please note that Cinny is currently *not* responsive and does not properly fit a small screen device.

## Building

This project is designed to be built on a stock Debian/Ubuntu system using distribution provided packages.
YMMV on anything else.

### Dependencies

To install the dependencies run:

```sh
# apt install make android-sdk android-sdk-platform-23
```

### Building

To compile the app, run the following command:

```sh
$ make
```

This will create `cinny.apk` in the root directory.

## Installing

### With USB debugging

Attach your Android device to your PC which has debugging enabled and run the following command:

```sh
$ adb install cinny.apk
```

You should have Cinny installed on your Android device.

### Manually

Copy the APK to your device and run it to start the sideload installation process.
Your device will need to have untrusted sources enabled.
