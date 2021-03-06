import DeleteTodo from "./DeleteTodo"
import { useContext } from 'react';
import AuthContext from './AuthContext';
import {Link} from 'react-router-dom';

function Todo(props) {
    const {todoId, text, createDate, username} = props.todoObj;
    const [user, setUser] = useContext(AuthContext);

    return (
        <div className="todo-item">
            <h3>User: {username}</h3>
            <p>Created: {createDate}</p>
            <p>Text: {text}</p>
            { user?.user.sub === username || user?.user.authorities.includes("ROLE_ADMIN") ? (
                <>
                <Link to={'/edit/' + todoId} >Edit</Link>
                <DeleteTodo todoId={todoId} removeFromState={props.removeFromState} /></>
            ) : (
                null
            ) } 
        </div>
    )
}

export default Todo;