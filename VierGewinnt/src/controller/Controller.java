package controller;

import model.Model;
import view.View;

public class Controller {

private final Model model;
private final View view;

public Controller (View view) {

 this.view = view;
 this.model = new Model();
}

 public void nextFrame(){
 if(!this.model.isGameOver())  {
  this.view.drawGrid(this.model.getGrid(),this.model.getCurrentPlayer());
 } else {
  this.view.showWinner(this.model.getCurrentPlayer(), this.model.isWinning(this.model.getCurrentPlayer()));
 }
}

public boolean isColumnValid(int column){return this.model.isColumnValid(column);}
 public void userInput(int column) {this.model.updateGridField(column);}
 public boolean isRowFull(int column) {return model.isRowFull(column);}

}
