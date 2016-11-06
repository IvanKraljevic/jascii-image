# jascii-image
Simply converts the input image to an ASCII text.

## Running
Clone, compile, run:
```
git clone git@github.com:IvanKraljevic/jascii-image.git
cd jascii-image
mkdir target && javac -cp src/ -d target/ src/com/ikraljevic/jascii/Main.java
java -cp  target/ com.ikraljevic.jascii.Main /path/to/image.png /path/to/output.txt
```

## Changing the "pixel to character" map
- there are 2 "pixel to character" maps provided: `short` and `long` (default)
- the `long` has more characters than the `short` (obvious) so it provides a detailed ASCII export
- to change the preferred mapping strategy simply edit the `resources/config.properties` file
