import { useEffect, useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AuthContext from "./AuthContext";

function EditTodo(){

    //we have the id
    //we need to ask the api for the todo with this id
    //then store/update it

    const [toEdit, setToEdit] = useState(null);

    const {someId} = useParams();

    const [userStatus, setUserStatus] = useContext(AuthContext);

    const nav = useNavigate();

    useEffect( 
        () => {

            const jwt = localStorage.getItem( "token" );
            if( jwt ){
                
                fetch( "http://localhost:8080/api/todo/" + someId,
                    {
                        headers: {
                            Authorization: "Bearer " + jwt
                        }
                    }
                )
                .then( response => {
                    if( response.status == 200 ){
                        return response.json();
                    } else {
                        console.log( response );
                        alert( "retrieving toEdit failed");
                    }
                })
                .then( retrievedTodo => {
                    console.log( retrievedTodo );
                    setToEdit( retrievedTodo );
                })
                .catch( rejection => {
                    console.log( rejection );
                    alert( "something very bad happened...");
                });
            } else {
                nav("/login");
            }
        },
        []
    );

    function handleTextChange( event ){
        let copy = {...toEdit};
        copy.text = event.target.value;
        setToEdit( copy );
    }

    function handleDateChange( event ){
        let copy = {...toEdit};
        copy.createDate = event.target.value;
        setToEdit( copy );
    }

    function handlePublicChange( event ){
        let copy = {...toEdit};
        copy.public = !copy.public;
        setToEdit( copy );
    }

    function handleEditSubmit(event){
        event.preventDefault();

        const jwt = localStorage.getItem("token");

        fetch( "http://localhost:8080/api/todo", {
            method: "PUT",
            headers: {
                Authorization: "Bearer " + jwt,
                "Content-Type": "application/json"
            },
            body: JSON.stringify( toEdit )
        })
        .then( response => {
            if( response.status == 200 ){
                nav( "/" );
            } else {
                console.log( response );
                alert( "update failed");
            }
        })
        .catch( rejection => {
            console.log( rejection );
            alert( "the sky is falling.");
        });



    }

    return toEdit ? <form onSubmit={handleEditSubmit}>
                <label htmlFor="todoText">Text: </label><br/>
                <textarea className="text-edit" id="todoText" value={toEdit?.text} onChange={handleTextChange}></textarea><br/>
                
                <label htmlFor="todoCreateDate">Created On: </label><br/>
                <input type="date" id="todoCreateDate" value={toEdit.createDate} onChange={handleDateChange}></input><br/>

                <label htmlFor="todoPublic">Public Todo: </label>
                <input id="todoPublic" type="checkbox" checked={toEdit.public} onChange={handlePublicChange} ></input><br/>
                <button>Submit</button>
        </form> :
                <></>

}

export default EditTodo;