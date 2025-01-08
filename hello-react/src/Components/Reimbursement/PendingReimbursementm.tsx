import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"


//User Interface for type safety
interface PendingReimbursement {
    userId:number,
    description:string,
    reimbId:number,
    amount:number,
    status:string,
    user:any
}

export const PendingReimbursementm:React.FC  = () => {

    //state object to store the User Array from the DB
    const [pendingReimbursement, setPendingReimbursement] = useState<PendingReimbursement[]>([])

    //useNavigate hook
    const navigate = useNavigate()

   let url = "http://localhost:4444/reimbursements/pending" 
   
    //useEffect to call the get request to get all users on component load
    useEffect(()=>{  

        //check that the user is a "manager", otherwise route them back to login
        if(store.loggedInUser.role != "manager"){
           url = "http://localhost:4444/reimbursements/" + store.loggedInUser.userId + "/pending/reimbursements"
        }

        getPendingReimbursement()
    }, []) //[] so that this runs only once, when the component re-renders



    //Function to get all users 
    const getPendingReimbursement = async () => {

        const response = await axios.get(url, {withCredentials:true})
        .then((response)=>{
            console.log(response)
            setPendingReimbursement(response.data)
        })

    }


    const approveR = async (reimbId:number) => {

        const reimbursement ={reimbId:reimbId, status:"approved"}

        const response = await axios.patch("http://localhost:4444/reimbursements/"+reimbId, reimbursement, {withCredentials:true})

        .then((response)=>{


            alert("Reimbursement " + reimbId + " has been approved. Hurray!!!!")

            navigate("/pendingreimbursementm")
        
        })
        .catch(()=>{alert("Oops")})
       
    }


    const denyR = async (reimbId:number) => {

        const reimbursement ={reimbId: reimbId, status:"denied"}

        const response = await axios.patch("http://localhost:4444/reimbursements/"+reimbId, reimbursement, {withCredentials:true})

        .then((response)=>{


            alert("Reimbursement " + reimbId + " has been approved. Hurray!!!!")

            navigate("/pendingreimbursementm")
        
        })
        .catch(()=>{alert("Oops")})
       
    }


    return(
        <Container>
            <Button onClick={()=>navigate("/")}>Logout</Button>
             {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allreimbursementm")}>All</Button> : <Button onClick={()=>navigate("/allreimbursement")}>All</Button>}
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/pendingreimbursementm")}>Pending</Button> : <Button onClick={()=>navigate("/pendingreimbursement")}>Pending</Button>}
            <Button onClick={()=>navigate("/createreimbursement")}>Create</Button>
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allusers")}>Users</Button> : ""}

            <h3>Pending Reimbursements:</h3>

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
                    {pendingReimbursement.map((pendingReimbursement:PendingReimbursement) => (
                        <tr>
                            <td>{pendingReimbursement.reimbId}</td>
                            <td>{pendingReimbursement.description}</td>
                            <td>{pendingReimbursement.amount}</td>
                            <td>{pendingReimbursement.status}</td>
                            <td>{pendingReimbursement.user.userId}</td>
                            <td>
                                {/* Conditional Rendering - promote button if the user is a player, and demote button if the user is a manager */}
                                {<Button>Edit</Button>} {<Button>Delete</Button>} {<Button onClick={()=>{approveR(pendingReimbursement.reimbId)}}>Approve</Button>} {<Button onClick={()=>{denyR(pendingReimbursement.reimbId)}}>Deny</Button>}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

            {/* TODO: if no users, say that */}

        </Container>
    )

}                                    