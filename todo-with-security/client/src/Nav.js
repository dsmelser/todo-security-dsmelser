import { Link } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from './AuthContext';s

function Nav() {
    const [user, setUser] = useContext(AuthContext);

    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>

                {user?.user ? (
                    <li>Logout {user.user.sub}</li>
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