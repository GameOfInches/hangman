import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Game from './components/Game';
import Home from './components/Home';

function App() {

 return (
     <Router>
       <div className="App">
         <Routes>
           <Route path="/" element={<Home />} />
           <Route path="/game/:lobbyId" element={<Game />} />
         </Routes>
       </div>
     </Router>
 );
 }
 
 export default App;