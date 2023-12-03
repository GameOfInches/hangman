# Hangman Game Project

## Project Description
The Hangman Game is a web-based application that allows users to play the classic word-guessing game. In this game, players attempt to guess a secret word by suggesting letters within a certain number of attempts. The application features a backend built with Spring Boot and a frontend developed using React. The backend communicates with the Words API to fetch random words for the game.

## Technologies Used
- **Backend:** Spring Boot
- **Frontend:** React
- **API:** Words API for fetching random words
- **Database:** Used for storing game data (e.g., lobby ID, secret word) in MySQL

## Project Features
- Users can create game lobbies with unique IDs.
- The game fetches a random word from the Words API for each lobby.
- Players can guess letters to uncover the secret word.
- Visual representation of incorrect guesses with hangman images.
- Game-over conditions include running out of attempts or correctly guessing the word.
- Future features may include multiplayer functionality, score tracking, and additional game customization options.

## Challenges Faced
- Integrating the Words API to fetch random words for the game.
- Handling game state and logic between the backend and frontend.
- Implementing visual elements such as hangman images based on incorrect guesses.
- Managing game flow and transitions between different states.

## How to Install and Run the Project
1. Clone the repository to your local machine.
2. Navigate to the project's backend and frontend directories.
3. Install dependencies using package managers like Maven for the backend and npm for the frontend.
4. Configure the Words API key in the backend, as well as database credentials in DatabaseConfig class.
5. Run the backend and frontend servers.
6. Build Docker container for Database (with docker build -t database .) and run it (with docker run -p 3306:3306 database).

```bash
# Example commands
cd backend
mvn spring-boot:run

cd frontend
npm install
npm start
