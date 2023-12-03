import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


function Home() {
 const navigate = useNavigate();

 const handlePlay = async () => {
     try {
       const backendUrl = 'http://localhost:8080';
       const createEndpoint = backendUrl + '/create'
       const action = 'lobbyCreate';
       const backendResponse = await axios.post(createEndpoint, action, {
           headers: {
             'Content-Type': 'text/plain', // Set content type to plain text
           },
         });

       const lobbyId = backendResponse.data;
       const navigateTo = '/game/' + lobbyId;

       navigate(navigateTo, { state: { lobbyId: lobbyId } });
     } catch (error) {
       console.error('Error creating game lobby:', error);
     }
  };

 return (
    <div>
      <h1>Create a Game</h1>
      <button onClick={handlePlay}>Play!</button>
    </div>
 );
}

export default Home;