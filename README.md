# SECRET-SANTA-MATCHER

## SETUP

**IMPORTANT**: To use this program, you must have a funded [Twilio](https://www.twilio.com/) account. This should cost less than $1.25 to run ($1 for a phone number and $0.0075 per SMS).

Add your Twilio phone number to line 40 of `SendMessage.java` where it says `new PhoneNumber("ADD YOUR TWILIO PHONE NUMBER HERE")`.

#### DEPENDENCIES
This program uses the [JSON-simple](https://code.google.com/archive/p/json-simple/) toolkit as well as the [Twilio Java API](https://www.twilio.com/docs/libraries/java). Follow the instructions there to get everything running.

#### CONFIGURATION FILE
This program loads data using a config.json file, which is expected to be found in the root of the project (I have included it with a basic template for how it should be written).

Everybody that is participating needs to have their name and phone number added as shown below. If one or more participants need to NOT be assigned a specific person or group of people, add each of the people to exclude to the person's 'exclusion' list, as shown below.

```js
{
  "participants": [
    {
      "name": "NAME GOES HERE",
      "number": "+X PHONE NUMBER",
      "exclusion": ["Person 1 to exclude", "Person 2 to exclude"]
    },
    {
      "name": "",
      "number": "",
      "exclusion": []
    },
    {
      "name": "",
      "number": "",
      "exclusion": []
    },
    {
      "name": "",
      "number": "",
      "exclusion": []
    }
  ]
}
```
It is important to note that a country code must be included for each phone number.

#### ENVIRONMENTS
There is a developer environment, as well as a 'production' environment. To toggle between them, change `devMode` in `SendMessage.java` and recompile. If `devMode == true`, then running the program will **NOT** send the messages via SMS. The results will instead be printed to the console or command line. If `devMode == false`, then the program will act as if it's in 'production' mode. This means that SMS messages **WILL** be sent when the program is run.

## WHAT THIS IS
This is a simple program to put a twist on [secret santa](https://en.wikipedia.org/wiki/Secret_Santa). Instead of drawing names from a hat or something similar, you input the names and phone numbers of each participant into a JSON file, run the program, and each participant will receive a test message telling them who to buy a gift for.


## WHY THIS EXISTS
Because I wanted to make it.

## UPCOMING FEATURES
1. Further optimizations
2. Moving from lists to sets because I was dumb and realized it too late
3. The ability to resend an individual message that was not properly sent

## LICENSE
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
