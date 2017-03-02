# BlueTape

![build status](https://travis-ci.org/dmitry-zaitsev/BlueTape.svg?branch=master)

Data binding library for Android which is:
- Lightweight
- Doing one thing and doing it well
- Easily extendable
- Fully covered with unit tests
- Working great with Jack, Retrolambda or Kotlin

## Getting started

Here is a simple yet common example of how to use `BlueTape`:

```java
BlueTape blueTape = BlueTape
  .with(() -> composite(                    // More about composite() later
          id(R.id.text,                     // id() takes id of the view and list of functions which will bind data to this view
              text(someVariable),           // text() is assigning text to current TextView. No cast needed!
              textColor(Color.RED)          // Following the same logic textColor() changes the color of the text
          ),
          id(R.id.button,
              onClick(v -> doSomething())   // onClick() works as `setOnClickListener` on a normal button
          )
  ))
  .into(this);                              // "this" might be either an Activity or a View
```

Now, every time data is updated we can trigger `BlueTape` to update all data in the views:

```java
someVariable = "New value";

blueTape.update();   // This will re-bind all views which we declared before
```

## Add it to your project

Gradle:

```groovy
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  compile 'com.github.dmitry-zaitsev:BlueTape:1.0.0'
}
```
