import { useState, useEffect } from 'react';
import Todo from './Todo'

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

    function todoFactory() {
        return pubTodos.map(todo => <Todo key={todo.todoId} todoObj={todo} />);
    }

    return (
        <>
            {todoFactory()}
        </>
    )
}

export default Todos;