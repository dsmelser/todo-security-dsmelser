import DeleteTodo from "./DeleteTodo"
import { useContext } from 'react';
import AuthContext from './AuthContext';

function Todo(props) {
    const {text, createDate, username} = props.todoObj;
    const [user, setUser] = useContext(AuthContext);

    return (
        <div className="todo-item">
            <h3>User: {username}</h3>
            <p>Created: {createDate}</p>
            <p>Text: {text}</p>
            { user?.user.sub === username || user?.user.authorities.includes("ROLE_ADMIN") ? (
                <DeleteTodo />
            ) : (
                null
            ) } 
        </div>
    )
}

export default Todo;