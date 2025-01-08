import axios from "axios"
import { useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../GlobalData/store"





function Main() {

    const navigate = useNavigate()

  return (

    
    <>
      
      
                                
      
            
            <Button onClick={()=>navigate("/login")}>Logout</Button>
             {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allreimbursementm")}>All</Button> : <Button onClick={()=>navigate("/allreimbursement")}>All</Button>}
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/pendingreimbursementm")}>Pending</Button> : <Button onClick={()=>navigate("/pendingreimbursement")}>Pending</Button>}
            <Button onClick={()=>navigate("/createreimbursement")}>Create</Button>
            {store.loggedInUser.role === "manager" ? <Button onClick={()=>navigate("/allusers")}>Users</Button> : ""}
            
      
     
    </>
  )
}

export default Main