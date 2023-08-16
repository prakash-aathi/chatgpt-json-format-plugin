import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Chat from './pages/Chat';
import LandingPage from './pages/LandingPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/chat' element={<Chat />} />
        <Route path='/' element={<LandingPage />} />
      </Routes>
    </Router>
  );
}

export default App;
