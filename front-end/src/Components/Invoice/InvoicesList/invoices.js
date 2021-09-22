import React from 'react';
import InvoiceTerm from '../InvoiceTerm/invoiceTerm';
import ReactPaginate from 'react-paginate'
import {Link, BrowserRouter} from 'react-router-dom';

class Invoices extends React.Component {

    constructor(props) {
        super(props);

        this.state= {
            page: 0,
            size: 20
        }

    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.invoices.length / this.state.size);
        const invoices = this.getInvoicesPage(offset, nextPageOffset);

        return (
            <div>
                <h3>Фактури</h3>
                <div>
                    <div>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Број на фактура</th>
                                <th scope={"col"}>Комитент</th>
                                <th scope={"col"}>Комерцијалист</th>
                                <th scope={"col"}>Креирана</th>
                                <th scope={"col"}>Истекува</th>
                                <th scope={"col"}>Износ</th>
                                <th scope={"col"}>Платена</th>
                            </tr>
                            </thead>
                            <tbody>
                            {invoices}
                            </tbody>
                        </table>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}
                               className={""}/>
            </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getInvoicesPage = (offset, nextPageOffset) => {
        return Array.from(this.props.invoices).map((term) => {
            return (
                <InvoiceTerm invoice={term} />
            );
        }).filter((invoices, index) => {
            return index>=offset && index < nextPageOffset;
        })
    }
}

export default Invoices;