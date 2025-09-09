import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Input from './components/Input/Input.jsx'
import Button from './components/Button/Button.jsx';
import Home from "./pages/Home";

function App() {
  return (
    <Router>
      <nav>
        <Button label="iдi нахуй" to="/" />
      </nav>

      <Routes>
        <Route path="/" element={<Home/>} />
      </Routes>
    </Router>
  );
}

export default App;