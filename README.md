# Yu-Gi-Oh! Game

A two-player desktop card game implemented in Java Swing.

## Requirements

- Java Development Kit (JDK) 8 or later

## Run from the command line

Run all commands from the project root directory. This is required because the game loads the CSV databases and the `images` directory using paths relative to the project root.

### macOS or Linux

```bash
mkdir -p out
find src -name "*.java" ! -path "*/tests/*" -print0 | xargs -0 javac -d out
java -cp out eg.edu.guc.yugioh.gui.YuGiOh
```

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse src -Filter *.java | Where-Object { $_.FullName -notmatch '[\\/]tests[\\/]' } | ForEach-Object FullName
javac -d out $files
java -cp out eg.edu.guc.yugioh.gui.YuGiOh
```

## Run from an IDE

1. Open the project root directory in IntelliJ IDEA, Eclipse, or another Java IDE.
2. Mark `src` as the source directory if the IDE does not detect it automatically.
3. Set the main class to `eg.edu.guc.yugioh.gui.YuGiOh`.
4. Set the working directory to the project root directory.
5. Run the main class.

The files under `src/eg/edu/guc/yugioh/tests` use JUnit and are not required to run the game.
