import React from 'react';
import Button from 'react-bootstrap/Button'

const invoiceTerm = (props) => {
    return(
        <tr>
            <td>{props.invoice.id.invoice_num}</td>
            <td>{props.invoice.companyName}</td>
            <td>{props.invoice.employee.name}</td>
            <td>{props.invoice.createAt}</td>
            <td>{props.invoice.expiresAt}</td>
            <td>{props.invoice.amount.amount}</td>
            <td>{props.invoice.paid}</td>
        </tr>
    )
}

export default invoiceTerm;