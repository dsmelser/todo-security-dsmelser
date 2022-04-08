function Todo(props) {
    const {text, userId, createDate} = props.todoObj;

    return (
        <div className="todo-item">
            <h3>User Id: {userId}</h3>
            <p>Created: {createDate}</p>
            <p>Text: {text}</p>
        </div>
    )
}

export default Todo;