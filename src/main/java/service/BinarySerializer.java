package service;

import data.interfaces.IGame;
import service.interfaces.IFileHandler;

import java.io.*;

public class BinarySerializer implements IFileHandler {

    @Override
    public IGame readGameFile(String path) {
        IGame game;
        File input = new File(path);

        if (!input.exists()) {
            return null;
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(input));
            game = (IGame) ois.readObject();
            ois.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return game;
    }

    @Override
    public boolean saveGame(IGame game, String path) {

        File save = new File(path);
        try {
            if (!save.exists()) {
                save.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(save);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
