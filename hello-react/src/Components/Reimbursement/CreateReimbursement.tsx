import axios from "axios"
import { SyntheticEvent, useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"

interface ReimbursementDTO {
    description:string,
    amount:number,
    userId:number
}

export const CreateReimbursement:React.FC = () => {

    //Define a state object to store the new User info
    const[newReimburse, setNewReimburse] = useState<ReimbursementDTO>({
        description:"",
        amount: 0,
        userId: store.loggedInUser.userId
    })

    //useNavigate to switch URLS
    const navigate = useNavigate()

    //function that stores values when input boxes change
    //we didn't need to specify the param's data type, I'm just being typesafe
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {

        console.log(event) //just to see the event in console

        //I'm going to store name and value in variables, for ease of use below
        const name = event.target.name 
        const value = event.target.value
        
        //annoying syntax - We need to send the entire user object to make a change to one field
        //"Take whatever input was changed, and set the matching state object field to the value of that input"
        //[name] can be EITHER username or password here. Flexible! Whatever input changes will change the appropriate newUser state field
        setNewReimburse((newReimburse) => ({...newReimburse, [name]:value}))

        console.log(newReimburse)

    }

    //Register function that sends the username/password to the backend in a POST request
    const request = async () => {

        //TODO: check that the username/password are present and valid

        //POST request - saving the response, but we won't need to use it here
        const response = await axios.post("http://localhost:4444/reimbursements", newReimburse, {withCredentials:true})
        .then(()=>{
            alert("New reimbursement request created!")
        
        })
        .catch(()=>{alert("Request failed! Make sure all fields are correct")})

    }

    return(
        
        <Container>

            <Button onClick={()=>navigate("/")}>Logout</Button>
             {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allreimbursementm")}>All</Button> : <Button onClick={()=>navigate("/allreimbursement")}>All</Button>}
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/pendingreimbursementm")}>Pending</Button> : <Button onClick={()=>navigate("/pendingreimbursement")}>Pending</Button>}
            <Button onClick={()=>navigate("/createreimbursement")}>Create</Button>
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allusers")}>Users</Button> : ""}
        
             <Container className="d-flex align-items-center flex-column mt-5">

           

                 <h3>Create Reimbursement</h3>

                 {/* Making a separate div for each input box */}
                  <div>
                       <Form.Control
                         type="text"
                         placeholder="description"
                         name="description"
                         onChange={storeValues}
                     />
                 </div>
                 <div>
                      <Form.Control
                         type="number"
                          placeholder="amount"
                           name="amount"
                         onChange={storeValues}
                     />
                 </div>
            
                 <div className="d-flex gap-1">
                        <Button onClick={request}>Create</Button>
                 </div>

                </Container>

        </Container>
    )

}