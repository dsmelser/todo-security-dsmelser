import { Link } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from './AuthContext';

function Nav() {
    const [user, setUser] = useContext(AuthContext);

    function handleLogout(){
        localStorage.removeItem( "token" );
        setUser( null );
    }

    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>

                {user?.user ? (
                    <li><button onClick={handleLogout}>Logout {user.user.sub}</button></li>
                    // Update with real link later
                ) : (
                    <li>
                        <Link to="/login">Login</Link>
                    </li>
                )}
                
            </ul>
        </nav>
    )
}

export default Nav;