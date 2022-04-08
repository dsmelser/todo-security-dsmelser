import { useState, useEffect } from 'react';

function Todos() {
    const [pubTodos, setPubTodos] = useState([]);
    
    useEffect(() => {
        fetch("http://localhost:8080/api/todo/public")
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                alert("Something went wrong while fetching...");
            }
        })
        .then(todosData => setPubTodos(todosData))
        .catch(rejection => alert("Failure: " + rejection.status + ": " + rejection.statusText));
    }, []);
    return <p>This is the Todos container component!</p>
}

export default Todos;