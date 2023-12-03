import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

async function createGame() {
 try {
    const response = await axios.post(`${BASE_URL}/games`);
    return response.data;
 } catch (error) {
    console.error('Error creating game:', error);
    throw error;
 }
}

async function joinGame(gameId, playerName) {
 try {
    const response = await axios.put(`${BASE_URL}/games/${gameId}/players/${playerName}`);
    return response.data;
 } catch (error) {
    console.error('Error joining game:', error);
    throw error;
 }
}

async function guessLetter(gameId, letter) {
 try {
    const response = await axios.put(`${BASE_URL}/games/${gameId}/guess/${letter}`);
    return response.data;
 } catch (error) {
    console.error('Error guessing letter:', error);
    throw error;
 }
}

export { createGame, joinGame, guessLetter };