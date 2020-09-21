[BotBlock]: https://botblock.org  
[API]: https://botblock.org/api/docs

[BotBlock4J]: https://github.com/Nathan-webb/BotBlock4J

[wiki]: https://jbba.dev/docs

[CodeMC]: https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/  
[CodeMCBadge]: https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.codemc.io%2Fjob%2Fbotblock%2Fjob%2FJavaBotBlockAPI%2F&label=Dev%20Builds&style=plastic

[BadgeDownload]: https://img.shields.io/bintray/v/andre601/maven/JavaBotBlockAPI?label=Bintray&style=plastic  
[Download]: https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion

[JDA]: https://github.com/DV8FromTheWorld/JDA  
[Javacord]: https://github.com/javacord/Javacord

[OkHttp]: https://github.com/square/okhttp/  
[JSON]: https://github.com/stleary/JSON-java  
[Caffeine]: https://github.com/ben-manes/caffeine  

[contributors.md]: https://github.com/botblock/JavaBotBlockAPI/blob/master/contributors.md

[Javadoc]: https://docs.botblock.org/JavaBotBlockAPI
[image]: https://docs.botblock.org/JavaBotBlockAPI/assets/img/JavaBotBlockAPI.png

![image]

JavaBotBlockAPI is a continued and updated Java Wrapper for [BotBlock], a website that makes it possible to update guild counts on multiple lists with one API.  
This wrapper is a fork of [BotBlock4J] and was updated and improved to make it as userfriendly as possible.

# Installation
[![BadgeDownload]](https://bintray.com/andre601/maven/JavaBotBlockAPI/_latestVersion) [![CodeMCBadge]](https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI/)

You can install JavaBotBlockAPI through the following methods.  
Make sure to replace `{version}` with the above shown version.

## Gradle
Put this code into your `build.gradle` to download all modules:  
```groovy
repositories{
    maven{ url = 'https://dl.bintray.com/andre601/maven' }
}

dependencies{
    compile group: 'org.botblock', name: 'javabotblockapi', version: '{version}'
}
```

if you want to only download specific modules can you just use `javabotblockapi-<module>`:
```groovy
repositories{
    maven{ url = 'https://dl.bintray.com/andre601/maven' }
}

dependencies{
    // Core Module. Always needed
    compile group: 'org.botblock', name: 'javabotblockapi-core', version: '{version}'

    // Request Module. Depends on Core
    compile group: 'org.botblock', name: 'javabotblockapi-request', version: '{version}'

    // JDA Module. Depends on Core and Request
    compile group: 'org.botblock', name: 'javabotblockapi-jda', version: '{version}'
    
    // Javacord Module. Depends on Core and Request
    compile group: 'org.botblock', name: 'javabotblockapi-javacord', version: '{version}'
}
```

## Maven
For maven use this code snippet to download all modules:
```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://dl.bintray.com/andre601/maven</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

if you want to only download specific modules can you just use `javabotblockapi-<module>`:
```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <url>https://dl.bintray.com/andre601/maven</url>
  </repository>
</repositories>

<dependencies>
  <!-- Core Module. Always needed -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-core</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- Request Module. Depends on Core -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-request</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- JDA Module. Depends on Core and Request -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-jda</artifactId>
    <version>{version}</version>
  </dependency>

  <!-- Javacord Module. Depends on Core on Request -->
  <dependency>
    <groupId>org.botblock</groupId>
    <artifactId>javabotblockapi-javacord</artifactId>
    <version>{version}</version>
  </dependency>
</dependencies>
```

# Usage
Please visit the [Javadoc] for all available POST and GET methods, as the amount of GET methods alone is quite large.

# Libraries/Dependencies
JavaBotBlockAPI utilizes different APIs to provide the functionality it offers right now.  
We have a list of those libraries listed here.

- **Javacord Module**
  - [Javacord] - Java Wrapper for making Discord Bots.
- **JDA Module**
  - [JDA] - Java library used for creating bots.
- **Request Module**
  - [OkHttp] - Library for creating and managing http requests.
  - [JSON] - Used for JSON management.
  - [Caffeine] - Library used for caching.


# Links
Here are some useful links:
- [BotBlock.org][BotBlock] Site for which this wrapper was made.
  - [API] API documentation.
- [Javadoc] Java documentation of the Wrapper.
- [CodeMC] CI server for dev builds. Those jar files may differ from the ones on bintray.
- [BotBlock4J] Original Wrapper from which this one originates.

## Contributors
We appreciate any contribution from others towards this project.  
All contributors are listed on the [contributors.md] file.
