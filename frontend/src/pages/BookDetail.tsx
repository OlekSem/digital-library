import {useParams} from "react-router-dom";
import {useState} from "react";
import {ReactReader} from "react-reader";

const BookDetail = () => {
    const {id} = useParams();
    const [location, setLocation] = useState<string | number>(0)
    return(
        <div style={{ height: "100vh", width: "100%" }}>
            <ReactReader url={`http://localhost:8080/book/${id}/download.epub`}
                         location={location}
                         locationChanged={(epubcfi: string) => setLocation(epubcfi)}
            />
        </div>
    )
}
export default BookDetail;