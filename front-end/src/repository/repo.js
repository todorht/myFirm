import axios from '../custom_axios/axios';

const LibraryService = {
    fetchInvoices: () =>{
        return axios.get("/invoice-catalog");
    },
    fetchEmployees: ()=>{
        return axios.get("/employee-catalog")
    },
    // addInvoice: (date, employeeName, activityType) => {
    //     return axios.post("/activities/insert", {
    //         "date" : date,
    //         "employeeName" : employeeName,
    //         "activityType": activityType
    //     });
    // },
}

export default LibraryService;