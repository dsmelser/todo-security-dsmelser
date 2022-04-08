import { Routes, Route } from 'react-router-dom';
import Home from "./Home";
import Nav from "./Nav";

function App() {
  return (
    <div className="App">
      <Nav />
      <Routes>
          <Route path="/" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App;
