import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.* ;
import javafx.scene.input.* ;
import javafx.geometry.* ;
import java.io.*;

import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import java.util.HashMap;


public class PlayGame extends Application{

    CardDeck  deck;
    StackPane root = new StackPane();
    Text instructions = new Text( "CLICK THE CARDS AFTER DEALING." );
    Group rowOfCards = new Group();
    Group groupOfLoneCards = new Group();
    Card loneCard;
    Card selectedCard ;

    public void start ( Stage stage ) throws FileNotFoundException {
        stage.setTitle( "The Game Of Castle" );

        Images.faceDown = new Image(new FileInputStream(
                "C:\\Users\\Queen\\IdeaProjects\\Castle\\cardBackArrows.png"),
                100, 150, false, false);

        Images.faceUp= new HashMap<String, Image>() ;
        String[] wordsInFilePNGNames= { "hearts", "diamonds", "spades", "clubs"} ;

        for (int suitIndex = 0; suitIndex < 4; suitIndex++){
            for ( int cardRank = 2; cardRank < 15; cardRank ++){
                //Files that contain card face images have formatted as such "2 of Hearts.png"
                String PNGFileName = "C:\\Users\\Queen\\IdeaProjects\\Castle\\Playing Card PNGs\\" + cardRank + " of " + wordsInFilePNGNames[suitIndex] + ".png";
                Image faceUpCards = new Image(new FileInputStream(PNGFileName));
                String cardNames = wordsInFilePNGNames[suitIndex] + cardRank ;
                Images.faceUp.put(cardNames, faceUpCards) ;
            }
        }

        deck  =  new CardDeck() ;
        Button  dealButton   = new Button( "DEAL" );
        Button  shuffleButton = new Button( "SHUFFLE" );

        dealButton.setOnAction((ActionEvent event) -> {
            if ( instructions != null ){
                root.getChildren().remove( instructions );
                instructions = null;
            }
            rowOfCards.getChildren().clear();

            for (int cardIndex  =  0; cardIndex  <  5; cardIndex  ++){
                Card newCard = deck.getCard();

                double cardPosX = 40 + ( Card.WIDTH + 20 ) * cardIndex;
                double cardPosY = 50;

                newCard.setCardPos(cardPosX, cardPosY);
                rowOfCards.getChildren().add(newCard);
            }

            loneCard = deck.getCard();
            loneCard.setCardPos( 188, 300 );

            groupOfLoneCards.getChildren().clear();
            groupOfLoneCards.getChildren().add(loneCard);
        });
        shuffleButton.setOnAction( (ActionEvent event) -> deck.shuffle());

        HBox buttonHolder = new HBox(16);
        buttonHolder.getChildren().addAll( dealButton, shuffleButton);
        buttonHolder.setAlignment( Pos.CENTER );
        buttonHolder.setPadding(new Insets( 0, 0, 20, 0 ));

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(buttonHolder);

        Group mainCardGroup = new Group();
        mainCardGroup.setManaged(false);
        mainCardGroup.getChildren().addAll(rowOfCards,groupOfLoneCards);

        instructions.setFont(new Font(24));

        root.getChildren().addAll(borderPane, mainCardGroup, instructions);

        Scene scene = new Scene(root, 910, 600 );

        scene.setOnMouseClicked((MouseEvent event ) -> {
            double clickX = event.getSceneX();
            double clickY = event.getSceneY();
            if (rowOfCards.getChildren().size() == 5){
                for (Node cardNode : rowOfCards.getChildren()){
                    Card cardInRow = (Card) cardNode;
                    if (cardInRow.onTable(clickX, clickY)){
                        cardInRow.turnCardFaceDown();
                        selectedCard = cardInRow;
                    }
                }
                if(loneCard != null  && loneCard.onTable(clickX, clickY))
                    loneCard.turnCardFaceDown();
            }
        });

        Images.background  = new Image(new FileInputStream("C:\\Users\\Queen\\IdeaProjects\\Castle\\Green Background.jpg"));
        ImagePattern ImagePattern  = new ImagePattern(Images.background);
        root.setBackground( null );
        scene.setFill(ImagePattern);
        stage.setScene( scene );
        stage.show();



    }

    public static void main( String args[] ){
        launch(args);
    }
}