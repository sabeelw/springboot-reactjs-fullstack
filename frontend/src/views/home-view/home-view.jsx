import { CardUser } from "../../components/card-component/card-component"
import { useEffect, useState } from "react"
import { CircularProgress } from "@mui/material";
import Box from '@mui/material/Box';
import { SampleContainer } from "../../components/container-component/container-component";
import { Form } from "../../components/form-component/form-component";


export const HomeView = () => {
    let [users, setUsers] = useState([]);
    let [formData, setFormData] = useState({})
    let [fetching, setFetching] = useState(0)
    const onChangeHandler = ({ target }) => {
        setFormData({ ...formData, [target.name.toLowerCase()]: target.value })
    }

    const fetchData = async () => {
        setFetching(1)
        await fetch("/users").then(r => r.json()).then(
            r =>
                setUsers(r.reverse())
        )
        setTimeout(setFetching, 1000, 0)
    }
    const registerHandler = async (e) => {
        e.preventDefault();
        try {
            const a = await fetch("/createUser", {
                method: "POST",
                body: JSON.stringify(formData),
                headers: {
                    "Content-Type": "application/json",
                },
            })
            if (a.ok) {
                fetchData()
            } else {
                throw Error("Some error");
            }
        } catch (e) {
            console.log("some problem")
        }
    }

    useEffect(() => {
        fetchData()
    }, []
    )
    return (
        <SampleContainer>

            <Form onChangeHandler={onChangeHandler} onClick={registerHandler}></Form>
            <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: "center", gap: "1rem" }}>
                <h1>Users: </h1>
                {fetching ? <CircularProgress /> : ""}
                {
                    users.map((user, id) =>
                        <div style={{ padding: "5px" }} key={id}>
                            <CardUser user={user} />
                        </div>
                    )
                }

            </Box>


        </SampleContainer >
    )
}

