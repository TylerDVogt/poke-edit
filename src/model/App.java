package src.model;

import src.view.Window;

public class App {

    private Window window;
    private static Data data;

    private App(){
        data = new Data();
        window = new Window();
    }

    public static void main(String[] args) {
        new App();
    }

    public Window getWindow() {
        return window;
    }

    public static Data getData() {
        return data;
    }
}
