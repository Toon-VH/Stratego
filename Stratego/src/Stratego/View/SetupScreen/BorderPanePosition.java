package Stratego.View.SetupScreen;

import javafx.scene.layout.BorderPane;

public class BorderPanePosition extends BorderPane {
    public BorderPanePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
