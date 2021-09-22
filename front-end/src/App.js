import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import React, {Component} from "react";
import LibraryService from "../src/repository/repo";
import Invoices from "./Components/Invoice/InvoicesList/invoices"
import Header from "./Components/Header/header"
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import ActivityAdd from "./Components/Invoice/ActivityAdd/activityAdd";

class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            invoices: [],
            employees: []
        }
    }

    render(){
        return (
            <main>
                <div className={"float-container"}>               
                    <div className={"float-child"}>
                        <Invoices invoices={this.state.invoices}/>
                    </div>
                </div>
            </main>
        );
    }

    loadInvoices = () => {
        LibraryService.fetchInvoices()
            .then((data) => {
                this.setState({
                    invoices: data.data
                })
            })
    }

    loadEmployees = () => {
                LibraryService.fetchEmployees()
                    .then((data) => {
                        this.setState({
                            employees: data.data
                        })
                    })
    }

    // addActivity = (date, employeeId, activityType ) => {
    //     LibraryService.addActivity(date, employeeId, activityType)
    //         .then(()=>{
    //             this.loadActivities();
    //         })
    // }

    componentDidMount() {
        this.loadInvoices();
        this.loadEmployees();
    }
}
export default App;


