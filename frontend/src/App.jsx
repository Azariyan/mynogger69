import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Main from "./pages/Main";

function App() {
  return (
    <Router>
      <nav>
        <Link to="/">Главная</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Main />} />
      </Routes>
    </Router>
  );
}

export default App;