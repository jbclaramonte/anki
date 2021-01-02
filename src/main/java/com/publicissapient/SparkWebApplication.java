package com.publicissapient;

import com.publicissapient.anki.Anki;
import com.publicissapient.anki.domain.Card;
import com.publicissapient.anki.domain.Session;
import com.publicissapient.anki.spi.SessionIOException;
import com.publicissapient.anki.spi.file.FileDeckIO;
import com.publicissapient.anki.spi.file.FileSessionIO;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SparkWebApplication {

    public static final int SPARK_SERVER_PORT = 5000;
    public static final String FILE_SESSIONS_LOCATION = System.getProperty("user.dir") + "/anki";

    private Anki anki;

    public SparkWebApplication() {
        this.initSpark();
        this.initAnki();
    }

    private void initSpark(){
        port(SPARK_SERVER_PORT);
    }

    private void initAnki(){
        this.anki = new Anki(new FileSessionIO(), new FileDeckIO());

        this.setupRoute_Home();
        this.setupRoute_LoadSession();
        this.setupRoute_SaveSession();
        this.setupRoute_PullCard();
        this.setupRoute_AnswerQuestion();
        this.setupRoute_ShowResult();
    }

    private void setupRoute_Home(){
        get("/home", (request, response) -> "Anki Server is operational");
    }


    private void setupRoute_LoadSession(){
        get("/load", (request, response) -> {
            String sessionName = request.queryParams("session");
            String deckName    = request.queryParams("deck");

            if(sessionName != null) {
                this.anki.loadSession(sessionName);
                response.redirect("/pullCard");
                return null;
            }

            else if(deckName != null) {
                this.anki.loadSessionByDeck(deckName);
                response.redirect("/pullCard");
                return null;
            }

            else {
                return "Erreur: un nom de session ou de deck est requis";
            }
        });
    }


    private void setupRoute_SaveSession(){
        get("/save", (request, response) -> {
            String sessionName = request.queryParams("session");

            if(sessionName != null)
            {
                try{
                    this.anki.saveSession(sessionName);
                    return "Session " + sessionName + " enregistrée";
                }

                catch(SessionIOException exception) {
                    exception.printStackTrace();
                    return exception.getMessage();
                }
            }
            else
            {
                return "Erreur ; un nom de session est requis";
            }
        });
    }


    private void setupRoute_PullCard(){
        get("/pullCard", (request, response) -> {
            System.out.println("setupRoute_PullCard");

            Session session = anki.getSession();

            if(null != session){

                Card card = session.pullCard();
                System.out.println("card = " + card);

                if(null != card){
                    return card.getQuestion();
                }
                else{
                    session.save();
                    return "Fin de la session";
                }
            }
            else{
                return "Erreur : aucune session n'est chargée";
            }
        });
    }

    private void setupRoute_AnswerQuestion(){
        get("/answer", (request, response) -> {
            Session session = anki.getSession();

            if(null != session){
                Card card = session.getPulledCard();

                if(null != card){
                    String myResponse = request.queryParams("response");
                    boolean correct = session.answer(myResponse);

                    if(correct == true){
                        response.redirect("/showResult?result=vrai");
                    }
                    else{
                        //response.redirect("/showResult");
                        response.redirect("/showResult?result=faux");
                    }

                    return "";
                }
                else{
                    return "Aucune carte n'a été tirée";
                }
            }
            else{
                return "Erreur : aucune session n'est chargée";
            }
        });
    }


    private void setupRoute_ShowResult(){

        get("/showResult", (request, response) -> {
            String result = request.queryParams("result");

            return result;
        });


        after("/showResult", (request, response) -> {
            Thread.sleep(1000);
            response.redirect("/pullCard");
        });
    }
}


