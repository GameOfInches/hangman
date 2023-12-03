import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const MAX_TRIES = 6;

const Game = () => {
  const { lobbyId } = useParams();
  const navigate = useNavigate();

  const [wordDisplay, setWordDisplay] = useState([]);
  const [secretWord, setSecretWord] = useState([]);
  const [buttonLetters, setButtonLetters] = useState([]);
  const [incorrectGuesses, setIncorrectGuesses] = useState(0);
  const [gameOver, setGameOver] = useState(false);
  const [victory, setVictory] = useState(false);
  const [guessedLetters, setGuessedLetters] = useState([]);
  const [hangmanImage, setHangmanImage] = useState('image-0'); // Default image class

  useEffect(() => {
    const getSecretWord = async () => {
      const backendUrl = 'http://localhost:8080';
      const options = {
        method: 'GET',
        url: `${backendUrl}/get`,
        params: { lobbyId: lobbyId },
      };

      try {
        const backendResponse = await axios.request(options);
        const base64Word = backendResponse.data.replace(/\+/g, ' '); // Replace '+' with space
        const decodedWord = atob(base64Word);
        const secretWordArray = decodedWord.split('');
        setSecretWord(secretWordArray);
        setWordDisplay(secretWordArray.map((char) => (char === ' ' ? ' ' : '_ ')));
      } catch (error) {
        console.error('Error fetching secret word:', error);
      }
    };

    getSecretWord();
  }, [lobbyId]);

  useEffect(() => {
    const generateButtonLetters = () => {
      const alphabet = 'abcdefghijklmnopqrstuvwxyz';
      const availableLetters = Array.from(alphabet).filter(
        (letter) => !guessedLetters.includes(letter) && (!secretWord.includes('-') || letter !== '-')
      );

      // Include the dash in available letters if the secret word contains it
      if (secretWord.includes('-') && !guessedLetters.includes('-')) {
        availableLetters.push('-');
      }

      setButtonLetters(availableLetters);
    };

    generateButtonLetters();
  }, [guessedLetters, secretWord]);





  const handleGuess = (letter) => {
      if (guessedLetters.includes(letter) || gameOver || victory) {
        return;
      }

      setGuessedLetters((prev) => [...prev, letter]);

      if (!secretWord.includes(letter)) {
        setIncorrectGuesses((prev) => prev + 1);

        // Update hangman image based on the number of incorrect guesses
        setHangmanImage(`image-${incorrectGuesses}`);
      }

      const newWordDisplay = [...wordDisplay];
      secretWord.forEach((wordLetter, index) => {
        if (wordLetter.toLowerCase() === letter.toLowerCase()) {
          newWordDisplay[index] = wordLetter + ' ';
        }
      });

      // Automatically add a dash for spaces
      if (secretWord.includes(' ')) {
        secretWord.forEach((wordLetter, index) => {
          if (wordLetter === ' ') {
            newWordDisplay[index] = '- ';
          }
        });
      }

      setWordDisplay(newWordDisplay);

      if (newWordDisplay.join('').replace(/ /g, '') === secretWord.join('')) {
        setVictory(true);
      }

      if (incorrectGuesses + 1 === MAX_TRIES) {
        setGameOver(true);
      }
    };


  const handleRestart = () => {
    navigate('/');
  };

  return (
    <div>
      <h2>Guess the word</h2>
      <div>{wordDisplay}</div>
      {buttonLetters.map((letter) => (
        <button key={letter} onClick={() => handleGuess(letter)} disabled={gameOver || victory}>
          {letter.toUpperCase()}
        </button>
      ))}
      {gameOver && (
        <div>
          <p>Game Over! You ran out of tries.</p>
          <button onClick={handleRestart}>Restart</button>
        </div>
      )}
      {victory && (
        <div>
          <p>Congratulations! You guessed the word.</p>
          <button onClick={handleRestart}>Restart</button>
        </div>
      )}
      {incorrectGuesses > 0 && (
        <div>
          <p>Incorrect Guesses: {incorrectGuesses}</p>
          <div className={`game-images image-${incorrectGuesses}`}></div>
        </div>
      )}

      {incorrectGuesses === 0 && (
        <div>
          <p>Incorrect Guesses: {incorrectGuesses}</p>
          <div className={`game-images image-0`}></div>
        </div>
      )}
    </div>
  );
};

export default Game;





