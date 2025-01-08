import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"


//User Interface for type safety
interface User {
    userId:number,
    firstName:string,
    lastName:string,
    role:string,
    username:string
}

export const AllUser:React.FC  = () => {

    //state object to store the User Array from the DB
    const [user, setUser] = useState<User[]>([])

    //useNavigate hook
    const navigate = useNavigate()

   let url = "http://localhost:4444/users"
   
    //useEffect to call the get request to get all users on component load
    useEffect(()=>{

        //check that the user is a "manager", otherwise route them back to login
        if(store.loggedInUser.role != "manager"){
           alert("Restricted - Managers only")
        }

        getAllUser()
    }, []) //[] so that this runs only once, when the component re-renders



    //Function to get all users 
    const getAllUser = async () => {

        const response = await axios.get(url, {withCredentials:true})
        .then((response)=>{
            console.log(response)
            setUser(response.data)
        })

    }


    const deleteR = async (userId:number) => {

        

        const response = await axios.delete("http://localhost:4444/users/"+userId,  {withCredentials:true})

        .then((response)=>{


            alert("User " + userId + " has been deleted!")

            setUser(response.data);

            navigate("/allusers")
        
        })
        .catch(()=>{alert("Oops")})
       
    }



    const promoteR = async (userId:number) => {

        const person ={userId: userId, role:"manger"}

        const response = await axios.patch("http://localhost:4444/users/"+userId, person, {withCredentials:true})

        .then((response)=>{


            alert("User " + userId + " has been promoted a manager. Hurray!!!!")

            navigate("/allusers")
        
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
           

            <h3>All Reimbursements:</h3>

            <Table className="table-primary table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>Employee Id</th>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Role</th>
                        <th>Username</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {user.map((user:User) => (
                        <tr>
                            <td>{user.userId}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.role}</td>
                            <td>{user.username}</td>
                            <td>
                                {/* Conditional Rendering - promote button if the user is a player, and demote button if the user is a manager */}
                                {<Button onClick={()=>{promoteR(user.userId)}}>Promote</Button>} {<Button onClick={()=>{deleteR(user.userId)}}>Delete</Button>}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

            {/* TODO: if no users, say that */}

        </Container>
    )

}