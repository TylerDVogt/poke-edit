package src.model;

import src.view.Window;

public class App {

    private Window window;
    private static Data data;

    private App(){
       new Window(); 
    }

    public static void main(String[] args) {
        data = new Data();
        new App();
    }

    public Window getWindow() {
        return window;
    }

    public static Data getData() {
        return data;
    }
}
