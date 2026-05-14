import './App.css'
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import BookDetail from "./pages/BookDetail.tsx";
import BookList from "./pages/BookList.tsx";

function App() {

  return (
    <BrowserRouter>
        <nav>
            <Link to="/">Home</Link> |{" "}
            <Link to="/books">All books</Link> |{" "}
        </nav>

        <Routes>
            <Route path="/" element={<h1>Home page </h1>} />
            <Route path="/books" element={<BookList/>}/>
            <Route path="/book/:id" element={<BookDetail/>} />
        </Routes>
    </BrowserRouter>
  )
}

export default App
