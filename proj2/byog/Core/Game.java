package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Game {
    private static final int HUD_HEIGHT = 3;
    private static final Path SAVE_PATH = Path.of("proj2", "byog", "Core", "save.txt");
    private final TERenderer ter = new TERenderer();
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    public void playWithKeyboard() {
        ter.initialize(WIDTH, HEIGHT + HUD_HEIGHT, 0, 0);
        while (true) {
            showMainMenu();
            if (!StdDraw.hasNextKeyTyped()) {
                StdDraw.pause(20);
                continue;
            }
            char key = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (key == 'q') {
                return;
            } else if (key == 'n') {
                String seedDigits = promptForSeed();
                if (seedDigits == null) {
                    continue;
                }
                String commandHistory = "n" + seedDigits + "s";
                GameState state = buildStateFromCommands(commandHistory);
                commandHistory = runKeyboardGame(state, commandHistory);
                if (commandHistory == null) {
                    return;
                }
            } else if (key == 'l') {
                String savedCommands = readSavedCommands();
                if (savedCommands == null || savedCommands.isEmpty()) {
                    showMessage("No saved game found. Press any key.");
                    waitForKeyPress();
                    continue;
                }
                GameState state = buildStateFromCommands(savedCommands);
                String commandHistory = runKeyboardGame(state, savedCommands);
                if (commandHistory == null) {
                    return;
                }
            }
        }
    }

    public TETile[][] playWithInputString(String input) {
        String commands = input == null ? "" : input.toLowerCase();
        String effectiveCommands = normalizeCommands(commands, true);
        GameState state = buildStateFromCommands(effectiveCommands);
        if (commands.endsWith(":q")) {
            saveCommands(effectiveCommands);
        }
        return state.world;
    }

    private String runKeyboardGame(GameState state, String commandHistory) {
        renderState(state, false);
        boolean awaitingQuit = false;
        while (true) {
            if (state.hasWon) {
                showEndScreen("You escaped the maze! Press M for menu or Q to quit.");
            }
            if (!StdDraw.hasNextKeyTyped()) {
                renderState(state, awaitingQuit);
                StdDraw.pause(20);
                continue;
            }
            char key = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (state.hasWon) {
                if (key == 'm') {
                    return commandHistory;
                } else if (key == 'q') {
                    return null;
                }
                continue;
            }
            if (awaitingQuit) {
                if (key == 'q') {
                    saveCommands(commandHistory);
                    return commandHistory;
                }
                awaitingQuit = false;
            }
            if (key == ':') {
                awaitingQuit = true;
                continue;
            }
            if (key == 'm') {
                return commandHistory;
            }
            if (isMoveKey(key)) {
                movePlayer(state, key);
                commandHistory += key;
            }
            renderState(state, awaitingQuit);
        }
    }

    private GameState buildStateFromCommands(String commands) {
        String normalized = normalizeCommands(commands, false);
        if (normalized.startsWith("l")) {
            String savedCommands = readSavedCommands();
            String suffix = normalized.substring(1);
            if (savedCommands == null) {
                savedCommands = "";
            }
            normalized = savedCommands + suffix;
        }

        long seed = extractSeed(normalized);
        TETile[][] world = createEmptyWorld();
        Random rand = new Random(seed);
        WorldGenerator.generateWorld(world, rand);
        placeExit(world);
        Position player = placePlayer(world);
        GameState state = new GameState(world, player, seed);

        String movementCommands = extractMovementCommands(normalized);
        for (char command : movementCommands.toCharArray()) {
            movePlayer(state, command);
        }
        return state;
    }

    private String normalizeCommands(String commands, boolean keepLoadPrefix) {
        String lower = commands == null ? "" : commands.toLowerCase();
        int quitIndex = lower.indexOf(":q");
        if (quitIndex >= 0) {
            lower = lower.substring(0, quitIndex);
        }
        if (!keepLoadPrefix && lower.startsWith("l")) {
            return lower;
        }
        return lower;
    }

    private long extractSeed(String commands) {
        if (commands.startsWith("l")) {
            commands = readSavedCommands() == null ? "" : readSavedCommands();
        }
        int nIndex = commands.indexOf('n');
        int sIndex = commands.indexOf('s', Math.max(nIndex, 0));
        if (nIndex < 0 || sIndex < 0 || sIndex <= nIndex + 1) {
            return 0L;
        }
        return Long.parseLong(commands.substring(nIndex + 1, sIndex));
    }

    private String extractMovementCommands(String commands) {
        int sIndex = commands.indexOf('s');
        if (commands.startsWith("l")) {
            return commands.substring(1).replaceAll("[^wasd]", "");
        }
        if (sIndex < 0 || sIndex == commands.length() - 1) {
            return "";
        }
        return commands.substring(sIndex + 1).replaceAll("[^wasd]", "");
    }

    private TETile[][] createEmptyWorld() {
        TETile[][] worldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                worldFrame[x][y] = Tileset.NOTHING;
            }
        }
        return worldFrame;
    }

    private Position placePlayer(TETile[][] world) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (world[x][y].equals(Tileset.FLOOR)) {
                    world[x][y] = Tileset.PLAYER;
                    return new Position(x, y);
                }
            }
        }
        throw new IllegalStateException("World has no floor tile for player placement.");
    }

    private void placeExit(TETile[][] world) {
        for (int x = WIDTH - 1; x >= 0; x--) {
            for (int y = HEIGHT - 1; y >= 0; y--) {
                if (world[x][y].equals(Tileset.FLOOR)) {
                    world[x][y] = Tileset.LOCKED_DOOR;
                    return;
                }
            }
        }
        throw new IllegalStateException("World has no floor tile for exit placement.");
    }

    private void movePlayer(GameState state, char key) {
        int dx = 0;
        int dy = 0;
        if (key == 'w') {
            dy = 1;
        } else if (key == 's') {
            dy = -1;
        } else if (key == 'a') {
            dx = -1;
        } else if (key == 'd') {
            dx = 1;
        }
        int nextX = state.player.x + dx;
        int nextY = state.player.y + dy;
        if (nextX < 0 || nextX >= WIDTH || nextY < 0 || nextY >= HEIGHT) {
            return;
        }
        TETile target = state.world[nextX][nextY];
        if (target.equals(Tileset.WALL) || target.equals(Tileset.NOTHING)) {
            return;
        }
        if (target.equals(Tileset.LOCKED_DOOR)) {
            state.world[state.player.x][state.player.y] = Tileset.FLOOR;
            state.player = new Position(nextX, nextY);
            state.world[nextX][nextY] = Tileset.PLAYER;
            state.hasWon = true;
            return;
        }
        state.world[state.player.x][state.player.y] = Tileset.FLOOR;
        state.player = new Position(nextX, nextY);
        state.world[nextX][nextY] = Tileset.PLAYER;
    }

    private void renderState(GameState state, boolean awaitingQuit) {
        ter.renderFrame(state.world);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(WIDTH / 2.0, HEIGHT + 1.5, WIDTH / 2.0, 1.5);
        StdDraw.setPenColor(Color.BLACK);
        Font hudFont = new Font("Monaco", Font.BOLD, 14);
        StdDraw.setFont(hudFont);
        StdDraw.textLeft(1, HEIGHT + 2, "Tile: " + hoveredDescription(state.world));
        StdDraw.text(WIDTH / 2.0, HEIGHT + 2,
                state.hasWon ? "Reach complete!" : "Use WASD to move through the maze");
        String status = awaitingQuit ? "Press Q to save & quit" : "M: menu   :Q: save and quit";
        StdDraw.textRight(WIDTH - 1, HEIGHT + 2, status);
        StdDraw.show();
    }

    private String hoveredDescription(TETile[][] world) {
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();
        if (mouseX < 0 || mouseX >= WIDTH || mouseY < 0 || mouseY >= HEIGHT) {
            return "outside map";
        }
        return world[mouseX][mouseY].description();
    }

    private void showMainMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 32));
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 + 8, "Maze Runner");
        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 + 2, "N - New Game");
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 1, "L - Load Game");
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 4, "Q - Quit");
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 9, "Seed + S starts a run. Explore with WASD.");
        StdDraw.show();
    }

    private String promptForSeed() {
        StringBuilder seedBuilder = new StringBuilder();
        while (true) {
            StdDraw.clear(Color.BLACK);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 24));
            StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 + 4, "Enter seed digits, then press S");
            StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0, seedBuilder.toString());
            StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 6, "Press B to go back");
            StdDraw.show();
            if (!StdDraw.hasNextKeyTyped()) {
                StdDraw.pause(20);
                continue;
            }
            char key = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (Character.isDigit(key)) {
                seedBuilder.append(key);
            } else if (key == 'b') {
                return null;
            } else if (key == 's' && seedBuilder.length() > 0) {
                return seedBuilder.toString();
            }
        }
    }

    private void showMessage(String message) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0, message);
        StdDraw.show();
    }

    private void showEndScreen(String message) {
        StdDraw.setPenColor(new Color(0, 0, 0, 180));
        StdDraw.filledRectangle(WIDTH / 2.0, HEIGHT / 2.0, WIDTH / 2.5, 3);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0, message);
        StdDraw.show();
    }

    private void waitForKeyPress() {
        while (!StdDraw.hasNextKeyTyped()) {
            StdDraw.pause(20);
        }
        StdDraw.nextKeyTyped();
    }

    private boolean isMoveKey(char key) {
        return key == 'w' || key == 'a' || key == 's' || key == 'd';
    }

    private void saveCommands(String commands) {
        try {
            Files.writeString(SAVE_PATH, commands, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save game.", e);
        }
    }

    private String readSavedCommands() {
        if (!Files.exists(SAVE_PATH)) {
            return null;
        }
        try {
            return Files.readString(SAVE_PATH, StandardCharsets.UTF_8).trim().toLowerCase();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load game.", e);
        }
    }

    private static class GameState {
        private final TETile[][] world;
        private Position player;
        private boolean hasWon;

        private GameState(TETile[][] world, Position player, long seed) {
            this.world = world;
            this.player = player;
            this.hasWon = false;
        }
    }

    private static class Position {
        private final int x;
        private final int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
