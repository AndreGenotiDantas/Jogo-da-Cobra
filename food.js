import { generateRandomBoardPosition } from "./gamebord.js";
import { collision as snakeCollision, expandSnake} from'./snake.js';

const EXPANSION_RATE = 2; // TAXA DE EXPANSÃO DA COBRINHA

let foodPosition = generateRandomFoodPosition();

export function update() {
  if (snakeCollision(foodPosition)) {
    expandSnake(EXPANSION_RATE);
    foodPosition = generateRandomFoodPosition();
  }
};

export function draw(gameboard) {
  const foodElement = document.createElement('div');

  foodElement.classList.add('food');

  foodElement.style.gridRowStart = foodPosition.y;
  foodElement.style.gridColumnStart = foodPosition.x;

  gameboard.appendChild(foodElement);
}

function generateRandomFoodPosition() {
  let newFoodPosition;

  while (newFoodPosition == undefined || snakeCollision(newFoodPosition)){
    newFoodPosition = generateRandomBoardPosition();
  }

  return newFoodPosition;
}