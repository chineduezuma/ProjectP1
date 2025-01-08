import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import 'bootstrap/dist/css/bootstrap.css' //NEED THIS IMPORT FOR BOOTSTRAP TO WORK
import { Login } from './Components/LoginRegister/Login'
import { Register } from './Components/LoginRegister/Register'
import { CreateReimbursement } from './Components/Reimbursement/CreateReimbursement'
import { AllReimbursement } from './Components/Reimbursement/AllReimbursement'
import Main from './Components/LoginRegister/Main'
import { PendingReimbursement } from './Components/Reimbursement/PendingReimbursement'
import { AllUser } from './Components/Users/AllUser'
import { PendingReimbursementm } from './Components/Reimbursement/PendingReimbursementm'
import { AllReimbursementm } from './Components/Reimbursement/AllReimbursementm'





function App() {

  return (

    
    <>
      <h1>EMPLOYEE REIMBURSEMENT SYSTEM</h1>
     
      <BrowserRouter>
        <Routes>
          {/* empty string or / for path to render a component at startup */}
          <Route path="" element={<Login/>}/>

          <Route path="register" element={<Register/>}/>

          <Route path="/main" element={<Main/>}/>
            
          <Route path="createreimbursement" element={<CreateReimbursement/>}/>

          <Route path="allreimbursement" element={<AllReimbursement/>}/>

          <Route path="allreimbursementm" element={<AllReimbursementm/>}/>

          <Route path="pendingreimbursement" element={<PendingReimbursement/>}/>

          <Route path="pendingreimbursementm" element={<PendingReimbursementm/>}/>

          <Route path="allusers" element={<AllUser/>}/>


        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App