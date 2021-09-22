import React from 'react';
import {BrowserRouter, Link} from 'react-router-dom';

const Header = (props) => {
    return (
            <nav>
                <div className={"div-header"}>
                    <div style={{display:'flex', flexDirection:'row',alignItems:'center'}}>
                        <BrowserRouter>
                        <Link className="nav-link" to='/invoice-catalog' ><h3>Фактури</h3></Link>
                        <Link className="nav-link" to='/salary' ><h3>Плати</h3></Link>
                        <Link className="nav-link" to='/employee-catalog' ><h3>Вработени</h3></Link>
                        </BrowserRouter>
                    </div>
                    {/*<ul className="navbar-nav mr-auto">*/}
                    {/*    <li className="nav-item active">*/}
                    {/*        <h3 className="nav-link" >Фактури</h3>*/}
                    {/*    </li>*/}
                    {/*    <li className="nav-item active">*/}
                    {/*        <h3 className="nav-link" >Плати</h3>*/}
                    {/*    </li>*/}
                    {/*    <li className="nav-item active">*/}
                    {/*        <h3 className="nav-link" >Промет</h3>*/}
                    {/*    </li>*/}

                    {/*</ul>*/}
                </div>
            </nav>
    )
}

export default Header;
