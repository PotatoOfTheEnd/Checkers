package com.example.arielle.checkers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Card {

    private int suit;
    private int value;
    private Bitmap image;

    Card(int suite, int value, Bitmap image){
        this.suit = suite;
        this.value = value;
        this.image = image;

    }

    Card(int suite, int value){
        this.suit = suite;
        this.value = value;
    }

    public Bitmap getImage(){
        return image;
    }

    public int getSuite(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public void updateValue(int newValue){
        this.value = newValue;
    }

    public int compareCards(Card c){
        if ((c.getSuite() == this.suit) && (c.getValue() == this.value)){
            return -1;
        }
        else{
            return 0;
        }
    }

    public String toString(){
        String suit;
        String value;

        if (this.getSuite() == 0){
            suit = "spades";
        }
        else if (this.getSuite() == 1){
            suit = "hearts";
        }
        else if (this.getSuite() == 2) {
            suit = "diamonds";
        }
        else{
            suit = "clubs";
        }

        if (this.getValue()== 1){
            value = "ace";
        }
        else if (this.getValue() == 11){
            value = "jack";
        }
        else if (this.getValue() == 12){
            value = "queen";
        }
        else if (this.getValue() == 13){
            value = "king";
        }
        else{
            value = String.valueOf(this.getValue());
        }

        return (value + "_of_" + suit);
    }

    public Card addImage(){
        Bitmap b;

        if (this.toString().equals("ace_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ace_of_clubs);
        }
        if (this.toString().equals("ace_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ace_of_hearts);
        }
        if (this.toString().equals("ace_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ace_of_diamonds);
        }
        if (this.toString().equals("ace_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ace_of_spades);
        }

        if (this.toString().equals("2_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.two_of_clubs);
        }
        if (this.toString().equals("2_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.two_of_hearts);
        }
        if (this.toString().equals("2_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.two_of_diamonds);
        }
        if (this.toString().equals("2_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.two_of_spades);
        }

        if (this.toString().equals("3_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.three_of_clubs);
        }
        if (this.toString().equals("3_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.three_of_hearts);
        }
        if (this.toString().equals("3_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.three_of_diamonds);
        }
        if (this.toString().equals("3_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.three_of_spades);
        }

        if (this.toString().equals("4_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.four_of_clubs);
        }
        if (this.toString().equals("4_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.four_of_hearts);
        }
        if (this.toString().equals("4_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.four_of_diamonds);
        }
        if (this.toString().equals("4_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.four_of_spades);
        }

        if (this.toString().equals("5_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.five_of_clubs);
        }
        if (this.toString().equals("5_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.five_of_hearts);
        }
        if (this.toString().equals("5_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.five_of_diamonds);
        }
        if (this.toString().equals("5_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.five_of_spades);
        }

        if (this.toString().equals("6_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.six_of_clubs);
        }
        if (this.toString().equals("6_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.six_of_hearts);
        }
        if (this.toString().equals("6_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.six_of_diamonds);
        }
        if (this.toString().equals("6_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.six_of_spades);
        }

        if (this.toString().equals("7_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.seven_of_clubs);
        }
        if (this.toString().equals("7_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.seven_of_hearts);
        }
        if (this.toString().equals("7_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.seven_of_diamonds);
        }
        if (this.toString().equals("7_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.seven_of_spades);
        }

        if (this.toString().equals("8_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.eight_of_clubs);
        }
        if (this.toString().equals("8_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.eight_of_hearts);
        }
        if (this.toString().equals("8_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.eight_of_diamonds);
        }
        if (this.toString().equals("8_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.eight_of_spades);
        }

        if (this.toString().equals("9_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nine_of_clubs);
        }
        if (this.toString().equals("9_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nine_of_hearts);
        }
        if (this.toString().equals("9_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nine_of_diamonds);
        }
        if (this.toString().equals("9_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.nine_of_spades);
        }

        if (this.toString().equals("10_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ten_of_clubs);
        }
        if (this.toString().equals("10_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ten_of_hearts);
        }
        if (this.toString().equals("10_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ten_of_diamonds);
        }
        if (this.toString().equals("10_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ten_of_spades);
        }

        if (this.toString().equals("jack_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.jack_of_clubs2);
        }
        if (this.toString().equals("jack_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.jack_of_hearts2);
        }
        if (this.toString().equals("jack_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.jack_of_diamonds2);
        }
        if (this.toString().equals("jack_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.jack_of_spades2);
        }

        if (this.toString().equals("queen_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.queen_of_clubs2);
        }
        if (this.toString().equals("queen_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.queen_of_hearts2);
        }
        if (this.toString().equals("queen_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.queen_of_diamonds2);
        }
        if (this.toString().equals("queen_of_spades")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.queen_of_spades2);
        }

        if (this.toString().equals("king_of_clubs")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.king_of_clubs2);
        }
        if (this.toString().equals("king_of_hearts")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.king_of_hearts2);
        }
        if (this.toString().equals("king_of_diamonds")){
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.king_of_diamonds2);
        }
        else{
            b = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.king_of_spades2);
        }

        Card c = new Card(this.getSuite(), this.getValue(), b);
        return c;
    }
}
