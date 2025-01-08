import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"


//User Interface for type safety
interface AllReimbursement {
    userId:number,
    description:string,
    reimbId:number,
    amount:number,
    status:string,
    user:any
}

export const AllReimbursement:React.FC  = () => {

    //state object to store the User Array from the DB
    const [allReimbursement, setAllReimbursement] = useState<AllReimbursement[]>([])

    //useNavigate hook
    const navigate = useNavigate()

   let url = "http://localhost:4444/reimbursements"
   
    //useEffect to call the get request to get all users on component load
    useEffect(()=>{

        //check that the user is a "manager", otherwise route them back to login
        if(store.loggedInUser.role != "manager"){
           url = "http://localhost:4444/reimbursements/" + store.loggedInUser.userId + "/reimbursements"
        }

        getAllReimbursement()
    }, []) //[] so that this runs only once, when the component re-renders



    //Function to get all users 
    const getAllReimbursement = async () => {

        const response = await axios.get(url, {withCredentials:true})
        .then((response)=>{
            console.log(response)
            setAllReimbursement(response.data)
        })

    }


    return(
        <Container>
            <Button onClick={()=>navigate("/")}>Logout</Button>
             {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allreimbursementm")}>All</Button> : <Button onClick={()=>navigate("/allreimbursement")}>All</Button>}
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/pendingreimbursementm")}>Pending</Button> : <Button onClick={()=>navigate("/pendingreimbursement")}>Pending</Button>}
            <Button onClick={()=>navigate("/createreimbursement")}>Create</Button>
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allusers")}>Users</Button> : ""}
            


            <h3>All Reimbursements:</h3>

            <Table className="table-primary table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>Reimbursement Id</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Employee Id</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {allReimbursement.map((allReimbursement:AllReimbursement) => (
                        <tr>
                            <td>{allReimbursement.reimbId}</td>
                            <td>{allReimbursement.description}</td>
                            <td>{allReimbursement.amount}</td>
                            <td>{allReimbursement.status}</td>
                            <td>{allReimbursement.user.userId}</td>
                            <td>
                                {/* Conditional Rendering - promote button if the user is a player, and demote button if the user is a manager */}
                                {<Button>Edit</Button>} {<Button>Delete</Button>} 
                            </td>
                            
                        </tr>
                    ))}
                </tbody>
            </Table>

            {/* TODO: if no users, say that */}

        </Container>
    )

}