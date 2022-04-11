import { useState, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import AuthContext from "./AuthContext";
import Home from "./Home";
import Nav from "./Nav";
import Login from "./Login";
import EditTodo from './EditTodo';
import jwtDecode from "jwt-decode";
import NotFound from './NotFound';

function App() {

  const [user, setUser] = useState(null);

  useEffect( () => {
    const jwt_token = localStorage.getItem("token");
    if( jwt_token ){
      setUser({ user: jwtDecode(jwt_token) });
    }
  }, []);



  return (
    <AuthContext.Provider value={[user, setUser]}>
      <div className="App">
        <Nav />
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/edit/:someId" element={<EditTodo />} />
            <Route path="*" element={<NotFound/>} />
        </Routes>
      </div>
    </AuthContext.Provider>
  );
}

export default App;
