import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card extends ImageView{
    int rank;
    int suit;

    Image CardFaceUp;

    public static final int  HEARTS =  1;
    public static final int  DIAMONDS =  2;
    public static final int  SPADES =  3;
    public static final int  CLUBS =  4;
    //TODO: public static final int  JOKER =  5;

    public static final int  WIDTH   =  5;
    public static final int  HEIGHT  =  10;

    public Card (int givenRank,int givenSuit){
        rank = givenRank;
        suit = givenSuit;
        if (suit == HEARTS)
            CardFaceUp = Images.faceUp.get( "hearts" + rank);
        else if (suit == DIAMONDS)
            CardFaceUp = Images.faceUp.get( "diamonds" + rank);
        else if (suit == SPADES)
            CardFaceUp = Images.faceUp.get( "spades" + rank);
        else if (suit == CLUBS)
            CardFaceUp = Images.faceUp.get( "clubs" + rank);
        /*TODO: else if (suit == JOKER)
            CardFaceUp = Images.faceUp.get( "joker" + rank);
         */
        setImage(Images.faceDown);
    }
    //Returns rank
    public int getRank(){
        return rank;
    }

    //Returns suit
    public int getSuit(){
        return suit;
    }

    //Flips cards
    public void flipCard(){
        if(getImage() == CardFaceUp)
            setImage(Images.faceDown);
        else
            setImage(CardFaceUp);
    }

    //Sets card face up
    public void turnCardFaceUp(){
        setImage(CardFaceUp);
    }

    //Sets card face down
    public void turnCardFaceDown(){
        if (getImage() == CardFaceUp)
            setImage(Images.faceDown);
        else
            setImage(CardFaceUp);
    }

    //Returns if card is face up
    public boolean isFaceUp(){
        return(getImage() == CardFaceUp);
    }

    //Sets the cards position on the board
    public void setCardPos(double x, double y ){
        setX(x);
        setY(y);
    }

    //Returns Cards X Position
    public double getCardPosX(){
        return getX();
    }

    //Returns Cards Y Position
    public double getCardPosY(){
        return getY();
    }

    public boolean getBounds(Card card, double x, double y){
        if(x > card.getCardPosX() && x < card.getCardPosX() + 100 && y > card.getCardPosY() && y < card.getCardPosY() + 150)
            return true;
        else
            return false;
    }

    public void setBounds(double x, double y){
        setX(x-50);
        setY(y-75);
    }

        //Returns suit Name
    public String getSuitString(){
        String  string  =  "";
        switch(suit){
            case  HEARTS:
                string  =  "Hearts";
                break;
            case  DIAMONDS:
                string  =  "Diamonds";
                break;
            case  SPADES:
                string  =  "Spades";
                break;
            case  CLUBS:
                string  =  "Clubs";
                break;
            /*TODO: case  JOKER:
                string = "Joker";
             */
            default:
                string  =  "Program error!!!";
        }
        return  string ;
    }

    //Returns if belongs to suit
    public boolean belongsToSuit(Card card){
        return (suit  ==  card.suit);
    }

    //Returns -1: card < other, 0: card = other, 1: card > other
    public int compareTo(Card card) {
        return Integer.compare(rank, card.rank);

    }

    //Determines if the card is on the table or not
    public boolean onTable(double x, double y){
        return (getX() != 0 && getY() != 0  &&
                x  >=  getX()  && x  <=  getX()  +  WIDTH  &&
                y  >=  getY()  && y  <=  getY()  +  HEIGHT);
    }

}
