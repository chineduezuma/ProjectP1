import axios from "axios"
import { SyntheticEvent, useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

interface User {
    username:string,
    password:string,
    firstName:string,
    lastName:string
}

export const Register:React.FC = () => {

    //Define a state object to store the new User info
    const[newUser, setNewUser] = useState<User>({
        username:"",
        password:"",
        firstName:"",
        lastName:""
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
        setNewUser((newUser) => ({...newUser, [name]:value}))

        console.log(newUser)

    }

    //Register function that sends the username/password to the backend in a POST request
    const register = async () => {

        //TODO: check that the username/password are present and valid

        //POST request - saving the response, but we won't need to use it here
        const response = await axios.post("http://localhost:4444/users", newUser, {withCredentials:true})
        .then(()=>{
            alert("User " + newUser.username + " created!")
            navigate("/")
        })
        .catch(()=>{alert("Registration failed! Make sure all fields are correct")})

    }

    return(
        <Container className="d-flex align-items-center flex-column mt-5">
            <h3>Register</h3>

            {/* Making a separate div for each input box */}
            <div>
                <Form.Control
                    type="text"
                    placeholder="firstname"
                    name="firstName"
                    onChange={storeValues}
                />
            </div>
            <div>
                <Form.Control
                    type="text"
                    placeholder="lastname"
                    name="lastName"
                    onChange={storeValues}
                />
            </div>
            <div>
                <Form.Control
                    type="text"
                    placeholder="username"
                    name="username"
                    onChange={storeValues}
                />
            </div>
            <div>
                <Form.Control
                    type="password"
                    placeholder="password"
                    name="password"
                    onChange={storeValues}
                />
            </div>
            <div className="d-flex gap-1">
                <Button onClick={() => navigate("/")}>Back</Button>
                <Button onClick={register}>Register</Button>
            </div>

        </Container>
    )

}