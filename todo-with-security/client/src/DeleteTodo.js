function DeleteTodo({ todoId, removeFromState }) {

    function handleDelete() {
        fetch("http://localhost:8080/api/todo/" + todoId, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
        .then(response => {
            if (response.status === 200) {
                alert("Deleted successfully!");
                removeFromState(todoId);
            }
        })
    }

    return <button onClick={handleDelete}>❌</button>
}

export default DeleteTodo;