import {useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import type {BookCard} from "../interfaces/BookCard.ts";

const AllBooks = () => {
    const [books, setBooks] = useState<null | Array<BookCard>>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/books")
                if (!response.ok) {
                    console.log(response.status)
                    return
                }
                const data: Array<BookCard> = await response.json();
                setBooks(data);
            } catch (error) {
                console.log(error);
            }
        }
        fetchData();
    }, [])

    return (
        <div className={"container mt-2"}>
            <h1 className={"text-center"}>All Books available for free</h1>
            <table className={"table table-bordered"}>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Link</th>
                </tr>
                </thead>
                <tbody>
                {books?.map(book =>
                    <tr>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td><Link className={"link-info"} to={`/book/${book.id}`}>Go to the book</Link></td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    )
}

export default AllBooks;