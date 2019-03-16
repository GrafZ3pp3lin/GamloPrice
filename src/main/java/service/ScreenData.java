package service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScreenData {

    private static Screen presentationScreen;
    private static Screen mainScreen;
    private List<Screen> connectedScreens = new ArrayList<>();

    public ScreenData() {
        reloadConnectedScreens();
    }

    /**
     * get the Main Screen
     *
     * @return screen for main menu, editing, etc.
     */
    public static Screen getMainScreen() {
        return mainScreen;
    }

    /**
     * get the Screen for the presentation mode
     *
     * @return Screen for presentation
     */
    public static Screen getPresentationScreen() {
        return presentationScreen;
    }

    /**
     * get all Screen devices
     *
     * @return all connected Screen devices
     */
    public List<Screen> getConnectedScreens() {
        return connectedScreens;
    }

    /**
     * load/reload amount and size of the connected Screens
     */
    public void reloadConnectedScreens() {
        connectedScreens.clear();

        try {
            GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

            for (var screen : gd) {
                Screen currentScreen = new Screen(screen.getDisplayMode().getWidth(), screen.getDisplayMode().getHeight(), screen.getIDstring());
                connectedScreens.add(currentScreen);
                if (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().equals(screen)) {
                    mainScreen = currentScreen;
                }
            }

            //if more than one screen available take the first screen which isn't the main screen as presentation screen
            if (connectedScreens.size() > 1) {
                presentationScreen = connectedScreens.stream().filter(screen -> screen != mainScreen).collect(Collectors.toList()).get(0);
            }

        }
        catch (HeadlessException e) {
            //throws in Environments without display support
            e.printStackTrace();
        }
    }

    /**
     * Data of a single Screen
     */
    public class Screen {
        private int width;
        private int height;
        private String ID;

        protected Screen(int width, int height, String ID) {
            this.width = width;
            this.height = height;
            this.ID = ID;
        }

        public String toString() {
            return ID;
        }
    }
}
