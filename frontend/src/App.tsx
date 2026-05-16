import './App.css'
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import BookDetail from "./pages/BookDetail.tsx";
import BookList from "./pages/BookList.tsx";

function App() {

  return (
    <BrowserRouter>
        <nav className={"navbar nav-fill navbar-dark bg-light"}>
            <Link className={"nav-link nav-item"} to="/">Home</Link>
            <Link className={"nav-link nav-item"} to="/books">All books</Link>
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
