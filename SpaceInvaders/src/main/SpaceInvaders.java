package main;

import gui.states.menu.CustomizationState;
import gui.states.menu.MenuState;
import gui.states.menu.RankingState;
import gui.states.menu.StartState;
import gui.states.multi.WaitingState;
import gui.states.single.*;
import logic.environment.manager.menu.Menu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.*;
import java.awt.Dimension;


public class SpaceInvaders extends StateBasedGame {

    private Menu menu;
    public static float SCALE_X;
    public static float SCALE_Y;

    public SpaceInvaders(Menu menu) {
        super("Space Invaders");
        this.menu = menu;
    }

    public void initStatesList(GameContainer gameContainer) {
        this.addState(new StartState(menu));
        this.addState(new MenuState(menu));
        this.addState(new SinglePlayerState(menu));
        this.addState(new GameOverStateSingle(menu));
        this.addState(new RankingState(menu));
        this.addState(new NewHighscoreState(menu));
        this.addState(new CustomizationState(menu));
        this.addState(new WaitingState(menu));
        this.enterState(0);
    }

    public static void main(String[] args) {
        try{
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            SCALE_X = (float) screenSize.getWidth()/175;
            SCALE_Y = (float) screenSize.getHeight()/120;
            Menu menu = new Menu();
            AppGameContainer container = new AppGameContainer(new SpaceInvaders(menu));
            container.setDisplayMode((int) (Dimensions.MAX_WIDTH*SCALE_X),(int) (Dimensions.MAX_HEIGHT*SCALE_Y),
                    false);
            container.setSmoothDeltas(false);
            container.setShowFPS(false);
            container.setVSync(false);
            container.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}
