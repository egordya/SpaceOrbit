package com.grupp7.spaceorbit.controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import model.gameModel.AGameModel;
import model.gameModel.GameModel;
import model.modelObjects.CelestialObject;
import utilitys.Vector2D;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements EventHandler<MouseEvent> {

    private GameModel model;


    private Vector2D pressedPos = new Vector2D(0,0);

    private Vector2D replayReleased = new Vector2D(0,0);



    public GameController(GameModel model) {
        this.model = model;
    }

    public void setGameModel(GameModel model){
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        EventType<? extends MouseEvent> type = event.getEventType();

        if (type == MouseEvent.MOUSE_PRESSED){
            handleMousePressed(event);
        }

        else if (type == MouseEvent.MOUSE_RELEASED){
            try {
                handleMouseReleased(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (type == MouseEvent.MOUSE_DRAGGED){
            handleMouseDragged(event);
        }
        else{
            throw new IllegalArgumentException("EventType not implemented");
        }
    }


    private void handleMousePressed(MouseEvent mouseEvent){
        pressedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
    }

    private void handleMouseReleased(MouseEvent mouseEvent) throws Exception {
        Vector2D releasedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());

        replayReleased = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
        saveReplayPos();

        Vector2D[] vectors = new Vector2D[model.getPlayers().length];
        for(int i = 0; i<model.getPlayers().length; i++){
            vectors[i] = releasedPos.sub(pressedPos);
        }

        model.SetShowPlayerArrows(false);
        model.setPlayerVelocity(vectors);
        model.startGame();

    }

    private void handleMouseDragged(MouseEvent mouseEvent){
        Vector2D draggedPos = new Vector2D(mouseEvent.getX(), mouseEvent.getY());
        Vector2D delta = draggedPos.sub(pressedPos);
        model.setPlayersArrow(delta);

    }


    public void replayController() throws Exception {
        readReplayPos();
        Vector2D draggedPos = new Vector2D(replayReleased.getX(), replayReleased.getY());
        Vector2D delta = draggedPos.sub(pressedPos);

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger m = new AtomicInteger(0);

        exec.scheduleAtFixedRate(() -> {

            boolean xNotEquals = (i.intValue() != delta.getX());
            boolean yNotEquals = (m.intValue() != delta.getY());

            if (delta.getX() > 0 && xNotEquals)      {
                i.incrementAndGet();
            }
            if (delta.getX() < 0 && xNotEquals){
                i.decrementAndGet();
            }
            if (delta.getY() > 0 && yNotEquals){
                m.incrementAndGet();
            }
            if (delta.getY() < 0 && yNotEquals){
                m.decrementAndGet();
            }

            if ((Math.abs(i.intValue()) >= Math.abs(delta.getX()) ) && (Math.abs(m.intValue()) >= Math.abs(delta.getY()) )) {

                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Vector2D[] vectors = new Vector2D[model.getPlayers().length];
                for(int a = 0; a<model.getPlayers().length; a++){
                    vectors[a] = replayReleased.sub(pressedPos);
                }
                model.SetShowPlayerArrows(false);
                model.setPlayerVelocity(vectors);
                model.startGame();
                exec.shutdownNow();
                return;
            }
            model.setPlayersArrow(new Vector2D(i.intValue(),m.intValue()));

        }, 0, 5, TimeUnit.MILLISECONDS);
    }


    private void saveReplayPos() throws Exception {

        String filePath =
                System.getProperty("user.dir") + File.separatorChar + "src"
                        + File.separatorChar + "main"
                        + File.separatorChar + "resources"
                        + File.separatorChar + "replay"
                        + File.separatorChar + "replay.txt";

        String xPosP = Integer.toString((int)pressedPos.getX());
        String yPosP = Integer.toString((int)pressedPos.getY());

        String xPosR = Integer.toString((int)replayReleased.getX());
        String yPosR = Integer.toString((int)replayReleased.getY());



        BufferedReader file = new BufferedReader(new FileReader(filePath));
        String line;
        String input = "";
        while((line = file.readLine()) != null)
        {
            input += line + '\n';
        }
        input = xPosP + "\n" + yPosP + "\n" + xPosR + "\n" + yPosR;
        FileOutputStream out = new FileOutputStream(filePath);
        out.write(input.getBytes());

    }
    private void readReplayPos() throws Exception {

        String filePath =
                System.getProperty("user.dir") + File.separatorChar + "src"
                        + File.separatorChar + "main"
                        + File.separatorChar + "resources"
                        + File.separatorChar + "replay"
                        + File.separatorChar + "replay.txt";

        File file = new File(filePath);
        Scanner scan = new Scanner(file);

        int lineContent;

        ArrayList<Integer> coordinates = new ArrayList<>();

        while (scan.hasNextLine()){
            lineContent = Integer.parseInt(scan.nextLine());
            coordinates.add(lineContent);
        }
        pressedPos = new Vector2D(coordinates.get(0), coordinates.get(1));
        replayReleased = new Vector2D(coordinates.get(2), coordinates.get(3));
    }



    private void savePlanetPos() throws Exception {

//        String filePath =
//                System.getProperty("user.dir") + File.separatorChar + "src"
//                        + File.separatorChar + "main"
//                        + File.separatorChar + "resources"
//                        + File.separatorChar + "replay"
//                        + File.separatorChar + "replay.txt";
//
//        Drawable[] planets = model.getPlanets();
//
//        for(Drawable i: planets)
//        {
//            double xPos = i.getXPos();
//            double yPos = i.getYPos();
//        }
//
//
//        String xPosP = Integer.toString((int)model.getPlanets());
//        String yPosP = Integer.toString((int)pressedPos.getY());
//
//        String xPosR = Integer.toString((int)replayReleased.getX());
//        String yPosR = Integer.toString((int)replayReleased.getY());
//
//
//
//        BufferedReader file = new BufferedReader(new FileReader(filePath));
//        String line;
//        String input = "";
//        while((line = file.readLine()) != null)
//        {
//            input += line + '\n';
//        }
//        input = xPosP + "\n"
//                + yPosP + "\n"
//                + xPosR + "\n"
//                + yPosR;
//        FileOutputStream out = new FileOutputStream(filePath);
//        out.write(input.getBytes());
//
    }



}


