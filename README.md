# PaddleBall

A simple paddle ball game built with libGDX in Java.

## Requirements
- Java 25 or later (both JDK and JVM)
- Maven

## Build

To build from source, clone this git repo and then cd into it:

```bash
git clone https://github.com/HumbleSeer42/Paddleball
cd Paddleball
mvn package -Pdesktop
```

The resulting jar should be in `desktop/target`

## Running

Run the jar using the below command

```bash
java -jar desktop/target/Paddleball-desktop-0.1.0-jar-with-dependencies.jar
```

To play you use left and right arrows to move the paddle, r to restart the game when the ball hits the floor.

# Future progress

- Add speed levels dependent upon score
- Maybe this will become a brick breaker game.
