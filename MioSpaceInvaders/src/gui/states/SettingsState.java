package gui.states;

import logic.environment.Menu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.Image;

import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;


import java.awt.*;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class SettingsState extends BasicGameState implements ComponentListener {

    private StateBasedGame stateBasedGame;
    private GameContainer gameContainer;

    private String credit;
    private String title;

    private Font fontTitle;
    private UnicodeFont uniFontTitle;
    private Font fontData;
    private UnicodeFont uniFontData;

    private ArrayList<Image> ships;
    private ArrayList<MouseOverArea> shipButtons;

    private Image background;
    private Image homeImage;
    private MouseOverArea homeButton;

    private Menu menu;

    public SettingsState(Menu menu) {
        this.menu = menu;
    }


    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        shipButtons = new ArrayList<>();
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;
        background = new Image("res/images/BackgroundSpace.png");
        homeImage = new Image("res/images/Home.png").getScaledCopy(6*gameContainer.getWidth()/100,
                6*gameContainer.getWidth()/100);;
        homeButton = new MouseOverArea(gameContainer, homeImage,5*gameContainer.getWidth()/100,7*gameContainer.getHeight()/100,
                6*gameContainer.getWidth()/100,6*gameContainer.getHeight()/100,this);
        title = "SET YOUR SHIP!";

        ships = new ArrayList<>();
        for (String s: menu.getCustomization().getSpaceShips()) {
            ships.add(new Image(s).getScaledCopy(10*gameContainer.getWidth()/100, 10*gameContainer.getWidth()/100));
        }

        int offset = 17*gameContainer.getWidth()/100;
        int i = 0;

        for (Image img: ships) {
            shipButtons.add(new MouseOverArea(gameContainer, img, 5*gameContainer.getScreenWidth()/100 + offset*i, 45*gameContainer.getHeight()/100,
                    6*gameContainer.getWidth()/100, 6*gameContainer.getHeight()/100, this ));
            i++;
        }

        try {

            fontData = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("res/font/invaders_font.ttf"));
            fontData = fontData.deriveFont(Font.BOLD, 40);
            uniFontData = new UnicodeFont(fontData);
            uniFontData.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFontData.addAsciiGlyphs();
            uniFontData.loadGlyphs();

            fontTitle = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("res/font/invaders_font.ttf"));
            fontTitle = fontTitle.deriveFont(Font.BOLD,60);
            uniFontTitle = new UnicodeFont(fontTitle);
            uniFontTitle.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFontTitle.addAsciiGlyphs();
            uniFontTitle.loadGlyphs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        credit = "Credits: " + menu.getPlayer().getCredit();
        graphics.drawImage(background, 0, 0);

        homeButton.render(gameContainer,graphics);

        uniFontData.drawString(5*gameContainer.getWidth()/100f, 20*gameContainer.getHeight()/100f, credit);

        uniFontTitle.drawString((gameContainer.getWidth() - uniFontTitle.getWidth(title))/2f,
                7*gameContainer.getHeight()/100f, title, org.newdawn.slick.Color.white);
        
        for (MouseOverArea but: shipButtons) {
            but.render(gameContainer, graphics);
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == homeButton) {
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        if (source == shipButtons.get(0)) {
            menu.getCustomization().setCurrentShip(menu.getCustomization().getSpaceShips().get(0));
        }
        if (source == shipButtons.get(1)) {
            menu.getCustomization().setCurrentShip(menu.getCustomization().getSpaceShips().get(1));
        }
        if (source == shipButtons.get(2)) {
            menu.getCustomization().setCurrentShip(menu.getCustomization().getSpaceShips().get(2));
        }
        if (source == shipButtons.get(3)) {
            menu.getCustomization().setCurrentShip(menu.getCustomization().getSpaceShips().get(3));
        }
        if (source == shipButtons.get(4)) {
            menu.getCustomization().setCurrentShip(menu.getCustomization().getSpaceShips().get(4));
        }
    }
}