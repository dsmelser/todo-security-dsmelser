import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import AuthContext from "./AuthContext";
import Home from "./Home";
import Nav from "./Nav";

function App() {

  const [user, setUser] = useState(null);

  return (
    <AuthContext.Provider value={[user, setUser]}>
      <div className="App">
        <Nav />
        <Routes>
            <Route path="/" element={<Home />} />
        </Routes>
      </div>
    </AuthContext.Provider>
  );
}

export default App;
