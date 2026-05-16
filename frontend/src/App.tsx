import './App.css'
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import BookDetail from "./pages/BookDetail.tsx";
import BookList from "./pages/BookList.tsx";

function App() {

  return (
    <BrowserRouter>
        <nav className={"nav nav-fill bg-dark"}>
            <Link className={"nav-link text-light"} to="/">Home</Link>
            <Link className={"nav-link text-light"} to="/books">All books</Link>
        </nav>

        <Routes>
            <Route path="/" element={<h1 className={"text-center"}>Home page. There is nothing here</h1>} />
            <Route path="/books" element={<BookList/>}/>
            <Route path="/book/:id" element={<BookDetail/>} />
        </Routes>
    </BrowserRouter>
  )
}

export default App
